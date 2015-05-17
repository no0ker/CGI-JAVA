package myApp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class JsonHelper {
    public static JSONObject parse(String fileName) throws JSONException {
        String file = ResourceHelper.getRes(fileName);
        return new JSONObject(file);
    }

    public static Map<String, List<String>> getQuestions(String fileName) throws JSONException {
        Map<String, List<String>> test = new HashMap<String, List<String>>();

        JSONObject a = JsonHelper.parse(fileName);
        JSONArray array = a.getJSONArray("questions");
        for (int i = 0; i < array.length(); i++) {
            JSONArray answers = array.getJSONObject(i).getJSONArray("answers");
            List<String> questions = new LinkedList<String>();
            for (int j = 0; j < answers.length(); j++) {
                questions.add(answers.getJSONObject(j).get("text").toString());
            }
            test.put(array.getJSONObject(i).get("question").toString(), questions);
        }

        return test;
    }

    public static Integer calc(String fileName, Map<Integer, Integer> userAnswers) throws JSONException {
        Integer result = 0;
        JSONObject a = JsonHelper.parse(fileName);
        JSONArray array = a.getJSONArray("questions");
        for (int i = 0; i < array.length(); i++) {
            JSONArray answers = array.getJSONObject(i).getJSONArray("answers");
            if (userAnswers.containsKey(i)) {
                String tmp = answers.getJSONObject(userAnswers.get(i)).get("weight").toString();
                result += Integer.valueOf(tmp);
            }
        }

        return result;
    }

    public static String getText(String fileName, Integer count) throws JSONException {
        JSONObject a = JsonHelper.parse(fileName);
        JSONArray array = a.getJSONArray("texts");
        for(int i = 0; i < array.length(); ++i){
            JSONObject t = array.getJSONObject(i);
            if(count <= (Integer)t.get("upTreshold")){
                return t.get("text").toString();
            }
        }
        return "";
    }
}
