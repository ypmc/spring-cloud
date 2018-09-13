package main.cn.icbc.bean;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(doNotUseGetters = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Item implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -4616377979418967171L;
    String id;
    long timestamp;
}
