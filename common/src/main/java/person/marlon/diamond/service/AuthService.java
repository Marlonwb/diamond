package person.marlon.diamond.service;

import org.springframework.stereotype.Component;
import person.marlon.diamond.util.HttpUtil;

import javax.annotation.PostConstruct;

/**
 * Created by Lenovo on 2017/4/5.
 *
 */
@Component
public class AuthService {
    public static String getToken(){
      return HttpUtil.sendGet("https://accounts.google.com/o/oauth2/auth","redirect_uri=http://localhost:8080/demo/greeting&prompt=consent&response_type=code&scope=https://www.googleapis.com/auth/youtube&client_id=812649657716-903hsoh1h342o2396kpbgpq75kargb5k.apps.googleusercontent.com&access_type=offline");
    }

    public static void main(String[] args){
       String result = getToken();
    }
}
