package person.marlon.diamond.dao.user;

import org.springframework.stereotype.Repository;
import person.marlon.diamond.dao.user.dto.User;
import person.marlon.diamond.dao.user.mappers.UserMapper;

import javax.annotation.PostConstruct;

@Repository("userDao")
public class UserDao extends UserSqlSessionDaoSupport {
    private UserMapper userMapper;

    @PostConstruct
    public void init(){
        userMapper = getSqlSession().getMapper(UserMapper.class);
    }

    public User getUser(Long userId) {
//        UserMapper userMapper  = getSqlSession().getMapper(UserMapper.class);
         return userMapper.selectByPrimaryKey(userId);
    }
}
