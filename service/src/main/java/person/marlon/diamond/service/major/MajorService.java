package person.marlon.diamond.service.major;

import org.springframework.stereotype.Service;
import person.marlon.diamond.dao.major.Major;
import person.marlon.diamond.dao.major.mappers.MajorMapper;

import javax.annotation.Resource;
import java.util.List;

@Service("majorService")
public class MajorService {
    
    @Resource
    private MajorMapper majorMapper;
    
    
    public List<Major> getMajorList(){
        return majorMapper.getMajorList();
    }
    
    
}
