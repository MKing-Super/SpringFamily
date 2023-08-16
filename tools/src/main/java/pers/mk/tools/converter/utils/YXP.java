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
        String url = "https://www.youxinpai.com/trade/getTradeList";
        String bodyStr = "{\"entities\":\"{\\\"req\\\":{\\\"cityIds\\\":[],\\\"serialIds\\\":[],\\\"appearanceGrades\\\":[],\\\"skeletonGrades\\\":[],\\\"interiorGrades\\\":[],\\\"emissionStandards\\\":[],\\\"carPriceLevel\\\":[],\\\"carYearLevel\\\":[],\\\"carGearbox\\\":[],\\\"carOwners\\\":[],\\\"carUseTypes\\\":[],\\\"fuelTypes\\\":[],\\\"conditionPriceType\\\":[],\\\"transferCounts\\\":[],\\\"startPriceType\\\":[],\\\"isNotBubbleCar\\\":false,\\\"isNotBurnCar\\\":false,\\\"isNotSmallReport\\\":false,\\\"orderFields\\\":10},\\\"page\\\":[{\\\"page\\\":1,\\\"pageSize\\\":2,\\\"pageTab\\\":\\\"pc_circle\\\"},{\\\"page\\\":4,\\\"pageSize\\\":15,\\\"pageTab\\\":\\\"immediately\\\"},{\\\"page\\\":1,\\\"pageSize\\\":2,\\\"pageTab\\\":\\\"delay\\\"},{\\\"page\\\":1,\\\"pageSize\\\":2,\\\"pageTab\\\":\\\"fixedPrice\\\"},{\\\"page\\\":1,\\\"pageSize\\\":2,\\\"pageTab\\\":\\\"benz\\\"},{\\\"page\\\":1,\\\"pageSize\\\":2,\\\"pageTab\\\":\\\"attention\\\"}]}\"}";
        HashMap<String, String> heads = dealHeads();
        HttpResponse execute = HttpRequest.post(url)
                .headerMap(heads, false)
                .body(bodyStr)
                .execute();
        String body = execute.body();
        System.out.println(body);

    }


    private static HashMap<String, String> dealHeads() {
        HashMap<String, String> map = new HashMap<>();
        String reqStr =
                "authority: www.youxinpai.com\n" +
                "method: POST\n" +
                "path: /trade/getTradeList\n" +
                "scheme: https\n" +
                "accept: application/json, text/plain, */*\n" +
                "accept-encoding: gzip, deflate, br\n" +
                "accept-language: zh-CN,zh;q=0.9\n" +
                "cache-control: no-cache\n" +
                "content-length: 757\n" +
                "content-type: application/json\n" +
                "cookie: id58=CrIbn2TbJwpWzyQHAxZjAg==; hasShowGongGao1=; hasShowZhongCai=; 58tj_uuid=86e9a8b5-31c9-476c-bcf2-b1945d42475c; new_uv=2; utm_source=; spm=; init_refer=; new_session=0; csrfToken_key=WqFzWv9XvCuRQsxNyw4GUBH3; csrfToken=VPscsu19-VxHyvHB-GjPyRtj5H-dxsGTo2NA; jwt_token=eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJkYXRhIjoiVlBzY3N1MTktVnhIeXZIQi1HalB5UnRqNUgtZHhzR1RvMk5BIiwiaWF0IjoxNjkyMTYyOTk0LCJleHAiOjE2OTIxNjQ3OTR9.RurxwfLqb9kiSnkS3WY0Ugd6mNzOIso8TM2nxoMQIz8\n" +
                "origin: https://www.youxinpai.com\n" +
                "pragma: no-cache\n" +
                "referer: https://www.youxinpai.com/trade\n" +
                "sec-ch-ua: \" Not;A Brand\";v=\"99\", \"Google Chrome\";v=\"97\", \"Chromium\";v=\"97\"\n" +
                "sec-ch-ua-mobile: ?0\n" +
                "sec-ch-ua-platform: \"Windows\"\n" +
                "sec-fetch-dest: empty\n" +
                "sec-fetch-mode: cors\n" +
                "sec-fetch-site: same-origin\n" +
                "sw8: 1-NjgwYjRjOWM3ZGRmNDY5NzkyZGViOWYzMTk4OWE1ODE=-N2Y2NWU5MThkMjVlNDIxNzg1MGZkMTY3ZGVhMDA0OWI=-0-OTAx-aHR0cHM6Ly93d3cueW91eGlucGFpLmNvbQ==-L3RyYWRlL2dldFRyYWRlTGlzdA==-d3d3LnlvdXhpbnBhaS5jb20=-0\n" +
                "sw8-correlation: c291cmNl:YmVpZG91\n" +
                "user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/97.0.4692.71 Safari/537.36";
        String[] split = reqStr.split("\n");
        for (String str : split) {
            String[] arr = str.split(": ");
            map.put(arr[0], arr[1]);
        }
        return map;
    }

}
