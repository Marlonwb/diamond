package person.marlon.diamond.service.journal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import person.marlon.diamond.common.generic.ApiPageResponse;
import person.marlon.diamond.common.generic.Page;
import person.marlon.diamond.common.dto.Journal;
import person.marlon.diamond.dao.journal.mapper.JournalMapper;
import person.marlon.diamond.common.dto.Major;
import person.marlon.diamond.dao.major.mapper.MajorMapper;
import person.marlon.diamond.service.major.MajorService;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("journalService")
public class JournalService {
    private Logger logger = LoggerFactory.getLogger(JournalService.class);

    @Resource
    private JournalMapper journalMapper;
    @Resource
    private MajorMapper majorMapper;
    @Resource
    private MajorService majorService;

    public boolean insert(Journal journal){
        return journalMapper.insert(journal) > 0;
    }
    //Put in a transaction to ensure that the list and total number read are consistent (may have other transactions insert new records)
    @Transactional("journalTransaction")
    public  ApiPageResponse<List<Journal>> getList(Map<String, Object> paramMap, Page page){
        Map<String,Object> searchMap = paddingFieldToSearchMap(paramMap);
        List<Journal> journalList = null;
        Integer totalCount = null;
        try {
            if(searchMap.get("level") != null){
                if(searchMap.get("majorId") != null){
                    int level = Integer.parseInt((String)searchMap.get("level"));
                    int majorId = Integer.parseInt((String)searchMap.get("majorId"));
                    // 最底层专业
                    if(level == 2){
                       // do nothing
                    }else{//上层专业，需要将下层专业也一起查出来
                        List<Integer> childMajorIds = majorMapper.getIdByParentMajorId(majorId);
                        searchMap.put("majorIds",childMajorIds);
                    }
                }

            }
            journalList = journalMapper.getList(searchMap,page);
            if(journalList != null){
                for(Journal journal : journalList){
                    String journalCover = journal.getJournalCover();
                    if(StringUtils.isNotEmpty(journalCover)){
                        journal.setJournalCover("/static/" + journal.getJournalId() + "_" + journalCover);
                    }
                    if(journal.getMajorId() != null){
                        // 只要journal存了major_id,必须是最后一层major(level=2)
                        String[] majors = new String[3];
                        Major grandChildMajor = majorService.getMajorById(journal.getMajorId());
                        if(grandChildMajor != null){
                            majors[2] = grandChildMajor.getName();
                            Major childMajor = majorService.getMajorById(grandChildMajor.getParentMajorId());
                            if(childMajor != null){
                                majors[1] = childMajor.getName();
                                Major major = majorService.getMajorById(grandChildMajor.getParentMajorId());
                                if(major != null){
                                    majors[0] = major.getName();
                                }
                            }
                        }
                        journal.setMajors(majors);
                    }
                }
            }

            totalCount = journalMapper.countList(searchMap, page);
        }catch (Exception e){
            logger.error("get JournalList form db occurred an exception --> {}",e);
        }

        if(journalList == null || totalCount == null){
            return new ApiPageResponse<>(-1,"Journal record is not exist.");
        }
        page.setTotalCount(totalCount);
        page.calculateTotalPage(totalCount);

        return new ApiPageResponse<>(journalList,page);
    }

    public Journal getById(Long id){
        return journalMapper.selectByPrimaryKey(id);
    }

    public int update(Journal journal){
        return journalMapper.updateByPrimaryKeySelective(journal);
    }

    private Map<String,Object> paddingFieldToSearchMap(Map<String,Object> paramsMap){
        //Map<String,Object> searchMap = new HashMap<>(paramsMap);
        return paramsMap;// FIXME:assemble search map form params map.
    }
}
