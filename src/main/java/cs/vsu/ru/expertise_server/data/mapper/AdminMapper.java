package cs.vsu.ru.expertise_server.data.mapper;

import cs.vsu.ru.expertise_server.data.dto.admin.AdminCreateDto;
import cs.vsu.ru.expertise_server.data.dto.admin.AdminDto;
import cs.vsu.ru.expertise_server.data.entity.AdminEntity;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminEntity toEntity(AdminCreateDto admin) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setLogin(admin.getLogin());
        adminEntity.setPassword(admin.getPassword());

        return adminEntity;
    }

    public AdminDto toDto(AdminEntity adminEntity) {
        int id = adminEntity.getId();
        String login = adminEntity.getLogin();
        String password = adminEntity.getPassword();

        return new AdminDto(id, login, password);
    }
}
