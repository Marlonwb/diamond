package person.marlon.diamond.dao.user_resource_authority.mapper;

import org.apache.ibatis.jdbc.SQL;
import person.marlon.diamond.common.dto.UserResourceAuthority;

public class UserResourceAuthoritySqlProvider {

    public String insertSelective(UserResourceAuthority record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("person/marlon/diamond/dao/user_resource_authority");
        
        if (record.getUserResourceAuthorityId() != null) {
            sql.VALUES("user_resource_authority_id", "#{userResourceAuthorityId,jdbcType=BIGINT}");
        }
        
        if (record.getResourceId() != null) {
            sql.VALUES("resource_id", "#{resourceId,jdbcType=BIGINT}");
        }
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }
        
        if (record.getAuthority() != null) {
            sql.VALUES("authority", "#{authority,jdbcType=VARCHAR}");
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

    public String updateByPrimaryKeySelective(UserResourceAuthority record) {
        SQL sql = new SQL();
        sql.UPDATE("person/marlon/diamond/dao/user_resource_authority");
        
        if (record.getResourceId() != null) {
            sql.SET("resource_id = #{resourceId,jdbcType=BIGINT}");
        }
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=BIGINT}");
        }
        
        if (record.getAuthority() != null) {
            sql.SET("authority = #{authority,jdbcType=VARCHAR}");
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
        
        sql.WHERE("user_resource_authority_id = #{userResourceAuthorityId,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}