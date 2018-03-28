package person.marlon.diamond.util;

//import org.apache.commons.lang.StringUtils;

import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

public class StringUtil {

    public static DecimalFormat Decimal2 = new DecimalFormat("###.00");

    /**
     * 是否为null
     * 
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static String toString(String keyword) {
        if (keyword == null) {
            return "";
        }

        return keyword.trim();
    }

	public static String toString(Long value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}
    /**
     * 不为null
     * 
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str) {
        return !StringUtil.isEmpty(str);
    }

    /**
     * 是否为空（包括null）
     * 
     * @param str
     * @return 空true，不空false
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 不为空
     * 
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str) {
        return !StringUtil.isBlank(str);
    }

    /**
     * 字符串去空
     * 
     * @param str
     * @return
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 字符串去null
     * 
     * @param str
     * @return
     */
    public static String trimToNull(String str) {
        String ts = trim(str);
        return isEmpty(ts) ? null : ts;
    }

    /**
     * 比较字符串
     * 
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equals(String str1, String str2) {
        return str1 == null ? str2 == null : str1.equals(str2);
    }

    /**
     * 字符串是否包含
     * 
     * @param str
     * @param searchStr
     * @return
     */
    public static boolean contains(String str, String searchStr) {
        if (str == null || searchStr == null) {
            return false;
        }
        return str.indexOf(searchStr) >= 0;
    }

    /**
     * 是否是数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        int sz = str.length();
        if (sz == 0) {
            return false;
        }
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(str.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isRequireLength(String str, int first, int last) {
        if (str == null) {
            return false;
        }

        int sz = str.length();
        if (sz >= first && sz <= last) {
            return true;
        }
        return false;
    }

    public static int countBytes(String str) throws UnsupportedEncodingException {
        return str.getBytes("UTF-8").length;
    }

    public static boolean isDouble(String str) {
        if (isBlank(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }

    public static String getHall(String str) {
        return String.valueOf(str.charAt(2));
    }

    public static String getRoom(String str) {
        return String.valueOf(str.charAt(0));
    }

    public static String[] getaddress(String address) {
        String[] args = address.split("\\|");
        return args;
    }

    /**
     * 1TB=1024GB 1GB=1024MB 1MB=1024KB 1KB=1024B
     * 
     * @param size(byte)
     * @return
     */
    public static String getHtmlFileSize(Long size) {
        if (size == null || size == 0) {
            return "0";
        }
        double ok = (double) size;
        if (ok < 1024) {
            return Decimal2.format(ok) + "B";
        }
        ok = ok / 1024;
        if (ok < 1024) {
            return Decimal2.format(ok) + "KB";
        }
        ok = ok / 1024;
        if (ok < 1024) {
            return Decimal2.format(ok) + "MB";
        }
        ok = ok / 1024;
        if (ok < 1024) {
            return Decimal2.format(ok) + "GB";
        }
        ok = ok / 1024;
        return Decimal2.format(ok) + "TB";
    }

    public static String trimNull(String value) {
        if (value == null) {
            value = "";
        }
        return value;
    }

    public static String getFullName(String firstName, String lastName) {
        return StringUtil.isEmpty(lastName) ? firstName : firstName + " " + lastName;
    }

    public static String append(String first, String separator) {
        if (!StringUtil.isEmpty(first) && separator != null) {
            return first + separator;
        }
        return "";
    }

//    public static String appendEscape(String first, String separator, String second) {
//        String result = "";
//        if (!StringUtil.isEmpty(first) && !StringUtil.isEmpty(second)) {
//            result = first + separator + second;
//        }
//        if (!StringUtil.isEmpty(first) && StringUtil.isEmpty(second)) {
//            result = first;
//        }
//        if (StringUtil.isEmpty(first) && !StringUtil.isEmpty(second)) {
//            result = second;
//        }
//        return StringEscapeUtil.escapeHtml(result);
//    }

    public static String append(String first, String separator, String second) {
        if (!StringUtil.isEmpty(first) && !StringUtil.isEmpty(second)) {
            return first + separator + second;
        }
        if (!StringUtil.isEmpty(first) && StringUtil.isEmpty(second)) {
            return first;
        }
        if (StringUtil.isEmpty(first) && !StringUtil.isEmpty(second)) {
            return second;
        }
        return "";
    }

