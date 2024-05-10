package cs.vsu.ru.expertise_server.data.mapper;

import cs.vsu.ru.expertise_server.data.dto.admin.AdminCreateDto;
import cs.vsu.ru.expertise_server.data.dto.admin.AdminDto;
import cs.vsu.ru.expertise_server.data.entity.AdminEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    public AdminEntity toEntity(AdminCreateDto admin) {
        AdminEntity adminEntity = new AdminEntity();
        adminEntity.setLogin(admin.getLogin());
        adminEntity.setPassword(DigestUtils.md5Hex(admin.getPassword()));

        return adminEntity;
    }

    public AdminDto toDto(AdminEntity admin) {
        int id = admin.getId();
        String login = admin.getLogin();

        return new AdminDto(id, login);
    }
}
