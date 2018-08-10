package main.cn.icbc.original;

import java.util.UUID;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.support.MessageBuilder;

import main.cn.icbc.bean.TimeInfo;

@EnableBinding(Source.class)
public class DataProducer {
    
    
    @Bean
    @InboundChannelAdapter(value = Source.OUTPUT, poller = @Poller(fixedDelay = "10000", maxMessagesPerPoll = "1"))
    public MessageSource<TimeInfo> timerMessageSource() {
        return () -> MessageBuilder.withPayload(
                TimeInfo.builder().time(Long.toString(System.currentTimeMillis())).label(UUID.randomUUID().toString()).build())
                .build();
    }
   
  
}
