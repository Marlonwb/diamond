package person.marlon.diamond.common.generic;

import com.google.gson.*;

import java.util.List;

public class ApiPageResponse<T> extends ApiResponse<T> {
//    "pageNum": 1,
//    "totalCount": 20,
//    "totalPage": 3,
    private Page page;
    
    public Page getPage() {
        return page;
    }
    
    public void setPage(Page page) {
        this.page = page;
    }
    
    public ApiPageResponse (T data) {
        super(data);
    }
    
    public ApiPageResponse (T data,Page page) {
        super(data);
        this.page = page;
    }
    
    public ApiPageResponse (int retCode,String msg) {
        super(retCode,msg);
    }
    
    public ApiPageResponse (T data,int retCode,String msg) {
        super(data,retCode,msg);
    }
    
    @Override
    public String toString() {
       JsonObject jsonObject = new JsonObject();
        T data = super.getData();
        JsonElement jsonElement;
        
        if(data == null){
            jsonElement = JsonNull.INSTANCE;
        } else if(data instanceof List){
            jsonElement = new Gson().toJsonTree(data).getAsJsonArray();
        }else{
            jsonElement = new Gson().toJsonTree(data).getAsJsonObject();
        }
        //todo 待优化：能否直接用反射，把父类对象属性遍历放进去？然后再把子类这几个要返回的分页属性返回
        if(jsonElement.isJsonNull()){
            jsonObject.addProperty("data", "" );
        }else{
            jsonObject.add("data", jsonElement);
        }
       
        jsonObject.addProperty("msg",super.getMsg());
        jsonObject.addProperty("retCode",super.getRetCode());
        if(this.page != null){
            jsonObject.addProperty("pageNum",this.page.getPageNum());
            jsonObject.addProperty("totalCount",this.page.getTotalCount());
            jsonObject.addProperty("totalPage",this.page.getTotalPage());
        }
        
        return  jsonObject.toString();
    }
}
