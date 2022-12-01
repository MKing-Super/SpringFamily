package pers.mk.tools.converter.model;

import java.io.Serializable;

/**
 * @Description: TODO
 * @Author: kun.ma
 * @Date: 2022/12/1 20:38
 */
public class Record implements Serializable {

    private Integer ma;
    private String maRemarks;
    private Integer ema;
    private String emaRemarks;
    private Integer macd;
    private String macdRemarks;
    private Integer rsi;
    private String rsiRemarks;


    public Integer getEma() {
        return ema;
    }

    public void setEma(Integer ema) {
        this.ema = ema;
    }

    public String getEmaRemarks() {
        return emaRemarks;
    }

    public void setEmaRemarks(String emaRemarks) {
        this.emaRemarks = emaRemarks;
    }

    public Integer getMacd() {
        return macd;
    }

    public void setMacd(Integer macd) {
        this.macd = macd;
    }

    public String getMacdRemarks() {
        return macdRemarks;
    }

    public void setMacdRemarks(String macdRemarks) {
        this.macdRemarks = macdRemarks;
    }

    public Integer getRsi() {
        return rsi;
    }

    public void setRsi(Integer rsi) {
        this.rsi = rsi;
    }

    public String getRsiRemarks() {
        return rsiRemarks;
    }

    public void setRsiRemarks(String rsiRemarks) {
        this.rsiRemarks = rsiRemarks;
    }

    public String getMaRemarks() {
        return maRemarks;
    }

    public void setMaRemarks(String maRemarks) {
        this.maRemarks = maRemarks;
    }

    public Integer getMa() {
        return ma;
    }

    public void setMa(Integer ma) {
        this.ma = ma;
    }
}
