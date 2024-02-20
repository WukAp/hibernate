package hibernate.entity;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "room_reserve", schema = "public", catalog = "hotel_db")
public class RoomReserve implements Serializable {

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
