package hibernate.entity;


import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Room implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "room_number", nullable = false)
    private Integer roomNumber;


    @Column(name = "bed_amount", nullable = false)
    private Integer bedAmount;


    @Column(name = "phone", nullable = true)
    private Integer phone;


    @Column(name = "floor", nullable = false)
    private Integer floor;

}
