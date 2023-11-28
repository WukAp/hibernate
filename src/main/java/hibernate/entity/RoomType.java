package hibernate.entity;


import javax.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Objects;

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
