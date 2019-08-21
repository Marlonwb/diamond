package person.marlon.diamond.common.dto;

import java.util.Date;

public class UserGroupRelation {
    private Long userGroupRelationId;

    private Long userId;

    private Long groupId;

    private Date createTime;

    private Date modifyTime;

    public UserGroupRelation(Long userGroupRelationId, Long userId, Long groupId, Date createTime, Date modifyTime) {
        this.userGroupRelationId = userGroupRelationId;
        this.userId = userId;
        this.groupId = groupId;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
    }

    public UserGroupRelation() {
        super();
    }

    public Long getUserGroupRelationId() {
        return userGroupRelationId;
    }

    public void setUserGroupRelationId(Long userGroupRelationId) {
        this.userGroupRelationId = userGroupRelationId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
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
}