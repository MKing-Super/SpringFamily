package pers.mk.tools.converter.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pers.mk.tools.converter.service.AccountService;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.service.impl
 * @Date 2023/5/6 10:55
 * @Version 1.0
 */
@Service
public class YourAccountService implements AccountService {
    @Override
    public String getMethod() {
        return "I am YourAccountService";
    }
}
