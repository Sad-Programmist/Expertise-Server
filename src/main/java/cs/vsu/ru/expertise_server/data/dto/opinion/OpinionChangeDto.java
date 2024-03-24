package cs.vsu.ru.expertise_server.data.dto.opinion;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@AllArgsConstructor
@Getter
public class OpinionChangeDto {
    private final Integer projectId;
    private final Integer expertId;
    private final List<Integer> scores;
    private final String text;
    private final Boolean conclusion;
}
