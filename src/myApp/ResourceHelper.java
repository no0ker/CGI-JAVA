package myApp;

import java.util.Scanner;

public class ResourceHelper {
    public static String getRes (String fileName){
//        Scanner scan = new Scanner(ResourceHelper.class.getResourceAsStream(fileName),"UTF-8");
        Scanner scan = new Scanner(ClassLoader.getSystemResourceAsStream(fileName),"UTF-8");
        StringBuilder sb = new StringBuilder();
        while (scan.hasNext()) {
            sb.append(scan.nextLine());
            sb.append('\n');
        }
        return sb.toString();
    }
}
