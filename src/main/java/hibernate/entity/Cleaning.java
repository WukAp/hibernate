package hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Cleaning {

    @Id
    @Column(name = "week_day", nullable = false, length = -1)
    private String weekDay;


    @Id
    @Column(name = "floor", nullable = false)
    private Integer floor;


    @Basic
    @Column(name = "id_cleaner", nullable = false)
    private Integer idCleaner;

}
