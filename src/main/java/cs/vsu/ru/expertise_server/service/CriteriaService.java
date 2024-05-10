package cs.vsu.ru.expertise_server.service;

import cs.vsu.ru.expertise_server.data.dto.criteria.CriteriaCreateDto;
import cs.vsu.ru.expertise_server.data.dto.criteria.CriteriaDto;
import cs.vsu.ru.expertise_server.data.entity.CategoryEntity;
import cs.vsu.ru.expertise_server.data.entity.CriteriaEntity;
import cs.vsu.ru.expertise_server.data.mapper.CriteriaMapper;
import cs.vsu.ru.expertise_server.data.repository.CategoryRepository;
import cs.vsu.ru.expertise_server.data.repository.CriteriaRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CriteriaService {

    private CategoryRepository categoryRepository;
    private CriteriaRepository criteriaRepository;

    private CriteriaMapper criteriaMapper;

    @Transactional
    public void createCriteria(CriteriaCreateDto criteria) {
        CategoryEntity category = categoryRepository.findCategoryEntityById(criteria.getCategoryId());
        criteriaRepository.save(criteriaMapper.toEntity(criteria, category));
    }

    @Transactional
    public void deleteCriteria(Integer criteriaId) {
        criteriaRepository.deleteById(criteriaId);
    }

    @Transactional
    public List<CriteriaDto> getAllCriteria() {
        Sort sort = Sort.by(Sort.Direction.ASC, "categoryNumber", "number");
        List<CriteriaEntity> criteriaEntities = criteriaRepository.findAll(sort);
        return criteriaEntities.stream().map(criteriaMapper::toDto).toList();
    }
}
