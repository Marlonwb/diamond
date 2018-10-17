package person.marlon.diamond.common.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ResourceBundle;

public final class ResourceUtil {

	private static Logger logger = LoggerFactory.getLogger(ResourceUtil.class);

	private static ResourceBundle system;

	static {
		try {
			system = ResourceBundle.getBundle("system");
		} catch (Exception e) {
			logger.error("system.properties Not Found,", e);
		}
	}

	public static String getValue(String key) {
		String msg = null;
		try {
			msg = system.getString(key);
			if (!StringUtils.isEmpty(msg)) {
				return msg.trim();
			}
		} catch (Exception e) {
			logger.error("Key['" + key + "'] Not Found in system.properties .");
		}
		return msg;
	}

	public static String getAppVersion(){
		return getValue("version");
	}

	public static String getSupportBrowsers(){
		return getValue("support_browsers");
	}

	public static Integer getLowestChromeVersion() {
		Integer version = Integer.MAX_VALUE;
		try {
			version = Integer.valueOf(getValue("lowest_chrome_version"));
		} catch (Exception e) {
			logger.error( "Key['lowest_chrome_version'] Not Found in system.properties ." );
		}
		return version;
	}

	public static Integer getLowestFirefoxVersion() {
		Integer version = Integer.MAX_VALUE;
		try {
			version = Integer.valueOf(getValue("lowest_firefox_version"));
		} catch (Exception e) {
			logger.error( "Key['lowest_firefox_version'] Not Found in system.properties ." );
		}
		return version;
	}

	public static Integer getLowestSafariVersion() {
		Integer version = Integer.MAX_VALUE;
		try {
			version = Integer.valueOf(getValue("lowest_safari_version"));
		} catch (Exception e) {
			logger.error( "Key['lowest_safari_version'] Not Found in system.properties ." );
		}
		return version;
	}

	public static Integer getLowestOperaVersion() {
		Integer version = Integer.MAX_VALUE;
		try {
			version = Integer.valueOf(getValue("lowest_opera_version"));
		} catch (Exception e) {
			logger.error( "Key['lowest_opera_version'] Not Found in system.properties ." );
		}
		return version;
	}

	public static String getLowestEdgeVersion() {
		return getValue("lowest_edge_version");
	}

	public static String getTempUnsupportedBrowserVersions() {
		return getValue("temp_unsupported_browser_versions");
	}
}
