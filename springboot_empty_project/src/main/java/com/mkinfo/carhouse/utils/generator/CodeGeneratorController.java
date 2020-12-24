package com.mkinfo.carhouse.utils.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @title: CodeGeneratorController
 * @package: com.mkinfo.carhouse.utils.generator
 * @description: 生成代码
 * @author: MK
 * @date: 2020-12-23 14:35
 * @version: V1.0
*/

@Controller
@RequestMapping("/code")
public class CodeGeneratorController {
    /**
     * @title: togenerator
     * @description: 跳转到代码生成页面
     * @author: MK
     * @date: 2020-12-23 14:36
     * @param: []
     * @return: java.lang.String
     * @throws:
     * @modefied:
     */
    @GetMapping(value = "/togenerator")
    public String togenerator(){
        return "CodeGenerator";
    }

    /**
     * @title: generator
     * @description: 接收页面传来的参数
     * @author: MK
     * @date: 2020-12-23 14:37
     * @param: [userName, password, ipAndPort, databaseName, tableName, packagesName]
     * @return: boolean
     * @throws:
     * @modefied:
     */
    @RequestMapping("/generator")
    @ResponseBody
    public boolean generator(String userName,String password,
                             String ipAndPort,String databaseName,
                             String tableName,String packagesName){
        AutoGenerator mpg = new AutoGenerator();
        //全局配置
        GlobalConfig gc = new GlobalConfig();
        //gc.setOutputDir("E:\\IdeaProjects\\examples_mybatis_plue\\demo01\\src\\main\\java");
        String path = System.getProperty("user.dir");
        path += "\\src\\main\\java";    //当前项目的路径，与注释类型等同
        gc.setOutputDir(path);      //输出文件的路径
        gc.setFileOverride(false);  //是否覆盖旧代码
        gc.setActiveRecord(false);
        gc.setEnableCache(false);   //XML二级缓存
        gc.setBaseResultMap(true);
        gc.setBaseColumnList(false);
        gc.setAuthor("MK");         //作者姓名
        gc.setControllerName("%sController");   //控制层命名规则
        gc.setServiceName("%sService");         //业务层接口命名规则
        gc.setServiceImplName("%sServiceImpl"); //业务层实现类命名规则
        gc.setMapperName("%sMapper");           //映射接口命名规则
        gc.setXmlName("%sMapper");              //XML文件命名规则
        mpg.setGlobalConfig(gc);

        //数据源配置
        DataSourceConfig dsc=new DataSourceConfig();
        dsc.setDbType(DbType.MYSQL);
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
//        dsc.setUsername("root");
        dsc.setUsername(userName);
//        dsc.setPassword("123456");
        dsc.setPassword(password);
//        dsc.setUrl("jdbc:mysql://localhost:3306/used_car_market?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8");
        String url = "jdbc:mysql://"+ipAndPort+"/"+databaseName+"?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8";
        dsc.setUrl(url);
        mpg.setDataSource(dsc);

        StrategyConfig strtegy = new StrategyConfig();
        strtegy.setNaming(NamingStrategy.underline_to_camel);
//        strtegy.setInclude("sys_user");
        strtegy.setInclude(tableName);   //数据库中的表名
        strtegy.setSuperServiceClass(null);
        strtegy.setSuperServiceImplClass(null);
        strtegy.setSuperMapperClass(null);
        mpg.setStrategy(strtegy);

        PackageConfig pc=new PackageConfig();
//        pc.setParent("com.mkinfo.carhouse");
        pc.setParent(packagesName);    //生成包的层级结构
        pc.setController("controller"); //controller层的包名
        pc.setService("service");       //service层的包名
        pc.setMapper("dao");         //dao层的包名
        pc.setXml("dao.xml");        //映射文件包名
        pc.setEntity("model");             //实体类的包名
        mpg.setPackageInfo(pc);
        try {
            //执行生成代码
            mpg.execute();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
