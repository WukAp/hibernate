package hibernate.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "room_type", schema = "public", catalog = "hotel_db")
public class RoomType implements Serializable {
    @Id
    @Column(name = "bed_amount", nullable = false)
    private Integer bedAmount;

    @Column(name = "price", nullable = false, precision = 0)
    private Double price;
    }
