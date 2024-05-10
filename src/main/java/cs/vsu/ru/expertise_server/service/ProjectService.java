package cs.vsu.ru.expertise_server.service;

import cs.vsu.ru.expertise_server.data.dto.project.ProjectChangeDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectCreateDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectDto;
import cs.vsu.ru.expertise_server.data.entity.ProjectEntity;
import cs.vsu.ru.expertise_server.data.mapper.ProjectMapper;
import cs.vsu.ru.expertise_server.data.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class ProjectService {

    private ProjectRepository projectRepository;

    private ProjectMapper projectMapper;

    @Transactional
    public void createProject(ProjectCreateDto project) {
        projectRepository.save(projectMapper.toEntity(project));
    }

    @Transactional
    public ProjectDto getProject(Integer projectId) {
        ProjectEntity project = projectRepository.findProjectEntityById(projectId);
        return projectMapper.toDto(project);
    }

    @Transactional
    public void changeProject(ProjectChangeDto project) {
        ProjectEntity projectEntity = projectRepository.findProjectEntityById(project.getId());
        projectRepository.save(projectMapper.toEntity(project, projectEntity));
    }

    @Transactional
    public void deleteProject(Integer projectId) {
        projectRepository.deleteById(projectId);
    }

    @Transactional
    public List<ProjectDto> getAllProjectsByYear(Integer year) {
        List<ProjectEntity> projectEntities = projectRepository.findProjectEntitiesByYearOrderByOrderNumberAsc(year);
        return projectEntities.stream().map(projectMapper::toDto).toList();
    }

    @Transactional
    public List<Integer> getAllYears() {
        List<Integer> years = projectRepository.findAllUniqueYears();
        years.sort(Collections.reverseOrder());
        return years;
    }
}
