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
public class Client implements Serializable {

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
