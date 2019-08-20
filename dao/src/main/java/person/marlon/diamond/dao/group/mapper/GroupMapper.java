package person.marlon.diamond.dao.group.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.marlon.diamond.dao.group.Group;

import java.util.Date;

public interface GroupMapper {
    @Delete({
        "delete from group",
        "where group_id = #{groupId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long groupId);

    @Insert({
        "insert into group (group_id, group_name, ",
        "create_time, modify_time, ",
        "comment)",
        "values (#{groupId,jdbcType=BIGINT}, #{groupName,jdbcType=VARCHAR}, ",
        "#{createTime,jdbcType=TIMESTAMP}, #{modifyTime,jdbcType=TIMESTAMP}, ",
        "#{comment,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="group_id", before=false, resultType=Long.class)
    int insert(Group record);

    @InsertProvider(type= GroupSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="group_id", before=false, resultType=Long.class)
    int insertSelective(Group record);

    @Select({
        "select",
        "group_id, group_name, create_time, modify_time, comment",
        "from group",
        "where group_id = #{groupId,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="group_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="group_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="modify_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="comment", javaType=String.class, jdbcType=JdbcType.VARCHAR)
    })
    Group selectByPrimaryKey(Long groupId);

    @UpdateProvider(type=GroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Group record);

    @Update({
        "update group",
        "set group_name = #{groupName,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP},",
          "comment = #{comment,jdbcType=VARCHAR}",
        "where group_id = #{groupId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Group record);
}