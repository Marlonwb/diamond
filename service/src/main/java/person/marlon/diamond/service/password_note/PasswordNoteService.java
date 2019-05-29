package person.marlon.diamond.service.password_note;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import person.marlon.diamond.common.generic.ApiPageResponse;
import person.marlon.diamond.common.generic.Page;
import person.marlon.diamond.dao.password.dto.PasswordNote;
import person.marlon.diamond.dao.password.mappers.PasswordNoteMapper;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PasswordNoteService {
    
    private Logger logger = LoggerFactory.getLogger(PasswordNoteService.class);

	@Resource
	private PasswordNoteMapper passwordNoteMapper;

	public boolean insert(PasswordNote passwordNote){
		if(passwordNote == null){
			return false;
		}
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
		
		passwordNoteMapper.insert(newPasswordNote);
		return true;
	}
	
	public Date getCurrentTime(){
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.HOUR,8);//服务器0时区，存储按东八区存
		return calendar.getTime();
	}
	
	public PasswordNote getById(Integer id){
		return passwordNoteMapper.selectByPrimaryKey(id);
	}

	public void update(PasswordNote passwordNote){
		passwordNoteMapper.updateByPrimaryKeySelective(passwordNote);
	}

	//Put in a transaction to ensure that the list and total number read are consistent (may have other transactions insert new records)
	@Transactional("marlonTransactionManager")
	public ApiPageResponse<List<PasswordNote>> getPassNotesList(Map<String, Object> paramMap, Page page){
		Map<String,Object> searchMap = paddingFieldToSearchMap(paramMap);
        List<PasswordNote> passwordNoteList = null;
        Integer totalCount = null;
		try {
            passwordNoteList = passwordNoteMapper.getPassNotesList(searchMap,page);
            totalCount = countPassNotesList(searchMap, page);
        }catch (Exception e){
            logger.error("getPassNotesList form db occurred an exception --> {}",e);
        }
        
        if(passwordNoteList == null || totalCount == null){
            return new ApiPageResponse<>(-1,"password note record is not exist.");
        }
        page.setTotalCount(totalCount);
        page.calculateTotalPage(totalCount);
       
		return new ApiPageResponse<>(passwordNoteList,page);
	}
	
	private Integer countPassNotesList(Map<String, Object> searchMap, Page page){
		return passwordNoteMapper.countPassNotesList(searchMap,page);
	}
	
	private Map<String,Object> paddingFieldToSearchMap(Map<String,Object> paramsMap){
		//Map<String,Object> searchMap = new HashMap<>(paramsMap);
		return paramsMap;// FIXME:assemble search map form params map.
	}
	
}
