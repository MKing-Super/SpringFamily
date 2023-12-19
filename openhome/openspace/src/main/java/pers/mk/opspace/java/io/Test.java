package pers.mk.opspace.java.io;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @describe: 估值

        SELECT
        avi.model_id,
        avi.reg_date,
        avi.mile,
        avi.register_province,
        avi.register_city,
        avi.level_id,
        avi.color,
        avi.star_skeleton,
        avi.star_interior,
        avi.star_facade,
        avi.star_condition,
        avi.star_electric,
        avi.using_model,
        avi.transfer_number,
        avi.owner_current,
        avi.vehicle_ppe,
        avi.vehicle_src_type,
        avi.vehicle_src_id,
        avi.auction_province,
        avi.auction_city,
        avi.produced_years,
        avi.auction_times,
        avi.car_location,
        avi.vehicle_margin,
        avi.condition_level,
        avi.star_prod,
        avi.damage_cn,
        avi.star_type_sum,
        avi.create_time,


        avi.b2b_price AS old_b2b_price,
        avi.b2b_price_lower AS old_b2b_price_lower,
        avi.b2b_price_upper AS old_b2b_price_upper,
        oi.final_price,
        avi.level_id,
        vb.vehicle_id
        FROM
        autost_vas.valuation_business vb
        INNER JOIN autost_vas.accurate_valuation_interface avi ON vb.valuation_interface_id = avi.id
        INNER JOIN autost_auction.vehicle v ON v.id = vb.vehicle_id
        INNER JOIN autost_auction.order_item oi ON oi.vehicle_id = v.id
        WHERE
        vb.valuation_type = 2
        AND v.oil_type = '电力'
        AND oi.deal_confirm_status = 2
        AND avi.model_id IS NOT NULL
        AND avi.create_time >= '2023-10-01 00:00:00'

 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.java.io
 * @Date 2023/3/15 13:54
 * @Version 1.0
 */
public class Test {

    private static String inPath = "D:/mk.csv";
    private static String outPath = "D:/mk-(result).csv";
    private static Integer errorTotal = 0;


    public static void main(String[] args) {
        readCsvByBufferedReaderBidCountMap();
    }

