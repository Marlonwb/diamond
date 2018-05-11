package person.marlon.diamond.dao.user;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.Resource;

public class UserSqlSessionDaoSupport extends SqlSessionDaoSupport {

    @Resource
    public void setSqlSessionFactory(SqlSessionFactory userSqlSessionFactory) {
      super.setSqlSessionFactory(userSqlSessionFactory);
    }

}
