package cs.vsu.ru.expertise_server.data.repository;

import cs.vsu.ru.expertise_server.data.entity.OpinionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OpinionRepository extends JpaRepository<OpinionEntity, Integer> {
    OpinionEntity findOpinionEntityById(Integer id);
    Integer countOpinionEntityByProjectId(Integer projectId);
    List<OpinionEntity> findOpinionEntitiesByProjectId(Integer projectId);
    OpinionEntity findOpinionEntityByProjectIdAndExpertId(Integer projectId, Integer expertId);

}
