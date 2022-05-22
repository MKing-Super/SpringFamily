package pers.mk.opspace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mk.opspace.service.DataService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/data")
public class DataController {

    @Resource
    private DataService dataService;

    @ResponseBody
    @GetMapping("/index")
    public String index() {
        String s = dataService.selectName();
        return s;
    }
}
