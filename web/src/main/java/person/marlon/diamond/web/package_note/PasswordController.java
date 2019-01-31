package person.marlon.diamond.web.package_note;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import person.marlon.diamond.common.generic.ApiPageResponse;
import person.marlon.diamond.common.generic.ApiResponse;
import person.marlon.diamond.common.generic.Page;
import person.marlon.diamond.common.util.GenericUtil;
import person.marlon.diamond.common.util.WebUtil;
import person.marlon.diamond.dao.password.dto.PasswordNote;
import person.marlon.diamond.service.password_note.PasswordNoteService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/pass_note")
public class PasswordController {
    
    private Logger logger = LoggerFactory.getLogger(PasswordController.class);

	@Resource
	private PasswordNoteService passwordNoteService;

	@RequestMapping(value = "/add", method = RequestMethod.POST,consumes = "application/json")
	@ResponseBody
	public String add(HttpServletRequest request, @RequestBody PasswordNote passwordNote){
		logger.info("received user [{}] /pass_note/list request param --> {}",WebUtil.getIpAddr(request),new Gson().toJson(passwordNote));
	    if(passwordNote == null) return new ApiResponse<>("").toString();
		PasswordNote newPasswordNote = new PasswordNote();
        newPasswordNote.setAccount(passwordNote.getAccount());
        newPasswordNote.setPassword(passwordNote.getPassword());
        newPasswordNote.setPlatform(passwordNote.getPlatform() == null?"unknown":passwordNote.getPlatform());
        newPasswordNote.setCategory(passwordNote.getCategory());
        newPasswordNote.setComment(passwordNote.getComment());
		Date currentTime = getCurrentTime();
        newPasswordNote.setLastModified(currentTime);
        newPasswordNote.setCreatedTime(currentTime);
        newPasswordNote.setPhoneNo(passwordNote.getPhoneNo());
        newPasswordNote.setEmail(passwordNote.getEmail());
        newPasswordNote.setSecureInfo(passwordNote.getSecureInfo());
        newPasswordNote.setDisplayName(passwordNote.getDisplayName());
		passwordNoteService.insert(newPasswordNote);
		return new ApiResponse(0,"save success").toString();
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST,consumes = "application/json")
	@ResponseBody
	public String update(Integer passwordId,PasswordNote oldPassNote){
		PasswordNote passwordNote = passwordNoteService.getById(passwordId);
		if(passwordNote == null){
			return new ApiResponse(-1,passwordId + " not exist.").toString() ;
		}

		PasswordNote newPasswordNote = new PasswordNote();
		newPasswordNote.setId(passwordNote.getId());

		newPasswordNote.setLastModified(getCurrentTime());
		passwordNoteService.update(newPasswordNote);

		return new ApiResponse(0,"update success").toString();
	}

	@RequestMapping(value = "/list",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String getAll(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap){
	    
        logger.info("received user [{}] /pass_note/list request param --> {}",WebUtil.getIpAddr(request),new Gson().toJson(paramMap));
        
        Page page = GenericUtil.map2Page(paramMap,"createdTime");// PasswordNote.createdTime
        ApiPageResponse<List<PasswordNote>> apiPageResponse = passwordNoteService.getPassNotesList(paramMap, page);
        
		return apiPageResponse.toString();
	}

	private Date getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR,8);//服务器0时区，存储按东八区存
		return calendar.getTime();
	}
}
