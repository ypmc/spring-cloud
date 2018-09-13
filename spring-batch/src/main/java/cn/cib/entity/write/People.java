package cn.cib.entity.write;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@ToString(doNotUseGetters = true)
@AllArgsConstructor
@Builder
@Entity
@Table(name = "people")
public class People {
    @Id
    @Column(name = "person_id")
    private String personId;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
}
