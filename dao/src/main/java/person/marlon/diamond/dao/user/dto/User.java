package person.marlon.diamond.dao.user.dto;

import java.util.Date;

public class User {
    private Long userId;

    private String nickname;

    private String password;

    private String firstname;

    private String lastname;

    private String middlename;

    private Integer nameShow;//0:first name first|1:last name first

    private String identity;

    private String phone;

    private String email;

    private Integer isEmailVerified;//0:not verified 1:verified

    private Date createTime;

    private Date modifyTime;

    private String iconUrl;//user profile icon.

    private String address;

    private Integer sex;//0-male 1-female 2-unknown

    private String preferLanguage;

    private Integer state;//0--frozen 1--normal -1--deleted

    private Long mainGroupId;

    public User(Long userId, String nickname, String password, String firstname, String lastname, String middlename, Integer nameShow, String identity, String phone, String email, Integer isEmailVerified, Date createTime, Date modifyTime, String iconUrl, String address, Integer sex, Integer state, String preferLanguage, Long mainGroupId) {
        this.userId = userId;
        this.nickname = nickname;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.nameShow = nameShow;
        this.identity = identity;
        this.phone = phone;
        this.email = email;
        this.isEmailVerified = isEmailVerified;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.iconUrl = iconUrl;
        this.address = address;
        this.sex = sex;
        this.state = state;
        this.preferLanguage = preferLanguage;
        this.mainGroupId = mainGroupId;
    }

    public User() {
        super();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname == null ? null : firstname.trim();
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname == null ? null : lastname.trim();
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename == null ? null : middlename.trim();
    }

    public Integer getNameShow() {
        return nameShow;
    }

    public void setNameShow(Integer nameShow) {
        this.nameShow = nameShow;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Integer getIsEmailVerified() {
        return isEmailVerified;
    }

    public void setIsEmailVerified(Integer isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
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

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl == null ? null : iconUrl.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getPreferLanguage() {
        return preferLanguage;
    }

    public void setPreferLanguage(String preferLanguage) {
        this.preferLanguage = preferLanguage == null ? null : preferLanguage.trim();
    }

    public Long getMainGroupId() {
        return mainGroupId;
    }

    public void setMainGroupId(Long mainGroupId) {
        this.mainGroupId = mainGroupId;
    }
}