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
    private String personId;
    @Column
    private String firstName;
    @Column
    private String lastName;
}
