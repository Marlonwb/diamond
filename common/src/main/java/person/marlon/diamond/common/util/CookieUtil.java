package person.marlon.diamond.common.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class CookieUtil {

    /**
     * 取得cookie值，经过编码转换
     *
     * @param request
     * @param key
     * @param encode
     * @return
     */
    public static String getCookieValue(HttpServletRequest request, String key, String encode) {
        if (encode == null || "".equals(encode.trim())) {
            encode = StandardCharsets.UTF_8.name(); // 默认"UTF-8"
        }
        String val = getRawCookieValue(request, key);
        if (val == null) return null;
        try {
            return URLDecoder.decode(val, encode);
        } catch (UnsupportedEncodingException e) {
//            log.error("decode cookie value failed, val=" + val, e);
            return val;
        }
    }

    /**
     * 获取未做编码转换的cookie原始值
     */
    private static String getRawCookieValue(HttpServletRequest request, String key) {
        Cookie sCookie = getCookie(request, key);
        if (sCookie == null) return null;
        return sCookie.getValue();
    }

    /**
     * 获取对应key的Cookie对象
     */
    public static Cookie getCookie(HttpServletRequest request, String key) {
        Cookie cookies[] = request.getCookies();
        if (cookies != null && null != key) {
            for (Cookie cookie : cookies) {
                if (key.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 设置为负值，为浏览器进程Cookie(内存中保存)，关闭浏览器就失效 设置session时需要
     */
    public static void setExpiredCookie(HttpServletResponse response, String name, String value) {
        setCookie(response, name, value, -1);
    }

    /**
     * 设置Cookie参数
     */
    public static void setCookie(HttpServletResponse response, String name, String value, int expiry) {
        Cookie _cookie = createCookie(name, value);
        _cookie.setMaxAge(expiry);// how many seconds
        _cookie.setPath("/");
        response.addCookie(_cookie);
    }

    public static void setCookieWithPath(HttpServletResponse response, String name, String value, int expiry, String domain,
                                 String path) {
        Cookie _cookie = createCookie(name, value);
        _cookie.setMaxAge(expiry);// how many seconds
        if(!StringUtils.isEmpty(domain)){
            _cookie.setDomain(domain);
        }
        _cookie.setPath(path);
        response.addCookie(_cookie);
    }

    public static void setSecureCookie(HttpServletResponse response, String name, String value, int expiry,
                                       String domain, String path) {
        Cookie _cookie = createCookie(name, value);
        _cookie.setMaxAge(expiry);// how many seconds
        if(!StringUtils.isEmpty(domain))
            _cookie.setDomain(domain);
        _cookie.setPath(path);
        _cookie.setSecure(true);
        response.addCookie(_cookie);
    }

    /**
     * 生成新Cookie对象
     */
    private static Cookie createCookie(String name, String value) {
        Cookie _cookie;
        try {
            _cookie = new Cookie(name, URLEncoder.encode(value, StandardCharsets.UTF_8.name()));
        } catch (UnsupportedEncodingException e) {
            _cookie = new Cookie(name, value);
//            log.error("decode cookie value failed, value=" + value, e);
        }
        return _cookie;
    }
}
