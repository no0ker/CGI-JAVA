package myApp;

import org.junit.Test;

public class JsonHelperTest{
    @Test
    public void testGetText() throws Exception {
        String a = JsonHelper.getText(Resources.testFileName, 19);
        System.out.println(a);
    }
}