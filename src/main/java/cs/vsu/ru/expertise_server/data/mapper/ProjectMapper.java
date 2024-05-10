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
        projectEntity.setAuthor(project.getAuthor());
        projectEntity.setTheme(project.getTheme());
        projectEntity.setYear(project.getYear());
        projectEntity.setOrderNumber(project.getOrderNumber());
        projectEntity.setScore(0);

        return projectEntity;
    }

    public ProjectEntity toEntity(ProjectChangeDto project, ProjectEntity projectEntity) {
        projectEntity.setAuthor(project.getAuthor());
        projectEntity.setTheme(project.getTheme());
        projectEntity.setYear(project.getYear());
        projectEntity.setOrderNumber(project.getOrderNumber());

        return projectEntity;
    }

    public ProjectDto toDto(ProjectEntity project) {
        int id = project.getId();
        String participants = project.getAuthor();
        String theme = project.getTheme();
        int year = project.getYear();
        int orderNumber = project.getOrderNumber();
        int score = project.getScore();

        return new ProjectDto(id, participants, theme, year, orderNumber, score);
    }
}
