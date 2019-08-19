package person.marlon.diamond.common.generic;

public class Page {
    
    private Integer pageNum = 1;
    private Integer totalCount;
    private Integer totalPage;
    private Integer pageSize = 20;
    private Integer offset = (pageNum - 1) * pageSize; // the page first record offset = (pageNum - 1) * pageSize;
    private Sort sort; //sort params
    
    public Page(){
    }
    
    public Page(Integer pageNum){
        this.pageNum = pageNum;
        this.offset = (pageNum - 1) * pageSize;
        // this.sort = new Sort();
    }
    
    public Page(String sortField){
        // this.offset = (pageNum - 1) * pageSize;
        this.sort = new Sort(sortField);
    }
    
    public Page(String sortField, boolean ascSortType) {
        // this.offset = (pageNum - 1) * pageSize;
        if(ascSortType){//specify the sort type not as default
            this.sort = new Sort(sortField, true);
        }else{
            this.sort = new Sort(sortField);
        }
    }
    
    public Page(int pageNum,int pageSize){
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        //this.sort = new Sort();
        this.offset = (pageNum - 1) * pageSize;
    }
    
    public Page(int pageNum, int pageSize, String sortField) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.offset = (pageNum - 1) * pageSize;
        this.sort = new Sort(sortField);
    }
    
    public Page(int pageNum, int pageSize, String sortField, boolean ascSortType) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.offset = (pageNum - 1) * pageSize;
        if(ascSortType){//specify the sort type. (default:desc)
            this.sort = new Sort(sortField, true);
        }else{
            this.sort = new Sort(sortField);
        }
    }
    
    public void calculateTotalPage(Integer totalCount){
        if(totalCount == null){
            this.totalPage = 1;
            return;
        }
        
        int totalPage;
        Integer pageSize = this.pageSize;
        if(pageSize == null){
            totalPage = 1;
        }else{
            int i = totalCount % pageSize;
            if(i == 0){
                totalPage = totalCount/pageSize;
            }else{
                totalPage = totalCount/pageSize + 1;
            }
        }
        this.totalPage = totalPage;
    }
    
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
        this.offset = (pageNum - 1) * pageSize;
    }
    
    public void setOffset(int offset) {
        this.offset = offset;
    }
    
    public Sort getSort() {
        return sort;
    }
    
    public void setSort(Sort sort) {
        this.sort = sort;
    }
    
    public int getPageNum() {
        return pageNum;
    }
    
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    
    public int getTotalCount() {
        return totalCount;
    }
    
    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
    
    public int getTotalPage() {
        return totalPage;
    }
    
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    
    public Integer getPageSize() {
        return pageSize;
    }
    
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
    
    public Integer getOffset() {
        return offset;
    }
    
    public void setOffset(Integer offset) {
        this.offset = offset;
    }
}
