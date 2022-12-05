package pers.mk.tools.converter.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class FuturesRecord implements Serializable {
    private static final long serialVersionUID = 564240519045960275L;

    private Integer id;
    private String futuresCode;
    private String futuresVariety;
    private BigDecimal startPrice;
    private BigDecimal endPrice;
    private BigDecimal maxPrice;
    private BigDecimal minPrice;
    private String judgeIndexs;
    private Date orderTime;
    private String orderNumber;
    private String orderReason;
    private Integer ma;
    private String maRemarks;
    private Integer ema;
    private String emaRemarks;
    private Integer macd;
    private String macdRemarks;
    private Integer rsi;
    private String rsiRemarks;
    private Integer wr;
    private String wrRemarks;
    private Integer kd;
    private String kdRemarks;
    private Integer fundamentals;
    private String fundamentalsUrl;
    private String fundamentalsContent;
    private Date createTime;
    private Date modifyTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFuturesCode() {
        return futuresCode;
    }

    public void setFuturesCode(String futuresCode) {
        this.futuresCode = futuresCode;
    }

    public String getFuturesVariety() {
        return futuresVariety;
    }

    public void setFuturesVariety(String futuresVariety) {
        this.futuresVariety = futuresVariety;
    }

    public BigDecimal getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(BigDecimal startPrice) {
        this.startPrice = startPrice;
    }

    public BigDecimal getEndPrice() {
        return endPrice;
    }

    public void setEndPrice(BigDecimal endPrice) {
        this.endPrice = endPrice;
    }

    public BigDecimal getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(BigDecimal maxPrice) {
        this.maxPrice = maxPrice;
    }

    public BigDecimal getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(BigDecimal minPrice) {
        this.minPrice = minPrice;
    }

    public String getJudgeIndexs() {
        return judgeIndexs;
    }

    public void setJudgeIndexs(String judgeIndexs) {
        this.judgeIndexs = judgeIndexs;
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderReason() {
        return orderReason;
    }

    public void setOrderReason(String orderReason) {
        this.orderReason = orderReason;
    }

    public Integer getMa() {
        return ma;
    }

    public void setMa(Integer ma) {
        this.ma = ma;
    }

    public String getMaRemarks() {
        return maRemarks;
    }

    public void setMaRemarks(String maRemarks) {
        this.maRemarks = maRemarks;
    }

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

    public Integer getWr() {
        return wr;
    }

    public void setWr(Integer wr) {
        this.wr = wr;
    }

    public String getWrRemarks() {
        return wrRemarks;
    }

    public void setWrRemarks(String wrRemarks) {
        this.wrRemarks = wrRemarks;
    }

    public Integer getKd() {
        return kd;
    }

    public void setKd(Integer kd) {
        this.kd = kd;
    }

    public String getKdRemarks() {
        return kdRemarks;
    }

    public void setKdRemarks(String kdRemarks) {
        this.kdRemarks = kdRemarks;
    }

    public Integer getFundamentals() {
        return fundamentals;
    }

    public void setFundamentals(Integer fundamentals) {
        this.fundamentals = fundamentals;
    }

    public String getFundamentalsUrl() {
        return fundamentalsUrl;
    }

    public void setFundamentalsUrl(String fundamentalsUrl) {
        this.fundamentalsUrl = fundamentalsUrl;
    }

    public String getFundamentalsContent() {
        return fundamentalsContent;
    }

    public void setFundamentalsContent(String fundamentalsContent) {
        this.fundamentalsContent = fundamentalsContent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }
}
