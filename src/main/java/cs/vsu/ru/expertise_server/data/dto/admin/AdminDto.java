package cs.vsu.ru.expertise_server.data.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AdminDto {
    private final Integer id;
    private final String login;
}
