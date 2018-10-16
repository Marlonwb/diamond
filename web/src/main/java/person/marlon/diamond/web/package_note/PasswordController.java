package person.marlon.diamond.web.package_note;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import person.marlon.diamond.dao.password.dto.PasswordNote;
import person.marlon.diamond.service.password_note.PasswordNoteService;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/password_note")
public class PasswordController {

	@Resource
	private PasswordNoteService passwordNoteService;

	@RequestMapping("/add")
	@ResponseBody
	public String add(){
		PasswordNote passwordNote = new PasswordNote();
		passwordNote.setAccount("marlon");
		passwordNote.setPassword("mynamehehe");
		passwordNote.setPlatform("e-diary");
		passwordNote.setCategory("笔记记事");
		passwordNote.setComment("e-diary个人电子笔记本");
		Date currentTime = getCurrentTime();
		passwordNote.setLastModified(currentTime);
		passwordNote.setCreatedTime(currentTime);
		passwordNoteService.insert(passwordNote);
		return "ok";
	}

	@RequestMapping("/update")
	@ResponseBody
	public String update(Integer passwordId){
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


	private Date getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR,8);//服务器0时区，存储按东八区存
		return calendar.getTime();
	}
}
