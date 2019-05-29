package person.marlon.diamond.common.util;

import com.google.common.base.CaseFormat;
import org.apache.commons.lang3.StringUtils;
import person.marlon.diamond.common.enums.SortOrderEnum;
import person.marlon.diamond.common.generic.Page;
import person.marlon.diamond.common.generic.Sort;

import java.util.Map;

public class GenericUtil {

	/**
	 * 将驼峰格式转换成数据库下划线形式：
	 *  TestData-->test_data
	 */
	private String convertCamelToUnderlinePattern(String field){
		return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field);
	}
	
	public static Page map2Page(Map<String,Object> paramMap,String defaultSortField){
		
		Page page;
		
		if(paramMap == null){
			page = new Page();// default: pageNum = 1,pageSize = 20,offset = (pageNum - 1) * pageSize
		}else{
			if(paramMap.get("pageNum") != null && paramMap.get("pageSize") != null){
				
				int pageNum = Integer.parseInt((String)paramMap.get("pageNum"));// 前端传过来是String
				int pageSize = Integer.parseInt((String)paramMap.get("pageSize"));
				page = new Page(pageNum,pageSize);
				
			}else if(paramMap.get("pageNum") != null /*&& paramMap.get("pageSize") == null */) {
				int pageNum = Integer.parseInt((String)paramMap.get("pageNum"));
				page = new Page(pageNum);
			}else{
				page = new Page();
			}
			
			Sort sort = null;
            String sortField = (String)paramMap.get("sortField");
            String sortType = (String)paramMap.get("sortType");
            if(sortField != null){
				//sortType
				if(SortOrderEnum.ASC.getValue().equalsIgnoreCase(sortType)){
					sort = new Sort(sortField,true);
				}else{
					sort = new Sort(sortField);
				}
			}else{
				if(StringUtils.isNotEmpty(defaultSortField)){
					sort = new Sort(defaultSortField);
				}
				//else{
				//	sort = new Sort();
				//}
                
                //set sortType
                if(StringUtils.isNotEmpty(sortType)){
                    sort = new Sort(SortOrderEnum.ASC.getValue().equalsIgnoreCase(sortType));
                }
			}
			if(sort != null){
				page.setSort(sort);
			}
		}
		
		
		
		return page;
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
			if (Character.isDigit( str.charAt( i ) ) == false) {
				return false;
			}
		}
		return true;
	}
}
