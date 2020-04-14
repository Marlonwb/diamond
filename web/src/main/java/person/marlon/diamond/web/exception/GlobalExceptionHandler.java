package person.marlon.diamond.web.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import person.marlon.diamond.common.generic.ApiResponse;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String exceptionHandler(Exception e){
		logger.error("",e);
		return new ApiResponse<>("",9999,e.toString()).toString();
	}
	
	
	@ExceptionHandler(MaxUploadSizeExceededException.class)
	public String handleMaxUploadException(MaxUploadSizeExceededException e, HttpServletRequest request){
		logger.error("MaxUploadSizeExceededException:" + request.getRequestURI(), e);
		return new ApiResponse<>("",9999,e.toString()).toString();
	}
}
