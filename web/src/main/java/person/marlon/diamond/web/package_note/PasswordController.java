package person.marlon.diamond.web.package_note;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import person.marlon.diamond.dao.password.dto.PasswordNote;
import person.marlon.diamond.service.password_note.PasswordNoteService;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/pass_note")
public class PasswordController {

	@Resource
	private PasswordNoteService passwordNoteService;

	@RequestMapping("/add")
	@ResponseBody
	public String add(){
		PasswordNote passwordNote = new PasswordNote();
		passwordNote.setAccount("");
		passwordNote.setPassword("");
		passwordNote.setPlatform("");
		passwordNote.setCategory("");
		passwordNote.setComment("");
		Date currentTime = getCurrentTime();
		passwordNote.setLastModified(currentTime);
		passwordNote.setCreatedTime(currentTime);
		passwordNote.setPhoneNo(1L);
		passwordNoteService.insert(passwordNote);
		return "ok";
	}

	@RequestMapping(value = "/update",method = RequestMethod.POST)
	@ResponseBody
	public String update(Integer passwordId,Foo foo){
		PasswordNote passwordNote = passwordNoteService.getById(passwordId);
		if(passwordNote == null){
			return passwordId + " not exist.";
		}

		PasswordNote newPasswordNote = new PasswordNote();
		newPasswordNote.setId(passwordNote.getId());

		newPasswordNote.setLastModified(getCurrentTime());
		passwordNoteService.update(newPasswordNote);

		return "ok";
	}

	@RequestMapping(value = "/getAll",method = RequestMethod.GET,produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getAll(){
		List<PasswordNote> passwordNoteList = passwordNoteService.getAll();
		if(passwordNoteList == null){
			return "password note record is not exist.";
		}

		return new Gson().toJson(passwordNoteList);
	}


	private Date getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR,8);//服务器0时区，存储按东八区存
		return calendar.getTime();
	}
}
