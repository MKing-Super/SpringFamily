package pers.mk.tools.converter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description: SpringCloud接口参数转换
 * @Author: kun.ma
 * @Date: 2022/3/2 16:49
 */
@Controller
public class SpringCloudInterfaceParamConvert {

    @GetMapping("/{custom}")
    public String index(@PathVariable("custom") String custom,
                        String oldServiceName,String feignServiceSuffix,
                        String className, String interfaceMethodName, String interfaceMethodSuffix,
                        String feignSuffix,
                        Model model)
    {
        model.addAttribute("custom",custom);
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
            if (interfaceMethodName != null && !"".equals(interfaceMethodName)){
                String interfaceResult = interfaceMethodName + feignSuffix;
                model.addAttribute("interfaceResult",interfaceResult);
            }else {
                model.addAttribute("interfaceResult","");
            }

        }catch (Exception e){
            model.addAttribute("feignServiceResult","请检查输入的值！！！");
            model.addAttribute("path","请检查输入的值！！！");
            model.addAttribute("result","请检查输入的值！！！");
            model.addAttribute("interfaceResult","请检查输入的值！！！");
            return "index";
        }
        return "index";
    }
}
