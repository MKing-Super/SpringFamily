package pers.mk.api.model;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.api.model
 * @Date 2023/5/6 17:10
 * @Version 1.0
 */
public class DIModel {
    public static class Service1 {
        private String desc;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    }

    public static class Service2 {
        private String desc;

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    }

    public static class Service3 {
        private String desc;

        public Service3() {
            System.out.println("这是延迟加载");
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

    }

    private Service1 service1;
    private Service2 service2;
    private Service3 service3;

    public DIModel() {
    }

    public DIModel(Service1 service1, Service2 service2) {
        this.service1 = service1;
        this.service2 = service2;
    }

    public DIModel(Service1 service1, Service2 service2, Service3 service3) {
        this.service1 = service1;
        this.service2 = service2;
        this.service3 = service3;
    }

    public Service1 getService1() {
        return service1;
    }

    public void setService1(Service1 service1) {
        System.out.println("setService1->" + service1); //@1
        this.service1 = service1;
    }

    public Service2 getService2() {
        return service2;
    }

    public void setService2(Service2 service2) {
        System.out.println("setService2->" + service2); //@2
        this.service2 = service2;
    }

    public Service3 getService3() {
        return service3;
    }

    public void setService3(Service3 service3) {
        this.service3 = service3;
    }
}
