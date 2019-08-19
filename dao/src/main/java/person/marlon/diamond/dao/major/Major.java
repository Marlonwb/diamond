package person.marlon.diamond.dao.major;

public class Major {
    private Integer majorId;

    private String name;

    private Integer level;

    private Integer parentMajorId;

    public Major(Integer majorId, String name, Integer level, Integer parentMajorId) {
        this.majorId = majorId;
        this.name = name;
        this.level = level;
        this.parentMajorId = parentMajorId;
    }

    public Major() {
        super();
    }

    public Integer getMajorId() {
        return majorId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getParentMajorId() {
        return parentMajorId;
    }

    public void setParentMajorId(Integer parentMajorId) {
        this.parentMajorId = parentMajorId;
    }
}