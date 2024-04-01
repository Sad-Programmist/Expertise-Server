package cs.vsu.ru.expertise_server.data.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProjectDto {
    private final int id;
    private final String participants;
    private final String theme;
    private final int year;
    private final int orderNumber;
    private final double score;
}
