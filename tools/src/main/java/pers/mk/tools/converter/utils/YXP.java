package pers.mk.tools.converter.utils;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.HashMap;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.utils
 * @Date 2023/8/16 13:19
 * @Version 1.0
 */
public class YXP {

    public static void main(String[] args) {
        String url = "https://www.youxinpai.com/home/trade/detail/5275886/8960e9";
        HashMap<String, String> heads = dealHeads();
        HttpResponse execute = HttpRequest.get(url)
                .headerMap(heads, false)
                .execute();
        String body = execute.body();
        Document doc = Jsoup.parse(body);

        //车辆全称
        String vehicleName = doc.getElementsByClass("MD-widget-tradedetail_new-common-crumbsInfo-crumb-last").first().childNodes().get(0).outerHtml();

        System.out.println(vehicleName);
    }


    private static HashMap<String, String> dealHeads() {
        HashMap<String, String> map = new HashMap<>();
        String reqStr = "authority: www.youxinpai.com\n" +
                "method: GET\n" +
                "path: /home/trade/detail/5275886/8960e9\n" +
                "scheme: https\n" +
                "accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\n" +
                "accept-encoding: gzip, deflate, br\n" +
                "accept-language: zh-CN,zh;q=0.9\n" +
                "cache-control: no-cache\n" +
                "cookie: SECKEY_ABVK=ZaepJiTMCgecQPCPb0QvKcXroxqzCMF2sxg1I+k9TmA%3D; BMAP_SECKEY=AI3MI9quTOIIiLzt2reM9wFztMg0_5SWHnyFqKxypUnWq0TMFZzXEeXpaiVVaX4iEvWmCtRTzu2Aoo3zhs1HMYJajiDp4Mu0v47_eruLhAqE6ChFiBEhsgp8zhdYNA3xJ-Mt1LOchCdfYOHz5JuW3mRQ_ACDAYoiS0bNsRsCRm_u1xDLY93z7Omafzi6qJjI; id58=CrIbn2TbJwpWzyQHAxZjAg==; hasShowGongGao1=; hasShowZhongCai=; 58tj_uuid=86e9a8b5-31c9-476c-bcf2-b1945d42475c; new_uv=1; utm_source=; spm=; init_refer=; new_session=0; csrfToken_key=VmYzBTwZgsidi-UVB7N6bupR; csrfToken=3c1MCzFu-sDnnjuhe--sshClAgoX5HV5WZs8; jwt_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjoiM2MxTUN6RnUtc0Rubmp1aGUtLXNzaENsQWdvWDVIVjVXWnM4IiwiaWF0IjoxNjkyMDg2NzU3LCJleHAiOjE2OTIwODg1NTd9.RGp8c7CWZrpcOdaJEC7JBR3RRAL3UcG1UGT5RsMneZ0\n" +
                "pragma: no-cache\n" +
                "referer: https://www.youxinpai.com/trade\n" +
                "sec-ch-ua: \" Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"97\", \"Chromium\";v=\"97\"\n" +
                "sec-ch-ua-mobile: ?0\n" +
                "sec-ch-ua-platform: \"Windows\"\n" +
                "sec-fetch-dest: document\n" +
                "sec-fetch-mode: navigate\n" +
                "sec-fetch-site: same-origin\n" +
                "sec-fetch-user: ?1\n" +
                "upgrade-insecure-requests: 1\n" +
                "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36";

        String[] split = reqStr.split("\n");
        for (String str : split) {
            String[] arr = str.split(": ");
            map.put(arr[0], arr[1]);
        }
        return map;
    }

}
