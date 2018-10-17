package person.marlon.diamond.common.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Map<String,Object> exceptionHandler(Exception e){
		Map<String,Object> result = new HashMap<>();
		result.put("data", "");
		result.put("retCode", "9999");
		result.put("msg", e.toString());
		//正常开发中，可创建一个统一响应实体，如CommonResp
		logger.error("",e);
		return result;
	}
}
