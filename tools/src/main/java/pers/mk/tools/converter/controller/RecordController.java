package pers.mk.tools.converter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mk.tools.converter.model.Record;

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


    @RequestMapping("/index")
    public String index(){
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

}
