package pers.mk.interview.hw;

import java.util.Scanner;

public class HJ017 {
    public static void main(String[] args) {
        mk();
        m1();
        m2();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        String[] split = str.split(";");
        int[] position = {0,0};
        for (String t : split) {
            if (t == null || "".equals(t)) {
                continue;
            } else {
                String tf = t.substring(0, 1);
                String ta = t.substring(1);
                if (tf.contains("A") || tf.contains("D") || tf.contains("W") ||
                        tf.contains("S") ) {
                    int taInt = 0;
                    try {
                        taInt = Integer.valueOf(ta);
                    } catch (Exception e) {
                        taInt = 0;
                    }
                    if(taInt != 0){
                        if("A".equals(tf)){
                            position[0] = position[0] - taInt;
                        }else if("D".equals(tf)){
                            position[0] = position[0] + taInt;
                        }else if("W".equals(tf)){
                            position[1] = position[1] + taInt;
                        }else if("S".equals(tf)){
                            position[1] = position[1] - taInt;
                        }
                    }
                }

            }
        }

        System.out.println(position[0] + "," + position[1]);
    }

    private static void m1(){

    }

    private static void m2(){

    }

}
