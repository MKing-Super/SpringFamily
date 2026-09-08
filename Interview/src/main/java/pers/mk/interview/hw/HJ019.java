package pers.mk.interview.hw;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class HJ019 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        Map<String, Integer> map = new LinkedHashMap<>();
        Map<String, Integer> countMap = new HashMap<>();
        while (in.hasNext()) {
            String address = in.next();
            int errorNum = in.nextInt();
            address = address.substring(address.lastIndexOf('\\') + 1);
            if(address.length() >= 16){
                address = address.substring(address.length()-16);
            }

            String key = address + " " + errorNum;
            map.put(key,errorNum);

            if(countMap.get(key) == null){
                countMap.put(key,1);
            }else{
                countMap.put(key,countMap.get(key)+1);
            }
        }
        int size = map.size();
        int i = 0;
        for(String key : map.keySet()){
            i++;
            if(size <= 8){
                System.out.println(key + " " + countMap.get(key));
            }else{
                if(i > size - 8){
                    System.out.println(key + " " + countMap.get(key));
                }
            }
        }
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
