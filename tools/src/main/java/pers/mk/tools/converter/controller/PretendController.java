package pers.mk.tools.converter.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.controller
 * @Date 2023/10/26 20:06
 * @Version 1.0
 */
@Controller
@RequestMapping("/test")
@Slf4j
public class PretendController {

    private static String outOrderNo = "";
    private static String reportNo = "";

    @RequestMapping(value = "/dycw", method = RequestMethod.POST)
    @ResponseBody
    public String test(String apikey,
                       String vin,
                       String imgUrl,
                       String callBackUrl,
                       String engineNo,
                       String licensePlate,
                       String date,
                       String secret) {
        System.out.println("into >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        if (StrUtil.isBlank(apikey) || StrUtil.isBlank(vin) || StrUtil.isBlank(imgUrl) ||
                StrUtil.isBlank(callBackUrl) || StrUtil.isBlank(engineNo) || StrUtil.isBlank(licensePlate) ||
                StrUtil.isBlank(date) || StrUtil.isBlank(secret)){
            JSONObject error = new JSONObject();
            error.set("code", "100001");
            error.set("msg", "参数错误");
            error.set("time", date);
            return error.toString();
        }


        outOrderNo = RandomStringUtils.randomAlphanumeric(10);
        reportNo = RandomStringUtils.randomAlphanumeric(10);

        System.out.println("outOrderNo => " + outOrderNo);
        System.out.println("reportNo => " + reportNo);

        JSONObject o1 = new JSONObject();
        o1.set("reportNo", reportNo);
        o1.set("outOrderNo", outOrderNo);
        o1.set("status", "2");

        JSONObject jsonObject = new JSONObject();
        jsonObject.set("code", "100000");
        jsonObject.set("msg", "成功");
        jsonObject.set("time", date);
        jsonObject.set("data", o1);


        ExecutorService cachedThreadPool = Executors.newCachedThreadPool();
        cachedThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);

                    String url = "dycw";
                    JSONObject json = new JSONObject();
                    json.put("outOrderNo", outOrderNo);
                    json.put("reportNo", reportNo);
                    json.put("status", 1);
                    json.put("content", content());

                    String result = HttpRequest.post(url)
                            .header("Content-Type", "application/x-www-form-urlencoded")
                            .form(json).execute().body();
                    System.out.println(result);

                } catch (Exception e) {
                    log.error(e.getMessage(),e);

                }
            }
        });
        System.out.println("success >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return jsonObject.toString();
    }

    private static String content(){
        com.alibaba.fastjson.JSONObject resume = new com.alibaba.fastjson.JSONObject();
        resume.put("sd",0);
        resume.put("ronum",2);
        resume.put("mile",50000);
        resume.put("hs",0);
        resume.put("mi",0);
        resume.put("sp",0);
        resume.put("lastdate","2023-10-01");

        JSONArray reportJson = new JSONArray();
        com.alibaba.fastjson.JSONObject r0 = new com.alibaba.fastjson.JSONObject();
        r0.put("material","各种材料");
        r0.put("repairDate","2023-10-01 00:00:00");
        r0.put("type","擦伤");
        r0.put("mileageWarn","公里数有点异常");
        r0.put("content","修车");
        r0.put("mileage","50000");
        reportJson.add(r0);

        com.alibaba.fastjson.JSONObject basic = new com.alibaba.fastjson.JSONObject();
        basic.put("pfsp","排放标准炸了");
        basic.put("vin","SAJFLSDAFJASLDFJASLFJASDLF");
        basic.put("model","五菱宏光");
        basic.put("displacement","4.0T");
        basic.put("gearbox","无极变速");
        basic.put("brand","五零");

        com.alibaba.fastjson.JSONObject ob = new com.alibaba.fastjson.JSONObject();
        ob.put("resume",resume);
        ob.put("reportJson",reportJson);
        ob.put("basic",basic);

        return ob.toJSONString();
    }



    public static void main(String[] args) {
        Date now = new Date();
        String dateStr = DateUtil.format(now, "yyyy-MM-dd HH:mm:ss");

        String substring = dateStr.substring(0, 10);
        System.out.println("".length());
        System.out.println(substring);


    }

}
