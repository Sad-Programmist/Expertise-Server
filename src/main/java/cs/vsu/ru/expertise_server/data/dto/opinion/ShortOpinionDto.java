package cs.vsu.ru.expertise_server.data.dto.opinion;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ShortOpinionDto {
    private final String projectName;
    private final String author;
    private final Integer finalScore;
    private final Boolean conclusion;
}
