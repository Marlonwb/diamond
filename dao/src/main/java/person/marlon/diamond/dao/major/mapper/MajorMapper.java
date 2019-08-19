package person.marlon.diamond.dao.major.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.marlon.diamond.dao.major.Major;

import java.util.List;

public interface MajorMapper {
    @Delete({
        "delete from major",
        "where major_id = #{majorId,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer majorId);

    @Insert({
        "insert into major (name, level, ",
        "parent_major_id)",
        "values (#{name,jdbcType=VARCHAR}, #{level,jdbcType=INTEGER}, ",
        "#{parentMajorId,jdbcType=INTEGER})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="majorId", before=false, resultType=Integer.class)
    int insert(Major record);

    @InsertProvider(type=MajorSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="majorId", before=false, resultType=Integer.class)
    int insertSelective(Major record);

    @Select({
        "select",
        "major_id, name, level, parent_major_id",
        "from major",
        "where major_id = #{majorId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
        @Arg(column="major_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
        @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="level", javaType=Integer.class,jdbcType=JdbcType.INTEGER),
        @Arg(column="parent_major_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    Major selectByPrimaryKey(Integer majorId);
    
    
    @Select({
            "select",
            "major_id",
            "from major",
            "where major_id = #{majorId,jdbcType=INTEGER}"
    })
    @ConstructorArgs({
            @Arg(column="major_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="level", javaType=Integer.class,jdbcType=JdbcType.INTEGER),
            @Arg(column="parent_major_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    List<Integer> getIdByParentMajorId(Integer majorId);
    
    
    @Select({
            "select",
            "major_id, name, level, parent_major_id",
            "from major"
    })
    @ConstructorArgs({
            @Arg(column="major_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER, id=true),
            @Arg(column="name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="level", javaType=Integer.class,jdbcType=JdbcType.INTEGER),
            @Arg(column="parent_major_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER)
    })
    List<Major> getMajorList();

    @UpdateProvider(type=MajorSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Major record);

    @Update({
        "update major",
        "set name = #{name,jdbcType=VARCHAR},",
          "level = #{level,jdbcType=INTEGER},",
          "parent_major_id = #{parentMajorId,jdbcType=INTEGER}",
        "where major_id = #{majorId,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Major record);
}