package cs.vsu.ru.expertise_server.data.repository;

import cs.vsu.ru.expertise_server.data.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Integer> {

    CategoryEntity findCategoryEntityById(Integer id);
}
