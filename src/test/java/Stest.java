import org.junit.Test;
import utils.MailUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;


public class Stest {
    @Test
    public void test() throws Exception {
        Calendar calendar = Calendar.getInstance();
        System.out.println(calendar.get(Calendar.DAY_OF_WEEK)-1);

    }
}
