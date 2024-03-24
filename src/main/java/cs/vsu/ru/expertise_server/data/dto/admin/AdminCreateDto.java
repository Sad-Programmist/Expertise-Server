package cs.vsu.ru.expertise_server.data.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AdminCreateDto {
    private final String login;
    private final String password;
}
