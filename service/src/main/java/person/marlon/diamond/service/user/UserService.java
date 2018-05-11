package person.marlon.diamond.service.user;

import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import person.marlon.diamond.dao.user.UserDao;
import person.marlon.diamond.dao.user.dto.User;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

//import person.marlon.diamond.dao.user.UserDao;

@Service("userService")
public class UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);
    private String name = getName();

    @Resource
    private UserDao userDao;

    public User getUser(){
        return userDao.getUser(1L);
    }

    @PostConstruct
    void init(){
        logger.info(new Gson().toJson(getUser()));
        logger.info(name);
    }

    private String getName(){
        return "wb";
    }
}
