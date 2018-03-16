package person.marlon.diamond.util;

import com.alibaba.fastjson.JSON;

/**
 * json工具类
 *
 * @author Robin 2014年7月14日 上午11:23:21
 */
public class JSONUtil {

    /**
     * 根据一个对象输出为json格式字符串
     *
     * @param value
     * @return
     */
    public static String object2JSON(Object value) {
        String str = JSON.toJSONString(value);
        return str;
    }

    /**
     * 根据json字符串生成一个java对象
     * @param jsonText
     * @param clazz
     * @return
     */
    public static <T> T json2Object(String jsonText,Class<T> clazz) {
        return JSON.parseObject(jsonText, clazz);
    }

}

