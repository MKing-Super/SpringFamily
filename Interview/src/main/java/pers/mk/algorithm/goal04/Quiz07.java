package pers.mk.algorithm.goal04;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Quiz07 {
    public static void main(String[] args) {
        // 思路正确
//        mk();

        // 优化
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());
        List<String[]> list = new ArrayList<>();
        for (int i = 0 ; i < n ; i++){
            String temp = in.nextLine().trim();
            String[] split = temp.split(":");
            String hours = split[0];
            String minutes = split[1];
            String[] split1 = split[2].split("\\.");
            String seconds = split1[0];
            String ms = split1[1];
            String[] tArr = new String[5];
            tArr[0] = temp;
            tArr[1] = hours;
            tArr[2] = minutes;
            tArr[3] = seconds;
            tArr[4] = ms;
            list.add(tArr);
        }

        list.sort((a,b) -> {
            int aHours = Integer.parseInt(a[1]);
            int bHours = Integer.parseInt(b[1]);

            int aMinutes = Integer.parseInt(a[2]);
            int bMinutes = Integer.parseInt(b[2]);

            int aSeconds = Integer.parseInt(a[3]);
            int bSeconds = Integer.parseInt(b[3]);

            int aMs = Integer.parseInt(a[4]);
            int bMs = Integer.parseInt(b[4]);

            if (aHours != bHours){
                return aHours - bHours;
            }else if (aMinutes != bMinutes){
                return aMinutes - bMinutes;
            }else if (aSeconds != bSeconds){
                return aSeconds - bSeconds;
            }else if (aMs != bMs){
                return aMs - bMs;
            }else {
                return 0;
            }

        });

        for (int i = 0 ; i < n ; i++){
            System.out.println(list.get(i)[0]);
        }
    }


    private static void m1(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());

        // 使用自定义对象代替String数组，更清晰
        List<LogTime> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String timeStr = in.nextLine().trim();
            String[] parts = timeStr.split(":");
            String[] secParts = parts[2].split("\\.");

            LogTime logTime = new LogTime(
                    timeStr,
                    Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1]),
                    Integer.parseInt(secParts[0]),
                    Integer.parseInt(secParts[1])
            );
            list.add(logTime);
        }

        // 使用Comparator链式比较
        list.sort(Comparator
                .comparingInt(LogTime::getHours)
                .thenComparingInt(LogTime::getMinutes)
                .thenComparingInt(LogTime::getSeconds)
                .thenComparingInt(LogTime::getMilliseconds)
        );

        list.forEach(logTime -> System.out.println(logTime.getOriginal()));
    }

    // 辅助类
    static class LogTime {
        private String original;
        private int hours, minutes, seconds, milliseconds;

        public LogTime(String original, int hours, int minutes, int seconds, int milliseconds) {
            this.original = original;
            this.hours = hours;
            this.minutes = minutes;
            this.seconds = seconds;
            this.milliseconds = milliseconds;
        }

        public String getOriginal() {
            return original;
        }

        public void setOriginal(String original) {
            this.original = original;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinutes() {
            return minutes;
        }

        public void setMinutes(int minutes) {
            this.minutes = minutes;
        }

        public int getSeconds() {
            return seconds;
        }

        public void setSeconds(int seconds) {
            this.seconds = seconds;
        }

        public int getMilliseconds() {
            return milliseconds;
        }

        public void setMilliseconds(int milliseconds) {
            this.milliseconds = milliseconds;
        }
    }

}
