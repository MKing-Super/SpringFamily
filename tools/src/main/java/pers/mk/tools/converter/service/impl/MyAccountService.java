package pers.mk.tools.converter.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pers.mk.tools.converter.service.AccountService;
import pers.mk.tools.converter.service.FuturesRecordService;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.service.impl
 * @Date 2023/4/24 14:45
 * @Version 1.0
 */
@Service
public class MyAccountService implements AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyAccountService.class);

    private final FuturesRecordService futuresRecordService;


    public MyAccountService(FuturesRecordService futuresRecordService) {
        this.futuresRecordService = futuresRecordService;
    }


    @Override
    public String getMethod() {
        String method = futuresRecordService.getMethod();
        LOGGER.warn("我炸了");
        return method;
    }
}
