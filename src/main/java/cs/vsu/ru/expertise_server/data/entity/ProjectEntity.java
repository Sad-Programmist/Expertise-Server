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
    @Column(name = "author", length = 512)
    private String author;
    @Column(name = "theme", length = 256)
    private String theme;
    @Column(name = "year")
    private int year;
    @Column(name = "number")
    private int orderNumber;
    @Column(name = "score")
    private int score;
    @OneToMany(mappedBy="project", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private Set<OpinionEntity> opinions;
}
