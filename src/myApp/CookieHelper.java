package myApp;

import org.apache.commons.lang3.StringUtils;

public class CookieHelper {
    public static String getText(String httpCookie) {
        Integer count = parse(httpCookie);
        if(count != null) {
            return JsonHelper.getText(Resources.testFileName, count);
        }
        else return Resources.emptyResult;
    }

    public static Integer parse(String httpCookie){
        if(httpCookie == null)
            return null;
        for(String split : StringUtils.split(httpCookie,";")){
            String row[] = StringUtils.split(split, "=");
            if(row.length < 2)
                return null;
            if(row[0].trim().equals("count")){
                try {
                    return Integer.valueOf(row[1]);
                } catch (NumberFormatException e){
                    return null;
                }
            }
        }
        return null;
    }
}
