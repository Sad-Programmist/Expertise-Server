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
@Table(name = "project")
public class ProjectEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "participants", length = 512)
    private String participants;
    @Column(name = "theme", length = 256)
    private String theme;
    @Column(name = "year")
    private int year;
    @Column(name = "score")
    private double score;
    @OneToMany(mappedBy="project", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<OpinionEntity> opinions;
}
