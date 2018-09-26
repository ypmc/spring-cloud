package cn.cib.model;

import lombok.*;

import java.io.Serializable;

/**
 * @author clyde lou
 */
@Getter
@Setter
@ToString(doNotUseGetters = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    private int id;
    private String userName;
    private String createTime;
}
