package person.marlon.diamond.web.user;

import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import person.marlon.diamond.common.generic.ApiResponse;
import person.marlon.diamond.common.util.WebUtil;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping("/register")
	public String register(){
		return "";
	}
	
	@RequestMapping(value = "/login",method = RequestMethod.POST,
			/*consumes = "application/x-www-form-urlencoded",*/produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String login(HttpServletRequest request){
		JsonObject loginRequestBody = WebUtil.getLoginRequestBody(request);
		logger.info("user [{}] login：{}",WebUtil.getLoginUser(request).getNickname(),loginRequestBody);
//            return new ApiResponse<>(-1,"save failed").toString();
		return new ApiResponse(0,"success").toString();
	}
}
