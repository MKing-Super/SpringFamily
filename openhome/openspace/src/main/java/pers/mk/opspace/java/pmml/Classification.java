package pers.mk.opspace.java.pmml;


import org.dmg.pmml.FieldName;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @describe: java实现调用pmml文件
 * @Author MK
 * @PackageName openhome
 * @Package pers.mk.opspace.java.pmml
 * @Date 2023/4/6 15:20
 * @Version 1.0
 */
public class Classification {
    public static void main(String[] args) throws Exception {
        //模型路径
        String pathxml = System.getProperty("user.dir") + "/model/xgboost.pmml";
        //传入模型特征数据
        Map<String, Double> map = new HashMap<String, Double>();
        map.put("x1", 5.1);
        map.put("x2", 3.5);
        map.put("x3", 0.4);
        map.put("x4", 0.2);
        //模型预测
        predictLrHeart(map, pathxml);
    }

    public static void predictLrHeart(Map<String, Double> input, String pathxml) throws Exception {
        PMML pmml;
        File file = new File(pathxml);
        InputStream inputStream = new FileInputStream(file);
        try (InputStream is = inputStream) {
            pmml = org.jpmml.model.PMMLUtil.unmarshal(is);

            ModelEvaluatorFactory modelEvaluatorFactory = ModelEvaluatorFactory.newInstance();
            ModelEvaluator<?> modelEvaluator = modelEvaluatorFactory.newModelEvaluator(pmml);
            Evaluator evaluator = (Evaluator) modelEvaluator;

//            getFieldMap
            List<InputField> inputFields = evaluator.getInputFields();
            Map<FieldName, FieldValue> argements = new LinkedHashMap<>();
            for (InputField inputField : inputFields) {
                FieldName inputFieldName = inputField.getName();
                Object raeValue = input.get(inputFieldName.getValue());
                FieldValue inputFieldValue = inputField.prepare(raeValue);
                argements.put(inputFieldName, inputFieldValue);
            }

//            evaluate
            Map<FieldName, ?> results = evaluator.evaluate(argements);
            List<TargetField> targetFields = evaluator.getTargetFields();
            for (TargetField targetField : targetFields) {
                FieldName targetFieldName = targetField.getName();
                Object targetFieldValue = results.get(targetFieldName);
//                System.out.println("target: " + targetFieldName.getValue());
                System.out.println(targetFieldValue);
            }
        }
    }
}


