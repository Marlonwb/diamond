package person.marlon.diamond.dao.journal.mappers;

import org.apache.ibatis.jdbc.SQL;
import person.marlon.diamond.common.generic.Page;
import person.marlon.diamond.dao.journal.Journal;

import java.util.List;
import java.util.Map;

public class JournalSqlProvider {

    public String insertSelective(Journal record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("journal");
        
        if (record.getPublicationNameZh() != null) {
            sql.VALUES("`publication_name_zh`", "#{publicationNameZh,jdbcType=VARCHAR}");
        }
        
        if (record.getPublicationNameEn() != null) {
            sql.VALUES("`publication_name_en`", "#{publicationNameEn,jdbcType=VARCHAR}");
        }
        
        if (record.getUsedPublicationName() != null) {
            sql.VALUES("`used_publication_name`", "#{usedPublicationName,jdbcType=VARCHAR}");
        }
        
        if (record.getDominantAgency() != null) {
            sql.VALUES("dominant_agency", "#{dominantAgency,jdbcType=VARCHAR}");
        }
        
        if (record.getOrganizer() != null) {
            sql.VALUES("organizer", "#{organizer,jdbcType=VARCHAR}");
        }
        
        if (record.getPublicationStart() != null) {
            sql.VALUES("publication_start", "#{publicationStart,jdbcType=INTEGER}");
        }
        
        if (record.getPublisher() != null) {
            sql.VALUES("publisher", "#{publisher,jdbcType=VARCHAR}");
        }
        
        if (record.getIssn() != null) {
            sql.VALUES("issn", "#{issn,jdbcType=VARCHAR}");
        }
        
        if (record.getCssn() != null) {
            sql.VALUES("cssn", "#{cssn,jdbcType=VARCHAR}");
        }
        
        if (record.getDistributionProvince() != null) {
            sql.VALUES("distribution_province", "#{distributionProvince,jdbcType=VARCHAR}");
        }
        
        if (record.getChiefEditor() != null) {
            sql.VALUES("chief_editor", "#{chiefEditor,jdbcType=VARCHAR}");
        }
        
        if (record.getPublicationCycle() != null) {
            sql.VALUES("publication_cycle", "#{publicationCycle,jdbcType=INTEGER}");
        }
        
        if (record.getPostalDistributeCode() != null) {
            sql.VALUES("postal_distribute_code", "#{postalDistributeCode,jdbcType=VARCHAR}");
        }
        
        if (record.getPostcode() != null) {
            sql.VALUES("postcode", "#{postcode,jdbcType=INTEGER}");
        }
        
        if (record.getReviewDuration() != null) {
            sql.VALUES("review_duration", "#{reviewDuration,jdbcType=INTEGER}");
        }
        
        if (record.getLanguage() != null) {
            sql.VALUES("language", "#{language,jdbcType=VARCHAR}");
        }
        
        if (record.getCompoundInfluenceFactor() != null) {
            sql.VALUES("compound_influence_factor", "#{compoundInfluenceFactor,jdbcType=VARCHAR}");
        }
        
        if (record.getComprehensiveImpactFactor() != null) {
            sql.VALUES("comprehensive_impact_factor", "#{comprehensiveImpactFactor,jdbcType=VARCHAR}");
        }
        
        if (record.getHonor() != null) {
            sql.VALUES("honor", "#{honor,jdbcType=VARCHAR}");
        }
        
        if (record.getJournalCover() != null) {
            sql.VALUES("journal_cover", "#{journalCover,jdbcType=VARCHAR}");
        }
        
        if (record.getCheifColumns() != null) {
            sql.VALUES("cheif_columns", "#{cheifColumns,jdbcType=VARCHAR}");
        }
        
        if (record.getMajorId() != null) {
            sql.VALUES("major_id", "#{majorId,jdbcType=INTEGER}");
        }
        
        if (record.getCategory() != null) {
            sql.VALUES("category", "#{category,jdbcType=VARCHAR}");
        }
        
        if (record.getJournalLevel() != null) {
            sql.VALUES("journal_level", "#{journalLevel,jdbcType=VARCHAR}");
        }
        
        if (record.getJournalInclusion() != null) {
            sql.VALUES("journal_inclusion", "#{journalInclusion,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            sql.VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(Journal record) {
        SQL sql = new SQL();
        sql.UPDATE("journal");
        
        if (record.getPublicationNameZh() != null) {
            sql.SET("`publication_name_zh` = #{publicationNameZh,jdbcType=VARCHAR}");
        }
        
        if (record.getPublicationNameEn() != null) {
            sql.SET("`publication_name_en` = #{publicationNameEn,jdbcType=VARCHAR}");
        }
        
        if (record.getUsedPublicationName() != null) {
            sql.SET("`used_publication_name` = #{usedPublicationName,jdbcType=VARCHAR}");
        }
        
        if (record.getDominantAgency() != null) {
            sql.SET("dominant_agency = #{dominantAgency,jdbcType=VARCHAR}");
        }
        
        if (record.getOrganizer() != null) {
            sql.SET("organizer = #{organizer,jdbcType=VARCHAR}");
        }
        
        if (record.getPublicationStart() != null) {
            sql.SET("publication_start = #{publicationStart,jdbcType=INTEGER}");
        }
        
        if (record.getPublisher() != null) {
            sql.SET("publisher = #{publisher,jdbcType=VARCHAR}");
        }
        
        if (record.getIssn() != null) {
            sql.SET("issn = #{issn,jdbcType=VARCHAR}");
        }
        
        if (record.getCssn() != null) {
            sql.SET("cssn = #{cssn,jdbcType=VARCHAR}");
        }
        
        if (record.getDistributionProvince() != null) {
            sql.SET("distribution_province = #{distributionProvince,jdbcType=VARCHAR}");
        }
        
        if (record.getChiefEditor() != null) {
            sql.SET("chief_editor = #{chiefEditor,jdbcType=VARCHAR}");
        }
        
        if (record.getPublicationCycle() != null) {
            sql.SET("publication_cycle = #{publicationCycle,jdbcType=INTEGER}");
        }
        
        if (record.getPostalDistributeCode() != null) {
            sql.SET("postal_distribute_code = #{postalDistributeCode,jdbcType=VARCHAR}");
        }
        
        if (record.getPostcode() != null) {
            sql.SET("postcode = #{postcode,jdbcType=INTEGER}");
        }
        
        if (record.getReviewDuration() != null) {
            sql.SET("review_duration = #{reviewDuration,jdbcType=INTEGER}");
        }
        
        if (record.getLanguage() != null) {
            sql.SET("language = #{language,jdbcType=VARCHAR}");
        }
        
        if (record.getCompoundInfluenceFactor() != null) {
            sql.SET("compound_influence_factor = #{compoundInfluenceFactor,jdbcType=VARCHAR}");
        }
        
        if (record.getComprehensiveImpactFactor() != null) {
            sql.SET("comprehensive_impact_factor = #{comprehensiveImpactFactor,jdbcType=VARCHAR}");
        }
        
        if (record.getHonor() != null) {
            sql.SET("honor = #{honor,jdbcType=VARCHAR}");
        }
        
        if (record.getJournalCover() != null) {
            sql.SET("journal_cover = #{journalCover,jdbcType=VARCHAR}");
        }
        
        if (record.getCheifColumns() != null) {
            sql.SET("cheif_columns = #{cheifColumns,jdbcType=VARCHAR}");
        }
        
        if (record.getMajorId() != null) {
            sql.SET("major_id = #{majorId,jdbcType=INTEGER}");
        }
        
        if (record.getCategory() != null) {
            sql.SET("category = #{category,jdbcType=VARCHAR}");
        }
        
        if (record.getJournalLevel() != null) {
            sql.SET("journal_level = #{journalLevel,jdbcType=VARCHAR}");
        }
        
        if (record.getJournalInclusion() != null) {
            sql.SET("journal_inclusion = #{journalInclusion,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            sql.SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("journal_id = #{journalId,jdbcType=BIGINT}");
        
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
    public String getList(Map<String, Object> searchMap, Page page){

        SQL sql = new SQL();

        sql.SELECT("journal_id, publication_name_zh, publication_name_en, used_publication_name," +
                "dominant_agency, organizer, publication_start, publisher, issn, cssn, distribution_province," +
                "chief_editor, publication_cycle, postal_distribute_code, postcode, review_duration," +
                "language, compound_influence_factor, comprehensive_impact_factor, honor, journal_cover," +
                "cheif_columns, major_id, category, journal_level, journal_inclusion, create_time, " +
                "modify_time");
        sql.FROM("journal");
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

            // 所属专业
            if(searchMap.get("level") != null){
                if(searchMap.get("majorId") != null){
                    int level = Integer.parseInt((String)searchMap.get("level"));
                    // 最底层专业
                    if(level == 2){
                        sql.WHERE("major_id = #{searchMap.majorId,javaType=Integer}");
                    }else{
                        // 动态拼接：参考 https://blog.csdn.net/fengchen0123456789/article/details/79287598
                        List<Integer> list = (List<Integer>) searchMap.get("majorIds");
                        StringBuilder range = new StringBuilder();
                        range.append("(");
                        for (int i = 0; i < list.size(); i++) {
                            range.append(list.get(i));
                            if (i < list.size() - 1) {
                                range.append(",");
                            }
                        }
                        range.append(")");
                        sql.WHERE("major_id in " + range);
                    }
                }
            }else{
                if(searchMap.get("majorId") != null){
                    //sql.WHERE("platform like CONCAT('%','${searchMap.platform}','%')");
                    sql.WHERE("major_id = #{searchMap.majorId,javaType=Integer}");
                    //"platform like CONCAT('%'," + searchMap.get("platform") + ",'%')");
                }
            }
            //快捷分类
            if(searchMap.get("category") != null){
//                //sql.WHERE("platform like CONCAT('%','${searchMap.platform}','%')");
                sql.WHERE("category= #{searchMap.category,jdbcType=VARCHAR}");
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

    public String countList(Map<String, Object> searchMap, Page page){

        SQL sql = new SQL();

        sql.SELECT("count(1)");

        sql.FROM("journal");
        sql.WHERE("1=1");
        if(searchMap!= null && searchMap.size()>0){
            // 所属专业
            if(searchMap.get("level") != null){
                if(searchMap.get("majorId") != null){
                    int level = Integer.parseInt((String)searchMap.get("level"));
                    // 最底层专业
                    if(level == 2){
                        sql.WHERE("major_id = #{searchMap.majorId,javaType=Integer}");
                    }else{
                        // 动态拼接：参考 https://blog.csdn.net/fengchen0123456789/article/details/79287598
                        List<Integer> list = (List<Integer>) searchMap.get("majorIds");
                        StringBuilder range = new StringBuilder();
                        range.append("(");
                        for (int i = 0; i < list.size(); i++) {
                            range.append(list.get(i));
                            if (i < list.size() - 1) {
                                range.append(",");
                            }
                        }
                        range.append(")");
                        sql.WHERE("major_id in " + range);
                    }
                }
            }else{
                if(searchMap.get("majorId") != null){
                    //sql.WHERE("platform like CONCAT('%','${searchMap.platform}','%')");
                    sql.WHERE("major_id = #{searchMap.majorId,javaType=Integer}");
                    //"platform like CONCAT('%'," + searchMap.get("platform") + ",'%')");
                }
            }
            //快捷分类
            if(searchMap.get("category") != null){
//                //sql.WHERE("platform like CONCAT('%','${searchMap.platform}','%')");
                sql.WHERE("category= #{searchMap.category,jdbcType=VARCHAR}");
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