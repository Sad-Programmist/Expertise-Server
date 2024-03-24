package cs.vsu.ru.expertise_server.data.mapper;

import cs.vsu.ru.expertise_server.data.dto.project.ProjectChangeDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectCreateDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectDto;
import cs.vsu.ru.expertise_server.data.entity.ProjectEntity;
import org.springframework.stereotype.Component;

@Component
public class ProjectMapper {

    public ProjectEntity toEntity(ProjectCreateDto project) {
        ProjectEntity projectEntity = new ProjectEntity();
        projectEntity.setParticipants(project.getParticipants());
        projectEntity.setTheme(project.getTheme());
        projectEntity.setYear(project.getYear());
        projectEntity.setScore(0);

        return projectEntity;
    }

    public ProjectEntity toEntity(ProjectChangeDto project, ProjectEntity projectEntity) {
        projectEntity.setParticipants(project.getParticipants());
        projectEntity.setTheme(project.getTheme());
        projectEntity.setYear(project.getYear());

        return projectEntity;
    }

    public ProjectDto toDto(ProjectEntity projectEntity) {
        int id = projectEntity.getId();
        String participants = projectEntity.getParticipants();
        String theme = projectEntity.getTheme();
        int year = projectEntity.getYear();
        double score = projectEntity.getScore();

        return new ProjectDto(id, participants, theme, year, score);
    }
}
