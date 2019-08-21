package person.marlon.diamond.common.dto;

import java.util.Date;

public class Resource {
    private Long resourceId;

    private String resourceName;

    private Date createTime;

    private Date modifyTime;

    private String comment;

    public Resource(Long resourceId, String resourceName, Date createTime, Date modifyTime, String comment) {
        this.resourceId = resourceId;
        this.resourceName = resourceName;
        this.createTime = createTime;
        this.modifyTime = modifyTime;
        this.comment = comment;
    }

    public Resource() {
        super();
    }

    public Long getResourceId() {
        return resourceId;
    }

    public void setResourceId(Long resourceId) {
        this.resourceId = resourceId;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName == null ? null : resourceName.trim();
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