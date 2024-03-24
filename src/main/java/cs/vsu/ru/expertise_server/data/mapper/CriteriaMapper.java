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

    public CriteriaDto toDto(CriteriaEntity criteriaEntity) {
        int id = criteriaEntity.getId();
        int number = criteriaEntity.getNumber();
        String text = criteriaEntity.getText();
        int categoryId = criteriaEntity.getCategory().getId();
        int categoryNumber = criteriaEntity.getCategory().getNumber();

        return new CriteriaDto(id, number, text, categoryId, categoryNumber);
    }
}
