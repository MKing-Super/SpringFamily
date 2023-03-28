package pers.mk.opspace.java.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import pers.mk.opspace.api.MyLambdaInterface;
import pers.mk.opspace.api.model.Person;
import pers.mk.opspace.java.async.AsyncThread;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @describe: 对外调用
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.java.test
 * @Date 2023/3/17 13:46
 * @Version 1.0
 */
public class GzMain {

    public static void main(String[] args) {
        String s = "2022-12-01 00:00:00 ~ 2023-03-20 23:59:59";
        boolean matches = s.contains("~");
        String[] split = s.split("~");
        DateTime parse = DateUtil.parse(split[0]);
        DateTime parse1 = DateUtil.parse(split[1]);

        System.out.println(matches);

    }


}
