package cs.vsu.ru.expertise_server.service;

import cs.vsu.ru.expertise_server.data.dto.category.CategoryChangeDto;
import cs.vsu.ru.expertise_server.data.dto.category.CategoryCreateDto;
import cs.vsu.ru.expertise_server.data.dto.category.CategoryDto;
import cs.vsu.ru.expertise_server.data.entity.CategoryEntity;
import cs.vsu.ru.expertise_server.data.mapper.CategoryMapper;
import cs.vsu.ru.expertise_server.data.repository.CategoryRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {

    private CategoryRepository categoryRepository;

    private CategoryMapper categoryMapper;

    @Transactional
    public void createCategory(CategoryCreateDto category) {
        categoryRepository.save(categoryMapper.toEntity(category));
    }

    @Transactional
    public void changeCategory(CategoryChangeDto category) {
        CategoryEntity categoryEntity = categoryRepository.findCategoryEntityById(category.getId());
        categoryRepository.save(categoryMapper.toEntity(category, categoryEntity));
    }

    @Transactional
    public void deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
    }

    @Transactional
    public List<CategoryDto> getAllCategories() {
        Sort sort = Sort.by(Sort.Direction.ASC, "number");
        List<CategoryEntity> categoryEntities = categoryRepository.findAll(sort);
        return categoryEntities.stream().map(categoryMapper::toDto).toList();
    }
}
