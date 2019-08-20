package person.marlon.diamond.dao.user.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.marlon.diamond.common.generic.Page;
import person.marlon.diamond.dao.user.dto.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserMapper {
    @Delete({
        "delete from user",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long userId);

    @Insert({
        "insert into user (nickname, password, ",
        "firstname, lastname, ",
        "middlename, name_show, ",
        "identity, phone, ",
        "email, is_email_verified, ",
        "create_time, modify_time, ",
        "icon_url, address, ",
        "sex, state, prefer_language, ",
        "main_group_id)",
        "values (#{nickname,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{firstname,jdbcType=VARCHAR}, #{lastname,jdbcType=VARCHAR}, ",
        "#{middlename,jdbcType=VARCHAR}, #{nameShow,jdbcType=INTEGER}, ",
        "#{identity,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{isEmailVerified,jdbcType=INTEGER}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{iconUrl,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, ",
        "#{sex,jdbcType=INTEGER}, #{state,jdbcType=INTEGER}, #{preferLanguage,jdbcType=VARCHAR}, ",
        "#{mainGroupId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userId", before=false, resultType=Long.class)
    int insert(User record);

    @InsertProvider(type=UserSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userId", before=false, resultType=Long.class)
    int insertSelective(User record);

    @Select({
        "select",
        "user_id, nickname, password, firstname, lastname, middlename, name_show, identity, ",
        "phone, email, is_email_verified, create_time, modify_time, icon_url, address, ",
        "sex, state, prefer_language, main_group_id",
        "from user",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="user_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="nickname", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="password", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="firstname", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="lastname", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="middlename", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="name_show", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="identity", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="phone", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="is_email_verified", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="modify_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="icon_url", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="address", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="sex", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="state", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="prefer_language", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="main_group_id", javaType=Long.class, jdbcType=JdbcType.BIGINT)
    })
    User selectByPrimaryKey(Long userId);

    @Select({
            "select",
            "user_id, nickname, password, firstname, lastname, middlename, name_show, identity, ",
            "phone, email, is_email_verified, create_time, modify_time, icon_url, address, ",
            "sex, state, prefer_language, main_group_id",
            "from user",
            "where nickname = #{nickname,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
            @Arg(column="user_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
            @Arg(column="nickname", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="password", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="firstname", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="lastname", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="middlename", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="name_show", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="identity", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="phone", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="is_email_verified", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="modify_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="icon_url", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="address", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="sex", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="state", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="prefer_language", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="main_group_id", javaType=Long.class, jdbcType=JdbcType.BIGINT)
    })
    User selectByUserName(String userName);

    @UpdateProvider(type=UserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(User record);

    @Update({
        "update user",
        "set nickname = #{nickname,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "firstname = #{firstname,jdbcType=VARCHAR},",
          "lastname = #{lastname,jdbcType=VARCHAR},",
          "middlename = #{middlename,jdbcType=VARCHAR},",
          "name_show = #{nameShow,jdbcType=INTEGER},",
          "identity = #{identity,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "is_email_verified = #{isEmailVerified,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "icon_url = #{iconUrl,jdbcType=VARCHAR},",
          "address = #{address,jdbcType=VARCHAR},",
          "sex = #{sex,jdbcType=INTEGER},",
          "state = #{state,jdbcType=INTEGER},",
          "prefer_language = #{preferLanguage,jdbcType=VARCHAR},",
          "main_group_id = #{mainGroupId,jdbcType=BIGINT}",
        "where user_id = #{userId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(User record);

    @SelectProvider(type=UserSqlProvider.class, method="getUserList")
//    @Select({
//            "select",
//            "id, account, password, platform, category, comment, last_modified, created_time, ",
//            "phone_no, email, secure_info",
//            "from password_note"
//    })
    @ConstructorArgs({
            @Arg(column="user_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
            @Arg(column="nickname", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="password", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="firstname", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="lastname", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="middlename", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="name_show", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="identity", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="phone", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="is_email_verified", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="modify_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="icon_url", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="address", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="sex", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="state", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="prefer_language", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="main_group_id", javaType=Long.class, jdbcType=JdbcType.BIGINT)
    })
    List<User> getUserList(Map<String, Object> searchMap, Page page);

    @SelectProvider(type=UserSqlProvider.class, method="countUserList")
//    @Select({
//            "select",
//            "id, account, password, platform, category, comment, last_modified, created_time, ",
//            "phone_no, email, secure_info",
//            "from password_note"
//    })
    @ResultType(Integer.class)
    Integer countUserList(Map<String, Object> searchMap, Page page);

}