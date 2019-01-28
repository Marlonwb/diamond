package person.marlon.diamond.common.generic;

import com.google.gson.Gson;

public class ApiResponse<T> {
    
    private T data;
    private String msg = "";
    private int retCode = 0;
    
//    public <T> ApiResponse(T data) {
//    }
    
    public ApiResponse() {
    
    }
    
    public ApiResponse (ApiResponse<T> apiResponse) {
        this.data = apiResponse.getData();
        this.msg = apiResponse.getMsg();
        this.retCode = apiResponse.getRetCode();
    }
    
    public ApiResponse (T data) {
        this.data = data;
    }
    
    public ApiResponse (int retCode,String msg) {
        this.retCode = retCode;
        this.msg = msg;
    }
    
    public ApiResponse (T data,int retCode,String msg) {
        this.data = data;
        this.retCode = retCode;
        this.msg = msg;
    }
    
    public T getData(){
        return this.data;
    }
    
    public void setData(T data) {
        this.data = data;
    }
    
    public String getMsg() {
        return msg;
    }
    
    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    public int getRetCode() {
        return retCode;
    }
    
    public void setRetCode(int retCode) {
        this.retCode = retCode;
    }
    
    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
