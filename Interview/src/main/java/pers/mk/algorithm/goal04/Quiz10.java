package pers.mk.algorithm.goal04;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Quiz10 {
    public static void main(String[] args) {
        // 思路错了！判断错了
//        mk();


        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int N = Integer.parseInt(in.nextLine().trim());
        List<AppMk> list = new ArrayList<>();
        for (int i = 0 ; i < N ; i++){
            String[] split = in.nextLine().trim().split(" ");
            String name = split[0];
            int level = Integer.parseInt(split[1]);
            String startTime = split[2];
            String endTime = split[3];
            String[] startStr = startTime.split(":");
            int startHour = Integer.parseInt(startStr[0]);
            int startMinute = Integer.parseInt(startStr[1]);
            String[] endStr = endTime.split(":");
            int endHour = Integer.parseInt(endStr[0]);
            int endMinute = Integer.parseInt(endStr[1]);
            if (startHour < endHour || (startHour == endHour && startMinute < endMinute) ){
                list.add(new AppMk(name,level,startHour,startMinute,endHour,endMinute));
            }
        }
        String pointTime = in.nextLine().trim();
        if (list.isEmpty()){
            System.out.println("NA");
            return;
        }
        String[] strings = pointTime.split(":");
        int pointHour = Integer.parseInt(strings[0]);
        int pointMinute = Integer.parseInt(strings[1]);

        list.sort((a,b) -> {
            if (a.startHour == b.startHour){
                return a.startMinute - b.endMinute;
            }
            return a.startHour - b.startHour;
        } );

        ArrayList<AppMk> pointList = new ArrayList<>();
        for (int i = 0 ; i < list.size() ; i++){
            AppMk t = list.get(i);
            if (pointHour > t.startHour || (pointHour == t.startHour && pointMinute >= t.startMinute) ){
                if (pointHour < t.endHour || (pointHour == t.endHour && pointMinute < t.endMinute) ){
                    pointList.add(t);
                }
            }
        }
        System.out.println(JSON.toJSONString(pointList));
        if (pointList.isEmpty()){
            System.out.println("NA");
            return;
        }
        if (list.size() == 1){
            System.out.println(list.get(0).name);
            return;
        }
        System.out.println(list.get(0).name);


    }

    private static class AppMk {
        String name;

        int level;

        int startHour;
        int startMinute;

        int endHour;
        int endMinute;

        public AppMk(String name, int level, int startHour, int startMinute, int endHour, int endMinute) {
            this.name = name;
            this.level = level;
            this.startHour = startHour;
            this.startMinute = startMinute;
            this.endHour = endHour;
            this.endMinute = endMinute;
        }
    }







    private static int parseTime(String time) {
        String[] parts = time.split(":");
        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

    static class App {
        String name;
        int priority;
        int start;
        int end;

        App(String name, int priority, String startStr, String endStr) {
            this.name = name;
            this.priority = priority;
            this.start = parseTime(startStr);
            this.end = parseTime(endStr);
        }
    }

    public static void m1() {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine().trim());
        List<App> registered = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] parts = sc.nextLine().trim().split(" ");
            String name = parts[0];
            int priority = Integer.parseInt(parts[1]);
            String startStr = parts[2];
            String endStr = parts[3];

            App newApp = new App(name, priority, startStr, endStr);
            if (newApp.start >= newApp.end) continue; // 无效注册

            boolean canRegister = true;
            List<App> toRemove = new ArrayList<>();
            List<App> toModify = new ArrayList<>(); // 用于记录需要截断的App及其新结束时间

            for (App existing : registered) {
                // 判断是否有重叠：[new.start, new.end) ∩ [ex.start, ex.end)
                if (newApp.end > existing.start && newApp.start < existing.end) {
                    if (existing.priority > newApp.priority) {
                        canRegister = false; // 已有App优先级更高，新App注册失败
                        break;
                    } else if (existing.priority == newApp.priority) {
                        canRegister = false; // 优先级相同，后添加的不能注册
                        break;
                    } else {
                        // 新App优先级更高，处理冲突
                        if (existing.start < newApp.start) {
                            // 保留左半段，截断结束时间为新App的开始时间
                            existing.end = newApp.start;
                            // 注意：如果截断后start>=end，则该App变为无效，需要删除
                            if (existing.start >= existing.end) {
                                toRemove.add(existing);
                            }
                        } else {
                            // 整个被覆盖，删除
                            toRemove.add(existing);
                        }
                    }
                }
            }

            if (canRegister) {
                // 执行删除（已在循环中标记）
                registered.removeAll(toRemove);
                // 注册新App
                registered.add(newApp);
            }
        }

        int query = parseTime(sc.nextLine().trim());
        String result = "NA";
        for (App app : registered) {
            if (app.start <= query && query < app.end) {
                result = app.name;
                break;
            }
        }
        System.out.println(result);
        sc.close();
    }

}
