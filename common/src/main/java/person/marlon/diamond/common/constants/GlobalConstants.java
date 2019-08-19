package person.marlon.diamond.common.constants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 动态从数据库获取全局配置项
 */
public class GlobalConstants {
	private  Logger logger = LoggerFactory.getLogger(GlobalConstants.class);

	public static final String ABSOLUTE_REQUEST_DOMAIN = "absoluteDomain";

	public static final String LOGIN_REQUEST_BODY = "loginRequestBody";

	public static final String LOGIN_USER = "loginUser";

}
