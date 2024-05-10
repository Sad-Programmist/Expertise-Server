package cs.vsu.ru.expertise_server.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "expert")
public class ExpertEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", length = 128)
    private String name;
    @Column(name = "login", length = 128)
    private String login;
    @Column(name = "password", length = 128)
    private String password;
    @OneToMany(mappedBy="expert", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    private Set<OpinionEntity> opinions;
}
