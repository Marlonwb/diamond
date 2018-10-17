package person.marlon.diamond.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import person.marlon.diamond.common.enums.BrowserEnum;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public static boolean checkAllowedBrowser(String browser){
        if(StringUtils.isEmpty(browser)){
            return false;
        }

        String supportList = ResourceUtil.getSupportBrowsers();
        //若是all，表示全部支持
        if ("all".equals(supportList)){
            return true;
        }
        //判断浏览器是否在配置范围
        if (supportList.contains(browser)) {
            return true;
        } else {
            return false;
        }
    }

    /*
     * cache regex patterns
     */
    private static final Pattern IE11_BROWSER =  Pattern.compile("rv:(\\d+)");
    private static final Pattern OPERA_BROWSER =  Pattern.compile("o(pr|pera)/(\\d+)");
    private static final Pattern EDGE_BROWSER_VERSION =  Pattern.compile("(?<=edge/)(\\d+).(\\d+)");
    private static final Pattern FIREFOX_BROWSER_VERSION =  Pattern.compile("(?<=firefox/)\\d+");
    private static final Pattern CHROME_BROWSER_VERSION =  Pattern.compile("(?<=chrome/)\\d+");
    private static final Pattern OPERA_BROWSER_VERSION =  Pattern.compile("(?<=o(pr|pera)/)\\d+");
    private static final Pattern SAFARI_BROWSER_VERSION =  Pattern.compile("(?<=version/)\\d+");

    public static String getBrowserType(String userAgent){
        userAgent = userAgent.toLowerCase();
        String browser = BrowserEnum.OTHER.getValue();
        try{
            //先判断浏览器类型:因为UA部分存在重复(chrome/safari等)，要按照如下的顺序依次判断
            //如果包含edge，特殊处理，因edge的ua含带了chrome safari
            if(userAgent.contains(BrowserEnum.EDGE.getValue())){
                browser = BrowserEnum.EDGE.getValue();
            } else if (userAgent.contains(BrowserEnum.FIREFOX.getValue())) {
                browser = BrowserEnum.FIREFOX.getValue();
            } else if (IE11_BROWSER.matcher(userAgent).find()) {
                browser = BrowserEnum.IE11.getValue();
            } else if (OPERA_BROWSER.matcher(userAgent).find()) {
                browser = BrowserEnum.OPERA.getValue();
            } else if (userAgent.contains(BrowserEnum.CHROME.getValue())) {
                browser = BrowserEnum.CHROME.getValue();
            } else if (userAgent.contains(BrowserEnum.SAFARI.getValue())) {
                browser = BrowserEnum.SAFARI.getValue();
            }
        }catch(Exception e){
            logger.error("get browser type occurs an exception:" + e);
        }
        return browser;
    }

    public boolean validateAllowedBrowserVersion(String userAgent,String browser){
        String supportList = ResourceUtil.getSupportBrowsers();
        //判断浏览器是否在配置范围，如果在，再判断版本号是否过期
        try{
            if (supportList.contains(browser)) {
                switch (browser) {
                    case "rv:11.0"://BrowserEnum.IE11.getValue()
                        return true;
                    case "chrome"://BrowserEnum.CHROME.getValue()
                        //Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3213.3 Safari/537.36
                        String chromeVersion = WebUtil.getChromeBrowserVersion(userAgent);
                        if (Integer.parseInt(chromeVersion) >= ResourceUtil.getLowestChromeVersion()) {
                            return true;
                        }
                        break;
                    case "firefox"://BrowserEnum.FIREFOX.getValue()
                        //Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:55.0) Gecko/20100101 Firefox/55.0
                        String fireFoxVersion = WebUtil.getFireFoxBrowserVersion(userAgent);
                        if (Integer.parseInt(fireFoxVersion) >= ResourceUtil.getLowestFirefoxVersion()) {
                            return true;
                        }
                        break;
                    case "safari"://BrowserEnum.SAFARI.getValue()
                        //mozilla/5.0 (macintosh; intel mac os x 10_11_6) applewebkit/604.4.7 (khtml, like gecko) version/11.0.2 safari/604.4.7
                        String safariVersion = WebUtil.getSafariBrowserVersion(userAgent);
                        int version = Integer.parseInt(safariVersion);
                        if (version >= ResourceUtil.getLowestSafariVersion()) {
                            //屏蔽Safari9/10
                            String unSupportedSafariVersion = ResourceUtil.getTempUnsupportedBrowserVersions();
                            if(StringUtils.isNotEmpty(unSupportedSafariVersion)){
                                ArrayList<String> unsupportedVersionList;
                                try{
                                    unsupportedVersionList = new ArrayList<>(Arrays.asList(unSupportedSafariVersion.split(",")));
                                    for(String unsupportedVersion :unsupportedVersionList){
                                        if(version == Integer.parseInt(unsupportedVersion)){
                                            return false;
                                        }
                                    }
                                }catch (Exception e){
                                    logger.warn("handle unsupport_safari_versions_join configuration occurred an exception:" + e.getMessage());
                                }
                            }

                            return true;
                        }
                        break;
                    case "opera"://BrowserEnum.OPERA.getValue()
                        //Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36 OPR/43.0.2442.1144
                        String operaVersion = WebUtil.getOperaBrowserVersion(userAgent);

                        if (Integer.parseInt(operaVersion) >= ResourceUtil.getLowestOperaVersion()) {
                            return true;
                        }
                        break;
                    case "edge":
                        //edge版本大于等于15.15063版本（UI版本，和内核版本有区别）
                        //Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393
                        String edgeVersion = WebUtil.getEdgeBrowserVersion(userAgent);
                        if(StringUtils.isNotEmpty(edgeVersion)){
                            String[] versions = edgeVersion.split("\\.");
                            if(versions.length > 1) {
                                try{
                                    int masterVersion = Integer.parseInt(versions[0]);
                                    int subVersion = Integer.parseInt(versions[1]);
                                    String[] cfgEdgeVersion = ResourceUtil.getLowestEdgeVersion().split("\\.");
                                    int cfgMasterVersion = Integer.valueOf(cfgEdgeVersion[0]);
                                    int cfgSubVersion = Integer.valueOf(cfgEdgeVersion[1]);
                                    if (masterVersion > cfgMasterVersion) {
                                        return true;
                                    }else {
                                        return masterVersion == cfgMasterVersion && subVersion >= cfgSubVersion;
                                    }
                                }catch (Exception e){
                                    logger.warn("occurred an exception during parsing edge version.");
                                }
                                return false;
                            }
                        }
                }
                return false;
            }else{
                return  false;
            }
        }catch (Exception e){
            logger.error("get allowed browser occurs an exception:" + e);
        }
        return false;
    }

    public static String getEdgeBrowserVersion(String ua){
        String version = "";
        try{
            Matcher matcher = EDGE_BROWSER_VERSION.matcher(ua);
            if(matcher.find()){
                return matcher.group(0);
            }
        }catch (Exception e){
            logger.error("edge ua can't match version pattern:" + e);
        }
        return "";
    }

    public static String getFireFoxBrowserVersion(String ua){
        String version = "";
        try{
            Matcher matcher = FIREFOX_BROWSER_VERSION.matcher(ua);
            if(matcher.find()){
                return matcher.group(0);
            }
        }catch (Exception e){
            logger.error("firefox ua can't match version pattern:" + e);
        }
        return "";
    }

    public static String getChromeBrowserVersion(String ua){
        String version = "";
        try{
            Matcher matcher = CHROME_BROWSER_VERSION.matcher(ua);
            if(matcher.find()){
                return matcher.group(0);
            }
        }catch (Exception e){
            logger.error("chrome ua can't match version pattern:" + e);
        }
        return "";
    }

    public static String getOperaBrowserVersion(String ua){
        String version = "";
        try{
            Matcher matcher = OPERA_BROWSER_VERSION.matcher(ua);
            if(matcher.find()){
                return matcher.group(0);
            }
        }catch (Exception e){
            logger.error("opera ua can't match version pattern:" + e);
        }
        return "";
    }

    public static String getSafariBrowserVersion(String ua){
        String version = "";
        try{
            Matcher matcher = SAFARI_BROWSER_VERSION.matcher(ua);
            if(matcher.find()){
                return matcher.group(0);
            }
        }catch (Exception e){
            logger.error("safari ua can't match version pattern:" + e);
        }
        return "";
    }
}
