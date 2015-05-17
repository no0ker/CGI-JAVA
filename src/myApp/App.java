package myApp;


import org.json.JSONException;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {

    static final String blankResp = ResourceHelper.getRes("html/blank_resp.txt");
    static final String blankQuess = ResourceHelper.getRes("html/blank_ques.txt");
    static final String blankForm = ResourceHelper.getRes("html/blank_form.txt");
    static final String blankReport = ResourceHelper.getRes("html/blank_report.txt");


    public static String getMethod() {
        Map<String, List<String>> questions = JsonHelper.getQuestions(Resources.testFileName);

        int quesNo = 0;
        StringBuilder quesSb = new StringBuilder();
        for (Map.Entry<String, List<String>> i : questions.entrySet()) {
            int respNo = 0;
            StringBuilder respSb = new StringBuilder();
            for (String j : i.getValue()) {
                respSb.append(MessageFormat.format(blankResp, quesNo, respNo, j));
                ++respNo;
            }
            quesSb.append(MessageFormat.format(blankQuess, i.getKey(), respSb.toString()));
            ++quesNo;
        }

        return MessageFormat.format(blankForm, quesSb.toString(), CookieHelper.getText(System.getenv("HTTP_COOKIE")));

    }

    public static String postMethod() {
        StringBuilder repSb = new StringBuilder();

        StringBuilder userAnswers = new StringBuilder();
        Scanner s = new Scanner(System.in);
        while (s.hasNextLine()) {
            userAnswers.append(s.nextLine());
        }
        Map<Integer, Integer> userAnswersNo = ParserAnswer.parse(userAnswers.toString());
        Integer count = JsonHelper.calc(Resources.testFileName, userAnswersNo);
        String text = JsonHelper.getText(Resources.testFileName, count);

        repSb.append(text);

        return MessageFormat.format(blankReport,
                MessageFormat.format(Resources.setCookie, "count = " + count),
                repSb.toString());
    }


    public static void main(String[] args) throws JSONException {
        String result;

        if ("POST".equals(System.getenv("REQUEST_METHOD"))) {
            result = postMethod();
        } else {
            result = getMethod();
        }

        System.out.println(result);
    }

}
