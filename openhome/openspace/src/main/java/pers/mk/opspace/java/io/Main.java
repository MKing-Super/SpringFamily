package pers.mk.opspace.java.io;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import pers.mk.opspace.annotation.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Description: TODO
 * @Author: kun.ma
 * @Date: 2023/2/2 9:53
 */
public class Main {
    public static void main(String[] args) {
        Date now = new Date();
        LoginInfo info = new LoginInfo();
        info.setMemberSid("00007c7558694feb8cde0786cc11ddda");
        info.setAppName("竞拍app");
        info.setSimultaneousLoginNum(2);
        info.setEquipmentList(
                Arrays.asList(
                        new EquipmentInfo("iphone","硬件地址0",73247123439157L),
                        new EquipmentInfo("xiaomi","硬件地址1",29472134723943924L)
                )
        );
        info.setTakingEffectTime(now.getTime());
        info.setEffectiveDate(DateUtil.offsetDay(now,30).getTime());
        info.setNewEquipmentEmpower(true);

        String s = JSON.toJSONString(info);
        System.out.println(s);

    }

}

class EquipmentInfo  implements Serializable{
    private static final long serialVersionUID = -2316026287700588641L;

    //设备名称
    private String name;

    //设备硬件唯一编码
    private String onlyCode;

    //最后一次使用设备的时间戳
    private Long lastTimestamp;

    public EquipmentInfo(String name, String onlyCode, Long lastTimestamp) {
        this.name = name;
        this.onlyCode = onlyCode;
        this.lastTimestamp = lastTimestamp;
    }

    public Long getLastTimestamp() {
        return lastTimestamp;
    }

    public void setLastTimestamp(Long lastTimestamp) {
        this.lastTimestamp = lastTimestamp;
    }

    public EquipmentInfo(String name, String onlyCode) {
        this.name = name;
        this.onlyCode = onlyCode;
    }

    public EquipmentInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOnlyCode() {
        return onlyCode;
    }

    public void setOnlyCode(String onlyCode) {
        this.onlyCode = onlyCode;
    }
}


class LoginInfo implements Serializable {
    private static final long serialVersionUID = 310301691720518897L;

    //会员sid
    private String memberSid;

    //app名称
    private String appName;

    //同时登录数
    private Integer simultaneousLoginNum;

    //设备信息详情
    private List<EquipmentInfo> equipmentList;

    //生效日期（开始时间）
    private Long takingEffectTime;

    //有效日期（结束时间）
    private Long effectiveDate;

    //新设备是否授权
    private Boolean isNewEquipmentEmpower;




    public String getMemberSid() {
        return memberSid;
    }

    public void setMemberSid(String memberSid) {
        this.memberSid = memberSid;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Integer getSimultaneousLoginNum() {
        return simultaneousLoginNum;
    }

    public void setSimultaneousLoginNum(Integer simultaneousLoginNum) {
        this.simultaneousLoginNum = simultaneousLoginNum;
    }

    public List<EquipmentInfo> getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(List<EquipmentInfo> equipmentList) {
        this.equipmentList = equipmentList;
    }

    public Boolean getNewEquipmentEmpower() {
        return isNewEquipmentEmpower;
    }

    public void setNewEquipmentEmpower(Boolean newEquipmentEmpower) {
        isNewEquipmentEmpower = newEquipmentEmpower;
    }

    public Long getTakingEffectTime() {
        return takingEffectTime;
    }

    public void setTakingEffectTime(Long takingEffectTime) {
        this.takingEffectTime = takingEffectTime;
    }

    public Long getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Long effectiveDate) {
        this.effectiveDate = effectiveDate;
    }
}
