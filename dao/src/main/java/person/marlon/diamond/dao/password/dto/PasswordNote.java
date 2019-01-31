package person.marlon.diamond.dao.password.dto;

import java.util.Date;

public class PasswordNote {
    private Integer id;

    private String account;

    private String password;

    private String platform;

    private String category;

    private String comment;

    private Date lastModified;

    private Date createdTime;

    private Long phoneNo;

    private String email;

    private String secureInfo;
    
    private String displayName;

    public PasswordNote(Integer id, String account, String password, String platform, String category, String comment, Date lastModified, Date createdTime, Long phoneNo, String email, String secureInfo,String displayName) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.platform = platform;
        this.category = category;
        this.comment = comment;
        this.lastModified = lastModified;
        this.createdTime = createdTime;
        this.phoneNo = phoneNo;
        this.email = email;
        this.secureInfo = secureInfo;
        this.displayName = displayName;
    }

    public PasswordNote() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getLastModified() {
        return lastModified;
    }

    public void setLastModified(Date lastModified) {
        this.lastModified = lastModified;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Long getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(Long phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getSecureInfo() {
        return secureInfo;
    }

    public void setSecureInfo(String secureInfo) {
        this.secureInfo = secureInfo == null ? null : secureInfo.trim();
    }
    
    public String getDisplayName() {
        return displayName;
    }
    
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}