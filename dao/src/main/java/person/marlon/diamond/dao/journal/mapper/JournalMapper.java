package person.marlon.diamond.dao.journal.mapper;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import person.marlon.diamond.common.generic.Page;
import person.marlon.diamond.common.dto.Journal;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface JournalMapper {
    @Delete({
        "delete from journal",
        "where journal_id = #{journalId,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long journalId);

    @Insert({
        "insert into journal (`publication_name_zh`, `publication_name_en`, ",
        "`used_publication_name`, dominant_agency, ",
        "organizer, publication_start, ",
        "publisher, issn, ",
        "cssn, distribution_province, ",
        "chief_editor, publication_cycle, ",
        "postal_distribute_code, postcode, ",
        "review_duration, language, ",
        "compound_influence_factor, comprehensive_impact_factor, ",
        "honor, journal_cover, ",
        "cheif_columns, major_id, ",
        "category, journal_level, ",
        "journal_inclusion, create_time, ",
        "modify_time)",
        "values (#{publicationNameZh,jdbcType=VARCHAR}, #{publicationNameEn,jdbcType=VARCHAR}, ",
        "#{usedPublicationName,jdbcType=VARCHAR}, #{dominantAgency,jdbcType=VARCHAR}, ",
        "#{organizer,jdbcType=VARCHAR}, #{publicationStart,jdbcType=INTEGER}, ",
        "#{publisher,jdbcType=VARCHAR}, #{issn,jdbcType=VARCHAR}, ",
        "#{cssn,jdbcType=VARCHAR}, #{distributionProvince,jdbcType=VARCHAR}, ",
        "#{chiefEditor,jdbcType=VARCHAR}, #{publicationCycle,jdbcType=INTEGER}, ",
        "#{postalDistributeCode,jdbcType=VARCHAR}, #{postcode,jdbcType=INTEGER}, ",
        "#{reviewDuration,jdbcType=INTEGER}, #{language,jdbcType=VARCHAR}, ",
        "#{compoundInfluenceFactor,jdbcType=VARCHAR}, #{comprehensiveImpactFactor,jdbcType=VARCHAR}, ",
        "#{honor,jdbcType=VARCHAR}, #{journalCover,jdbcType=VARCHAR}, ",
        "#{cheifColumns,jdbcType=VARCHAR}, #{majorId,jdbcType=INTEGER}, ",
        "#{category,jdbcType=VARCHAR}, #{journalLevel,jdbcType=VARCHAR}, ",
        "#{journalInclusion,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, ",
        "#{modifyTime,jdbcType=TIMESTAMP})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="journalId", before=false, resultType=Long.class)
    int insert(Journal record);

    @InsertProvider(type=JournalSqlProvider.class, method="insertSelective")
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="journalId", before=false, resultType=Long.class)
    int insertSelective(Journal record);

    @Select({
        "select",
        "journal_id, `publication_name_zh`, `publication_name_en`, `used_publication_name`, ",
        "dominant_agency, organizer, publication_start, publisher, issn, cssn, distribution_province, ",
        "chief_editor, publication_cycle, postal_distribute_code, postcode, review_duration, ",
        "language, compound_influence_factor, comprehensive_impact_factor, honor, journal_cover, ",
        "cheif_columns, major_id, category, journal_level, journal_inclusion, create_time, ",
        "modify_time",
        "from journal",
        "where journal_id = #{journalId,jdbcType=BIGINT}"
    })
    @ConstructorArgs({
        @Arg(column="journal_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
        @Arg(column="publication_name_zh", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="publication_name_en", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="used_publication_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="dominant_agency", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="organizer", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="publication_start", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="publisher", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="issn", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="cssn", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="distribution_province", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="chief_editor", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="publication_cycle", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="postal_distribute_code", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="postcode", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="review_duration", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="language", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="compound_influence_factor", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="comprehensive_impact_factor", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="honor", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="journal_cover", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="cheif_columns", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="major_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
        @Arg(column="category", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="journal_level", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="journal_inclusion", javaType=String.class, jdbcType=JdbcType.VARCHAR),
        @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
        @Arg(column="modify_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP)
    })
    Journal selectByPrimaryKey(Long journalId);

    @UpdateProvider(type=JournalSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Journal record);

    @Update({
        "update journal",
        "set `publication_name_zh` = #{publicationNameZh,jdbcType=VARCHAR},",
          "`publication_name_en` = #{publicationNameEn,jdbcType=VARCHAR},",
          "`used_publication_name` = #{usedPublicationName,jdbcType=VARCHAR},",
          "dominant_agency = #{dominantAgency,jdbcType=VARCHAR},",
          "organizer = #{organizer,jdbcType=VARCHAR},",
          "publication_start = #{publicationStart,jdbcType=INTEGER},",
          "publisher = #{publisher,jdbcType=VARCHAR},",
          "issn = #{issn,jdbcType=VARCHAR},",
          "cssn = #{cssn,jdbcType=VARCHAR},",
          "distribution_province = #{distributionProvince,jdbcType=VARCHAR},",
          "chief_editor = #{chiefEditor,jdbcType=VARCHAR},",
          "publication_cycle = #{publicationCycle,jdbcType=INTEGER},",
          "postal_distribute_code = #{postalDistributeCode,jdbcType=VARCHAR},",
          "postcode = #{postcode,jdbcType=INTEGER},",
          "review_duration = #{reviewDuration,jdbcType=INTEGER},",
          "language = #{language,jdbcType=VARCHAR},",
          "compound_influence_factor = #{compoundInfluenceFactor,jdbcType=VARCHAR},",
          "comprehensive_impact_factor = #{comprehensiveImpactFactor,jdbcType=VARCHAR},",
          "honor = #{honor,jdbcType=VARCHAR},",
          "journal_cover = #{journalCover,jdbcType=VARCHAR},",
          "cheif_columns = #{cheifColumns,jdbcType=VARCHAR},",
          "major_id = #{majorId,jdbcType=INTEGER},",
          "category = #{category,jdbcType=VARCHAR},",
          "journal_level = #{journalLevel,jdbcType=VARCHAR},",
          "journal_inclusion = #{journalInclusion,jdbcType=VARCHAR},",
          "create_time = #{createTime,jdbcType=TIMESTAMP},",
          "modify_time = #{modifyTime,jdbcType=TIMESTAMP}",
        "where journal_id = #{journalId,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Journal record);

    @SelectProvider(type=JournalSqlProvider.class, method="getList")
//    @Select({
//            "select",
//            "id, account, password, platform, category, comment, last_modified, created_time, ",
//            "phone_no, email, secure_info",
//            "from password_note"
//    })
    @ConstructorArgs({
            @Arg(column="journal_id", javaType=Long.class, jdbcType=JdbcType.BIGINT, id=true),
            @Arg(column="publication_name_zh", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="publication_name_en", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="used_publication_name", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="dominant_agency", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="organizer", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="publication_start", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="publisher", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="issn", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="cssn", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="distribution_province", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="chief_editor", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="publication_cycle", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="postal_distribute_code", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="postcode", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="review_duration", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="language", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="compound_influence_factor", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="comprehensive_impact_factor", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="honor", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="journal_cover", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="cheif_columns", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="major_id", javaType=Integer.class, jdbcType=JdbcType.INTEGER),
            @Arg(column="category", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="journal_level", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="journal_inclusion", javaType=String.class, jdbcType=JdbcType.VARCHAR),
            @Arg(column="create_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP),
            @Arg(column="modify_time", javaType=Date.class, jdbcType=JdbcType.TIMESTAMP)
    })
    List<Journal> getList(Map<String, Object> searchMap, Page page);

    @SelectProvider(type=JournalSqlProvider.class, method="countList")
//    @Select({
//            "select",
//            "id, account, password, platform, category, comment, last_modified, created_time, ",
//            "phone_no, email, secure_info",
//            "from password_note"
//    })
    @ResultType(Integer.class)
    Integer countList(Map<String, Object> searchMap, Page page);

}