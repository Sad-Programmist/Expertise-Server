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
@Table(name = "criteria")
public class CriteriaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "number")
    private int number;
    @Column(name = "text", length = 512)
    private String text;
    @ManyToOne
    @JoinColumn(name="category", nullable=false)
    private CategoryEntity category;
}
