package main.cn.icbc.multi;

import java.util.UUID;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.core.MessageSource;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.GenericMessage;

@EnableBinding(SampleSource.MultiOutputSource.class)
public class SampleSource {

    @Bean
    @InboundChannelAdapter(value = MultiOutputSource.OUTPUT1, poller = @Poller(fixedDelay = "1000", maxMessagesPerPoll = "1"))
    public synchronized MessageSource<String> messageSource1() {
        return new MessageSource<String>() {
            public Message<String> receive() {
                return new GenericMessage<String>("message1_" + UUID.randomUUID().toString());
            }
        };
    }

    @Bean
    @InboundChannelAdapter(value = MultiOutputSource.OUTPUT2, poller = @Poller(fixedDelay = "1000", maxMessagesPerPoll = "1"))
    public synchronized MessageSource<String> messageSource2() {
        return new MessageSource<String>() {
            public Message<String> receive() {
                return new GenericMessage<String>("message2_" + UUID.randomUUID().toString());
            }
        };
    }

    public interface MultiOutputSource {
        String OUTPUT1 = "output11";

        String OUTPUT2 = "output22";

        @Output(OUTPUT1)
        MessageChannel output1();

        @Output(OUTPUT2)
        MessageChannel output2();
    }
}
