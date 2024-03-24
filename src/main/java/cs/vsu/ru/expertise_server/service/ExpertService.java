package cs.vsu.ru.expertise_server.service;

import cs.vsu.ru.expertise_server.data.dto.expert.ExpertAuthDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertChangeDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertCreateDto;
import cs.vsu.ru.expertise_server.data.dto.expert.ExpertDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionChangeDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionCreateDto;
import cs.vsu.ru.expertise_server.data.dto.opinion.OpinionDto;
import cs.vsu.ru.expertise_server.data.dto.project.ProjectDto;
import cs.vsu.ru.expertise_server.data.entity.ExpertEntity;
import cs.vsu.ru.expertise_server.data.entity.OpinionEntity;
import cs.vsu.ru.expertise_server.data.entity.ProjectEntity;
import cs.vsu.ru.expertise_server.data.mapper.ExpertMapper;
import cs.vsu.ru.expertise_server.data.mapper.OpinionMapper;
import cs.vsu.ru.expertise_server.data.mapper.ProjectMapper;
import cs.vsu.ru.expertise_server.data.repository.ExpertRepository;
import cs.vsu.ru.expertise_server.data.repository.OpinionRepository;
import cs.vsu.ru.expertise_server.data.repository.ProjectRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class ExpertService {

    private ExpertRepository expertRepository;

    private ExpertMapper expertMapper;

    @Transactional
    public ExpertDto expertAuth(ExpertAuthDto expert) {
        ExpertEntity expertEntity = expertRepository.findExpertEntityByLoginAndPassword(expert.getLogin(), expert.getPassword());
        return expertMapper.toDto(expertEntity);
    }

    @Transactional
    public Boolean createExpert(ExpertCreateDto expert) {
        try {
            expertRepository.save(expertMapper.toEntity(expert));
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Transactional
    public Boolean changeExpert(ExpertChangeDto expert) {
        ExpertEntity expertEntity = expertRepository.findExpertEntityById(expert.getId());
        try {
            expertRepository.save(expertMapper.toEntity(expert, expertEntity));
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Transactional
    public ExpertDto getExpert(Integer expertId) {
        ExpertEntity expert = expertRepository.findExpertEntityById(expertId);
        return expertMapper.toDto(expert);
    }

    @Transactional
    public Boolean deleteExpert(Integer expertId) {
        try {
            expertRepository.deleteById(expertId);
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    @Transactional
    public List<ExpertDto> getAllExperts() {
        List<ExpertEntity> expertEntities = expertRepository.findAll();
        return expertEntities.stream().map(expertMapper::toDto).toList();
    }


}
