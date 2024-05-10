package cs.vsu.ru.expertise_server.service;

import cs.vsu.ru.expertise_server.data.dto.expert.ExpertAuthDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertChangeDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertCreateDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertDto;
import cs.vsu.ru.expertise_server.data.entity.ExpertEntity;
import cs.vsu.ru.expertise_server.data.mapper.ExpertMapper;
import cs.vsu.ru.expertise_server.data.repository.ExpertRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ExpertService {

    private ExpertRepository expertRepository;

    private ExpertMapper expertMapper;

    @Transactional
    public ExpertDto expertAuth(ExpertAuthDto expert) {
        ExpertEntity expertEntity = expertRepository.findExpertEntityByLogin(expert.getLogin());
        if (expertEntity.getPassword().equals(DigestUtils.md5Hex(expert.getPassword())))
            return expertMapper.toDto(expertEntity);
        else
            return null;
    }

    @Transactional
    public Boolean identification(String login) {
        ExpertEntity expert = expertRepository.findExpertEntityByLogin(login);
        return expert == null;
    }

    @Transactional
    public void createExpert(ExpertCreateDto expert) {
        expertRepository.save(expertMapper.toEntity(expert));
    }

    @Transactional
    public void changeExpert(ExpertChangeDto expert) {
        ExpertEntity expertEntity = expertRepository.findExpertEntityById(expert.getId());
        expertRepository.save(expertMapper.toEntity(expert, expertEntity));
    }

    @Transactional
    public ExpertDto getExpert(Integer expertId) {
        ExpertEntity expert = expertRepository.findExpertEntityById(expertId);
        return expertMapper.toDto(expert);
    }

    @Transactional
    public void deleteExpert(Integer expertId) {
        ExpertEntity expert = expertRepository.findExpertEntityById(expertId);
        expert.setLogin(null);
        expert.setPassword(null);
    }

    @Transactional
    public List<ExpertDto> getAllExperts() {
        List<ExpertEntity> expertEntities = expertRepository.findByLoginIsNotNull();
        return expertEntities.stream().map(expertMapper::toDto).toList();
    }


}
