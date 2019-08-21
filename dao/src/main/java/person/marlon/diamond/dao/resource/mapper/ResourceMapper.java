package person.marlon.diamond.dao.resource.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.marlon.diamond.common.dto.Resource;

import java.util.Date;

public interface ResourceMapper {
    @Delete({
        "delete from resource",
        "where resource_id = #{resourceId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long resourceId);

    @Insert({
        "insert into resource (resource_id, resource_name, ",
        "create_time, modify_time, ",
        "comment)",
        "values (#{resourceId,jdbcType=BIGINT}, #{resourceName,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{comment,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="resource_id", before=false, resultType=Long.class)
    int insert(Resource record);

    @InsertProvider(type= ResourceSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="resource_id", before=false, resultType=Long.class)
    int insertSelective(Resource record);

    @Select({
        "select",
        "resource_id, resource_name, create_time, modify_time, comment",
        "from resource",
        "where resource_id = #{resourceId,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="resource_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="resource_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="modify_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="comment", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Resource selectByPrimaryKey(Long resourceId);

    @UpdateProvider(type= ResourceSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Resource record);

    @Update({
        "update resource",
        "set resource_name = #{resourceName,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "comment = #{comment,jdbcType=VARCHAR}",
        "where resource_id = #{resourceId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Resource record);
}