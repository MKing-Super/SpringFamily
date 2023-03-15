package pers.mk.opspace.java.io;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @describe: TODO
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.java.io
 * @Date 2023/3/15 13:54
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {

        Map<String, String> stringStringMap = readCsvByBufferedReaderBidCountMap();
    }

    public static Map<String,String> readCsvByBufferedReaderBidCountMap(){
        File csv = new File("D:/20230315test.csv");
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
            FileWriter w = new FileWriter("D:/20230315result.csv");
            BufferedWriter bw = new BufferedWriter(w);
            bw.write("\"parameter\"," +
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
                    arr1[arr1.length - 1] = arr1[arr1.length - 1].split("\"")[0];
                    for (int n = 0 ; n < arr0.length ; n++){
                        records.put(arr0[n],arr1[n]);
                    }
                    String create_time = records.get("create_time") == null ? "" : records.get("create_time").split(" ")[0];
                    String requestStr = "http://127.0.0.1:80/ai/evalution/exact?" +
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
                    JSONObject jsonObject = JSON.parseObject(result);
                    JSONObject b2bPrice = jsonObject.getJSONObject("b2bAreaExactPirce");
                    String priceUpper = b2bPrice.get("priceUpper").toString();
                    String priceLow = b2bPrice.get("priceLow").toString();
                    String price = b2bPrice.get("price").toString();
                    Double old_b2b_price = Double.valueOf(records.get("old_b2b_price"));
                    Double old_b2b_price_lower = Double.valueOf(records.get("old_b2b_price_lower"));
                    Double old_b2b_price_upper = Double.valueOf(records.get("old_b2b_price_upper"));
                    double final_price = Double.valueOf(records.get("final_price")) / 10000;

                    bw.newLine();
                    bw.write("\""+requestStr + "\",\"" +
                            price + " \",\"" +
                            priceLow + " \",\"" +
                            priceUpper + " \",\"" +
                            old_b2b_price + " \",\"" +
                            old_b2b_price_lower + " \",\"" +
                            old_b2b_price_upper + " \",\"" +
                            final_price + "\"");
                    records = new HashMap<>();
                }
                i++;
            }
            bw.close();

            System.out.println("end <<<<<<<<<<<<<<<<");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }
        return records;
    }
}
