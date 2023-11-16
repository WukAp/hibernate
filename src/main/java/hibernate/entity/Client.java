package hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {

    @Id
    @Column(name = "passport", nullable = false)
    private Long passport;


    @Column(name = "name", nullable = false, length = -1)
    private String name;


    @Column(name = "second_name", nullable = true, length = -1)
    private String secondName;


    @Column(name = "patronymic", nullable = true, length = -1)
    private String patronymic;


    @Column(name = "city", nullable = true, length = -1)
    private String city;
}
