package pers.mk.tools.converter.controller;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import com.sun.speech.freetts.Voice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.speech.freetts.VoiceManager;

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

    private static String authorization;
    private static int contentLength;
    private static String conversationId;

    @RequestMapping("/index")
    public String index() {
        return "/chat/index";
    }

    @RequestMapping("/token")
    @ResponseBody
    public String token(String token){
        authorization = token;
        return "success";
    }

    @RequestMapping("/talk")
    @ResponseBody
    public String talk(String prompt) {
        if (StrUtil.isBlank(prompt)) {
            return "parameter is null";
        }
        try {
            prompt = prompt.trim();
            Map<String, Object> paramMap = new HashMap<>();
            if (StrUtil.isNotBlank(conversationId)) {
                paramMap.put("conversationId", conversationId);
            }
            paramMap.put("prompt", prompt);
            JSONObject obj = JSONUtil.createObj();
            obj.putAll(paramMap);
            String reqStr = obj.toString();
            contentLength = reqStr.getBytes(StandardCharsets.UTF_8).length;

            Map<String, List<String>> headers = new HashMap<>();
            headers.put("Accept", CollectionUtil.newArrayList("*/*"));
            headers.put("Accept-Encoding", CollectionUtil.newArrayList("gzip", "deflate", "br"));
            headers.put("Accept-Language", CollectionUtil.newArrayList("zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6"));
            headers.put("Authorization", CollectionUtil.newArrayList("Bearer " + authorization));
            headers.put("Connection", CollectionUtil.newArrayList("keep-alive"));
            headers.put("Content-Length", CollectionUtil.newArrayList(String.valueOf(contentLength)));
            headers.put("Content-Type", CollectionUtil.newArrayList("application/json;charset=UTF-8"));
            headers.put("Host", CollectionUtil.newArrayList("wetabchat.haohuola.com"));
            headers.put("Origin", CollectionUtil.newArrayList("chrome-extension://bpelnogcookhocnaokfpoeinibimbeff"));
            headers.put("Sec-Fetch-Dest", CollectionUtil.newArrayList("empty"));
            headers.put("Sec-Fetch-Mode", CollectionUtil.newArrayList("cors"));
            headers.put("Sec-Fetch-Site", CollectionUtil.newArrayList("none"));
            headers.put("User-Agent", CollectionUtil.newArrayList("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67"));
            headers.put("i-app", CollectionUtil.newArrayList("hitab"));
            headers.put("i-branch", CollectionUtil.newArrayList("zh"));
            headers.put("i-lang", CollectionUtil.newArrayList("zh-CN"));
            headers.put("i-platform", CollectionUtil.newArrayList("edge"));
            headers.put("i-version", CollectionUtil.newArrayList("1.0.53"));
            headers.put("sec-ch-ua", CollectionUtil.newArrayList("\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Microsoft Edge\";v=\"114\""));
            headers.put("sec-ch-ua-mobile", CollectionUtil.newArrayList("?0"));
            headers.put("sec-ch-ua-platform", CollectionUtil.newArrayList("\"Windows\""));

            HttpResponse execute = HttpRequest.post("https://wetabchat.haohuola.com/api/chat/conversation-v2")
                    .header(headers)
                    .body(reqStr)
                    .execute();
            String body = execute.body();
            String[] sss = body.split("_e79218965e_");
            log.info(body);
            String result = "";
            for (String str : sss) {
                JSONObject jsonObject = JSONUtil.parseObj(str);
                JSONObject data = jsonObject.get("data", JSONObject.class);
                Integer code = jsonObject.get("code", Integer.class);
                String content = data.get("content", String.class);
                if (code == 5001){
                    conversationId = data.get("conversationId", String.class);
                }
                if (StrUtil.isNotBlank(content)){
                    result += content;
                }
            }
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            log.error("chat error {}", e);
            return "error";
        } finally {
            contentLength = 0;
        }
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String delete() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("conversationId", conversationId);
        JSONObject obj = JSONUtil.createObj();
        obj.putAll(paramMap);
        String reqStr = obj.toString();

        Map<String, List<String>> headers = new HashMap<>();
        headers.put("Accept", CollectionUtil.newArrayList("*/*"));
        headers.put("Accept-Encoding", CollectionUtil.newArrayList("gzip", "deflate", "br"));
        headers.put("Accept-Language", CollectionUtil.newArrayList("zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6"));
        headers.put("Authorization", CollectionUtil.newArrayList("Bearer " + authorization));
        headers.put("Connection", CollectionUtil.newArrayList("keep-alive"));
        headers.put("Content-Length", CollectionUtil.newArrayList(String.valueOf(conversationId.getBytes(StandardCharsets.UTF_8).length)));
        headers.put("Content-Type", CollectionUtil.newArrayList("application/json;charset=UTF-8"));
        headers.put("Host", CollectionUtil.newArrayList("wetabchat.haohuola.com"));
        headers.put("Origin", CollectionUtil.newArrayList("chrome-extension://bpelnogcookhocnaokfpoeinibimbeff"));
        headers.put("Sec-Fetch-Dest", CollectionUtil.newArrayList("empty"));
        headers.put("Sec-Fetch-Mode", CollectionUtil.newArrayList("cors"));
        headers.put("Sec-Fetch-Site", CollectionUtil.newArrayList("none"));
        headers.put("User-Agent", CollectionUtil.newArrayList("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36 Edg/114.0.1823.67"));
        headers.put("i-app", CollectionUtil.newArrayList("hitab"));
        headers.put("i-branch", CollectionUtil.newArrayList("zh"));
        headers.put("i-lang", CollectionUtil.newArrayList("zh-CN"));
        headers.put("i-platform", CollectionUtil.newArrayList("edge"));
        headers.put("i-version", CollectionUtil.newArrayList("1.0.53"));
        headers.put("sec-ch-ua", CollectionUtil.newArrayList("\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Microsoft Edge\";v=\"114\""));
        headers.put("sec-ch-ua-mobile", CollectionUtil.newArrayList("?0"));
        headers.put("sec-ch-ua-platform", CollectionUtil.newArrayList("\"Windows\""));

        HttpResponse execute = HttpRequest.post("https://wetabchat.haohuola.com/api/chat/delete")
                .header(headers)
                .body(reqStr)
                .execute();
        conversationId = "";
        return execute.body();
    }

    @RequestMapping("/check-key")
    @ResponseBody
    public String checkKey(){
        HashMap<String, String> map = new HashMap<>();
        map.put("conversationId",conversationId);
        map.put("authorization",authorization);
        return JSON.toJSONString(map);
    }


    public static void main(String[] args) {
//        windows();
        freeTTS();
    }

    private static void windows(){
        //调用windowsApi 的 com组件，Sapi.spVoice是 windows com组件名称
        ActiveXComponent activeXComponent = new ActiveXComponent("Sapi.SpVoice");
        //从com组件中获得调度目标
        Dispatch dis = activeXComponent.getObject();
        try {
            //设置语言组件属性
            activeXComponent.setProperty("Volume", new Variant(100));
            activeXComponent.setProperty("Rate", new Variant(-1));
            Dispatch.call(dis, "Speak", new Variant("今天天气不错，风和日丽的。"));
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            dis.safeRelease();
            activeXComponent.safeRelease();
        }
    }

    private static void freeTTS(){
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        // 初始化 FreeTTS 引擎
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice voice = voiceManager.getVoice("kevin16");
        voice.allocate();

        // 合成语音
        String text = "Hello, World!";
        voice.speak(text);

        // 释放资源
        voice.deallocate();
    }

}
