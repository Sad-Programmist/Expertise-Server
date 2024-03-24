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
@Table(name = "category")
public class CategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "number")
    private int number;
    @Column(name = "text", length = 512)
    private String text;
    @Column(name = "minsum")
    private int minsum;
    @Column(name = "maxsum")
    private int maxsum;
    @OneToMany(mappedBy="category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<CriteriaEntity> criterias;
}
