package person.marlon.diamond.dao.password.mappers;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.marlon.diamond.common.generic.Page;
import person.marlon.diamond.common.dto.PasswordNote;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
        "created_time, phone_no, ",
        "email, secure_info, ",
        "display_name)",
        "values (#{account,jdbcType=VARCHAR}, #{password,jdbcType=VARCHAR}, ",
        "#{platform,jdbcType=VARCHAR}, #{category,jdbcType=VARCHAR}, ",
        "#{comment,jdbcType=VARCHAR}, #{lastModified,jdbcType=TIMESTAMP}, ",
        "#{createdTime,jdbcType=TIMESTAMP}, #{phoneNo,jdbcType=BIGINT}, ",
        "#{email,jdbcType=VARCHAR}, #{secureInfo,jdbcType=VARCHAR} ," ,
        " #{displayName,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insert(PasswordNote record);

    @InsertProvider(type=PasswordNoteSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Integer.class)
    int insertSelective(PasswordNote record);

    @Select({
        "select",
        "id, account, password, platform, category, comment, last_modified, created_time, ",
        "phone_no, email, secure_info, display_name",
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
        @Arg(column="phone_no", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="secure_info", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="display_name", javaType=String.class, jdbcType=JdbcType.VARCHAR)
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
          "phone_no = #{phoneNo,jdbcType=BIGINT},",
          "email = #{email,jdbcType=VARCHAR},",
          "secure_info = #{secureInfo,jdbcType=VARCHAR}",
          "display_name = #{displayName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(PasswordNote record);
    
    @SelectProvider(type=PasswordNoteSqlProvider.class, method="getPassNotesList")
//    @Select({
//            "select",
//            "id, account, password, platform, category, comment, last_modified, created_time, ",
//            "phone_no, email, secure_info",
//            "from password_note"
//    })
    @ConstructorArgs({
            @Arg(column="id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="account", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="password", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="platform", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="category", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="comment", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="last_modified", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="created_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="phone_no", javaType=Long.class, jdbcType=JdbcType.BIGINT),
            @Arg(column="email", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="secure_info", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="display_name", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    List<PasswordNote> getPassNotesList(Map<String, Object> searchMap, Page page);
    
    @SelectProvider(type=PasswordNoteSqlProvider.class, method="countPassNotesList")
//    @Select({
//            "select",
//            "id, account, password, platform, category, comment, last_modified, created_time, ",
//            "phone_no, email, secure_info",
//            "from password_note"
//    })
    @ResultType(Integer.class)
    Integer countPassNotesList(Map<String, Object> searchMap, Page page);
    
}
