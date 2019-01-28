package person.marlon.diamond.common.enums;

public enum SortOrderEnum {
    
    ASC("asc"),DESC("desc");
    
    private String value;
    
    private SortOrderEnum(String value) {
        this.value = value;
    }
    
    public String getValue(){
        return this.value;
    }
}
