package hibernate.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Entity
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cleaner {

    @Id
    @Column(name = "id_cleaner", nullable = false)
    private Integer idCleaner;


    @Column(name = "name", nullable = false, length = -1)
    private String name;


    @Column(name = "second_name", nullable = true, length = -1)
    private String secondName;

    @Column(name = "patronymic", nullable = true, length = -1)
    private String patronymic;

}
