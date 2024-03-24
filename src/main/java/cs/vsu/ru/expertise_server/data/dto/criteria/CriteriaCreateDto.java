package cs.vsu.ru.expertise_server.data.dto.criteria;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CriteriaCreateDto {
    private final int number;
    private final String text;
    private final int categoryId;
}
