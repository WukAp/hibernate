package hibernate.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

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


    @ManyToOne
    @JoinColumn(name = "id_cleaner", nullable = true)
    private Cleaner cleaner;

}
