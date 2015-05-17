package myApp;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParserAnswer {
    public static Map<Integer, Integer> parse(String answer){
        Map<Integer, Integer> result = new HashMap<Integer, Integer>();
        List<String> splitAnswer = Arrays.asList(StringUtils.split(answer, "&"));
        for(String i : splitAnswer){
            String[] tmp = StringUtils.split(i, "=");
            Integer answerNo = Integer.valueOf(tmp[1]);
            Integer quesNo = Integer.valueOf(StringUtils.split(tmp[0], "_")[1]);
            result.put(quesNo, answerNo);
        }
        return result;
    }
}
