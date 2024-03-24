package cs.vsu.ru.expertise_server.data.repository;

import cs.vsu.ru.expertise_server.data.entity.CriteriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CriteriaRepository extends JpaRepository<CriteriaEntity, Integer> {
}
