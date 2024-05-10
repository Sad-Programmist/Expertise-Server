package cs.vsu.ru.expertise_server.service;

import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionChangeDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionCreateDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.ShortOpinionDto;
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
    public void createOpinion(OpinionCreateDto opinion) {
        ExpertEntity expert = expertRepository.findExpertEntityById(opinion.getExpertId());
        ProjectEntity project = projectRepository.findProjectEntityById(opinion.getProjectId());
        opinionRepository.save(opinionMapper.toEntity(opinion, project, expert));
        calculateScore(project);
    }

    @Transactional
    public List<ProjectDto> getProjectsForCreateOpinion(Integer expertId, Integer year) {
        List<ProjectEntity> projectEntities = projectRepository.findProjectEntitiesByYearOrderByOrderNumberAsc(year);
        List<ProjectDto> result = new ArrayList<>();
        for (ProjectEntity project : projectEntities) {
            OpinionEntity opinion = opinionRepository.findOpinionEntityByProjectIdAndExpertId(project.getId(), expertId);
            if (opinion == null) {
                result.add(projectMapper.toDto(project));
            }
        }
        return result;
    }

    @Transactional
    public OpinionDto getOpinion(Integer expertId, Integer projectId) {
        OpinionEntity opinion = opinionRepository.findOpinionEntityByProjectIdAndExpertId(projectId, expertId);
        return opinionMapper.toDto(opinion);
    }

    @Transactional
    public List<ShortOpinionDto> getShortOpinion(Integer expertId, Integer year) {
        List<OpinionEntity> opinions = opinionRepository.findOpinionEntitiesByExpertIdAndProjectYear(expertId, year);
        return opinions.stream().map(opinionEntity -> opinionMapper.toShortDto(opinionEntity)).toList();
    }

    @Transactional
    public void changeOpinion(OpinionChangeDto opinion) {
        OpinionEntity opinionEntity = opinionRepository.findOpinionEntityByProjectIdAndExpertId(opinion.getProjectId(), opinion.getExpertId());
        ProjectEntity project = projectRepository.findProjectEntityById(opinion.getProjectId());
        opinionRepository.save(opinionMapper.toEntity(opinion, opinionEntity));
        calculateScore(project);
    }

    @Transactional
    public List<ProjectDto> getProjectsForChangeOpinion(Integer expertId, Integer year) {
        List<ProjectEntity> projectEntities = projectRepository.findProjectEntitiesByYearOrderByOrderNumberAsc(year);
        List<ProjectDto> result = new ArrayList<>();
        for (ProjectEntity project : projectEntities) {
            OpinionEntity opinion = opinionRepository.findOpinionEntityByProjectIdAndExpertId(project.getId(), expertId);
            if (opinion != null) {
                result.add(projectMapper.toDto(project));
            }
        }
        return result;
    }

    @Transactional
    public void calculateScore(ProjectEntity project) {
        List<OpinionEntity> opinions = opinionRepository.findOpinionEntitiesByProjectId(project.getId());
        int overallScore = 0;
        for (OpinionEntity opinion : opinions) {
            overallScore += opinion.getFinalScore();
        }
        project.setScore(overallScore);
        projectRepository.save(project);
    }
}
