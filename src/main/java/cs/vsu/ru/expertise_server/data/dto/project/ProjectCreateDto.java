package cs.vsu.ru.expertise_server.data.dto.project;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ProjectCreateDto {
    private final String author;
    private final String theme;
    private final int orderNumber;
    private final int year;
}
