package person.marlon.diamond.common.dto;

import java.util.Date;

public class UserResourceAuthority {
    private Long userResourceAuthorityId;

    private Long resourceId;

    private Long userId;

    private String authority;

    private Date createTime;

    private Date modifyTime;

    private String comment;

    public UserResourceAuthority(Long userResourceAuthorityId, Long resourceId, Long userId, String authority, Date createTime, Date modifyTime, String comment) {
        this.userResourceAuthorityId = userResourceAuthorityId;
        this.resourceId = resourceId;
        this.userId = userId;
        this.authority = authority;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.comment = comment;
    }

    public UserResourceAuthority() {
        super();
    }

    public Long getUserResourceAuthorityId() {
        return userResourceAuthorityId;
    }

    public void setUserResourceAuthorityId(Long userResourceAuthorityId) {
        this.userResourceAuthorityId = userResourceAuthorityId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority == null ? null : authority.trim();
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }
}