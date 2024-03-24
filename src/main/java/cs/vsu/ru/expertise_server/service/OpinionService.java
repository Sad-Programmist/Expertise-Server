package cs.vsu.ru.expertise_server.service;

import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionChangeDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionCreateDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectDto;
import cs.vsu.ru.expertise_server.data.entity.ExpertEntity;
import cs.vsu.ru.expertise_server.data.entity.OpinionEntity;
import cs.vsu.ru.expertise_server.data.entity.ProjectEntity;
import cs.vsu.ru.expertise_server.data.mapper.OpinionMapper;
import cs.vsu.ru.expertise_server.data.mapper.ProjectMapper;
import cs.vsu.ru.expertise_server.data.repository.ExpertRepository;
import cs.vsu.ru.expertise_server.data.repository.OpinionRepository;
import cs.vsu.ru.expertise_server.data.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class OpinionService {

    private OpinionRepository opinionRepository;
    private ProjectRepository projectRepository;
    private ExpertRepository expertRepository;

    private ProjectMapper projectMapper;
    private OpinionMapper opinionMapper;

    @Transactional
    public Boolean createOpinion(OpinionCreateDto opinion) {
        ExpertEntity expert = expertRepository.findExpertEntityById(opinion.getExpertId());
        ProjectEntity project = projectRepository.findProjectEntityById(opinion.getProjectId());

        if (testProject(project)) {
            calculateScore(opinion.getProjectId());
        }

        try {
            opinionRepository.save(opinionMapper.toEntity(opinion, project, expert));
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Transactional
    public List<ProjectDto> getProjectsForCreateOpinion(Integer expertId, Integer year) {
        List<ProjectEntity> projectEntities = projectRepository.findProjectEntitiesByYear(year);
        List<ProjectDto> result = new ArrayList<>();
        for (ProjectEntity project : projectEntities) {
            OpinionEntity opinion = opinionRepository.findOpinionEntityByProjectIdAndExpertId(project.getId(), expertId);
            if (opinion==null) {
                result.add(projectMapper.toDto(project));
            }
        }
        return result;
    }

    @Transactional
    public OpinionDto getOpinion(Integer expertId, Integer projectId) {
        OpinionEntity opinion = opinionRepository.findOpinionEntityByProjectIdAndExpertId(projectId, expertId);
        ProjectEntity project = projectRepository.findProjectEntityById(opinion.getProject().getId());
        ExpertEntity expert = expertRepository.findExpertEntityById(opinion.getExpert().getId());
        return opinionMapper.toDto(opinion, project, expert);
    }

    @Transactional
    public Boolean changeOpinion(OpinionChangeDto opinion) {
        OpinionEntity opinionEntity = opinionRepository.findOpinionEntityByProjectIdAndExpertId(opinion.getProjectId(), opinion.getExpertId());

        if (testProject(projectRepository.findProjectEntityById(opinionEntity.getProject().getId()))) {
            calculateScore(opinion.getProjectId());
        }

        try {
            opinionRepository.save(opinionMapper.toEntity(opinion, opinionEntity));
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Transactional
    public List<ProjectDto> getProjectsForChangeOpinion(Integer expertId, Integer year) {
        List<ProjectEntity> projectEntities = projectRepository.findProjectEntitiesByYear(year);
        List<ProjectDto> result = new ArrayList<>();
        for (ProjectEntity project : projectEntities) {
            OpinionEntity opinion = opinionRepository.findOpinionEntityByProjectIdAndExpertId(project.getId(), expertId);
            if (opinion!=null) {
                result.add(projectMapper.toDto(project));
            }
        }
        return result;
    }

    @Transactional
    public void calculateScore(Integer projectId) {
        List<OpinionEntity> opinions = opinionRepository.findOpinionEntitiesByProjectId(projectId);

        int support = 0;
        for (OpinionEntity opinion : opinions) {
            if (opinion.getConclusion())
                support += 1;
        }

        if (support < opinions.size() / 2) {
            ProjectEntity project = projectRepository.findProjectEntityById(projectId);
            project.setScore(0);
            projectRepository.save(project);
            return;
        }

        int overallScore = 0;
        for (OpinionEntity opinion : opinions) {
            List<Integer> scores = Arrays.stream(opinion.getScores().split(";"))
                    .map(Integer::parseInt)
                    .toList();
            overallScore += scores.stream().mapToInt(Integer::intValue).sum();
        }
        ProjectEntity project = projectRepository.findProjectEntityById(projectId);
        project.setScore(overallScore);
        projectRepository.save(project);
    }

    @Transactional
    public Boolean testProject(ProjectEntity project) {
        int numberOfOpinionsByProject = opinionRepository.countOpinionEntityByProjectId(project.getId());
        int numberOfExperts = (int) expertRepository.count();
        return numberOfOpinionsByProject == numberOfExperts;
    }
}
