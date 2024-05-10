package cs.vsu.ru.expertise_server.data.repository;

import cs.vsu.ru.expertise_server.data.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Integer> {

    AdminEntity findAdminEntityByLogin(String login);
    AdminEntity findAdminEntityByLoginAndPassword(String login, String password);
}
