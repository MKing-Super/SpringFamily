package pers.mk.tools.converter.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.controller
 * @Date 2023/7/5 14:57
 * @Version 1.0
 */
@Controller
@Slf4j
@RequestMapping("/chat-ai")
public class ChatAiController {

    private static String authorization =
            "Bearer ";
    private static int contentLength;

    @RequestMapping("/index")
    public String index(){
        return "/chat/index";
    }

    @RequestMapping("/talk")
    @ResponseBody
    public String talk(String content) {
        if (StrUtil.isBlank(content)){
            return "parameter is null";
        }
        try {
            content = content.trim();
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("conversationId","64a537a20ff484e0ecaeae64");
            paramMap.put("prompt",content);
            JSONObject obj = JSONUtil.createObj();
            obj.putAll(paramMap);
            String reqStr = obj.toString();
            contentLength = reqStr.getBytes(StandardCharsets.UTF_8).length;

            Map<String, List<String>> headers = new HashMap<>();
            headers.put("Accept", CollectionUtil.newArrayList("*/*"));
            headers.put("Accept-Encoding",CollectionUtil.newArrayList("gzip","deflate","br"));
            headers.put("Accept-Language",CollectionUtil.newArrayList("zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6"));
            headers.put("Authorization",CollectionUtil.newArrayList(authorization));
            headers.put("Connection",CollectionUtil.newArrayList("keep-alive"));
            headers.put("Content-Length",CollectionUtil.newArrayList(String.valueOf(contentLength)));
            headers.put("Content-Type",CollectionUtil.newArrayList("application/json;charset=UTF-8"));
            headers.put("Host",CollectionUtil.newArrayList("wetabchat.haohuola.com"));
            headers.put("Origin",CollectionUtil.newArrayList("chrome-extension://bpelnogcookhocnaokfpoeinibimbeff"));
            headers.put("Sec-Fetch-Dest",CollectionUtil.newArrayList("empty"));
            headers.put("Sec-Fetch-Mode",CollectionUtil.newArrayList("cors"));
            headers.put("Sec-Fetch-Site",CollectionUtil.newArrayList("none"));
            headers.put("User-Agent",CollectionUtil.newArrayList("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67"));
            headers.put("i-app",CollectionUtil.newArrayList("hitab"));
            headers.put("i-branch",CollectionUtil.newArrayList("zh"));
            headers.put("i-lang",CollectionUtil.newArrayList("zh-CN"));
            headers.put("i-platform",CollectionUtil.newArrayList("edge"));
            headers.put("i-version",CollectionUtil.newArrayList("1.0.53"));
            headers.put("sec-ch-ua",CollectionUtil.newArrayList("\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Microsoft Edge\";v=\"114\""));
            headers.put("sec-ch-ua-mobile",CollectionUtil.newArrayList("?0"));
            headers.put("sec-ch-ua-platform",CollectionUtil.newArrayList("\"Windows\""));

            HttpResponse execute = HttpRequest.post("https://wetabchat.haohuola.com/api/chat/conversation-v2")
                    .header(headers)
                    .body(reqStr)
                    .execute();
            String body = execute.body();
            String[] sss = body.split("_e79218965e_");
            String result = "";
            for (String str : sss){
                JSONObject jsonObject = JSONUtil.parseObj(str);
                JSONObject data = jsonObject.get("data", JSONObject.class);
                String tt = data.get("content", String.class);
                if (tt == null){
                    continue;
                }
                result += tt;
            }
            return result;
        }catch (Exception e){
            e.printStackTrace();
            log.error("chat error {}",e);
            return "error";
        }finally {
            contentLength = 0;
        }
    }


}
