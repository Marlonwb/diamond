package person.marlon.diamond.dao.user.mapper;

import org.apache.ibatis.jdbc.SQL;
import person.marlon.diamond.common.generic.Page;
import person.marlon.diamond.dao.user.dto.User;

import java.util.Map;

public class UserSqlProvider {

    public String insertSelective(User record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("user");
        
        if (record.getNickname() != null) {
            sql.VALUES("nickname", "#{nickname,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getFirstname() != null) {
            sql.VALUES("firstname", "#{firstname,jdbcType=VARCHAR}");
        }
        
        if (record.getLastname() != null) {
            sql.VALUES("lastname", "#{lastname,jdbcType=VARCHAR}");
        }
        
        if (record.getMiddlename() != null) {
            sql.VALUES("middlename", "#{middlename,jdbcType=VARCHAR}");
        }
        
        if (record.getNameShow() != null) {
            sql.VALUES("name_show", "#{nameShow,jdbcType=INTEGER}");
        }
        
        if (record.getIdentity() != null) {
            sql.VALUES("identity", "#{identity,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            sql.VALUES("phone", "#{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.VALUES("email", "#{email,jdbcType=VARCHAR}");
        }
        
        if (record.getIsEmailVerified() != null) {
            sql.VALUES("is_email_verified", "#{isEmailVerified,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            sql.VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIconUrl() != null) {
            sql.VALUES("icon_url", "#{iconUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.VALUES("address", "#{address,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            sql.VALUES("sex", "#{sex,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            sql.VALUES("state", "#{state,jdbcType=INTEGER}");
        }
        
        if (record.getPreferLanguage() != null) {
            sql.VALUES("prefer_language", "#{preferLanguage,jdbcType=VARCHAR}");
        }
        
        if (record.getMainGroupId() != null) {
            sql.VALUES("main_group_id", "#{mainGroupId,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(User record) {
        SQL sql = new SQL();
        sql.UPDATE("user");
        
        if (record.getNickname() != null) {
            sql.SET("nickname = #{nickname,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getFirstname() != null) {
            sql.SET("firstname = #{firstname,jdbcType=VARCHAR}");
        }
        
        if (record.getLastname() != null) {
            sql.SET("lastname = #{lastname,jdbcType=VARCHAR}");
        }
        
        if (record.getMiddlename() != null) {
            sql.SET("middlename = #{middlename,jdbcType=VARCHAR}");
        }
        
        if (record.getNameShow() != null) {
            sql.SET("name_show = #{nameShow,jdbcType=INTEGER}");
        }
        
        if (record.getIdentity() != null) {
            sql.SET("identity = #{identity,jdbcType=VARCHAR}");
        }
        
        if (record.getPhone() != null) {
            sql.SET("phone = #{phone,jdbcType=VARCHAR}");
        }
        
        if (record.getEmail() != null) {
            sql.SET("email = #{email,jdbcType=VARCHAR}");
        }
        
        if (record.getIsEmailVerified() != null) {
            sql.SET("is_email_verified = #{isEmailVerified,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            sql.SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getIconUrl() != null) {
            sql.SET("icon_url = #{iconUrl,jdbcType=VARCHAR}");
        }
        
        if (record.getAddress() != null) {
            sql.SET("address = #{address,jdbcType=VARCHAR}");
        }
        
        if (record.getSex() != null) {
            sql.SET("sex = #{sex,jdbcType=INTEGER}");
        }
        
        if (record.getState() != null) {
            sql.SET("state = #{state,jdbcType=INTEGER}");
        }
        
        if (record.getPreferLanguage() != null) {
            sql.SET("prefer_language = #{preferLanguage,jdbcType=VARCHAR}");
        }
        
        if (record.getMainGroupId() != null) {
            sql.SET("main_group_id = #{mainGroupId,jdbcType=BIGINT}");
        }
        
        sql.WHERE("user_id = #{userId,jdbcType=BIGINT}");
        
        return sql.toString();
    }

    /**
     * return new SQL() {{
     *             SELECT("P.ID, P.USERNAME, P.PASSWORD, P.FIRST_NAME, P.LAST_NAME");
     *             FROM("PERSON P");
     *             if (id != null) {
     *                 WHERE("P.ID like #{id}");
     *             }
     *             if (firstName != null) {
     *                 WHERE("P.FIRST_NAME like #{firstName}");
     *             }
     *             if (lastName != null) {
     *                 WHERE("P.LAST_NAME like #{lastName}");
     *             }
     *             ORDER_BY("P.LAST_NAME");
     *         }}.toString();
     * @param page
     * @return
     */
    public String getUserList(Map<String, Object> searchMap, Page page){

        SQL sql = new SQL();

        sql.SELECT("id, account, password, platform, category, comment, last_modified, created_time, phone_no, email, secure_info, display_name");
        sql.FROM("password_note");
        sql.WHERE("1=1");
        if(searchMap!=null && searchMap.size()>0){
//            if(searchMap.get("minAccount") != null && searchMap.get("maxAccount") != null){// filter integer
//                sql.WHERE(" AND" +
//                        "                   <![CDATA[" +
//                        "total >=#{searchMap.minAccount,jdbcType=DOUBLE}" +
//                        "AND total<=#{searchMap.maxAccount,jdbcType=DOUBLE}" +
//                        "]]>");
//            }

//            if(searchMap.get("start") != null && searchMap.get("end") != null){// filter time
//                sql.WHERE(" AND" +
//                        "AND" +
//                        "<![CDATA[" +
//                        "apply_time >=#{searchMap.start,jdbcType=TIMESTAMP}" +
//                        "AND apply_time <=#{searchMap.end,jdbcType=TIMESTAMP}" +
//                        "]]>");
//            }

            if(searchMap.get("name") != null){
                //sql.WHERE("platform like CONCAT('%','${searchMap.platform}','%')");
                sql.WHERE("nickname like CONCAT('%','"+ searchMap.get("name") +"','%')");
                //"platform like CONCAT('%'," + searchMap.get("platform") + ",'%')");
            }
            if(searchMap.get("email") != null){
//                //sql.WHERE("platform like CONCAT('%','${searchMap.platform}','%')");
                sql.WHERE("email like CONCAT('%','"+ searchMap.get("email") +"','%')");
//                //"platform like CONCAT('%'," + searchMap.get("platform") + ",'%')");
            }
        }

        if(page.getSort() != null){
            sql.ORDER_BY( page.getSort().getSortField() + " " + page.getSort().getSortType());
        }

        if(page.getOffset() != null && page.getPageSize() != null){
            return sql.usingAppender(new StringBuilder()).append(" limit ").append(page.getOffset()).append(",").append(page.getPageSize()).toString();
        }

        return sql.toString();
    }

    public String countUserList(Map<String, Object> searchMap, Page page){

        SQL sql = new SQL();

        sql.SELECT("count(1)");

        sql.FROM("password_note");
        sql.WHERE("1=1");
        if(searchMap!= null && searchMap.size()>0){
//            if(searchMap.get("minAccount") != null && searchMap.get("maxAccount") != null){// filter integer
//                sql.WHERE(" AND" +
//                        "                   <![CDATA[" +
//                        "total >=#{searchMap.minAccount,jdbcType=DOUBLE}" +
//                        "AND total<=#{searchMap.maxAccount,jdbcType=DOUBLE}" +
//                        "]]>");
//            }

//            if(searchMap.get("start") != null && searchMap.get("end") != null){// filter time
//                sql.WHERE(" AND" +
//                        "AND" +
//                        "<![CDATA[" +
//                        "apply_time >=#{searchMap.start,jdbcType=TIMESTAMP}" +
//                        "AND apply_time <=#{searchMap.end,jdbcType=TIMESTAMP}" +
//                        "]]>");
//            }

            if(searchMap.get("name") != null){
                //sql.WHERE("platform like CONCAT('%','${searchMap.platform}','%')");
                sql.WHERE("nickname like CONCAT('%','"+ searchMap.get("name") +"','%')");
                //"platform like CONCAT('%'," + searchMap.get("platform") + ",'%')");
            }
            if(searchMap.get("email") != null){
//                //sql.WHERE("platform like CONCAT('%','${searchMap.platform}','%')");
                sql.WHERE("email like CONCAT('%','"+ searchMap.get("email") +"','%')");
//                //"platform like CONCAT('%'," + searchMap.get("platform") + ",'%')");
            }
        }
        //The following two are not necessary.
//        if(page.getSort() != null){
//            sql.ORDER_BY( page.getSort().getSortField() + " " + page.getSort().getSortType());
//        }
//
//        if(page.getOffset() != null && page.getPageSize() != null){
//            return sql.usingAppender(new StringBuilder()).append("limit ").append(page.getOffset()).append(",").append(page.getPageSize()).toString();
//        }

        return sql.toString();
    }

}