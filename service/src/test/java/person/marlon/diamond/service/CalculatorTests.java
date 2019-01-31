package person.marlon.diamond.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.junit.jupiter.api.Test;
import person.marlon.diamond.common.util.TimeUtil;
import person.marlon.diamond.service.test.Calculator;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.util.*;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CalculatorTests {

    @Test
    void add() {
        Calculator calculator = new Calculator();
        assertEquals(3, calculator.add(1, 2), "1 + 1 should equal 2");
    }

    @Test
    void test(){
        JsonObject jsonObject = new Gson().fromJson("{\n" +
				"\t\"ok\": true,\n" +
				"\t\"user\": {\n" +
				"\t\t\"name\": \"Sonny Whether\",\n" +
				"\t\t\"id\": \"U0G9QF9C6\"\n" +
				"\t\t},\n" +
				"\t\"team\": {\n" +
				"\t\t\"id\": \"T0G9PQBBK\"\n" +
				"\t\t}\n" +
				"}", JsonObject.class);

        Boolean state = jsonObject.get("ok").getAsBoolean();
        System.out.println("state = " + state);
        JsonObject user = jsonObject.getAsJsonObject("user");
        String name = user.get("name").getAsString();
		System.out.println("name = " + name);
        String id = user.get("id").getAsString();
        System.out.println("id = " + id);

		JsonObject jsonObject1 = new JsonObject();
		jsonObject1.addProperty("response_type","ephemeral");
		jsonObject1.addProperty("text","authorize denied.");
		System.out.println("jsonObject1 = " + jsonObject1);
    }

    @Test
	void test1(){
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response_type","ephemeral");
		jsonObject.addProperty("replace_original",true);
		jsonObject.addProperty("text","当前没有空闲的 web 套餐或可用的 web 套餐\n " +
				"<https://account.ipvideotalk.com | 立即购买> 或 <https://account.ipvideotalk.com | 结束其他会议>");
		System.out.println("jsonObject = " + jsonObject);
	}
	@Test
	void test2(){
		Gson gson = new GsonBuilder().setPrettyPrinting().create();//格式化Json:(gson.toJson)
		JsonObject interactiveMessage = new JsonObject();
		JsonObject attachment = new JsonObject();
		attachment.addProperty("callback_id", "111_222");
		attachment.addProperty("title","<https://meetings.ipvideotalk.com/101005 | Join Meeting>.");
		JsonArray attachments = new JsonArray();
		attachments.add(attachment);
		interactiveMessage.add("attachments",attachments);

		interactiveMessage.addProperty("text","<@101005> created a meeting.");
		interactiveMessage.addProperty("response_type","in_channel");
		interactiveMessage.addProperty("replace_original",true);

		interactiveMessage.add("attachments",attachments);
		System.out.println("jsonObject = " + interactiveMessage);
	}

	@Test
	void test21(){
//		Gson gson = new GsonBuilder().setPrettyPrinting().create();//格式化Json:(gson.toJson)
		JsonObject interactiveMessage = new JsonObject();

		JsonArray actions = new JsonArray();
		JsonObject basicFreeJoinButton = new JsonObject();
		basicFreeJoinButton.addProperty("name","instant");
		basicFreeJoinButton.addProperty("text","Instant Meeting");
		basicFreeJoinButton.addProperty("type","button");
		basicFreeJoinButton.addProperty("value","to JOIN an instant meeting.");
		basicFreeJoinButton.addProperty("style","default");//default||primary(green)||danger
		actions.add(basicFreeJoinButton);

		JsonObject advancedJoinButton = new JsonObject();
		advancedJoinButton.addProperty("name","scheduled");
		advancedJoinButton.addProperty("text","Scheduled Meeting");
		advancedJoinButton.addProperty("type","button");
		advancedJoinButton.addProperty("value","to JOIN an advanced meeting by your ipvt account.");
		advancedJoinButton.addProperty("style","primary");//default||JsonObject advancedJoinButton = new JsonObject();
		actions.add(advancedJoinButton);

		JsonObject managementButton = new JsonObject();
		managementButton.addProperty("name","management");
		managementButton.addProperty("text","Meeting Management");
		managementButton.addProperty("type","button");
		managementButton.addProperty("value","manage meetings.");
		managementButton.addProperty("style","danger");//default||primary(green)||danger
		actions.add(managementButton);

		JsonArray attachments = new JsonArray();

		JsonObject attachment = new JsonObject();
		attachment.addProperty("callback_id", "111_222");
		attachment.addProperty("title","<https://meetings.ipvideotalk.com/101005 | Join Meeting>.");
		attachment.addProperty("fallback","your current version doesn't support this function yet,please upgrade to the newest version.");
		attachment.addProperty("color","good");//good, warning, danger, or any hex color code (eg. #439FE0)
		attachment.add("actions",actions);
		attachments.add(attachment);

//		JsonObject attachment1 = new JsonObject();
//		attachment.addProperty("callback_id", "111_222_333");
////		attachment.addProperty("title","<https://meetings.ipvideotalk.com/101005 | Join Meeting>.");
////		attachment.addProperty("fallback","your current version doesn't support this function yet,please upgrade to the newest version.");
//		attachment.addProperty("color","warning");
//		attachment.add("actions",actions);
//		attachments.add(attachment1);
//
//		JsonObject attachment2 = new JsonObject();
//		attachment.addProperty("callback_id", "111_222_333_333");
////		attachment.addProperty("title","<https://meetings.ipvideotalk.com/101005 | Join Meeting>.");
////		attachment.addProperty("fallback","your current version doesn't support this function yet,please upgrade to the newest version.");
//		attachment.addProperty("color","danger");
//		attachment.add("actions",actions);
//		attachments.add(attachment2);

		interactiveMessage.add("attachments",attachments);
		interactiveMessage.addProperty("text","joinMeeting");
		interactiveMessage.addProperty("response_type","in_channel");
		//interactiveMessage.addProperty("replace_original",true);

		System.out.println("jsonObject = " + interactiveMessage);
	}

	@Test
	void test3(){
		String name = "aaa    bbbbb       cc".split("\\s+ ",2)[1];
		String args = "aaa   		   bbbbb     cc".split("\\s+ ",1)[0];
		System.out.println("name = " + name);
		System.out.println("name1 = " +args);
		System.out.println("123456789 = " + "123456789".substring(0,8));
		assertTrue(isNumeric("123456789"));
//		assertTrue(isNumeric("adshlsjfl;"));
	}

	@Test
	void test4(){
		String domain = "https://account.ipvideotalk.com";
		String requestURL = null;
		try {
			requestURL = domain + "/meeting/schedule?client_id=" + "aaaaa" + "&team_id=" + "bbbb" + "&redirect_uri=" +
					URLEncoder.encode("https://marlon.test.com", "UTF-8");
		} catch (UnsupportedEncodingException e) {
//			sout.warn("URLEncoder occurred an exception --> {}",e);
//			return generateEphemeralErrorMessage("UnsupportedEncodingException");
		}
		JsonObject scheduledInteractiveMessage = new JsonObject();
		scheduledInteractiveMessage.addProperty("text","请立即<" + requestURL +" | 预约会议>");
		scheduledInteractiveMessage.addProperty("response_type","ephemeral");

//		JsonObject attachment = new JsonObject();
//
//
//		attachment.addProperty("title",requestURL);
//		attachment.addProperty("callback_id","schedule_url");
//
//		JsonArray attachments = new JsonArray();
//		attachments.add(attachment);
//		scheduledInteractiveMessage.add("attachments",attachments);
		System.out.println("scheduledInteractiveMessage = " + scheduledInteractiveMessage);
	}

	@Test
	void test5(){
		String domain = "https://account.ipvideotalk.com";
		JsonObject interactiveMessage = new JsonObject();
		//List<Attachment> attachments = new ArrayList<Attachment>();

		String notConnectedAttachmentTitle = "There's no connection between Slack and the IPVT account yet.\n" +
				"You need to connect your IPVT account first.\n";

		String restParam = null;
		//String domain = ResourceUtil.getAppDomain();后续为域名定制
		try {
			restParam = "?client_id=" + "1111111" + "&team_id=" + "222222222" + "&redirect_uri=" +
					URLEncoder.encode("https://marlon.test.com", "UTF-8");
		} catch (UnsupportedEncodingException e) {
//			logger.warn("URLEncoder occurred an exception --> {}",e);
//			return generateEphemeralErrorMessage("UnsupportedEncodingException");
		}

		String loginURL = domain + "/login" + restParam;
		String signUpURL = domain + "/signup" + restParam;
		//notConnectedAttachmentTitle = notConnectedAttachmentTitle + "Click <" + requestURL + " | here> to connect.\n";

		//interactiveMessage.setText("You need log in your IPVT account first.");
		interactiveMessage.addProperty("text","欢迎使用IPVideoTalk,请先<"+ loginURL +" |登录> 或 <" + signUpURL + "|注册>");
		interactiveMessage.addProperty("response_type","ephemeral");
		System.out.println("interactiveMessage = " + interactiveMessage);
	}

	@Test
	void test6(){
		String scheduleUrl = "aaa.com";
		String managementUrl="bbb.com";
		String email = "bw@test.com";
		JsonObject joinInteractiveMessage = new JsonObject();
		joinInteractiveMessage.addProperty("text",email + ",您现在可以开始会议啦！");

		JsonArray actions = new JsonArray();
		JsonObject basicFreeJoinButton = new JsonObject();
		basicFreeJoinButton.addProperty("name","instant");
		basicFreeJoinButton.addProperty("text","开启即时会议");
		basicFreeJoinButton.addProperty("type","button");
		basicFreeJoinButton.addProperty("value","to JOIN an instant meeting.");
		basicFreeJoinButton.addProperty("style","primary");//default||primary(green)||danger
		actions.add(basicFreeJoinButton);

		JsonArray attachments = new JsonArray();

		JsonObject attachment = new JsonObject();
		attachment.addProperty("callback_id", "111_222");
		//attachment.addProperty("title","<https://meetings.ipvideotalk.com/101005 | Join Meeting>.");
		attachment.addProperty("title","您可以<" + scheduleUrl + "|预约会议> 或进行 <" + managementUrl + "|会议管理>，还可以：");
		attachment.addProperty("fallback","your current version doesn't support this function yet,please upgrade to the newest version.");
		//attachment.addProperty("color","good");//good, warning, danger, or any hex color code (eg. #439FE0)
		attachment.add("actions",actions);
		attachments.add(attachment);

		joinInteractiveMessage.add("attachments",attachments);

		joinInteractiveMessage.addProperty("response_type","in_channel");
		System.out.println("joinInteractiveMessage = " + joinInteractiveMessage);
	}

	@Test
	void test7(){
		String domain = "www.ipvideotalk.com";
		String buyPackageUrl = "account.com";
		String endMeetingUrl = domain + "/meeting/list";
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("response_type","ephemeral");
		jsonObject.addProperty("replace_original",true);
		jsonObject.addProperty("text","当前没有空闲的 web 套餐或可用的 web 套餐，" +
				"<" + buyPackageUrl +" | 购买套餐> 或 <" + endMeetingUrl + " | 结束其他会议>");
		System.out.println("jsonObject = " + jsonObject);
	}

	@Test
	void test8(){
		long time = new Date().getTime();
		Date date = new Date(time);
		System.out.println("date = " + date.toString());
	}

	@Test
	void test9(){
		String responseUrl = "test.com";
		String joinMeetingUrl = "meetings.ipvideotalk.com";
		String meetingNum = "111111";
		String hostCode = "" + 2345;
		String startTimeStr = "11:30  3018";
		String meetingTheme = "哈哈哈哈哈哈哈哈哈哈";

		JsonObject interactiveMessage = new JsonObject();
		//interactiveMessage.addProperty("text","you have created a meeting successfully.\n");
		interactiveMessage.addProperty("text","您已成功创建会议。立即<"+joinMeetingUrl+"|主持会议>.");

		JsonArray attachments = new JsonArray();
		JsonObject titleAttachment = new JsonObject();
		titleAttachment.addProperty("callback_id","title_" + meetingTheme);
		titleAttachment.addProperty("title",meetingTheme);
		attachments.add(titleAttachment);

		JsonObject meetingInfoAttachment = new JsonObject();
		meetingInfoAttachment.addProperty("callback_id","host_meeting_" + meetingNum);
		meetingInfoAttachment.addProperty("title",
				"\n\nMeeting ID : " + meetingNum +
				"\n Meeting Time : " + startTimeStr +
				"\n Host Code :" + hostCode);
		attachments.add(meetingInfoAttachment);
		interactiveMessage.add("attachments",attachments);
		interactiveMessage.addProperty("response_type","ephemeral");
		System.out.println("attachment = " + interactiveMessage);
	}

	@Test
	void test10(){
		String publicJoinMeetingUrl = "meetings.ipvideotalk.com/rtc/dispatch?meetingNum=101005";

		JsonObject interactiveMessage = new JsonObject();
		interactiveMessage.addProperty("text","meeting created by <@" + "dsaflgn" +  ">.");

		JsonArray attachments = new JsonArray();

		//attachment.addProperty("title","<" + publicJoinMeetingUrl + "/" + meetingNum +" | Join Meeting>.");

		//会议标题
		JsonObject titleAttachment = new JsonObject();
		titleAttachment.addProperty("callback_id","in_channel_title_" + "12123");
		titleAttachment.addProperty("title","哈哈哈哈哈哈");
		attachments.add(titleAttachment);

		//会议信息
		JsonObject meetingInfoAttachment = new JsonObject();
		meetingInfoAttachment.addProperty("callback_id","in_channel_meeting_" + 101005);
		meetingInfoAttachment.addProperty("title",
				"\n\nMeeting ID : " + 101005 +
						"\n Meeting Time : " + "11:30  3018"+
						"\n <" + publicJoinMeetingUrl + "|加入会议>");
		attachments.add(meetingInfoAttachment);

		interactiveMessage.add("attachments",attachments);

		interactiveMessage.addProperty("response_type","in_channel");
//		if(replaceFlag){
//			interactiveMessage.addProperty("replace_original",true);
//		}
		System.out.println("interactiveMessage = " + interactiveMessage);
	}

	@Test
	void test11(){
		System.out.println("new String[]{\"1\",\"2\",\"3\"} = " + new Gson().toJson(new String[]{"1", "2", "3"}));
	}

	@Test
	void test12(){
		String joinMeetingUrl = "marlon.test.com";
		String meetingNum = "101005";
		String hostCode = "1234";
		String startTimeStr = "1234:3344";
		String meetingTheme = "哈哈哈";

		JsonObject interactiveMessage = new JsonObject();
		interactiveMessage.addProperty("text","会议未开始。立即 <"+joinMeetingUrl+" | 主持会议>.");

		JsonArray attachments = new JsonArray();

		//会议标题
		JsonObject titleAttachment = new JsonObject();
		titleAttachment.addProperty("callback_id","not_start_" + meetingTheme);
		titleAttachment.addProperty("title",meetingTheme);
		attachments.add(titleAttachment);

		//会议信息
		JsonObject meetingInfoAttachment = new JsonObject();
		meetingInfoAttachment.addProperty("callback_id","host_meeting_" + meetingNum);
		String meetingInfoAttachmentTitle ="\n\nMeeting ID : " + meetingNum +
				"\n Meeting Time : " + startTimeStr +
				"\n Host Code :" + hostCode;

		meetingInfoAttachment.addProperty("title", meetingInfoAttachmentTitle);
		attachments.add(meetingInfoAttachment);
		interactiveMessage.add("attachments",attachments);
		interactiveMessage.addProperty("response_type","ephemeral");
		System.out.println("meetingInfoAttachmentTitle = " + interactiveMessage.toString());
	}

	@Test
	void test13(){
		JsonObject interactiveMessage = new JsonObject();
		interactiveMessage.addProperty("text","此会议号不存在/已结束/已取消");

		//会议信息
		JsonObject attachment = new JsonObject();
		attachment.addProperty("callback_id","not_exist_" + "101005");
		attachment.addProperty("fallback","your current version doesn't support this function yet,please upgrade to the newest version.");
		String domain = "https://account.ipvideotalk.com";
		String requestURL = null;
		try {
			requestURL = domain + "/meeting/schedule?client_id=" + "111" + "&team_id=" + "222" + "&redirect_uri=" +
					URLEncoder.encode("marlon.test.com", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			System.out.println("e = " + e);
		}
		attachment.addProperty("title","您可以<" + requestURL + "|预约会议>");


		JsonArray actions = new JsonArray();
		JsonObject basicFreeJoinButton = new JsonObject();
		basicFreeJoinButton.addProperty("name","instant");
		basicFreeJoinButton.addProperty("text","开启即时会议");
		basicFreeJoinButton.addProperty("type","button");
		basicFreeJoinButton.addProperty("value","to JOIN an instant meeting.");
		basicFreeJoinButton.addProperty("style","primary");//default||primary(green)||danger
		actions.add(basicFreeJoinButton);
		attachment.add("actions",actions);

		JsonArray attachments = new JsonArray();
		attachments.add(attachment);

		interactiveMessage.add("attachments",attachments);
		interactiveMessage.addProperty("response_type","ephemeral");
		System.out.println("interactiveMessage = " + interactiveMessage);
	}

	@Test
	void test14(){
		String text = "meetingsssss";
		if(text.startsWith("meeting")){
			String[] split = text.split("\\s+", 2);//limit = 2,分割成2部分，不管后面是不是带空格都当做一个数
			System.out.println(split.length);
			System.out.println(new Gson().toJson(split));
			//String meetingTheme = "< &nbsp 我是谁 我在哪里，我在干什么";
			//System.out.println(meetingTheme);
		}
	}

	@Test
	void test15(){
		String text = "meetingsssss";
		if(text.startsWith("meeting")){
			String[] split = text.split("\\s+", 2);//limit = 2,分割成2部分，不管后面是不是带空格都当做一个数
			System.out.println(split.length);
			System.out.println(new Gson().toJson(split));
			//String meetingTheme = "< &nbsp 我是谁 我在哪里，我在干什么";
			//System.out.println(meetingTheme);
		}
	}

	@Test
	void test16(){
    	Integer[] a = new Integer[9];
		for(int i = 0;i<9;i++){
			a[i] = getNum(i);
		}
		System.out.println("a = " + Arrays.asList(a));
	}

	Integer getNum(Integer n){
    	if(n == 0){
    		return 1;
		}else if( n > 0){
    		return getNum(n-1) + 2 * n;
		}else{
    		return null;
		}
	}

	@Test
	void test17(){
		Pattern IP_PATTERN = Pattern.compile("^(((1[0-9]{2})|((2[0-4][0-9])|(25[0-5]))|([1-9]{2})|([0-9]))\\.){3}((1[0-9][0-9])|((2[0-4][0-9])|(25[0-5]))|([1-9][0-9])|([0-9]))$");
		assertTrue(IP_PATTERN.matcher("129.123").matches());
		Pattern DOMAIN_PATTERN =Pattern.compile("^((https|http)://)?(((([a-zA-Z0-9]+[-a-zA-Z0-9]*[a-zA-Z0-9]+)|([a-zA-Z0-9]+))\\.){1,2}(([a-zA-Z0-9]+[-a-zA-Z0-9]*[a-zA-Z0-9]+)|([a-zA-Z0-9]+)))$");
		assertTrue(DOMAIN_PATTERN.matcher("https://google.com.dd").matches());
		String domain = "http://api.ipvideotalk.com";
		String s = null;
		if(domain.startsWith("http")){
			s = domain.replaceAll("http://|https://", "");
		}
		System.out.println(s);
		boolean contains = "api.ipvideotalk.com".contains(s);
		System.out.println(contains);
	}

	@Test
	void test18(){
		DecimalFormat df = new DecimalFormat();
		String style = "0.00";
		df.applyPattern(style);
		System.out.println("v-->" + df.format(1.8999999999999999d));  //1.90
		//double v = new BigDecimal(1.9999999999999d).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	@Test
	void test19(){
		int dateDuration = getDateBetween(new Date(1568449546000L),new Date(1536827387000L));
		System.out.println("dateDuration = " + dateDuration);
		double costDifference = 61.7 - 57.6;
		double totalCost = costDifference * (dateDuration / 30) * 1;
		System.out.println("totalCost = " + totalCost);
		double totalCost1 = (costDifference/ 30) * dateDuration * 1;
		System.out.println("totalCost = " + totalCost1);
		double totalCost2 = costDifference / 30 * dateDuration;
		System.out.println("totalCost = " + totalCost2);

	}

	@Test
	void test20(){
		int a = 20;
		double b = (double)a;
		System.out.println("b = " + b);
	}

	@Test
	void test23(){
		JsonObject jsonObject = new JsonObject();
		String a = null;
		jsonObject.addProperty("aaa",a);
		System.out.println("a = " + jsonObject.get("aaa").getAsString());
	}

	@Test
	void test24(){
		System.out.println((59.6d / 30d) * 365 * 1);
	}


	@Test
	void test25(){
    	Integer[] a = new Integer[2];
		GregorianCalendar startCalendar = new GregorianCalendar(2018,10,11);
		System.out.println("start Date  YEAR-MONTH-DAY_OF_MONTH --> " + startCalendar.get(Calendar.YEAR) + "-" +
														+ (startCalendar.get(Calendar.MONTH) + 1)  + "-" +
														+ startCalendar.get(Calendar.DAY_OF_MONTH));
		GregorianCalendar endCalendar = new GregorianCalendar(2018,11,10);
		System.out.println("end Date YEAR-MONTH-DAY_OF_MONTH --> " + endCalendar.get(Calendar.YEAR) + "-" +
														+ (endCalendar.get(Calendar.MONTH) + 1)+ "-" +
														+ endCalendar.get(Calendar.DAY_OF_MONTH));

		GregorianCalendar nextStartCalendar = new GregorianCalendar(startCalendar.get(Calendar.YEAR),startCalendar.get(Calendar.MONTH) + 1,1);
		GregorianCalendar nextEndCalendar = new GregorianCalendar(endCalendar.get(Calendar.YEAR),endCalendar.get(Calendar.MONTH) + 1,1);
		//确定当月最后一天的日期，比较当前时间start是否是当月最后一天，是，就按照结束月份的天数算n个月+多出的天数
		nextStartCalendar.add(Calendar.DATE, -1);
		nextEndCalendar.add(Calendar.DATE, -1);
		System.out.println("start Date month days --> " + nextStartCalendar.get(Calendar.DAY_OF_MONTH));
		System.out.println("end Date month days --> " + nextEndCalendar.get(Calendar.DAY_OF_MONTH));
		int betweenDaysOfDayOfDate = 0;//起始时间和结束时间月份日期天数相差的天数，如15,16，则为-1
		int betweenMonths = endCalendar.get(Calendar.MONTH) - startCalendar.get(Calendar.MONTH);//两个月份的差额，按天（几号确定是否要+-1）
		int betweenYears = endCalendar.get(Calendar.YEAR) - startCalendar.get(Calendar.YEAR);
		//考虑年份
		if(betweenYears > 0){
			betweenMonths += (12 *betweenYears);
		}

		//起始时间距离当前月底相差的天数
		int restDaysToStartMonthEnd = nextStartCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH);
		//结束时间距离当前月底相差的天数
		int restDaysToEndMonthEnd =  nextEndCalendar.get(Calendar.DAY_OF_MONTH) - endCalendar.get(Calendar.DAY_OF_MONTH);

		//如果起始月已经是当前最后一天，直接按结束月的时间天数
		if(restDaysToStartMonthEnd == 0){
			//2-28(非闰)--6-28  3个月 + 28天(当月的月份1要减去)  --2.28 - 7.31(30)这种情形,如果是最后一天，31，算整月，30不算一个月,按结束当月总天数算
			if(restDaysToEndMonthEnd == 0){//3.31-5.31
				a[0] = betweenMonths;
				a[1] = betweenDaysOfDayOfDate;
			}else{//3.31-5.30 | 2-28 - 7-30
				betweenMonths -= 1;
				a[0] = betweenMonths;
				a[1] = endCalendar.get(Calendar.DAY_OF_MONTH);
			}
		}else{//否则按照起始/结束两个月天数日期计算月份天数差值（6.25,3.24）
			betweenDaysOfDayOfDate = endCalendar.get(Calendar.DAY_OF_MONTH) - startCalendar.get(Calendar.DAY_OF_MONTH);
			if(betweenDaysOfDayOfDate >= 0){//1.1-2.1
				//如果结束的时间是当月最后一天
				if(restDaysToEndMonthEnd == 0){//6.1-7.31 | 6.1-6.30 | 2.27-7.31
					//退款当天都不算，比如月底订购，月底退款（6.30-8.31），直接计算7、8,而如果7.1号退，则7月少算一天（restDaysToStartMonthEnd应该为30）
					a[0] = betweenMonths;
					a[1] = restDaysToStartMonthEnd;
				}else{//2.27-7.30
					a[0] = betweenMonths;
					a[1] = betweenDaysOfDayOfDate;//天数按相差日期算
				}
			}else{
				//到这里，不可能开始和结束的日期相减超过30了，超过的情况上面已经判断过2.28-7.31这种（除非是一个月31天，另一个月1天才可能，但是上面开始月最后一天已判断过）
				//if(betweenDaysOfDayOfDate < -30){//7.30-8.1 | 7.30-9.1
					betweenMonths -= 1;
					betweenDaysOfDayOfDate = betweenDaysOfDayOfDate + 30;//1.30-2.1，这里要把结束当天给加上
					a[0] = betweenMonths;
					a[1] = betweenDaysOfDayOfDate;
				//}
			}
		}

		System.out.println("betweenMonths = " + a[0]);
		System.out.println("betweenDays = " + a[1]);

		//boolean leapYear = calendar.isLeapYear(2001);
		//System.out.println(leapYear);
	}

	@Test
	void test26(){
    	Calendar calendar = Calendar.getInstance();
		TimeZone timeZone = calendar.getTimeZone();
		System.out.println("timeZone = " + timeZone);
		int timeZoneOffset = TimeUtil.getTimeZoneOffset(timeZone);
		System.out.println("timeZoneOffset = " + timeZoneOffset);
		String id = timeZone.getID();
		int timeZoneOffset1 = TimeUtil.getTimeZoneOffset(id);
		System.out.println("timeZoneOffset1 = " + timeZoneOffset1);
		TimeZone timeZone1 = TimeZone.getTimeZone(id);
		System.out.println("timeZone1 = " + timeZone1);
	}
	
	@Test
	void test27(){
		int i = Runtime.getRuntime().availableProcessors();
		System.out.println("availableProcessors = " + i);
		long l = Runtime.getRuntime().freeMemory();
		System.out.println("freeMemory = " + l);
		long l1 = Runtime.getRuntime().totalMemory();
		System.out.println("totalMemory = " + l1);
//		Runtime.getRuntime().gc();
		
	}

	public static int getDateBetween(Date first,Date second){
		long diff = first.getTime()-second.getTime();
		long daysmill = 24*60*60*1000 ;
		int a = (int) Math.ceil(diff/daysmill);
		return a;
	}

	/**
	 * 是否是数字
	 *
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		if (str == null) {
			return false;
		}
		int sz = str.length();
		if (sz == 0) {
			return false;
		}
		for (int i = 0; i < sz; i++) {
			if (Character.isDigit( str.charAt( i ) ) == false) {
				return false;
			}
		}
		return true;
	}
}
