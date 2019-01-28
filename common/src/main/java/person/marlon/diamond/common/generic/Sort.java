package person.marlon.diamond.common.generic;

import com.google.common.base.CaseFormat;
import person.marlon.diamond.common.enums.SortOrderEnum;

public class Sort {
    
    private String sortField; // The default sort field can be specified in the constructor.
    private String sortType = SortOrderEnum.DESC.getValue(); // default:SortOrderEnum.DESC.getValue()|SortOrderEnum.ASC.getValue()
    
    public Sort() {
    }
    
    public Sort(String sortField) {
        this.sortField = convertCamelToUnderlinePattern(sortField);
    }
    
    public Sort(String sortField, Boolean ascSortType) {
        this.sortField = convertCamelToUnderlinePattern(sortField);
        if(ascSortType){
            this.sortType = SortOrderEnum.ASC.getValue();
        }
        // false , no need to set sortType = SortOrderEnum.DESC.getValue() again.(default value).
    }
    
    public String getSortField() {
        return sortField;
    }
    
    public void setSortField(String sortField) {
        this.sortField = sortField;
    }
    
    public String getSortType() {
        return sortType;
    }
    
    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
    
    /**
     * Convert camel characters to underscore characters (database case insensitive):
     *
     *  TestData-->test_data
     */
    private String convertCamelToUnderlinePattern(String field){
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field);
    }
    
}
