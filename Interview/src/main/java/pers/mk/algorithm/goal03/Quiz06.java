package pers.mk.algorithm.goal03;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;
import java.util.Scanner;

public class Quiz06 {
    public static void main(String[] args) {
        mk();
        m1();
    }

    static final int CNY = 100,fen = 100;
    static final int JPY = 1825,sen = 1825;
    static final int HKD = 123,cents = 123;
    static final int EUR = 14,eurocents = 14;
    static final int GBP = 12,pence = 12;
    private static void mk(){
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine().trim());
        int totalFen = 0;
        for (int i = 0 ; i < N ; i++){
            String str = in.nextLine().trim();
            if (str.contains("CNY")){
                totalFen += moneyExchange(str,"CNY","fen");
            }else if (str.contains("fen")){
                totalFen += moneyExchange(str,null,"fen");
            }
            else if (str.contains("JPY")){
                totalFen += moneyExchange(str,"JPY","sen");
            }else if (str.contains("sen")){
                totalFen += moneyExchange(str,null,"sen");
            }
            else if (str.contains("HKD")){
                totalFen += moneyExchange(str,"HKD","cents");
            }else if (str.contains("cents")){
                totalFen += moneyExchange(str,null,"cents");
            }
            else if (str.contains("EUR")){
                totalFen += moneyExchange(str,"EUR","eurocents");
            }else if (str.contains("eurocents")){
                totalFen += moneyExchange(str,null,"eurocents");
            }
            else if (str.contains("GBP")){
                totalFen += moneyExchange(str,"GBP","pence");
            }else if (str.contains("pence")){
                totalFen += moneyExchange(str,null,"pence");
            }

        }
        System.out.println(totalFen);
    }

    static int moneyExchange(String str,String big,String small){
        int totalSmall = 0;
        if (big != null){
            String[] split = str.split(big);
            totalSmall += Integer.parseInt(split[0]) * 100;
            if (split.length > 1){
                String[] split1 = split[1].split(small);
                totalSmall += Integer.parseInt(split1[0]);
            }
        }else if (big == null && small != null){
            String[] arr = str.split(small);
            totalSmall += Integer.parseInt(arr[0]);
        }
        if ("CNY".equals(big) || "fen".equals(small)){
            return totalSmall;
        }else if ("JPY".equals(big) || "sen".equals(small)){
            return totalSmall * CNY / JPY;
        }else if ("HKD".equals(big) || "cents".equals(small)){
            return totalSmall * CNY / HKD;
        }else if ("EUR".equals(big) || "eurocents".equals(small)){
            return totalSmall * CNY / EUR;
        }else if ("GBP".equals(big) || "pence".equals(small)){
            return totalSmall * CNY / GBP;
        }
        return totalSmall;
    }

    private static void m1(){

    }



}
