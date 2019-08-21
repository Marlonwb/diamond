package person.marlon.diamond.dao.user_group_relation.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.marlon.diamond.common.dto.UserGroupRelation;

import java.util.Date;

public interface UserGroupRelationMapper {
    @Delete({
        "delete from person.marlon.diamond.dto.user_group_relation",
        "where user_group_relation_id = #{userGroupRelationId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long userGroupRelationId);

    @Insert({
        "insert into person.marlon.diamond.dto.user_group_relation (user_id, group_id, ",
        "create_time, modify_time)",
        "values (#{userId,jdbcType=BIGINT}, #{groupId,jdbcType=BIGINT}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userGroupRelationId", before=false, resultType=Long.class)
    int insert(UserGroupRelation record);

    @InsertProvider(type=UserGroupRelationSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="userGroupRelationId", before=false, resultType=Long.class)
    int insertSelective(UserGroupRelation record);

    @Select({
        "select",
        "user_group_relation_id, user_id, group_id, create_time, modify_time",
        "from person.marlon.diamond.dto.user_group_relation",
        "where user_group_relation_id = #{userGroupRelationId,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="user_group_relation_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="user_id", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="group_id", javaType=Long.class, jdbcType=JdbcType.BIGINT),
        @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="modify_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP)
    })
    UserGroupRelation selectByPrimaryKey(Long userGroupRelationId);

    @UpdateProvider(type=UserGroupRelationSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserGroupRelation record);

    @Update({
        "update person.marlon.diamond.dto.user_group_relation",
        "set user_id = #{userId,jdbcType=BIGINT},",
          "group_id = #{groupId,jdbcType=BIGINT},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP}",
        "where user_group_relation_id = #{userGroupRelationId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserGroupRelation record);
}