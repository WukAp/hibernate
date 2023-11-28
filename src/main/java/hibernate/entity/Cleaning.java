package hibernate.entity;


import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Cleaning implements Serializable {

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
