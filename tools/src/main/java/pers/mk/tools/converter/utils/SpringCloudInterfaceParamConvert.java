package pers.mk.tools.converter.utils;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: SpringCloud接口参数转换
 * @Author: kun.ma
 * @Date: 2022/3/2 16:49
 */
@Controller
@RequestMapping("SpringCloudInterfaceParamConvert")
public class SpringCloudInterfaceParamConvert {

    @GetMapping("/index")
    public String index(String oldServiceName,String feignServiceSuffix,
                        String className, String interfaceMethodName, String interfaceMethodSuffix,
                        String feignSuffix,
                        Model model){
        try {
            //标准feignService接口类名称：
            String feignServiceResult = oldServiceName + feignServiceSuffix;
            model.addAttribute("feignServiceResult",oldServiceName == ""?"":feignServiceResult);
            int length = interfaceMethodName.length();
            int length1 = oldServiceName.length();
            String path;
            if (length1 > 1){
                String pre = oldServiceName.substring(0, 1).toLowerCase();
                String suf = oldServiceName.substring(1, length1);
                path = pre + suf;
                //标准path名称：
                model.addAttribute("path",path);
            }else {
                model.addAttribute("result","");
                model.addAttribute("interfaceResult","");
            }

            //标准SpringCloud的实体类名称：
            String result;
            if (length > 1 && className != null && !"".equals(className)){
                String pre = interfaceMethodName.substring(0, 1).toUpperCase();
                String suf = interfaceMethodName.substring(1, length);
                result = className + pre + suf + interfaceMethodSuffix;
                model.addAttribute("result",result);
            }else {
                model.addAttribute("result","");
            }


            //标准SpringCloud的接口方法名称：
            String interfaceResult = interfaceMethodName + feignSuffix;
            model.addAttribute("interfaceResult",interfaceResult);
        }catch (Exception e){
            model.addAttribute("result","后台报错了！请检查输入的值！！！");
            model.addAttribute("interfaceResult","后台报错了！请检查输入的值！！！");
            return "index";
        }
        return "index";
    }
}
