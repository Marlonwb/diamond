import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Lenovo on 2017/4/18.
 */
public class Minustest {
    public static void main(String[] args) {
        String s = "<iframe src=https://www.facebook.com/plugins/video.php?href=https%3A%2F%2Fwww.facebook.com%2Fmarlon.wang.902%2Fvideos%2F125585291327828%2F&width=400\"\n" +
                " width=\"400\" height=\"400\" style=\"border:none;overflow:hidden\" scrolling=\"no\" frameborder=\"0\" allowTransparency=\"true\" allowFullScreen=\"true\">\n" +
                "</iframe>";
//        Pattern FACEBOOK_SHARE_URL = Pattern.compile("(?<shareURL>src=\"\\S\\s(?=width))");
        String[] strarry = s.split(" ");
        String src ="";
        for (int i=0; i < strarry.length; i++){
            if (strarry[i].startsWith("src=http")){
                src = strarry[i].substring(4);
                break;
            }
        }

        if (src.indexOf("\"") != -1){
            src = src.substring(0, src.indexOf("\""));
        }

//        Matcher matcher = FACEBOOK_SHARE_URL.matcher(s);
//        String shareURL = matcher.group("shareURL");
        System.out.println("shareURL = " + src);
    }
}
