package person.marlon.diamond.dao.group_resource_authority.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.marlon.diamond.dao.group_resource_authority.GroupResourceAuthority;

import java.util.Date;

public interface GroupResourceAuthorityMapper {
    @Delete({
        "delete from person.marlon.diamond.dto.group_resource_authority",
        "where group_resource_authority_id = #{userResourceAuthorityId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long userResourceAuthorityId);

    @Insert({
        "insert into person.marlon.diamond.dto.group_resource_authority (group_resource_authority_id, resource_id, ",
        "group_id, authority, ",
        "create_time, modify_time, ",
        "comment)",
        "values (#{userResourceAuthorityId,jdbcType=BIGINT}, #{resourceId,jdbcType=BIGINT}, ",
        "#{groupId,jdbcType=BIGINT}, #{authority,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{comment,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="group_resource_authority_id", before=false, resultType=Long.class)
    int insert(GroupResourceAuthority record);

    @InsertProvider(type=GroupResourceAuthoritySqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="group_resource_authority_id", before=false, resultType=Long.class)
    int insertSelective(GroupResourceAuthority record);

    @Select({
        "select",
        "group_resource_authority_id, resource_id, group_id, authority, create_time, modify_time, ",
        "comment",
        "from person.marlon.diamond.dto.group_resource_authority",
        "where group_resource_authority_id = #{userResourceAuthorityId,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="group_resource_authority_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="resource_id", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="group_id", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="authority", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="modify_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="comment", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    GroupResourceAuthority selectByPrimaryKey(Long groupResourceAuthorityId);

    @UpdateProvider(type=GroupResourceAuthoritySqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(GroupResourceAuthority record);

    @Update({
        "update person.marlon.diamond.dto.group_resource_authority",
        "set resource_id = #{resourceId,jdbcType=BIGINT},",
          "group_id = #{groupId,jdbcType=BIGINT},",
          "authority = #{authority,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "comment = #{comment,jdbcType=VARCHAR}",
        "where group_resource_authority_id = #{userResourceAuthorityId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(GroupResourceAuthority record);
}