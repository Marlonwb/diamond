package person.marlon.diamond.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;

public class WebUtil {

    private static Logger logger = LoggerFactory.getLogger(WebUtil.class);

    private static final String ABSOLUTE_REQUEST_DOMAIN = "absoluteDomain";


    /**
     * 获取访问IP
     *
     * @param request client request
     * @return ip
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forwarded-For");
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_CLIENT_IP");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("x-Forward-for");
        }
        if (StringUtils.isEmpty(ipAddress) || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ipAddress = inet.getHostAddress();
                } catch (Exception e) {
                    logger.error("get ip address error", e);
                }
            }

        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) {
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }

        return ipAddress;
    }

    /**
     * consider that if proxy exists,need the proxy provide an extra header filed to specify the scheme or port
     * because the real port or scheme can be changed sometimes.
     * just like:String port = request.getHeader("X-Forwarded-Port");
     */
    public static String getRequestPort(HttpServletRequest request){
        //String port = String.valueOf(request.getServerPort());
        String port = request.getHeader("X-Forwarded-Port");
        return StringUtils.isNotEmpty(port) ? port : "";
    }

    /**
     * see also {getRequestPort} method annotation
     * String scheme = request.getHeader("X-Forwarded-Proto");
     */
    public static String getRequestScheme(HttpServletRequest request){
        //String scheme = request.getScheme();
        String scheme = request.getHeader("X-Forwarded-Proto");
        return StringUtils.isNotEmpty(scheme) ? scheme : "";
    }

    /**
     * return the full path of the server domain with scheme and port
     * (of course if the request doesn't exist,will not return either)
     */
    public static String getAbsoluteRequestDomain(HttpServletRequest request){
        return (String)request.getAttribute(WebUtil.ABSOLUTE_REQUEST_DOMAIN);
    }

    public static void setAbsoluteRequestDomain(HttpServletRequest request,String requestServerName){
        //set http request domain with port
        String absoluteUrl;
        String scheme = WebUtil.getRequestScheme(request);
        absoluteUrl = StringUtils.isEmpty(scheme) ? requestServerName : scheme + "://" + requestServerName;
        String port = WebUtil.getRequestPort(request);
        absoluteUrl = StringUtils.isEmpty(port) || "80".equals(port) || "443".equals(port) ? absoluteUrl :absoluteUrl + ":" + port;
        //为了防止链接会多带斜杠（拼接接口时会就会带上斜杠，无需多带，/rtc/dispatch）
        if(absoluteUrl.endsWith("/")){
            absoluteUrl = absoluteUrl.substring(0,absoluteUrl.length()-1);
        }
        request.setAttribute(WebUtil.ABSOLUTE_REQUEST_DOMAIN,absoluteUrl);
    }

    /**
     *  return the request server domain without scheme or port.
     */
    public static String getCurrentDomain(String absoluteDomain){
        if (StringUtils.isEmpty(absoluteDomain)) {
            return "";
        }
        if (absoluteDomain.indexOf("//") != -1) {
            absoluteDomain = absoluteDomain.split("//")[1];
        }
        if (absoluteDomain.indexOf(":") != -1) {
            absoluteDomain = absoluteDomain.split(":")[0];
        }
        return absoluteDomain;
    }

    /**
     * 返回重定向链接
     * @param absoluteDomain 参数为空，则直接返回相对路径
     * @param relativeUri 业务接口路径，带不带"/"均可
     * @return redirect:path
     */
    public static String getRedirectUri(String absoluteDomain,String relativeUri){
        if(StringUtils.isEmpty(relativeUri)){
            relativeUri = "";
        }else if(!relativeUri.startsWith("/")){
            relativeUri = "/" + relativeUri;
        }
        return StringUtils.isEmpty(absoluteDomain) ? "redirect:" + relativeUri : "redirect:" + absoluteDomain + relativeUri;
    }

}
