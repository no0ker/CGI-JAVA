package myApp;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class CookieHelperTest {

    @Test
    public void testParse() throws Exception {
        assertEquals(Integer.valueOf(11), CookieHelper.parse("test=sfsdfdsfdsfsdfdsfdsfsdfsdfsdfsdcookie!; result=?? ????????; count=11;"));
        assertNull(CookieHelper.parse("test=sfsdfdsfdsfsdfdsfdsfsdfsdfsdfsdcookie!; result=?? ????????; count="));
        assertNull(CookieHelper.parse("test=sfsdfdsfdsfsdfdsfdsfsdfsdfsdfsdcookie!; result=?? ????????"));
    }

    @Test
    public void testGetText() throws Exception {
        assertEquals(Resources.emptyResult, CookieHelper.getText("test=sfsdfdsfdsfsdfdsfdsfsdfsdfsdfsdcookie!; result=?? ????????; count="));
        assertEquals(Resources.emptyResult, CookieHelper.getText("test=sfsdfdsfdsfsdfdsfdsfsdfsdfsdfsdcookie!; result=?? ????????; count"));
        assertEquals(Resources.emptyResult, CookieHelper.getText("test=sfsdfdsfdsfsdfdsfdsfsdfsdfsdfsdcookie!; result=?? ????????; count=sfsdf"));
        assertEquals(Resources.emptyResult, CookieHelper.getText(null));
        assertEquals(Resources.emptyResult, CookieHelper.getText("sfsdfdsfdsfdsfdsdsfsdfsdfdsfdsfdsfdsf"));
    }
}