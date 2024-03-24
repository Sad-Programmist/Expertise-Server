package cs.vsu.ru.expertise_server.data.dto.expert;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExpertCreateDto {
    private final String name;
    private final String login;
    private final String password;
}
