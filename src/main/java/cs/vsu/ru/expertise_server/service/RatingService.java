package cs.vsu.ru.expertise_server.service;

import cs.vsu.ru.expertise_server.data.dto.project.ProjectDto;
import cs.vsu.ru.expertise_server.data.entity.ProjectEntity;
import cs.vsu.ru.expertise_server.data.mapper.ProjectMapper;
import cs.vsu.ru.expertise_server.data.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RatingService {

    private ProjectRepository projectRepository;

    private ProjectMapper projectMapper;

    @Transactional
    public List<ProjectDto> getRating(Integer year) {
        List<ProjectEntity> projects = projectRepository.findProjectEntitiesByYearOrderByScoreDesc(year);
        return projects.stream().map(projectMapper::toDto).toList();
    }
}
