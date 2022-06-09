package pers.mk.opspace.controller;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pers.mk.opspace.service.DataService;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/data")
public class DataController {

    @Resource
    private DataService dataService;
    @Resource
    private OkHttpClient okHttpClient;
    @Resource
    private ObjectMapper objectMapper;

    @ResponseBody
    @GetMapping("/index")
    public String index() {
        String s = dataService.selectName();
        return s;
    }

    @GetMapping("/query-ip")
    public String queryCityByip(String akStr, String ipStr, Model model){
        String url = "http://api.map.baidu.com/location/ip?ak=" + akStr + "&ip=" + ipStr + "&coor=bd09ll";
        Call call = okHttpClient.newCall(new Request.Builder().get().url(url).build());
        try {
            Response execute = call.execute();
            okhttp3.ResponseBody responseBody = execute.body();
            String resultStr = responseBody.string();
            Map reslutMap = objectMapper.readValue(resultStr, Map.class);
            model.addAttribute("reslutMap",reslutMap);
            log.info("resultMap:{}",JSON.toJSONString(reslutMap));
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/index";
    }
}
