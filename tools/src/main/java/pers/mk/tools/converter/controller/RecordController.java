package pers.mk.tools.converter.controller;

import cn.hutool.http.Header;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mk.tools.converter.model.FuturesRecord;
import pers.mk.tools.converter.model.Record;
import pers.mk.tools.converter.service.FuturesRecordService;

import java.util.Date;
import java.util.HashMap;

/**
 * @Description: 日志
 * @Author: kun.ma
 * @Date: 2022/11/29 19:04
 */
@Slf4j
@Controller
@RequestMapping("/record")
public class RecordController {
    @Autowired
    private FuturesRecordService futuresRecordService;


    @RequestMapping("/index")
    public String index(){
        FuturesRecord futuresRecord = futuresRecordService.selectById(1);
        return "/record/index";
    }

    @RequestMapping("/detail")
    public String detail(String recordDate, Model model){
        HashMap<String, Object> map = new HashMap<>();
        map.put("recordDate",recordDate);
        model.addAttribute("map",map);
        return "/record/detail";
    }

    @RequestMapping("/detail-save")
    @ResponseBody
    public Boolean detailSave(Record record){
        System.out.println(record);
        return true;
    }

    @RequestMapping("/now-price")
    @ResponseBody
    public String nowPrice(){
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
