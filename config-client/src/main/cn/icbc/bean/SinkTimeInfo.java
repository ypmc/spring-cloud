package main.cn.icbc.bean;

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
public class SinkTimeInfo {

    private String time;
    private String label;
}
