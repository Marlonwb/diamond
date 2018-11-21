package person.marlon.diamond.common.util;

import com.google.common.base.CaseFormat;

public class GenericUtil {

	/**
	 * 将驼峰格式转换成数据库下划线形式：
	 *  TestData-->test_data
	 */
	private String convertCamelToUnderlinePattern(String field){
		return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field);
	}
}
