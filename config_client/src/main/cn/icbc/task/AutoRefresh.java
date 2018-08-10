package main.cn.icbc.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
public class AutoRefresh {
    public volatile AtomicBoolean flag = new AtomicBoolean(true);

    @Scheduled(fixedRate = 1000 * 60 * 60 * 60)
    public void refreshConfig() {
        if (flag.get()) {
            System.out.println(new Date());
        }
    }


    @GetMapping("/start/{flag}")
    void receiveFlag(@PathVariable("flag") boolean flag) {
        System.out.println("this flag = " + flag);
        this.flag.set(flag);
    }


}
