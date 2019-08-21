package person.marlon.diamond.service.major;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import person.marlon.diamond.common.dto.Major;
import person.marlon.diamond.dao.major.mapper.MajorMapper;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("majorService")
public class MajorService {

    private Logger logger = LoggerFactory.getLogger(MajorService.class);

    @Resource
    private MajorMapper majorMapper;

    private Map<String,Major> majorIdMap = new HashMap<>(256);

    @PostConstruct
    private void init(){
        List<Major> majorList = majorMapper.getMajorList();
        for(Major major: majorList){
            majorIdMap.put(major.getName(),major);
        }
    }

    public Major getMajorByName(String name){
        return majorIdMap.get(name);
    }

    public Major getMajorById(Integer majorId){
        return majorMapper.selectByPrimaryKey(majorId);
    }

    public List<Major> getMajorList(){
        return majorMapper.getMajorList();
    }
}
