package main.cn.icbc.custom;

import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import main.cn.icbc.bean.Item;
import main.cn.icbc.bean.Order;

@EnableBinding({ CustomSource.class })
@Component
public class Producer {

    @Autowired
    private CustomSource source;

    @Scheduled(fixedRate = 5000)
    public void produceHotDrinks() {
        source.output().send(
                MessageBuilder.withPayload(Order.builder().flag("Hot").num(new Random().nextInt(100)).build()).build());
    }

    @Scheduled(fixedRate = 3000)
    public void produceColdDrinks() {
        source.output().send(MessageBuilder
                .withPayload(Order.builder().flag("Cold").num(new Random().nextInt(100)).build()).build());

    }

    @Scheduled(fixedRate = 3000)
    public void produceItem() {
        source.output1()
                .send(MessageBuilder.withPayload(
                        Item.builder().id(UUID.randomUUID().toString()).timestamp(System.currentTimeMillis()).build())
                        .build());

    }

    @Scheduled(fixedRate = 3000)
    public void produceMsg() {
        source.output2().send(MessageBuilder.withPayload(UUID.randomUUID().toString()).build());

    }

}
