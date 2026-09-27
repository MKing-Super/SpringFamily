package pers.mk.algorithm.goal02;

import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/*

题目描述
某设备需要记录每分钟检测到的指标值。为了节约存储空间，将连续相同指标值的记录合并。
压缩之前： 202411231000,11 202411231001,11 202411231002,12 202411231003,12 202411231004,10 202411231005,17 202411231006,17 202411231007,17
压缩之后： 202411231000,202411231001,11 202411231002,202411231003,12 202411231004,202411231004,10 202411231005,202411231007,17

查询时，根据输入的时间范围进行查询，需要返回回时间范围内记录的每分钟的指标值，如果某个时间点没有记录值，则此条记录忽略不返回。

输入描述
第一行为查询的时间范围，格式是：startTime,endTime。查询的时间范围为闭区间，即大于等于startTime且小于等于endTime， startTime <= endTime，且他们跨度的分钟数小于100；
第二行为压缩日志记录的行数，100 >= N > 0；
第三行及以后为压缩日志内容。每一行的格式为：startTime,endTime,kpi，其中 startTime<=endTime，10^5>kpi>=0；记录已按升序进行排序。
不保证两行记录之间是紧密连接，startTime和endTime的时间跨度可能很大。 如：上一行的数据显示范围是202411231540, 202411231542，下一行的数据显示可以是 202411231544, 202411231547， 中间202411231543的数据可能由于其他原因缺失。

输出描述
输出描述 查询到的日志清单，如： 202411231010,11 202411231011,10 202411231012,10 202411231013,16
输出结果按数据时间升序排序。

补充说明 输入的数据可能超出当前已存储的数据范围，此时只输出查询到的数据。 如果从头到尾都没有查询到记录，则输出-1。

用例1
输入
202411231010,202411231013
4
202411231000,202411231010,11
202411231011,202411231012,10
202411231013,202411231020,16
202411231021,202411231028,17

输出
202411231010,11
202411231011,10
202411231012,10
202411231013,16

说明：
202411231010时间的指标值在202411231000,202411231010范围内，值是11202411231011,202411231012时间的指标值在202411231011,202411231012范围内，值是10 202411231013时间的指标值在202411231013,202411231020范围内，值是16


 */
public class Quiz05 {
    public static void main(String[] args) {
//        mk();
//        https://blog.csdn.net/Chennai585/article/details/155968719
        m1();
    }

    private static void mk(){
        Scanner scanner = new Scanner(System.in);
        String[] queryRange = scanner.nextLine().split(",");
        long startQuery = Long.parseLong(queryRange[0]);
        long endQuery = Long.parseLong(queryRange[1]);

        int n = Integer.parseInt(scanner.nextLine());

        List<Record> records = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().split(",");
            long start = Long.parseLong(parts[0]);
            long end = Long.parseLong(parts[1]);
            int value = Integer.parseInt(parts[2]);

            long overlapStart = Math.max(start, startQuery);
            long overlapEnd = Math.min(end, endQuery);

            if (overlapStart <= overlapEnd) {
                for (long t = overlapStart; t <= overlapEnd; t++) {
                    records.add(new Record(t, value));
                }
            }
        }

        // 设置时间格式
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        if (records.isEmpty()) {
            System.out.println(-1);
        } else {
            List<Record> resList = new ArrayList<>();
            for (int i = 0 ; i < records.size() ; i++){
                try {
                    long time = records.get(i).getTime();
                    LocalDateTime.parse(Long.toString(time), fmt);
                    resList.add(records.get(i));
                }catch (Exception e){
//                    e.printStackTrace();
                }
            }
            resList.sort(Comparator.comparingLong(Record::getTime));
            for (Record record : resList) {
                System.out.println(record.getTime() + "," + record.getValue());
            }
        }

    }

    static class Record {
        private long time;
        private int value;

        public Record(long time, int value) {
            this.time = time;
            this.value = value;
        }

        public long getTime() {
            return time;
        }

        public int getValue() {
            return value;
        }
    }



    private static void m1(){
        Scanner in = new Scanner(System.in);

        // 查询时间范围
        String[] q = in.nextLine().trim().split(",");
        long qStart = Long.parseLong(q[0]);
        long qEnd = Long.parseLong(q[1]);

        int n = Integer.parseInt(in.nextLine().trim());

        boolean found = false;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMddHHmm");
        for (int i = 0; i < n; i++) {
            String[] rec = in.nextLine().trim().split(",");
            long s = Long.parseLong(rec[0]);
            long e = Long.parseLong(rec[1]);
            int kpi = Integer.parseInt(rec[2]);

            long left = Math.max(s, qStart);
            long right = Math.min(e, qEnd);

            if (left > right) continue;

            LocalDateTime lTime = LocalDateTime.parse(String.valueOf(left),fmt);
            LocalDateTime rTime = LocalDateTime.parse(String.valueOf(right),fmt);

            for (;lTime.compareTo(rTime) <= 0;){
                System.out.println(lTime.format(fmt) + "," + kpi);

                lTime = lTime.plusMinutes(1);
            }
            found = true;

        }

        if (!found) {
            System.out.println(-1);
        }
    }
}
