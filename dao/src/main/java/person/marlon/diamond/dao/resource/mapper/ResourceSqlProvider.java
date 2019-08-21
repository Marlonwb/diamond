package person.marlon.diamond.dao.resource.mapper;

import org.apache.ibatis.jdbc.SQL;
import person.marlon.diamond.common.dto.Resource;

public class ResourceSqlProvider {

    public String insertSelective(Resource record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("resource");
        
        if (record.getResourceId() != null) {
            sql.VALUES("resource_id", "#{resourceId,jdbcType=BIGINT}");
        }
        
        if (record.getResourceName() != null) {
            sql.VALUES("resource_name", "#{resourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            sql.VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getComment() != null) {
            sql.VALUES("comment", "#{comment,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Resource record) {
        SQL sql = new SQL();
        sql.UPDATE("resource");
        
        if (record.getResourceName() != null) {
            sql.SET("resource_name = #{resourceName,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            sql.SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getComment() != null) {
            sql.SET("comment = #{comment,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("resource_id = #{resourceId,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}