package pers.mk.api.enums;

public enum DemoEnum {

    SMALL("S"),
    MEDIUM("M"),
    LARGE("L");

    private String type;

    DemoEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
