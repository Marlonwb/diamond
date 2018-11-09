package person.marlon.diamond.dao.password.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.marlon.diamond.dao.password.dto.PasswordNote ;

import java.util.Date;

public interface PasswordNoteMapper {
    @Delete({
        "delete from password_note",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into password_note (account, password, ",
        "platform, category, ",
        "comment, last_modified, ",
        "created_time, phone_no)",
        "values (#{account,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{platform,jdbcType=VARCHAR}, #{category,jdbcType=VARCHAR}, ",
        "#{comment,jdbcType=VARCHAR}, #{lastModified,jdbcType=TIMESTAMP}, ",
        "#{createdTime,jdbcType=TIMESTAMP}, #{phoneNo,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(PasswordNote record);

    @InsertProvider(type=PasswordNoteSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(PasswordNote record);

    @Select({
        "select",
        "id, account, password, platform, category, comment, last_modified, created_time, ",
        "phone_no",
        "from password_note",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="account", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="password", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="platform", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="category", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="comment", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="last_modified", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="created_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="phone_no", javaType=Long.class, jdbcType=JdbcType.BIGINT)
    })
    PasswordNote selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PasswordNoteSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(PasswordNote record);

    @Update({
        "update password_note",
        "set account = #{account,jdbcType=VARCHAR},",
          "password = #{password,jdbcType=VARCHAR},",
          "platform = #{platform,jdbcType=VARCHAR},",
          "category = #{category,jdbcType=VARCHAR},",
          "comment = #{comment,jdbcType=VARCHAR},",
          "last_modified = #{lastModified,jdbcType=TIMESTAMP},",
          "created_time = #{createdTime,jdbcType=TIMESTAMP},",
          "phone_no = #{phoneNo,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PasswordNote record);
}