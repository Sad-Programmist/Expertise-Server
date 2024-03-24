package cs.vsu.ru.expertise_server.data.repository;

import cs.vsu.ru.expertise_server.data.entity.ProjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProjectRepository extends JpaRepository<ProjectEntity, Integer> {
    ProjectEntity findProjectEntityById(Integer id);
    List<ProjectEntity> findProjectEntitiesByYear(Integer year);
    List<ProjectEntity> findProjectEntitiesByYearOrderByScoreDesc(int year);
    @Query("SELECT DISTINCT p.year FROM ProjectEntity p")
    List<Integer> findAllUniqueYears();
}
