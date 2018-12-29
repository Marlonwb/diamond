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
    
    @Override
    public String toString() {
        return  new Gson().toJson(this);
    }
}
