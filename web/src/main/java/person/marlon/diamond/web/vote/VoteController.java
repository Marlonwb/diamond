package person.marlon.diamond.web.vote;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import person.marlon.diamond.common.generic.ApiResponse;
import person.marlon.diamond.common.util.WebUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/vote")
public class VoteController {
    
    private Logger logger = LoggerFactory.getLogger(VoteController.class);
    
    private Map<String,JsonObject> votes = new HashMap<>();//vote的item-count映射关系,以及voteIpList
    private Map<String,Integer> validVotePersonMap = new HashMap<>(); //ip-投票次数的映射
    private final Integer VOTE_LIMIT_COUNT = 1;
    private final Object lock = new Object();
    
    @RequestMapping(value = "/add", method = RequestMethod.POST,
            consumes = "application/json",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String vote(HttpServletRequest request, @RequestBody Map<String, Object> map){
        String ipAddr = WebUtil.getIpAddr(request);
    
        logger.info("received user [{}] /vote/add request param --> {}", ipAddr,new Gson().toJson(map));
    
//        String sessionId = request.getSession().getId();
        int voteCount = validVotePersonMap.get(ipAddr) == null ? 0 : validVotePersonMap.get(ipAddr);
        //该用户是否达到了投票限制
        if(voteCount < VOTE_LIMIT_COUNT){
            synchronized (lock){
                
                String item = map.get("item") + "";
                JsonObject jsonObject = votes.get(item) == null ? new JsonObject() :  votes.get(item);
                int amount = jsonObject.get("amount") == null ? 0 : jsonObject.get("amount").getAsInt();
                JsonArray voteIpList = jsonObject.get("voteIpList") == null ? new JsonArray() : jsonObject.get("voteIpList").getAsJsonArray();
                
                //有可能多个请求同时判断少于投票设置，加锁后再次判断
                voteCount = validVotePersonMap.get(ipAddr) == null ? 0 : validVotePersonMap.get(ipAddr);
                if(voteCount < VOTE_LIMIT_COUNT){
                    validVotePersonMap.put(ipAddr,++voteCount);
                    voteIpList.add(ipAddr);
                    jsonObject.addProperty("amount",++amount);
                    jsonObject.add("voteIpList",voteIpList);
                    votes.put(item,jsonObject);
                    logger.info("user [{}] voted success --> {}", ipAddr,new Gson().toJson(map));
                }else{
                    JsonArray voteList = populateVoteList(votes);
                    return new ApiResponse<>(voteList,-1,"You have reached the maximum voting limit, thank you for your participation!").toString();
                }
            }
        }else{
            JsonArray voteList = populateVoteList(votes);
            return new ApiResponse<>(voteList,-1,"You have reached the maximum voting limit, thank you for your participation!").toString();
        }
        JsonArray voteList = populateVoteList(votes);
        return new ApiResponse<>(voteList).toString();
    }
    
    /**
     * {
     * "data":[
     *          {
     *              "item":"小品演出：三国杀",
     *              "amount":6,
     *              "voteIpList":["192.168.126.110","192.168.126.131","192.168.127.242",
     *                "192.168.200.8","192.168.126.103","192.168.126.142"]
     *          },
     *          {
     *              "item":"合唱：《好汉歌》",
     *              "amount":4,
     *              "voteIpList":["192.168.127.142","192.168.127.137","192.168.127.248","192.168.127.230"]
     *          }
     *      ],
     * "msg":"",
     * "retCode":0
     * }
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET,
            consumes = "application/json",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String voteList(HttpServletRequest request, @RequestParam Map<String, Object> map){
        logger.info("received user [{}] /vote/list request param --> {}", WebUtil.getIpAddr(request),new Gson().toJson(map));
    
        return new ApiResponse<>(populateVoteList(votes)).toString();
    }
    
    @RequestMapping(value = "/clear", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String clear(HttpServletRequest request, @RequestParam Map<String, Object> map){
        logger.info("received user [{}] /vote/clear request param --> {}", WebUtil.getIpAddr(request),new Gson().toJson(map));
        validVotePersonMap.clear();
        votes.clear();
        return new ApiResponse<>(populateVoteList(votes)).toString();
    }
    
    private JsonArray populateVoteList(Map<String,JsonObject> votes){
        JsonArray jsonArray = new JsonArray();
        for(Map.Entry<String,JsonObject> vote : votes.entrySet()){
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("item",vote.getKey());
            JsonObject voteObj = vote.getValue();
            jsonObject.addProperty("amount",voteObj.get("amount").getAsInt());
            jsonObject.add("voteIpList",voteObj.get("voteIpList").getAsJsonArray());
            jsonArray.add(jsonObject);
        }
        return jsonArray;
    }
}
