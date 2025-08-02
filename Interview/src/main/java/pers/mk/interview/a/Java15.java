package pers.mk.interview.a;


import java.util.HashMap;
import java.util.Map;

public class Java15 {
    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put(null, 1);      // 存入 null key
        map.put(null, 2);      // 覆盖之前的 null key 的 value
        map.put("A", null);    // 存入 null value
        map.put("B", null);    // 再次存入 null value
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(map.get(entry.getKey()));
//            if (entry.getValue() == null) {
//                System.out.println("Key: " + entry.getKey() + ", Value: null");
//            }
        }
    }
}
