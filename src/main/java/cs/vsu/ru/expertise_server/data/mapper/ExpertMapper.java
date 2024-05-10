package cs.vsu.ru.expertise_server.data.mapper;

import cs.vsu.ru.expertise_server.data.dto.expert.ExpertChangeDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertCreateDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertDto;
import cs.vsu.ru.expertise_server.data.entity.ExpertEntity;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;

@Component
public class ExpertMapper {

    public ExpertEntity toEntity(ExpertCreateDto expert) {
        ExpertEntity expertEntity = new ExpertEntity();
        expertEntity.setName(expert.getName());
        expertEntity.setLogin(expert.getLogin());
        expertEntity.setPassword(DigestUtils.md5Hex(expert.getPassword()));

        return expertEntity;
    }

    public ExpertEntity toEntity(ExpertChangeDto expert, ExpertEntity expertEntity) {
        expertEntity.setName(expert.getName());
        expertEntity.setLogin(expert.getLogin());
        expertEntity.setPassword(DigestUtils.md5Hex(expert.getPassword()));

        return expertEntity;
    }

    public ExpertDto toDto(ExpertEntity expert) {
        int id = expert.getId();
        String name = expert.getName();
        String login = expert.getLogin();

        return new ExpertDto(id, name, login);
    }
}
