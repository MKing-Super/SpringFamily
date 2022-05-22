package pers.mk.opspace.java;

import pers.mk.opspace.annotation.Test;
import pers.mk.opspace.enums.DemoEnum;
import pers.mk.opspace.model.DemoModel;

import java.util.Random;

public class OpenSpaceApplication {


    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        DemoEnum large = DemoEnum.LARGE;
//        System.out.println(large.getType());
        DemoEnum small = Enum.valueOf(DemoEnum.class, "SMALL");
//        System.out.println(small);
        DemoEnum[] values = DemoEnum.values();
        for (DemoEnum d : values){
//            System.out.println(d);
        }
        DemoModel demoModel = new DemoModel();
        Class cl = demoModel.getClass();
        System.out.println(cl.getClass().getName());
        System.out.println(cl.getName());
        Class aClass = Class.forName("pers.mk.opspace.model.DemoModel");
        System.out.println(aClass.getName());
        Class<Random> randomClass = Random.class;
        System.out.println(randomClass.getName());
        System.out.println(int.class.getName());
        System.out.println(Double[].class.getName());
        Object o = Class.forName("java.util.Random").newInstance();
        System.out.println(o);

    }
}
