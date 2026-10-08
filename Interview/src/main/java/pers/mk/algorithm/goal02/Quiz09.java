package pers.mk.algorithm.goal02;

import com.alibaba.fastjson.JSON;

import java.util.*;

public class Quiz09 {
    public static void main(String[] args) {
        // 漏了很多条件！！！！
//        mk();

        // √
        m1();
    }

    private static void mk(){
        Scanner in = new Scanner(System.in);
        int n = Integer.parseInt(in.nextLine().trim());
        String[] licenseFreeArr = in.nextLine().trim().split(" ");
        List<String> licenseFreeList = Arrays.asList(licenseFreeArr);

        List<String[]> list = new ArrayList<>();
        while (true){
            String[] s = in.nextLine().trim().split(" ");
            if (s[0].equals("")){
                break;
            }
            String time = s[0];
            String license = s[1];
            String deal = s[2];
            if (!licenseFreeList.contains(license)){
                list.add(new String[]{license,time,deal});
            }

        }

        list.sort((a,b) -> {
            if (!a[0].equals(b[0])){
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });

        int fee = 0;
        for (int i = 0 ; i < list.size() ; i++){
            if (i >= 1 && "leave".equals(list.get(i)[2] ) && "enter".equals(list.get(i - 1)[2] ) ){
                String[] strEnd = list.get(i)[1].split(":");
                String[] strStart = list.get(i - 1)[1].split(":");
                int hourStart = Integer.parseInt(strStart[0]);
                int minuteStart = Integer.parseInt(strStart[1]);
                int hourEnd = Integer.parseInt(strEnd[0]);
                int minuteEnd = Integer.parseInt(strEnd[1]);
                int diffMinute = hourEnd * 60 + minuteEnd - (hourStart * 60 + minuteStart );
                int halfHours = (diffMinute - 1) / 30 + 1;
                fee = fee + halfHours * 1;
            }
        }
        System.out.println(fee);
    }




    static class Record {
        String time;
        String action;

        public Record(String time, String action) {
            this.time = time;
            this.action = action;
        }
    }

    private static void m1(){
        Scanner in = new Scanner(System.in);

        int n = Integer.parseInt(in.nextLine().trim());

        HashSet<String> freeLicenseSet = new HashSet<>();
        if (n > 0){
            String[] licenseFreeArr = in.nextLine().trim().split(" ");
            for (String s : licenseFreeArr){
                freeLicenseSet.add(s);
            }
        }

        // 存储非包月车辆的进出记录
        HashMap<String, List<Record>> carRecords = new HashMap<>();

        while (in.hasNextLine()){
            String line = in.nextLine().trim();
            if (line.isEmpty()){
                break;
            }
            String[] parts = line.split(" ");
            String time = parts[0];
            String license = parts[1];
            String action = parts[2];

            if (!freeLicenseSet.contains(license)){
                carRecords.computeIfAbsent(license,k -> new ArrayList<>())
                        .add(new Record(time,action));
            }
        }

        int totalFee = 0;

        // 处理每辆车的记录
        for (Map.Entry<String,List<Record>> entry : carRecords.entrySet() ){
            List<Record> records = entry.getValue();

            // 按时间排序
            records.sort(Comparator.comparing(r -> r.time));

            // 匹配enter和leave
            String enterTime = null;
            for (Record record : records){
                if ("enter".equals(record.action)){
                    enterTime = record.time;
                }else if ("leave".equals(record.action) && enterTime != null){
                    // 计算本次停车的费用
                    totalFee += calculateFee(enterTime,record.time);
                    enterTime = null;
                }
            }
        }

        System.out.println(totalFee);
    }

    private static int calculateFee(String startTime,String endTime){
        String[] startParts = startTime.split(":");
        String[] endParts = endTime.split(":");

        int startHour = Integer.parseInt(startParts[0]);
        int startMinute = Integer.parseInt(startParts[1]);
        int endHour = Integer.parseInt(endParts[0]);
        int endMintue = Integer.parseInt(endParts[1]);

        int startTotalMinutes = startHour * 60 + startMinute;
        int endTotalMinutes = endHour * 60 + endMintue;

        int totalMinutes = endTotalMinutes - startTotalMinutes;

        // 超过8小时不收费
        if (totalMinutes >= 60 * 8){
            return 0;
        }

        // 扣除免费时段（11:30-13:30）
        int chargeableMinutes = calculateChargeableMinutes(startTotalMinutes,endTotalMinutes);

        // 不满半小时不收钱
        if (chargeableMinutes < 30){
            return 0;
        }

        // 超过半小时，零头不满半小时按半小时算
        int halfHours = (chargeableMinutes + 29) / 30;

        return halfHours * 1;
    }

    // 计算可收费的分钟数（排除免费时段）
    static int calculateChargeableMinutes(int startMinutes,int endMinutes){
        int freeStart = 11 * 60 + 30;
        int freeEnd = 13 * 60 + 30;

        int chargeableMinutes = 0;
        int current = startMinutes;

        while (current < endMinutes){
            if (current >= freeStart && current < freeEnd){
                current = freeEnd;
            }else {
                int nextFreeStart = current < freeStart ? freeStart : freeStart + 24 * 60;
                int segmentEnd = Math.min(nextFreeStart,endMinutes);
                chargeableMinutes += segmentEnd - current;
                current = segmentEnd;
            }
        }
        return chargeableMinutes;
    }


}
