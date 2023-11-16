package hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigInteger;
import java.util.Objects;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "room_type", schema = "public", catalog = "hotel_db")
public class RoomType {
    @Id
    @Column(name = "bed_amount", nullable = false)
    private Integer bedAmount;

    @Column(name = "price", nullable = false, precision = 0)
    private Double price;
    }
