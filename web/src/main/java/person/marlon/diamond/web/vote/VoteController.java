package person.marlon.diamond.web.vote;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import person.marlon.diamond.common.generic.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/vote")
public class VoteController {
    private Map<String,Integer> votes = new HashMap<>();
    private Map<String,Integer> validVotePersnMap = new HashMap<>();
    private final Integer VOTE_LIMIT_COUNT = 1;
    private final Object lock = new Object();
    
    @RequestMapping(value = "/play",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String vote(HttpServletRequest request, @RequestParam(defaultValue = "default")String item){
        Integer value = votes.get(item) == null ? 0 : votes.get(item);
        String sessionId = request.getSession().getId();
    
        int voteCount = validVotePersnMap.get(sessionId) == null ? 0 : validVotePersnMap.get(sessionId);
        //该用户是否达到了投票限制
        if(voteCount < VOTE_LIMIT_COUNT){
            synchronized (lock){
                //有可能多个请求同时判断少于投票设置，加锁后再次判断
                voteCount = validVotePersnMap.get(sessionId) == null ? 0 : validVotePersnMap.get(sessionId);
                if(voteCount < VOTE_LIMIT_COUNT){
                    validVotePersnMap.put(sessionId,++voteCount);
                    votes.put(item,++value);
                }else{
                    return new ApiResponse<>(votes,-1,"You have reached the maximum voting limit, thank you for your participation!").toString();
                }
            }
        }else{
            return new ApiResponse<>(votes,-1,"You have reached the maximum voting limit, thank you for your participation!").toString();
        }
        return new ApiResponse<>(votes).toString();
    }
}
