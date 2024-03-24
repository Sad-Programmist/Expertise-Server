package cs.vsu.ru.expertise_server.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "opinion")
public class OpinionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "project", nullable = false)
    private ProjectEntity project;
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "expert", nullable = false)
    private ExpertEntity expert;
    @Column(name = "scores", length = 512)
    private String scores;
    @Column(name = "text", length = 512)
    private String text;
    @Column(name = "conclusion")
    private Boolean conclusion;
}
