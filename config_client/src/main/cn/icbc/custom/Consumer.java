package main.cn.icbc.custom;

import main.cn.icbc.bean.Item;
import main.cn.icbc.bean.Order;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding({ CustomSink.class })
public class Consumer {

    @StreamListener(CustomSink.INPUT)
    public synchronized void listen_average(Order order) {
        System.out.println("Order Received For Average : " + order);
    }
    
    @StreamListener(CustomSink.INPUT0)
    public synchronized void listen_hdfsWrite(Order order) {
        System.out.println("Order Received For hdfsWrite : " + order);
    }
    

    @StreamListener(CustomSink.INPUT1)
    public synchronized void receive(Item item) {
        System.out.println("Item Received: " + item);
    }

    @StreamListener(CustomSink.INPUT2)
    public synchronized <T> void get(GenericMessage<T> msg) {
        System.out.println("Msg Received: " + msg.getPayload());
    }

}
