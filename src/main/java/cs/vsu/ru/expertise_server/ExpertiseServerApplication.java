package cs.vsu.ru.expertise_server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class ExpertiseServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ExpertiseServerApplication.class, args);
    }
}
