package cs.vsu.ru.expertise_server.data.dto.expert;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ExpertDto {
    private final Integer id;
    private final String name;
    private final String login;
    private final String password;
}
