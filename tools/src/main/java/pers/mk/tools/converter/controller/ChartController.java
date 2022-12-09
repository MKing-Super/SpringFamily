package pers.mk.tools.converter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@Controller
@RequestMapping("/chart")
public class ChartController {

    private static BigDecimal init = new BigDecimal("5500");
    private static BigDecimal upPrice = BigDecimal.ZERO;
    private static BigDecimal downPrice = BigDecimal.ZERO;;
    private static BigDecimal totalProfit = BigDecimal.ZERO;

    @RequestMapping("/index")
    public String index(){
        return "/chart/index";
    }

    @RequestMapping("/index000")
    public String index000(){
        return "/chart/index000";
    }

    @RequestMapping("/getData")
    @ResponseBody
    public Map<String,Object> getData(){
        BigDecimal range = this.obtainC();
        BigDecimal multiply = null;
        if (range.compareTo(BigDecimal.ZERO) > 0){
            BigDecimal abs = range.abs();
            BigDecimal up = init.multiply(abs);
            multiply = init.add(up).setScale(0, RoundingMode.DOWN);
        }else {
            BigDecimal abs = range.abs();
            BigDecimal down = init.multiply(abs, MathContext.UNLIMITED);
            multiply = init.subtract(down).setScale(0, RoundingMode.DOWN);
        }
        init = multiply;
        HashMap<String, Object> map = new HashMap<>();
        map.put("nowPrice",init.intValue());
        map.put("upProfit",upPrice.equals(BigDecimal.ZERO)?null:init.subtract(upPrice));
        map.put("downProfit",downPrice.equals(BigDecimal.ZERO)?null:downPrice.subtract(init));
        map.put("totalProfit",totalProfit);
        return map;
    }

    @RequestMapping("/up")
    @ResponseBody
    public Integer up(Integer price){
        if (upPrice.equals(BigDecimal.ZERO)){
            upPrice = new BigDecimal(price);
        }else {
            totalProfit = totalProfit.add(new BigDecimal(init.subtract(upPrice).intValue()));
            upPrice = BigDecimal.ZERO;
        }
        return price;
    }

    @RequestMapping("/down")
    @ResponseBody
    public Integer down(Integer price){
        if (downPrice.equals(BigDecimal.ZERO)){
            downPrice = new BigDecimal(price);
        }else {
            totalProfit = totalProfit.add(new BigDecimal(downPrice.subtract(init).intValue()));
            downPrice = BigDecimal.ZERO;
        }
        return price;
    }



    private BigDecimal recursion(List<BigDecimal> cs, Integer n){
        BigDecimal fz1 = cs.get(n).multiply(new BigDecimal(2));
        if (n == 0){
            return new BigDecimal(0);
        }
        BigDecimal fz2 = new BigDecimal(n - 1).multiply(this.recursion(cs, n - 1));
        BigDecimal fm = new BigDecimal(n + 1);
        BigDecimal fz = fz1.add(fz2);
        BigDecimal divide = fz.divide(fm,2, RoundingMode.HALF_UP);
        return divide;
    }

    private BigDecimal obtainC(){
        Random r = new Random();
        int t = r.nextInt(10) + 1;
        //获取随机数
        int i = r.nextInt(t*100);
        int symbol = r.nextInt(100) + 1;
        BigDecimal c = new BigDecimal(i).divide(new BigDecimal(10000),4,RoundingMode.HALF_UP);
        BigDecimal multiply = c.multiply(new BigDecimal(symbol > 49 ? 1 : -1));
        return multiply;
    }
}
