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
public class Order implements Serializable{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -8523282372885298582L;
    String flag;
    int num;
}
