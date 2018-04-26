package person.marlon.diamond.demo;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * Created by Lenovo on 2017/5/26.
 */
public class test {
    public static void main(String[] args) {
        try {
            DateFormat dateFormat =  new SimpleDateFormat("MMM dd, yyyy hh:mma", new Locale("zh"));
            dateFormat.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            Date aa =dateFormat.parse("五月 26, 2017 09:10上午");
            String format = dateFormat.format(aa);
            System.out.println("format = " + format);
            System.out.println("aa = " + aa);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
