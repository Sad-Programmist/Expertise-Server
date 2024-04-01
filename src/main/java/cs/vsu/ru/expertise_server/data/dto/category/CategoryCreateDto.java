package cs.vsu.ru.expertise_server.data.dto.category;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CategoryCreateDto {
    private final int number;
    private final String text;
    private final int maxsum;
}
