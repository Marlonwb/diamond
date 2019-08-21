package person.marlon.diamond.common.dto;

import java.util.Date;

public class Journal {
    private Long journalId;

    private String publicationNameZh;

    private String publicationNameEn;

    private String usedPublicationName;

    private String dominantAgency;

    private String organizer;

    private Integer publicationStart;

    private String publisher;

    private String issn;

    private String cssn;

    private String distributionProvince;

    private String chiefEditor;

    private Integer publicationCycle;

    private String postalDistributeCode;

    private Integer postcode;

    private Integer reviewDuration;

    private String language;

    private String compoundInfluenceFactor;

    private String comprehensiveImpactFactor;

    private String honor;

    private String journalCover;

    private String cheifColumns;

    private Integer majorId;

    private String category;

    private String journalLevel;

    private String journalInclusion;

    private Date createTime;

    private Date modifyTime;



    private String[] majors;

    public Journal(Long journalId, String publicationNameZh, String publicationNameEn, String usedPublicationName, String dominantAgency, String organizer, Integer publicationStart, String publisher, String issn, String cssn, String distributionProvince, String chiefEditor, Integer publicationCycle, String postalDistributeCode, Integer postcode, Integer reviewDuration, String language, String compoundInfluenceFactor, String comprehensiveImpactFactor, String honor, String journalCover, String cheifColumns, Integer majorId, String category, String journalLevel, String journalInclusion, Date createTime, Date modifyTime) {
        this.journalId = journalId;
        this.publicationNameZh = publicationNameZh;
        this.publicationNameEn = publicationNameEn;
        this.usedPublicationName = usedPublicationName;
        this.dominantAgency = dominantAgency;
        this.organizer = organizer;
        this.publicationStart = publicationStart;
        this.publisher = publisher;
        this.issn = issn;
        this.cssn = cssn;
        this.distributionProvince = distributionProvince;
        this.chiefEditor = chiefEditor;
        this.publicationCycle = publicationCycle;
        this.postalDistributeCode = postalDistributeCode;
        this.postcode = postcode;
        this.reviewDuration = reviewDuration;
        this.language = language;
        this.compoundInfluenceFactor = compoundInfluenceFactor;
        this.comprehensiveImpactFactor = comprehensiveImpactFactor;
        this.honor = honor;
        this.journalCover = journalCover;
        this.cheifColumns = cheifColumns;
        this.majorId = majorId;
        this.category = category;
        this.journalLevel = journalLevel;
        this.journalInclusion = journalInclusion;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public Journal() {
        super();
    }

    public Long getJournalId() {
        return journalId;
    }

    public void setJournalId(Long journalId) {
        this.journalId = journalId;
    }

    public String getPublicationNameZh() {
        return publicationNameZh;
    }

    public void setPublicationNameZh(String publicationNameZh) {
        this.publicationNameZh = publicationNameZh == null ? null : publicationNameZh.trim();
    }

    public String getPublicationNameEn() {
        return publicationNameEn;
    }

    public void setPublicationNameEn(String publicationNameEn) {
        this.publicationNameEn = publicationNameEn == null ? null : publicationNameEn.trim();
    }

    public String getUsedPublicationName() {
        return usedPublicationName;
    }

    public void setUsedPublicationName(String usedPublicationName) {
        this.usedPublicationName = usedPublicationName == null ? null : usedPublicationName.trim();
    }

    public String getDominantAgency() {
        return dominantAgency;
    }

    public void setDominantAgency(String dominantAgency) {
        this.dominantAgency = dominantAgency == null ? null : dominantAgency.trim();
    }

    public String getOrganizer() {
        return organizer;
    }

    public void setOrganizer(String organizer) {
        this.organizer = organizer == null ? null : organizer.trim();
    }

    public Integer getPublicationStart() {
        return publicationStart;
    }

    public void setPublicationStart(Integer publicationStart) {
        this.publicationStart = publicationStart;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public String getIssn() {
        return issn;
    }

    public void setIssn(String issn) {
        this.issn = issn == null ? null : issn.trim();
    }

    public String getCssn() {
        return cssn;
    }

    public void setCssn(String cssn) {
        this.cssn = cssn == null ? null : cssn.trim();
    }

    public String getDistributionProvince() {
        return distributionProvince;
    }

    public void setDistributionProvince(String distributionProvince) {
        this.distributionProvince = distributionProvince == null ? null : distributionProvince.trim();
    }

    public String getChiefEditor() {
        return chiefEditor;
    }

    public void setChiefEditor(String chiefEditor) {
        this.chiefEditor = chiefEditor == null ? null : chiefEditor.trim();
    }

    public Integer getPublicationCycle() {
        return publicationCycle;
    }

    public void setPublicationCycle(Integer publicationCycle) {
        this.publicationCycle = publicationCycle;
    }

    public String getPostalDistributeCode() {
        return postalDistributeCode;
    }

    public void setPostalDistributeCode(String postalDistributeCode) {
        this.postalDistributeCode = postalDistributeCode == null ? null : postalDistributeCode.trim();
    }

    public Integer getPostcode() {
        return postcode;
    }

    public void setPostcode(Integer postcode) {
        this.postcode = postcode;
    }

    public Integer getReviewDuration() {
        return reviewDuration;
    }

    public void setReviewDuration(Integer reviewDuration) {
        this.reviewDuration = reviewDuration;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language == null ? null : language.trim();
    }

    public String getCompoundInfluenceFactor() {
        return compoundInfluenceFactor;
    }

    public void setCompoundInfluenceFactor(String compoundInfluenceFactor) {
        this.compoundInfluenceFactor = compoundInfluenceFactor == null ? null : compoundInfluenceFactor.trim();
    }

    public String getComprehensiveImpactFactor() {
        return comprehensiveImpactFactor;
    }

    public void setComprehensiveImpactFactor(String comprehensiveImpactFactor) {
        this.comprehensiveImpactFactor = comprehensiveImpactFactor == null ? null : comprehensiveImpactFactor.trim();
    }

    public String getHonor() {
        return honor;
    }

    public void setHonor(String honor) {
        this.honor = honor == null ? null : honor.trim();
    }

    public String getJournalCover() {
        return journalCover;
    }

    public void setJournalCover(String journalCover) {
        this.journalCover = journalCover == null ? null : journalCover.trim();
    }

    public String getCheifColumns() {
        return cheifColumns;
    }

    public void setCheifColumns(String cheifColumns) {
        this.cheifColumns = cheifColumns == null ? null : cheifColumns.trim();
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getJournalLevel() {
        return journalLevel;
    }

    public void setJournalLevel(String journalLevel) {
        this.journalLevel = journalLevel == null ? null : journalLevel.trim();
    }

    public String getJournalInclusion() {
        return journalInclusion;
    }

    public void setJournalInclusion(String journalInclusion) {
        this.journalInclusion = journalInclusion == null ? null : journalInclusion.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String[] getMajors() {
        return majors;
    }

    public void setMajors(String[] majors) {
        this.majors = majors;
    }
}