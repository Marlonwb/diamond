package person.marlon.diamond.web.journal;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import person.marlon.diamond.common.generic.ApiPageResponse;
import person.marlon.diamond.common.generic.ApiResponse;
import person.marlon.diamond.common.generic.Page;
import person.marlon.diamond.common.util.GenericUtil;
import person.marlon.diamond.common.util.WebUtil;
import person.marlon.diamond.common.dto.Journal;
import person.marlon.diamond.common.dto.Major;
import person.marlon.diamond.service.file.FileService;
import person.marlon.diamond.service.journal.JournalService;
import person.marlon.diamond.service.major.MajorService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/journal")
public class JournalController {
    private Logger logger = LoggerFactory.getLogger(JournalController.class);

    @Resource
    private JournalService journalService;

    @Resource
    private FileService fileService;

    @Resource
    private MajorService majorService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam(required = false) MultipartFile file, @ModelAttribute Journal journal,
                      Model model) {
        if(journal == null){
            model.addAttribute("error","缺少上传数据");
            return "upload_fail";
//			return new ApiResponse<>(-1,"journal param is empty!").toString();
        }
        String[] majors = journal.getMajors();
        if(majors != null && majors.length == 3){
            Major major = majorService.getMajorByName(majors[2]);
            if(major != null){
                journal.setMajorId(major.getMajorId());
            }
        }
        // if upload with file, update journal_cover by original file name.
        if(file != null){
            String originalFilename = "" + file.getOriginalFilename();
            journal.setJournalCover(originalFilename);
        }

        Date current = new Date();
        journal.setCreateTime(current);
        journal.setModifyTime(current);
        boolean insert = journalService.insert(journal);
        if(insert){
            if(file != null){
                String newFilePrefix = "" + journal.getJournalId() + "_";
                if(!fileService.writeFile(file,newFilePrefix)){
                    model.addAttribute("error","上传文件错误");
                    return "upload_fail";
//					return new ApiResponse<>(-1,"write file failed!").toString();
                }
            }
        }
//		return new ApiResponse<>(0, "upload success!").toString();
        return "redirect:/static/journal.html";
    }

    @RequestMapping(value = "/update",method = RequestMethod.POST,consumes = "application/json")
    @ResponseBody
    public String update(Long journalId,Journal newJournal){
        Journal oldJournal = journalService.getById(journalId);
        if(oldJournal == null || newJournal == null){
            return new ApiResponse(-1,journalId + " not exist.").toString() ;
        }

        newJournal.setJournalId(oldJournal.getJournalId());
        newJournal.setModifyTime(new Date());
        int update = journalService.update(newJournal);
        if(update > 0){
            return new ApiResponse(0,"update success").toString();
        }else {
            return new ApiResponse(-1,"update failed").toString() ;
        }
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getList(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap){

        logger.info("received user [{}] /journal/list request param --> {}",WebUtil.getIpAddr(request),new Gson().toJson(paramMap));

        Page page = GenericUtil.map2Page(paramMap,"createTime");// Journal.createTime
        ApiPageResponse<List<Journal>> apiPageResponse = journalService.getList(paramMap, page);

        return apiPageResponse.toString();
    }

    @RequestMapping(value = "/detail",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getAll(HttpServletRequest request, @RequestParam HashMap<String, Object> paramMap){

        logger.info("received user [{}] /pass_note/list request param --> {}",WebUtil.getIpAddr(request),new Gson().toJson(paramMap));
        // todo
//        Page page = GenericUtil.map2Page(paramMap,"createdTime");// PasswordNote.createdTime
//        ApiPageResponse<List<PasswordNote>> apiPageResponse = passwordNoteService.getPassNotesList(paramMap, page);
//
//        return apiPageResponse.toString();
        return null;
    }

}

