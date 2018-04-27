package person.marlon.diamond.common.demo.model;

public class Option {

    private String key;

    private String value;

    private String jumpContentKey;

    public Option(String key, String value,String jumpContentKey) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getJumpContentKey() {
        return jumpContentKey;
    }

    public void setJumpContentKey(String jumpContentKey) {
        this.jumpContentKey = jumpContentKey;
    }
}
