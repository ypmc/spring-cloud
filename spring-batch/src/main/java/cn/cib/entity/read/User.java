package cn.cib.entity.read;


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
@Table
public class User {
    @Id
    @Column
    private String id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "update_time")
    private String updateTime;

}
