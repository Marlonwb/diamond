package person.marlon.diamond.dao.user.mappers;

import org.apache.ibatis.annotations.Param;
import person.marlon.diamond.dao.user.dto.User;

public interface UserMapper {

//    @Select("SELECT * FROM blog WHERE id = #{userId}")
    User selectOne(@Param("userId") Long userId);
}
