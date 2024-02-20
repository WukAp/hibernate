package hibernate.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Entity
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Cleaner implements Serializable {

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
