package main.cn.icbc.multi;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.SubscribableChannel;

@EnableBinding(SampleSink.MultiInputSink.class)
public class SampleSink {

    @StreamListener(MultiInputSink.INPUT1)
    public synchronized void receive1(String message) {
        System.out.println("Received message @Sink1: " + message);
    }

    @StreamListener(MultiInputSink.INPUT2)
    public synchronized void receive2(String message) {
        System.out.println("Received message @Sink2: " + message);
    }

    public interface MultiInputSink {
        String INPUT1 = "input11";

        String INPUT2 = "input22";

        @Input(INPUT1)
        SubscribableChannel input1();

        @Input(INPUT2)
        SubscribableChannel input2();
    }
}
