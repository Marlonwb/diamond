package person.marlon.diamond.dao.user_group_relation.mapper;

import org.apache.ibatis.jdbc.SQL;
import person.marlon.diamond.common.dto.UserGroupRelation;

public class UserGroupRelationSqlProvider {

    public String insertSelective(UserGroupRelation record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("person/marlon/diamond/dao/user_group_relation");
        
        if (record.getUserId() != null) {
            sql.VALUES("user_id", "#{userId,jdbcType=BIGINT}");
        }
        
        if (record.getGroupId() != null) {
            sql.VALUES("group_id", "#{groupId,jdbcType=BIGINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            sql.VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(UserGroupRelation record) {
        SQL sql = new SQL();
        sql.UPDATE("person/marlon/diamond/dao/user_group_relation");
        
        if (record.getUserId() != null) {
            sql.SET("user_id = #{userId,jdbcType=BIGINT}");
        }
        
        if (record.getGroupId() != null) {
            sql.SET("group_id = #{groupId,jdbcType=BIGINT}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            sql.SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("user_group_relation_id = #{userGroupRelationId,jdbcType=BIGINT}");
        
        return sql.toString();
    }
}