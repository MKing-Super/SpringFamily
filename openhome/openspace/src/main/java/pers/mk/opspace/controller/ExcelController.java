package pers.mk.opspace.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Description: TODO
 * @Author: kun.ma
 * @Date: 2022/9/27 13:40
 */
@Controller
@RequestMapping("/excel")
public class ExcelController {



    @RequestMapping(value = "/export")
    @ResponseBody
    public void exportExcel(){
        try {
            this.tttt();
        }catch(Exception e){

        }
    }




    public void tttt() throws Exception {
        File f = new File("D:\\企业&委托人信息登记表-0926-预处理.xlsx");
        Workbook wb = readExcel(new FileInputStream(f),f.getName());
        Row row = null;
        if(wb != null){
            //获取第一个sheet
            Sheet sheet = wb.getSheetAt(0);
            //获取最大行数
            int rownum = sheet.getPhysicalNumberOfRows();
            //获取第一行
            row = sheet.getRow(0);
            //获取最大列数
            for (int i = 1; i<rownum; i++) {
                row = sheet.getRow(i);
                //企业注册号
                String qyzch = row.getCell(2).toString();
                //企业名称
                String qymc = row.getCell(3).toString();
                //法人名称
                String manName = row.getCell(4).toString();

                String companyResult = null;
                companyResult = qymc.replace("YQDZ-", "");
                companyResult = companyResult.replace("CJ-","");

                System.out.println(companyResult);
                //查询企业信息


//                operationLogMapper.insert()
            }
        }

    }


    public static Workbook readExcel(InputStream is, String extString){
        Workbook wb = null;
        extString = extString.substring(extString.lastIndexOf("."));
        try {
            if(".xls".equals(extString)){
                return wb = new HSSFWorkbook(is);
            }else if(".xlsx".equals(extString)){
                return wb = new XSSFWorkbook(is);
            }else {
                return wb = null;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return wb;
    }


}
