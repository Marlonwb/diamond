package person.marlon.diamond.dao.group_resource_authority;

import java.util.Date;

public class GroupResourceAuthority {
    private Long groupResourceAuthorityId;

    private Long resourceId;

    private Long groupId;

    private String authority;

    private Date createTime;

    private Date modifyTime;

    private String comment;

    public GroupResourceAuthority(Long groupResourceAuthorityId, Long resourceId, Long groupId, String authority, Date createTime, Date modifyTime, String comment) {
        this.groupResourceAuthorityId = groupResourceAuthorityId;
        this.resourceId = resourceId;
        this.groupId = groupId;
        this.authority = authority;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.comment = comment;
    }

    public GroupResourceAuthority() {
        super();
    }

    public Long getGroupResourceAuthorityId() {
        return groupResourceAuthorityId;
    }

    public void setGroupResourceAuthorityId(Long groupResourceAuthorityId) {
        this.groupResourceAuthorityId = groupResourceAuthorityId;
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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