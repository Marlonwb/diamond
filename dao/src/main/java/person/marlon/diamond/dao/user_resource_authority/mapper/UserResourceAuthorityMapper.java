package person.marlon.diamond.dao.user_resource_authority.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.marlon.diamond.dao.user_resource_authority.UserResourceAuthority;

import java.util.Date;

public interface UserResourceAuthorityMapper {
    @Delete({
        "delete from person.marlon.diamond.dao.user_resource_authority",
        "where user_resource_authority_id = #{userResourceAuthorityId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long userResourceAuthorityId);

    @Insert({
        "insert into person.marlon.diamond.dao.user_resource_authority (user_resource_authority_id, resource_id, ",
        "user_id, authority, ",
        "create_time, modify_time, ",
        "comment)",
        "values (#{userResourceAuthorityId,jdbcType=BIGINT}, #{resourceId,jdbcType=BIGINT}, ",
        "#{userId,jdbcType=BIGINT}, #{authority,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{comment,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="user_resource_authority_id", before=false, resultType=Long.class)
    int insert(UserResourceAuthority record);

    @InsertProvider(type=UserResourceAuthoritySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="user_resource_authority_id", before=false, resultType=Long.class)
    int insertSelective(UserResourceAuthority record);

    @Select({
        "select",
        "user_resource_authority_id, resource_id, user_id, authority, create_time, modify_time, ",
        "comment",
        "from person.marlon.diamond.dao.user_resource_authority",
        "where user_resource_authority_id = #{userResourceAuthorityId,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="user_resource_authority_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="resource_id", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="user_id", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="authority", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="modify_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="comment", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    UserResourceAuthority selectByPrimaryKey(Long userResourceAuthorityId);

    @UpdateProvider(type=UserResourceAuthoritySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserResourceAuthority record);

    @Update({
        "update person.marlon.diamond.dao.user_resource_authority",
        "set resource_id = #{resourceId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "authority = #{authority,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "comment = #{comment,jdbcType=VARCHAR}",
        "where user_resource_authority_id = #{userResourceAuthorityId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserResourceAuthority record);
}