    public static String toStringBySeparator(List<String> arrays, String separator) {
        String result = "";
        if (arrays != null && !StringUtil.isEmpty(separator)) {
            for (int i = 0; i < arrays.size(); i++) {
                String str = arrays.get(i);
                if (StringUtil.isEmpty(str)) {
                    continue;
                }
                if (i == 0) {
                    result = str;
                } else {
                    result = result + separator + str;
                }
            }
        }
        return result;
    }

    public static List<String> asListDistinction(String inviteeEmails, String separator) {
        return conversionSetToList(asSet(inviteeEmails, separator));
    }

    public static List<String> asList(String inviteeEmails, String separator) {
        List<String> list = new ArrayList<String>();
        if (!StringUtil.isEmpty(inviteeEmails) && !StringUtil.isEmpty(separator)) {
            String[] inviteeEmailArray = inviteeEmails.split(separator);
            list = new ArrayList<String>(Arrays.asList(inviteeEmailArray));
        }
        return list;
    }

    public static List<String> removeAll(List<String> emails, String removeEmail) {
        if (emails != null && !StringUtil.isEmpty(removeEmail)) {
            while (emails.contains(removeEmail)) {
                emails.remove(removeEmail);
            }
        }
        return emails;
    }

    public static String escapeString(String cancelNotice) {
        if (!StringUtil.isEmpty(cancelNotice)) {
            // cancelNotice = cancelNotice.replaceAll("&lt;div&gt;", "<div>");
            // cancelNotice = cancelNotice.replaceAll("&lt;br&gt;", "<br>");
            // cancelNotice = cancelNotice.replaceAll("&lt;/div&gt;", "</div>");
            cancelNotice = cancelNotice.replaceAll(">", "&gt;");
            cancelNotice = cancelNotice.replaceAll("<", "&lt;");
            cancelNotice = cancelNotice.replaceAll("\r\n", "<br>");
            cancelNotice = cancelNotice.replaceAll("\n", "<br>");
            cancelNotice = cancelNotice.replaceAll(" ", "&nbsp;");
            // cancelNotice = cancelNotice.replaceAll("&amp;","&");
        }
        return cancelNotice;
    }

    /**
     * @param str
     * @param separator
     * @return
     */
    public static Set<String> asSet(String str, String separator) {
        Set<String> set = new HashSet<String>();
        if (!StringUtil.isEmpty(str) && !StringUtil.isEmpty(separator)) {
            String[] strs = str.split(separator);
            for (int i = 0; i < strs.length; i++) {
                set.add(strs[i]);
            }
        }
        return set;
    }

    /**
     * 转换set 为list
     * 
     * @param set
     * @return
     */
    public static <T> List<T> conversionSetToList(Set<T> set) {
        List<T> list = new ArrayList<T>();
        if (set != null) {
            Iterator<T> iterable = set.iterator();
            while (iterable.hasNext()) {
                list.add(iterable.next());
            }
        }
        return list;
    }

//    /**
//     * 验证邮箱
//     *
//     * @param emails
//     * @param separator
//     * @return
//     */
//    public static boolean verifyEmail(String emails, String separator) {
//        List<String> list = StringUtil.asList(emails, separator);
//        for (int i = 0; i < list.size(); i++) {
//            String email = list.get(i);
//            if (!ValidatorUtil.isCertifiedEmail(email) || email.length() > 64) {
//                return false;
//            }
//        }
//        return true;
//    }

//    public static float convertToFloat(String value) {
//        if (StringUtil.isEmpty(value)) {
//            return 0;
//        }
//        try {
//            String valueArray[] = StringUtils.split(value, ".");
//            String relust = "";
//            if (valueArray.length > 2) {
//                for (int i = 0; i < valueArray.length; i++) {
//                    if (i > 1) {
//                        relust = relust + valueArray[i];
//                    } else {
//                        if (i == 0) {
//                            relust = valueArray[i];
//                        } else {
//                            relust = relust + "." + valueArray[i];
//                        }
//
//                    }
//                }
//                return Float.parseFloat(relust);
//            } else {
//                return Float.parseFloat(value);
//            }
//        } catch (Exception e) {
//            return 0;
//        }
//    }

    public static String replaceHtml(String html) {
        // html = html.replace( "'", "&apos;");
        // html = html.replaceAll( "&", "&amp;");
        // html = html.replace( "\"", "&quot;"); //"
        // html = html.replace( "\t", "&nbsp;&nbsp;");// 替换跳格
        // html = html.replace( " ", "&nbsp;");// 替换空格
        // html = html.replace("<", "&lt;");
        // html = html.replaceAll( ">", "&gt;");
        return html;
    }
}
