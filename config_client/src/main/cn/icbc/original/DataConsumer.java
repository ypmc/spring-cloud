package main.cn.icbc.original;

import main.cn.icbc.bean.SinkTimeInfo;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;

@EnableBinding(Sink.class)
public class DataConsumer {

    @StreamListener(Sink.INPUT)
    public void loggerSink(SinkTimeInfo sinkTimeInfo) {
        System.out.println("TimeInfo Received: " + sinkTimeInfo.toString());
    }  

}
