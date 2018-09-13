package main.cn.icbc.bean;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString(doNotUseGetters = true)
@Builder
public class TimeInfo {

    private String time;
    private String label;
}