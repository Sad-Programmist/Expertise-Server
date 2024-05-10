package cs.vsu.ru.expertise_server.data.mapper;

import cs.vsu.ru.expertise_server.data.dto.criteria.CriteriaCreateDto;
import cs.vsu.ru.expertise_server.data.dto.criteria.CriteriaDto;
import cs.vsu.ru.expertise_server.data.entity.CategoryEntity;
import cs.vsu.ru.expertise_server.data.entity.CriteriaEntity;
import org.springframework.stereotype.Component;

@Component
public class CriteriaMapper {

    public CriteriaEntity toEntity(CriteriaCreateDto criteria, CategoryEntity category) {
        CriteriaEntity criteriaEntity = new CriteriaEntity();
        criteriaEntity.setNumber(criteria.getNumber());
        criteriaEntity.setText(criteria.getText());
        criteriaEntity.setCategory(category);

        return criteriaEntity;
    }

    public CriteriaDto toDto(CriteriaEntity criteria) {
        int id = criteria.getId();
        int number = criteria.getNumber();
        String text = criteria.getText();
        int categoryId = criteria.getCategory().getId();
        int categoryNumber = criteria.getCategory().getNumber();

        return new CriteriaDto(id, number, text, categoryId, categoryNumber);
    }
}
