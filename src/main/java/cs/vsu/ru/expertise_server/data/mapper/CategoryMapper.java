package cs.vsu.ru.expertise_server.data.mapper;

import cs.vsu.ru.expertise_server.data.dto.category.CategoryChangeDto;
import cs.vsu.ru.expertise_server.data.dto.category.CategoryCreateDto;
import cs.vsu.ru.expertise_server.data.dto.category.CategoryDto;
import cs.vsu.ru.expertise_server.data.entity.CategoryEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryEntity toEntity(CategoryCreateDto category) {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setNumber(category.getNumber());
        categoryEntity.setText(category.getText());
        categoryEntity.setMaxsum(category.getMaxsum());

        return categoryEntity;
    }

    public CategoryEntity toEntity(CategoryChangeDto category, CategoryEntity categoryEntity) {
        categoryEntity.setNumber(category.getNumber());
        categoryEntity.setText(category.getText());
        categoryEntity.setMaxsum(category.getMaxsum());

        return categoryEntity;
    }

    public CategoryDto toDto(CategoryEntity category) {
        int id = category.getId();
        int number = category.getNumber();
        String text = category.getText();
        int maxsum = category.getMaxsum();

        return new CategoryDto(id, number, text, maxsum);
    }
}
