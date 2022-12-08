package pers.mk.tools.converter;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class ToolsApplicationTests {

    @Test
    void contextLoads() {
        BigDecimal c = new BigDecimal(2);
        List<BigDecimal> cs = new ArrayList<>();
        cs.add(new BigDecimal("0"));
        cs.add(new BigDecimal(5572));
        cs.add(new BigDecimal(5501));
        cs.add(new BigDecimal(5561));
        cs.add(new BigDecimal(5541));
        cs.add(new BigDecimal(5531));
        BigDecimal recursion = recursion(cs, 5);
        System.out.println(recursion);
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

}
