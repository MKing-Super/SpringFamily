package per.mk.pirate.frame.test;

public class TestController {
    private String name;
    private Integer code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setName(String name,Integer code) {
        this.name = name;
        this.code = code;
    }

    public Integer getCode(){
        return code;
    }
}
