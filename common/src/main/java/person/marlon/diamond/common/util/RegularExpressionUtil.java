package person.marlon.diamond.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

public class RegularExpressionUtil {
	//IP正则
	private static final Pattern IP_PATTERN = Pattern.compile("^(((1[0-9]{2})|((2[0-4][0-9])|(25[0-5]))|([1-9]{2})|([0-9]))\\.){3}((1[0-9][0-9])|((2[0-4][0-9])|(25[0-5]))|([1-9][0-9])|([0-9]))$");

	//域名URL正则
	private static final Pattern DOMAIN_PATTERN =Pattern.compile("^((https|http)://)?(((([a-zA-Z0-9]+[-a-zA-Z0-9]*[a-zA-Z0-9]+)|([a-zA-Z0-9]+))\\.){1,2}(([a-zA-Z0-9]+[-a-zA-Z0-9]*[a-zA-Z0-9]+)|([a-zA-Z0-9]+)))$");

	/**
	 * 判断字符串是否ip
	 */
	public static boolean isIpPattern(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		return IP_PATTERN.matcher(str).matches();
	}

	/**
	 * 判断字符串是否是域名
	 */
	public static boolean isDomainPattern(String str){
		if(StringUtils.isEmpty(str)){
			return false;
		}
		return DOMAIN_PATTERN.matcher(str).matches();
	}

}
