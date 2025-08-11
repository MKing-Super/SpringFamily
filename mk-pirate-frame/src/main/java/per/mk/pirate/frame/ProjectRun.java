package per.mk.pirate.frame;

/**
 修改 BeanRegister.PACKAGE_PATH 指定扫包路径
 修改 HttpServer.HTTP_PORT 指定httpServer监视接口

 项目启动 Ignition.start()
 **/
public class ProjectRun {

    // 启动框架
    public static void main(String[] args) {
        BeanRegister.PACKAGE_PATH = "per.mk.pirate.frame.test";
        HttpServer.HTTP_PORT = 9000;
        Ignition.start();
        System.out.println(">>>>>>>>>>>>>>>>>>>>>");
    }

}
