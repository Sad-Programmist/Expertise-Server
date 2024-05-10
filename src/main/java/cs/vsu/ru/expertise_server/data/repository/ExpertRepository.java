package cs.vsu.ru.expertise_server.data.repository;

import cs.vsu.ru.expertise_server.data.entity.ExpertEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpertRepository extends JpaRepository<ExpertEntity, Integer> {
    ExpertEntity findExpertEntityById(Integer id);
    ExpertEntity findExpertEntityByLoginAndPassword(String login, String password);
    ExpertEntity findExpertEntityByLogin(String login);
    List<ExpertEntity> findByLoginIsNotNull();
    long count();
}
