package hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Date;
import java.util.Objects;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "room_reserve", schema = "public", catalog = "hotel_db")
public class RoomReserve {

    @Id
    @Column(name = "room_number", nullable = false)
    private Integer roomNumber;

    @Id
    @Column(name = "passport", nullable = false)
    private Long passport;


    @Id
    @Column(name = "check_in_date", nullable = false)
    private Date checkInDate;


    @Column(name = "check_out_date", nullable = true)
    private Date checkOutDate;

}
