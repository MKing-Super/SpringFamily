package pers.mk.tools.converter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
class ToolsApplicationTests {

    @Test
    void contextLoads() {
        List<BigDecimal> cs = new ArrayList<>();
        ArrayList<BigDecimal> rangeList = new ArrayList<>();
        BigDecimal init = new BigDecimal("5500");
        cs.add(init);
        for (int i = 0 ; i < 5 ; i++){
            BigDecimal range = this.obtainC();
            rangeList.add(range);
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
            cs.add(multiply);
            init = multiply;
        }
        BigDecimal recursion12 = recursion(cs, 5);
        for (int i = 0 ; i < 5 ; i++){
            BigDecimal range = this.obtainC();
            rangeList.add(range);
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
            cs.add(multiply);
            init = multiply;
        }
        BigDecimal recursion = recursion(cs, 10);
        System.out.println(recursion12);
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
        int t1 = r.nextInt(3) + 1;
        int t = r.nextInt(t1) + 1;
        //获取随机数
        int i = r.nextInt(t*100);
        int symbol = r.nextInt(2);
        BigDecimal c = new BigDecimal(i).divide(new BigDecimal(10000),4,RoundingMode.HALF_UP);
        BigDecimal multiply = c.multiply(new BigDecimal(symbol == 0 ? -1 : 1));
        return multiply;
    }

}
