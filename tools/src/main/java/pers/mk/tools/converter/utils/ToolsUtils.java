package pers.mk.tools.converter.utils;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ToolsUtils {

    public static String getNowPrice(){
        String url = "https://hq.sinajs.cn/rn=e4rdy&list=nf_C0,nf_C2301,nf_C2303,nf_C2305,nf_C2307,nf_C2309,nf_C2311";
        String result = HttpRequest.get(url)
                .header(Header.REFERER, "https://vip.stock.finance.sina.com.cn/")
                .timeout(2000)
                .execute().body();
        int start = result.indexOf("hq_str_nf_C2303=");
        int end = result.indexOf("hq_str_nf_C2305=");
        String substr = result.substring(start, end);
        String[] split = substr.split(",");
        String s = split[6];
        Double v = Double.parseDouble(s);
        String s1 = String.valueOf(v.intValue());
        log.info(s1);
        return s1;
    }

}
