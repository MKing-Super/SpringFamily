package pers.mk.tools.converter.controller;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.FileWriter;
import java.io.IOException;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName tools
 * @Package pers.mk.tools.converter.controller
 * @Date 2025/5/14 10:14
 * @Version 1.0
 */
@Slf4j
@Controller
@RequestMapping("/dailyRecord")
public class DailyRecordController {

    // 指定文件路径
    private static String filePath = "";

    @RequestMapping("/index")
    public String index(){
        return "/dailyRecord/index";
    }

    @RequestMapping("/writeTxt")
    @ResponseBody
    public Boolean writeTxt(String title,String content){
//        String content = "这是要追加到文件的内容";
//        String filePath = "output.txt"; // 指定文件路径
        if (StrUtil.isNotBlank(title)){
            filePath = "D:\\DailyRecords\\" + title + ".txt";
        }
        if (StrUtil.isBlank(filePath)){
            return false;
        }
        if (StrUtil.isBlank(content)){
            return false;
        }

        try (FileWriter writer = new FileWriter(filePath, true)) { // true表示追加模式
            writer.write(content);
            writer.write(System.lineSeparator()); // 可选：添加换行符
            System.out.println("成功追加内容到文件: " + filePath);
        } catch (IOException e) {
            System.err.println("写入文件时出错: " + e.getMessage());
            return false;
        } catch (Exception e){
            return false;
        }
        System.out.println(content);
        return true;
    }

}
