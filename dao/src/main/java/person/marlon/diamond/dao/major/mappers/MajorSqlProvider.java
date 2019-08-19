package person.marlon.diamond.dao.major.mappers;

import org.apache.ibatis.jdbc.SQL;
import person.marlon.diamond.dao.major.Major;

public class MajorSqlProvider {

    public String insertSelective(Major record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("major");
        
        if (record.getName() != null) {
            sql.VALUES("name", "#{name,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.VALUES("level", "#{level,jdbcType=BIT}");
        }
        
        if (record.getParentMajorId() != null) {
            sql.VALUES("parent_major_id", "#{parentMajorId,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Major record) {
        SQL sql = new SQL();
        sql.UPDATE("major");
        
        if (record.getName() != null) {
            sql.SET("name = #{name,jdbcType=VARCHAR}");
        }
        
        if (record.getLevel() != null) {
            sql.SET("level = #{level,jdbcType=BIT}");
        }
        
        if (record.getParentMajorId() != null) {
            sql.SET("parent_major_id = #{parentMajorId,jdbcType=INTEGER}");
        }
        
        sql.WHERE("major_id = #{majorId,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}