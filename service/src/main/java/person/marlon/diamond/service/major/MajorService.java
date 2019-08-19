package person.marlon.diamond.service.major;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import person.marlon.diamond.dao.major.Major;
import person.marlon.diamond.dao.major.mapper.MajorMapper;

import javax.annotation.Resource;
import java.util.List;

@Service("majorService")
public class MajorService {

    private Logger logger = LoggerFactory.getLogger(MajorService.class);
    
    @Resource
    private MajorMapper majorMapper;
    
    
    public List<Major> getMajorList(){
        return majorMapper.getMajorList();
    }
    
    
}
