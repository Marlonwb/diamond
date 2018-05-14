import person.marlon.diamond.common.util.JSONUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Lenovo on 2017/4/18.
 *
 */
public class JsonTest {
    public static void main(String[] args) {
        Map<String, String> paramMap = new ConcurrentHashMap<>();
        paramMap.put("confSerialNum", "11");
        paramMap.put("inviteeEmail", "11");
        paramMap.put("userName", "11");
        paramMap.put("country", "11");
        //支持注册定制化，加所有参数的json参数
        String infoJsonPattern = JSONUtil.object2JSON(paramMap);
        System.out.println("infoJsonPattern = " + infoJsonPattern);
        paramMap.put("info",infoJsonPattern);
        System.out.println("paramMap = " + paramMap);

    }
}
