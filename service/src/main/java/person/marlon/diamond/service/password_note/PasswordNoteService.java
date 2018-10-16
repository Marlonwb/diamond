package person.marlon.diamond.service.password_note;

import org.springframework.stereotype.Service;
import person.marlon.diamond.dao.password.dto.PasswordNote;
import person.marlon.diamond.dao.password.mappers.PasswordNoteMapper;

import javax.annotation.Resource;

@Service
public class PasswordNoteService {

	@Resource
	private PasswordNoteMapper passwordNoteMapper;

	public void insert(PasswordNote passwordNote){
		passwordNoteMapper.insert(passwordNote);
	}

	public PasswordNote getById(Integer id){
		return passwordNoteMapper.selectByPrimaryKey(id);
	}

	public void update(PasswordNote passwordNote){
		passwordNoteMapper.updateByPrimaryKeySelective(passwordNote);
	}

}
