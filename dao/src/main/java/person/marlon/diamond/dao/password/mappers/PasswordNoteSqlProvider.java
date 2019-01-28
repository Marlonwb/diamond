package person.marlon.diamond.dao.password.mappers;

import org.apache.ibatis.jdbc.SQL;
import person.marlon.diamond.common.generic.Page;
import person.marlon.diamond.dao.password.dto.PasswordNote;

import java.util.Map;

public class PasswordNoteSqlProvider {

    public String insertSelective(PasswordNote record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("password_note");
        
        if (record.getAccount() != null) {
            sql.VALUES("account", "#{account,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.VALUES("password", "#{password,jdbcType=VARCHAR}");
        }
        
        if (record.getPlatform() != null) {
            sql.VALUES("platform", "#{platform,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.VALUES("category", "#{category,jdbcType=VARCHAR}");
        }
        
        if (record.getComment() != null) {
            sql.VALUES("comment", "#{comment,jdbcType=VARCHAR}");
        }
        
        if (record.getLastModified() != null) {
            sql.VALUES("last_modified", "#{lastModified,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.VALUES("created_time", "#{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getPhoneNo() != null) {
            sql.VALUES("phone_no", "#{phoneNo,jdbcType=BIGINT}");
        }
        
        if (record.getEmail() != null) {
            sql.VALUES("email", "#{email,jdbcType=VARCHAR}");
        }
        
        if (record.getSecureInfo() != null) {
            sql.VALUES("secure_info", "#{secureInfo,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(PasswordNote record) {
        SQL sql = new SQL();
        sql.UPDATE("password_note");
        
        if (record.getAccount() != null) {
            sql.SET("account = #{account,jdbcType=VARCHAR}");
        }
        
        if (record.getPassword() != null) {
            sql.SET("password = #{password,jdbcType=VARCHAR}");
        }
        
        if (record.getPlatform() != null) {
            sql.SET("platform = #{platform,jdbcType=VARCHAR}");
        }
        
        if (record.getCategory() != null) {
            sql.SET("category = #{category,jdbcType=VARCHAR}");
        }
        
        if (record.getComment() != null) {
            sql.SET("comment = #{comment,jdbcType=VARCHAR}");
        }
        
        if (record.getLastModified() != null) {
            sql.SET("last_modified = #{lastModified,jdbcType=TIMESTAMP}");
        }
        
        if (record.getCreatedTime() != null) {
            sql.SET("created_time = #{createdTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getPhoneNo() != null) {
            sql.SET("phone_no = #{phoneNo,jdbcType=BIGINT}");
        }
        
        if (record.getEmail() != null) {
            sql.SET("email = #{email,jdbcType=VARCHAR}");
        }
        
        if (record.getSecureInfo() != null) {
            sql.SET("secure_info = #{secureInfo,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
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
    public String getPassNotesList(Map<String, Object> searchMap, Page page){
        
        SQL sql = new SQL();
    
        sql.SELECT("id, account, password, platform, category, comment, last_modified, created_time, phone_no, email, secure_info");
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
            
            if(searchMap.get("platform") != null){
                //sql.WHERE("platform like CONCAT('%','${searchMap.platform}','%')");
                sql.WHERE("platform like CONCAT('%','#{platform}','%')");
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
    
    public String countPassNotesList(Map<String, Object> searchMap, Page page){
        
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
            
            if(searchMap.get("platform") != null){
                //sql.WHERE("platform like CONCAT('%','${searchMap.platform}','%')");
                sql.WHERE("platform like CONCAT('%','#{platform}','%')");
                //"platform like CONCAT('%'," + searchMap.get("platform") + ",'%')");
            }
            if(searchMap.get("email") != null){
//                //sql.WHERE("platform like CONCAT('%','${searchMap.platform}','%')");
                sql.WHERE("email like CONCAT('%','"+ searchMap.get("email") +"','%')");
                //"platform like CONCAT('%'," + searchMap.get("platform") + ",'%')");
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