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
    public String index(String className, String methodName, String suffix,String feignSuffix, Model model){
        try {
            //新实体类名
            String pre;
            String suf;
            String result;
            int length = methodName.length();
            if (length > 1){
                pre = methodName.substring(0, 1).toUpperCase();
                suf = methodName.substring(1, length);
            }else {
                model.addAttribute("result","接口方法名称错误！！！");
                return "index";
            }
            result = className + pre + suf + suffix;
            model.addAttribute("result",result);

            //新接口方法名
            String interfaceResult = methodName + feignSuffix;
            model.addAttribute("interfaceResult",interfaceResult);
        }catch (Exception e){
            model.addAttribute("result","后台报错了！请检查输入的值！！！");
            model.addAttribute("interfaceResult","后台报错了！请检查输入的值！！！");
            return "index";
        }
        return "index";
    }
}
