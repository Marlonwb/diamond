package person.marlon.diamond.dao.group;

import java.util.Date;

public class Group {
    private Long groupId;

    private String groupName;

    private Date createTime;

    private Date modifyTime;

    private String comment;

    public Group(Long groupId, String groupName, Date createTime, Date modifyTime, String comment) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.comment = comment;
    }

    public Group() {
        super();
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName == null ? null : groupName.trim();
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