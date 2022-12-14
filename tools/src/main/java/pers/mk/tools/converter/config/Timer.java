package pers.mk.tools.converter.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pers.mk.tools.converter.utils.ToolsUtils;

import java.util.Date;

@Slf4j
@Component
@EnableScheduling
public class Timer {
    @Scheduled(fixedRate = 5000)
    public void initTime() {
        ToolsUtils.getNowPrice();
    }

}
