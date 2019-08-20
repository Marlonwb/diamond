package person.marlon.diamond.service.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import person.marlon.diamond.common.generic.ApiPageResponse;
import person.marlon.diamond.common.generic.Page;
import person.marlon.diamond.dao.group.Group;
import person.marlon.diamond.dao.group.mapper.GroupMapper;
import person.marlon.diamond.dao.user.dto.User;
import person.marlon.diamond.dao.user.mapper.UserMapper;
import person.marlon.diamond.dao.user_group_relation.UserGroupRelation;
import person.marlon.diamond.dao.user_group_relation.mapper.UserGroupRelationMapper;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

//import person.marlon.diamond.dao.user.UserDao;

@Service("userService")
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    private UserMapper userMapper;
    private GroupMapper groupMapper;
    private UserGroupRelationMapper userGroupRelationMapper;

    @Transactional("journalTransaction")
    public boolean insertUser(User user){
        if(user == null){
            return false;
        }
        try{
            // 新增User,默认按该用户名作为group name,后面就不用判断mainGroupId是否为空
            Group group = new Group();
            group.setGroupName(user.getNickname());
            if(groupMapper.insert(group) > 0){
                user.setMainGroupId(group.getGroupId());
                if(userMapper.insert(user) > 0){
                    UserGroupRelation userGroupRelation = new UserGroupRelation();
                    userGroupRelation.setUserId(user.getUserId());
                    userGroupRelation.setGroupId(group.getGroupId());
                    userGroupRelationMapper.insert(userGroupRelation);
                    return userGroupRelationMapper.insert(userGroupRelation) > 0;
                }
            }
        }catch (Exception e){
            logger.error("insertUser occurred exception:" + e);
        }
        return false;
    }

    public User getUserByName(String userName){
        return userMapper.selectByUserName(userName);
    }

    public User getById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    public boolean update(User user){
        return userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    //Put in a transaction to ensure that the list and total number read are consistent (may have other transactions insert new records)
    @Transactional("journalTransaction")
    public ApiPageResponse<List<User>> getUserList(Map<String, Object> paramMap, Page page){
        Map<String,Object> searchMap = paddingFieldToSearchMap(paramMap);
        List<User> userList = null;
        Integer totalCount = null;
        try {
            userList = userMapper.getUserList(searchMap,page);
            totalCount = countUserList(searchMap, page);
        }catch (Exception e){
            logger.error("getUserList form db occurred an exception:" + e);
        }

        if(userList == null || totalCount == null){
            return new ApiPageResponse<>(-1,"user record is not exist.");
        }
        page.setTotalCount(totalCount);
        page.calculateTotalPage(totalCount);

        return new ApiPageResponse<>(userList,page);
    }

    private Integer countUserList(Map<String, Object> searchMap, Page page){
        return userMapper.countUserList(searchMap,page);
    }

    private Map<String,Object> paddingFieldToSearchMap(Map<String,Object> paramsMap){
        //Map<String,Object> searchMap = new HashMap<>(paramsMap);
        return paramsMap;// FIXME:assemble search map form params map.
    }
}
