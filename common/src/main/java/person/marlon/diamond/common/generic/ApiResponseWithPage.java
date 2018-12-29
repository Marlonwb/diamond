package person.marlon.diamond.common.generic;

import com.google.gson.Gson;

public class ApiResponseWithPage<E> extends ApiResponse<E> {
    //    "pageNum": 1,
//    "totalCount": 20,
//    "totalPage": 3,
    private int pageNum = 1;
    private int totalCount ;
    private int totalPage;
    
    public ApiResponseWithPage (E data) {
        super(data);
    }
    
    public E getData() {
        return super.getData();
    }

    public ApiResponseWithPage(){
        super();
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
    
    @Override
    public String toString() {
        return  new Gson().toJson(this);
    }
}