    public static Map<String,String> readCsvByBufferedReaderBidCountMap(){
        long startTime = System.currentTimeMillis();
        File csv = new File(inPath);
        csv.setReadable(true);
        csv.setWritable(true);
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            isr = new InputStreamReader(new FileInputStream(csv), "UTF-8");
            br = new BufferedReader(isr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String line = "";
        Map<String, String> records = new HashMap<>();
        try {
            OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(outPath), StandardCharsets.UTF_8);
            BufferedWriter bw=new BufferedWriter(write);
            bw.write("\"parameter\"," +
                    "\"level_id\"," +
                    "\"vehicle_id\"," +
                    "\"price\"," +
                    "\"priceLow\"," +
                    "\"priceUpper\"," +
                    "\"old_price\"," +
                    "\"old_priceLow\"," +
                    "\"old_priceUpper\"," +
                    "\"deal_price\"");
            int i = 0;
            String[] arr0 = new String[0];
            String[] arr1 = new String[0];
            while ((line = br.readLine()) != null) {
                if (i == 0){
                    arr0 = line.split("\",\"");
                    arr0[0] = arr0[0].split("\"")[1];
                    arr0[arr0.length - 1] = arr0[arr0.length - 1].split("\"")[0];
                }else {
                    arr1 = line.split("\",\"");
                    arr1[0] = arr1[0].split("\"")[1];
//                    arr1[arr1.length - 1] = arr1[arr1.length - 1].split("\"")[0];
                    for (int n = 0 ; n < arr0.length ; n++){
                        records.put(arr0[n],arr1[n]);
                    }
                    String create_time = records.get("create_time") == null ? "" : records.get("create_time").split(" ")[0];
                    String requestStr = "http://gz.autostreets.com:8080" +
                            "/ai/evalution/exact?" +
                            "modelID=" + records.get("model_id") +
                            "&predictType=2" +
                            "&regDate=" + records.get("reg_date") +
                            "&mile=" + records.get("mile") +
                            "&regProvince=" + records.get("register_province") +
                            "&regCity=" + records.get("register_city") +
                            "&levelID=" + records.get("level_id") +
                            "&color=" + records.get("color") +
                            "&starSkeleton=" + records.get("star_skeleton") +
                            "&starInterior=" + records.get("star_interior") +
                            "&starFacade=" + records.get("star_facade") +
                            "&starCondition=" + records.get("star_condition") +
                            "&starElectric=" + records.get("star_electric") +
                            "&usingModel=" + records.get("using_model") +
                            "&transferNumber=" + records.get("transfer_number") +
                            "&ownerCurrent=" + records.get("owner_current") +
                            "&vehiclePpe=" + records.get("vehicle_ppe") +
                            "&vehicleSrcType=" + records.get("vehicle_src_type") +
                            "&vehicleSrcId=" + records.get("vehicle_src_id") +
                            "&auctionProvince=" + records.get("auction_province") +
                            "&auctionCity=" + records.get("auction_city") +
                            "&producedYears=" + records.get("produced_years") +
                            "&auctionTimes=" + records.get("auction_times") +
                            "&carLocation=" + records.get("car_location") +
                            "&vehicleMargin=" + records.get("vehicle_margin") +
                            "&conditionLevel=" + records.get("condition_level") +
                            "&createDate=" + create_time +
                            "&starProd=" + records.get("star_prod") +
                            "&damageCn=" + records.get("damage_cn") +
                            "&starTypeSum=" + records.get("star_type_sum");
                    String result = HttpUtil.get(requestStr);
                    JSONObject jsonObject = null;
                    try {
                        jsonObject = JSON.parseObject(result);
                    }catch (Exception e){
                        System.out.println(result);
                        errorTotal++;
                        System.out.println("errorTotal => " + errorTotal);
                        String s = "\"" + requestStr + "\",\"" +
                                "" + " \",\"" +
                                "" + " \",\"" +
                                "" + " \",\"" +
                                "" + " \",\"" +
                                "" + " \",\"" +
                                "" + " \",\"" +
                                "" + " \",\"" +
                                "" + " \",\"" +
                                "" + " \",\"" +
                                "result" + "\"";
                        bw.newLine();
                        bw.write(s);
                        records = new HashMap<>();
                        continue;
                    }
                    JSONObject b2bPrice = jsonObject.getJSONObject("b2bAreaExactPirce");
                    String priceUpper = b2bPrice.get("priceUpper").toString();
                    String priceLow = b2bPrice.get("priceLow").toString();
                    String price = b2bPrice.get("price").toString();
                    Double old_b2b_price = Double.valueOf(records.get("old_b2b_price"));
                    Double old_b2b_price_lower = Double.valueOf(records.get("old_b2b_price_lower"));
                    Double old_b2b_price_upper = Double.valueOf(records.get("old_b2b_price_upper"));
                    double final_price = Double.valueOf(records.get("final_price")) / 10000;
                    String level_id = records.get("level_id").toString();
                    String vehicle_id = records.get("vehicle_id").toString();

                    String s = "\"" + requestStr + "\",\"" +
                            level_id + " \",\"" +
                            vehicle_id + " \",\"" +
                            price + " \",\"" +
                            priceLow + " \",\"" +
                            priceUpper + " \",\"" +
                            old_b2b_price + " \",\"" +
                            old_b2b_price_lower + " \",\"" +
                            old_b2b_price_upper + " \",\"" +
                            final_price + "\"";

                    bw.newLine();
                    bw.write(s);
                    records = new HashMap<>();
                }
                i++;
                System.out.println(i);
            }
            bw.close();

//            System.out.println("end <<<<<<<<<<<<<<<<");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            long endTime = System.currentTimeMillis();
            long l = endTime - startTime;
//            System.out.println(l);
        }
        return records;
    }
}
