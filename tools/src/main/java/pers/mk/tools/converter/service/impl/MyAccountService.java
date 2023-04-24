package pers.mk.tools.converter.service.impl;

import com.alibaba.fastjson.JSON;
import org.springframework.stereotype.Service;
import pers.mk.tools.converter.model.FuturesRecord;
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

    private final FuturesRecordService futuresRecordService;


    public MyAccountService(FuturesRecordService futuresRecordService) {
        this.futuresRecordService = futuresRecordService;
    }


    @Override
    public String getMethod() {
        String method = futuresRecordService.getMethod();
        System.out.println(method);
        return method;
    }
}
