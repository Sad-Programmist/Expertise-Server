package cs.vsu.ru.expertise_server.data.mapper;

import cs.vsu.ru.expertise_server.data.dto.expert.ExpertChangeDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertCreateDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertDto;
import cs.vsu.ru.expertise_server.data.entity.ExpertEntity;
import org.springframework.stereotype.Component;

@Component
public class ExpertMapper {

    public ExpertEntity toEntity(ExpertCreateDto expert) {
        ExpertEntity expertEntity = new ExpertEntity();
        expertEntity.setName(expert.getName());
        expertEntity.setLogin(expert.getLogin());
        expertEntity.setPassword(expert.getPassword());

        return expertEntity;
    }

    public ExpertEntity toEntity(ExpertChangeDto expert, ExpertEntity expertEntity) {
        expertEntity.setName(expert.getName());
        expertEntity.setLogin(expert.getLogin());
        expertEntity.setPassword(expert.getPassword());

        return expertEntity;
    }

    public ExpertDto toDto(ExpertEntity expertEntity) {
        int id = expertEntity.getId();
        String name = expertEntity.getName();
        String login = expertEntity.getLogin();
        String password = expertEntity.getPassword();

        return new ExpertDto(id, name, login, password);
    }
}
