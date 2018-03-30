package person.marlon.diamond.controller;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.LocaleResolver;
import person.marlon.diamond.demo.model.Content;
import person.marlon.diamond.demo.model.Option;
import org.springframework.ui.Model;
import person.marlon.diamond.util.I18nUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Controller
@RequestMapping("/**")
public class BaseController {

    @Resource
    private LocaleResolver localeResolver;

    /**
     * 真·送命题
     * 假设你是一名生活在A市的普通十八岁学生，由于成绩不理想你参加了艺术生培训班，并希望走艺术生这条路给你的高考加分。测试开始于病毒
     * 爆发时的A市南大街，下午四点半，北方的天空下着清雪，天色却已经暗了下来，有远方大厦的灯光闪烁。你背着书包，站在十字路口，等着对面
     * 的小红人变绿。你刚从艺术班回来，准备回学校上课，并和同桌姜生谈一些事儿。测试正式开始。
     */
    private Map<String,Content> selections = new HashMap<>(64);//一共53题，用最接近的2的N次方
    {
        selections.put("1",new Content("第一题:你等着红绿灯并打量着过往的匆匆行人，寒冷也阻挡不了他们的脚步。这时，" +
                "人流中一个漂亮美女突然倒在地上，口吐鲜血，四周立马围了一圈人，并造成了短暂的交通瘫痪。但没有一个人打算扶起她，" +
                "还好有人拨打了急救电话。你并没有凑热闹的打算，快步离开现场。这时，人群中突然大喊一句:“杀人啦！”然后整个街道顿时混乱" +
                "了起来，你发现还有许多人也昏迷在地，血流不止。这时你的脑海中已经浮现了“丧尸”这个词，于是你快步向家的方向跑去，" +
                "但学校就在你对面，倒地昏迷的人越来越多，混乱也越来越大，此时的你该何去何从？",
                new Option("a","回家（跳转到第二题）","2"),
                new Option("b","去学校（跳转到第三题）","3"),
                new Option("c","原地搜寻一些武器（跳转到第四题）","4")));

        selections.put("2",new Content("第二题:面对未知的恐惧，还是先回家最好。而且父母也说不定在家。你快速奔跑着，这时你" +
                "已经确定是真正的丧尸爆发了，你没有太多时间了!就在这紧急关头，一个该挨千刀的、丑的不能再丑的丧尸出现在你回家的必经之" +
                "路上!你该怎么办？",
                new Option("a","绕远道走胡同避开这只丧尸（跳转到第五题）","5"),
                new Option("b","你没时间了，直接弄死它！（跳转到第六题）","6")));

        selections.put("3",new Content("第三题:你准备先去学校看看，因为那里有你的老哥们儿，有你的同学，还有你暗恋已久的女" +
                "神。而且你关于“丧尸”的启蒙老师就是同桌姜生，他是个资深的ss迷，不得不说你的选择很不错，你一路狂奔到学校操场，" +
                "发现没有丧尸追来后才小心翼翼的向教学楼而去，你已经知道这个世界将要变成丧尸的乐园，趁丧尸还没有染指校园，你必须抓" +
                "紧时间和你的小伙伴们会合!但现在问题出现了，你居然忘记了去锁学校大门!而你已经跑出很远了，你该怎么办？",
                new Option("a","不用担心，找到小伙伴才是关键（跳转到第七题）","7"),
                new Option("b","你不太放心，折回去锁好大门再找小伙伴（跳转到第八题）","8")));

        selections.put("4",new Content("第四题:你原地观察了一下，发现的确是末日丧尸病毒出现了，但第一波受感染的全是抵抗力" +
                "弱和免疫力弱的人群，比如老人，小孩。你觉得必须先找个趁手的武器才能大大增加生存几率，于是你环顾四周，发现了这两个武器。",
                new Option("a","M9手枪（跳转到第九题）","9"),
                new Option("b","菜刀（跳转到第十题）","10")));

        selections.put("5",new Content("第五题:你的想法非常明智，大街是人流最密集的区域之一，也一定十分混乱，走胡同虽然远，" +
                "但及时保证了自己的安全。你闪进胡同，没有丧尸发现你，你跑啊跑，这时一个东西吸了你的注意。在胡同的某角落，一堆废钢筋" +
                "丢弃在那里，你环顾四周，胡同里只有一户人家的窗户是亮着的，幽深昏暗的胡同让你有点害怕，此时你该怎么办？",
                new Option("a","拿一根钢筋，保险（跳转到第十三题）","13"),
                new Option("b","不拿，直接离开（跳转到第十一题）","11"),
                new Option("c","去敲这户人家的门（跳转到第十二题）","12")));

        selections.put("6",new Content("第六题:\n 时间太紧迫了，丧尸显然发现了你，并向你走了过来。你发现地上有一块板砖，你该怎么办?",
                new Option("a","弯腰捡砖时间不够，徒手轮它（跳转到第十四题）","14"),
                new Option("b","尽量快速的捡板砖（跳转到第十五题）","15")));

        //TODO 结果项
        selections.put("7",new Content("第七题:你觉得没有必要为锁一个校门而浪费时间，于是你进入了教学楼，因为病毒大多愿意" +
                "侵入免疫系统弱的老人和小孩，你的学校没有爆发疫情。你，姜生，还有其他小伙伴很容易的将唯一一个丧尸（老校长）杀死了，" +
                "此时一大群街道上的丧尸冲入了校园并引发了巨大的骚乱，你虽然有能力从尸潮中逃出生天，但及时锁住校门无疑会使结局更好，" +
                "记住，永远不要在细节上吃亏。（测试失败）"));

        selections.put("8",new Content("第八题:\n" +
                "你觉得学校大门就这么大大咧咧的敞开着让你很不放心，于是你又特意折回去去锁校门。天格外的冷，雪也随着越来越昏暗的天空变" +
                "的越来越大，脚踩在地上嘎吱嘎吱的响，你看到校门外的骚乱还没有结束，那撕心裂肺般的惨叫让你毛骨悚然。正当你走向校门，" +
                "并离校门只有一步之遥时，一个胳膊上淌血的女孩突然挣脱丧尸的束缚，向你跑了过来，口中还喊着:“救我，我没有被咬，”并且大" +
                "步冲了过来:“快关门!”你默默地看着后面张牙舞爪的丧尸还有女孩真诚的双眼，鲜血映入雪白的土地上是那么的刺眼......",
                new Option("a","杀掉女孩身后的丧尸（跳转到第十六题）","16"),
                new Option("b","允许女孩进来并想办法快速的将丧尸关在门外（跳转到第十七题）","17"),
                new Option("c","直接关门，不管女生的死活（跳转到第十八题）","18")));

        selections.put("9",new Content("第九题:\n" +
                "你从死去的警察身上找到了一把精致的M9手枪，漆黑的纹路再加上机械般的触感让你爱不释手。但一个天大的问题摆在了你的面前:" +
                "你不晓得如何上膛开枪！但你又不忍心丢弃它。这时，一个丢了半边手臂，身体布满腥血的丧尸发现了你，呲着干瘪的牙床，向你走" +
                "了过来!",
                new Option("a","用枪托砸它（跳转到第十九题）","19"),
                new Option("b","果断弃枪逃跑（跳转到第二十题）","20")));

        selections.put("10",new Content("第十题:\n" +
                "你捡起菜刀，虽然这刀长得有点丑陋但还能凑活用，夜幕下的A市布满了血腥味和心底油然而生的肃杀感。你环顾四周，发现大街上" +
                "到处都在发生着流血事件，相比和平时代而言，就算暴力分子出现也没有向今天这么惨烈。丧尸也越来越多了，你觉得这里不安全，" +
                "便持刀离开，但一个女孩的哭声引起了你的注意。那是一个抱着洋娃娃，坐在马路中央抹眼泪的小萝莉，你刚才亲眼看见她的妈妈" +
                "为了保护她而被拖入丧尸堆儿中。女孩哭的是如此无助，让你立即想起了还在家中的亲妹妹。但四周的丧尸有点儿多，" +
                "你救还是不救?",
                new Option("a","救（跳转到第二十一题）","21"),
                new Option("b","不救（跳转到第二十二题）","22")));

        selections.put("11",new Content("第十一题:\n" +
                "你觉得以自己目前的体格根本不适合拿钢筋做武器，虽然它看起来很拉风的样子（哪里拉风）。你赤着手胆战心惊地走完了这条胡同" +
                "，好在没有丧尸出现在胡同之中，要不然.....你想想都觉得后怕。这时的你已经来到了另一条街道，稍微离自己家远了点，但这条" +
                "街道三天前动土施工，要在下面铺管道，所以黑天也就空无一人。你看着空空的街道，物是人非的感觉让你的心中五味沉杂，很不" +
                "舒服。突然，前方传来一阵突兀的枪响，“哒哒哒”的声音是如此的具有威慑力!你悄悄的潜了过去，终于在拐角处发现了枪声的来" +
                "源:“军队!”这是三辆轻型坦克，外面六七个士兵穿着制服，一个大胖子端着一挺机枪坐在第一个坦克上，剩下几个有拿枪的，有拿" +
                "刀的，坦克里面还有多少人就不知道了。这是你有生以来第一次亲眼目睹坦克，，可能是听从哪个上级的指令从郊区开过来的，但" +
                "你也不敢妄下结论。此时你该怎么办?",
                new Option("a","继续观察（跳转到第二十三题）","23"),
                new Option("b","直接向他们勇敢的走出去（跳转到第二十四题）","24"),
                new Option("c","离开（跳转到第二十五题）","25"),
                new Option("d","向其呼救（跳转到第二十六题）","26")));

        selections.put("12",new Content("第十二题:\n" +
                "你敲了敲这户人家的门，只见这户人家的灯立马暗了下来，紧随其后的便是一个低沉的男中音:“谁?”",
                new Option("a","你表示打算借宿一晚（跳转到第二十七题））","27"),
                new Option("b","开门查水表（跳转到第二十八题）","28"),
                new Option("c","敲门求救（跳转到第二十九题）","29")));

        selections.put("13",new Content("第十三题:\n" +
                "你缓缓拿起一根钢筋，一股巨大的力量从掌心向下拖拽而去，你吓的闪电般松开了手，只听咣啷一声，一堆废钢筋被打翻，滚的到处" +
                "都是!只听不远处传来一阵丧尸独有的嘶吼声!你被发现了!",
                new Option("a","逃跑（跳转到第三十题）","30"),
                new Option("b","试试去敲门（跳转到第三十一题）","31")));

        //TODO 结果项
        selections.put("14",new Content("第十四题:\n" +
                "没时间去捡东西了，丧尸张着恶心的大口向你扑了过来，你咬咬牙，拳头攥实冲丧尸脑门轮了过去，同时丧尸也抓住了你的衣领，" +
                "你挣扎着，又是一拳打碎了它半口牙齿，丧尸的嘶吼声让你从心里往外想呕吐。就在这时，你感觉背后凉风袭来，没来得及回头，" +
                "又是一双丑陋的爪子扣住了你的肩膀。后来你虽然没有死，但还是被丧尸咬了一口。记住，永远不要赤手空拳面对面的和丧尸搏斗，" +
                "即使你自认为很强壮。（测试未完成）"));

        selections.put("15",new Content("第十五题:\n" +
                "你弯下腰从地上拾起一个板砖，但就在这时丧尸已经扑了过来，并狠狠地将你扑倒在地。你大吼着，砖头猛地砸在了丧尸的脑门上，" +
                "然后快速地爬了起来，丧尸怒吼着，也跌跌撞撞地往起爬。这时你已经发现有许多丧尸将贪婪目光对准了你。一看形式不妙，你立即:",
                new Option("a","丢下板砖玩命地跑（跳转到第三十二题）","32"),
                new Option("b","拿着板砖玩命地跑（跳转到第三十三题）","33")));

        //TODO 结果项
        selections.put("16",new Content("第十六题:\n" +
                "一个女孩子和自己年龄相仿，却有这么大的求生欲望，你认为是时候展现真正的技术帮她一下了。你盯着女孩身后的丧尸，准备给它" +
                "迎头痛击。可就在这时，诡异的一幕发生了！跑到你后面的女孩突然转身，一把将你推倒在地！你本来是很强壮的，但奈何女孩是" +
                "偷袭。你毫无防备的倒在地上，余光扫到了女孩哭泣的脸:“对不起...对不起...我也是迫不得已啊。”她哭的泪流满面，声音充满" +
                "哽咽，但此时的你已经听不到女孩在说什么了，因为你的耳朵已经完全被丧尸的嘶吼声所覆盖。那是你生命最后一刻奏响的警钟！" +
                "你被卖了。记住，永远不要相信陌生人，连和平时代都不信，何况是末世呢？有时人心比丧尸要可怕的多。（测试失败）"));

        selections.put("17",new Content("第十七题:\n" +
                "你觉得应该帮助这女孩一下，因为自己最起码的人性还是有的。你抓准时机，在女孩进入学校的一瞬间闪电般关上了门，并牢牢锁死。" +
                "你和女孩都松了口气，天空还下着大雪，你和女孩四目相对，你发现她真心挺漂亮的。这时你一下子抓起她的手，她的胳膊上清晰" +
                "可见三道血淋淋抓痕:“你骗我！你说你没感染的！”你突然愤怒了。\n" +
                "“对...对不起...我...”女孩一下子慌了:“我以为只有被咬才算感染...”你看着女孩越来越深陷的眼眶，不禁后退了一步，然后弯" +
                "腰从地上抓起一根湿漉漉的木棍，看着她。",
                new Option("a","放她走，永远不想再见她（跳转到第三十四题）","34"),
                new Option("b","直接乱棍打死（跳转到第三十五题）","35")));

        selections.put("18",new Content("第十八题: “快！快关门，我没被咬”女孩大喊着，并企图闯入学校，你冷冷地看着女孩和她" +
                "身后的丧尸，然后毫不留情的关上了门，并“咔嚓”一声将门锁死。女孩一下子扑到校门的铁丝网上，大声向你呼救，你连头都不回就" +
                "离开了。只听一阵丧尸的嘶吼声，牙齿与血肉挤压发出的“噗叽”声，还有女孩不甘的惨叫。铁丝网上一片触目惊心猩红的血。你回到" +
                "了教学楼，教学楼还是一片灯火通明的样子。同学们都在学习，由于冬天关窗隔音好，年轻人不易感染，所有人都不知道末日已经来" +
                "了。此时的你",
                new Option("a","告诉同学们真实的情况，并自荐当首领（跳转到第三十六题）","36"),
                new Option("b","只告诉你的几个小伙伴，然后开启你的求生之路（跳转到第三十七题）","37"),
                new Option("c","固守学校，等待救援（跳转到第三十八题）","38")));

        //TODO 结果项
        selections.put("19",new Content("第十九题:\n" +
                "你依旧不肯放弃你的枪，并用力拿枪托去砸丧尸，但这想法无疑很愚蠢，你几乎没撑过十秒就被丧尸抓伤，并被三个丧尸团团围住。" +
                "记住，贪婪是人性最大的劣根。（测试失败）"));

        selections.put("20",new Content("第二十题:\n" +
                "舍得，有舍才会有得。机智的你果断弃枪而逃，所幸丧尸追了你一会儿就转移了目标。你跑啊跑，这时一架直升机从你头顶飞过，巨" +
                "大的螺旋桨卷起一阵狂风。你兴奋的向直升机挥手呼救，但回应你的却是一连串的子弹，你吓的闪到一边，此时的你:",
                new Option("a","也许飞行员误以为我是丧尸，继续呼救（跳转到第三十九题）","39"),
                new Option("b","放弃呼救（跳转到第四十题）","40")));

        //TODO 结果项
        selections.put("21",new Content("第二十一题:\n" +
                "你紧张地环顾四周，发现四周的丧尸的确很多，已经没有太多准备的时间了。不仅大街上试图抵抗的幸存者越来越少，最重要的是一" +
                "个巨丑的，嘴角被撕裂到耳根处的丧尸发现了摊坐在地上哭的小萝莉！你一个箭步窜了出去，速度是如此的惊人。似乎周围的一切，" +
                "飞雪，灯光，鲜红的血都变的越来越朦胧，只有那个哭泣的孩子还有这个，丑陋的丧尸！你一拳击中丧尸的脑门，只听呱唧一声，丧" +
                "尸的脑瓜从开裂的嘴角处一分为二！无头的尸体最后挣扎了一下，便倒在了血泊中。小萝莉停止了哭泣，睁大水灵灵的大眼睛看着" +
                "你:“大哥哥....”你轻轻的将她抱起，这时，大道上除你以外最后一个幸存者停止了呼吸。整个街道顿时出现短暂的安静，然后，" +
                "所有的丧尸将凶狠的目光对准了你。记住，真正的英雄只存在电影之中，现实世界里任何幸存者都会将自己的生命看在第一位。" +
                "生命不复存在，小萝莉也只会成为过往云烟。（测试失败）"));

        selections.put("22",new Content("你看向小萝莉的目光中充满了怜悯，她太可爱了，不应该就这么白白死去。但....你叹了口" +
                "气，手中的菜刀似乎又沉重了几分。小萝莉虽然可爱，但家中的妹妹难道不可爱吗？你打定主意，快速逃离了这里。或许这个小萝莉" +
                "会给自己一生流下阴影吧....你跑啊跑，不知不觉已经离家很近了，这时你突然发现一个熟人，以前曾当众羞辱过你的人。他也发现" +
                "了你，但此时他正在和一个丧尸搏斗，你该怎么办?",
                new Option("a","帮助他杀死丧尸（跳转到第四十三题）","43"),
                new Option("b","帮他杀了丧尸再杀了他（跳转到第四十四题）","44"),
                new Option("c","帮助丧尸杀了他（跳转到第四十五题）","45")));

        selections.put("23",new Content("第二十三题:\n" +
                "平时谨慎的性格促使你决定继续观察一下。这时，一个四十左右岁的中年男子牵着一个八岁左右的正太从对面的银行里跑了出来，并" +
                "向这些全副武装的士兵大声求救。你紧紧的盯住他们的一举一动，只见队伍里一个精瘦的士兵听到了他们的呼救，然后，然后将枪直" +
                "接对准了中年男人:“砰”。男人胸口中弹倒在血泊中，这些兵头也不回的继续前进！正太撕心裂肺般的嚎啕大哭和士兵们的行为所形" +
                "成的鲜明对比，深深烙在了你的心里。你盯住这些人的脸看了好几秒，深深记住了他们，尤其是开枪的那个！（直接跳转到第四十六题）",
                "46"));

        //TODO 结果项
        selections.put("24",new Content("第二十四题:\n" +
                "你大声的呼救让士兵们一下子就发现了你，你满怀欣喜的冲他们跑了过去，但迎接你的却是一颗炽热的子弹！记住，末日来临时除了" +
                "自己的父母以外千万不要相信任何人。何况是危险的士兵?（测试失败，我没有要黑中国士兵的意思）"));

        selections.put("25",new Content("第二十五题:\n" +
                "你觉得不能将自己的生命完全交给他们手中，于是你快速离开了。心里默默祈祷着回到家后不会看到什么恐怖的画面。" +
                "（直接跳转到第四十六题）", "46"));

        //TODO 结果项
        selections.put("26",new Content("第二十六题:\n" +
                "你高声呼喊着向他们求救，刚开始没有人听到，直到你喊了第三遍，坦克上的胖子才发现了你，你不知道接下来会发生什么，便默默" +
                "地看着他。只见胖子愣了一下，然后毫不犹豫的将机枪对准了你！“哒哒哒哒哒哒哒哒”你吓了一跳，赶紧闪到了一边，但还是有一颗" +
                "子弹像锋利的刀子般划伤了你的脚踝，你一下子扑倒在地。只听胖子对身边的士兵说，长官有令，为了避免突发情况，无论丧尸还是" +
                "幸存者一律杀光......你惊的一头冷汗，然后你就发现自己悄悄被丧尸们包围了。（测试失败）"));

        //TODO 结果项
        selections.put("27",new Content("第二十七题: 你说自己是借宿的，但对方听完后就没了声音。这时你已经发现街道上的丧尸" +
                "进入了胡同，没太多时间了！你焦急的敲着门:“快开门啊，开门！”但任凭你如何敲，里面就是不开，你一转身，发现你身后已经" +
                "围了好几个丧尸了！（测试失败）"));

        //TODO 直接跳转结果项
        selections.put("28",new Content("第二十八题:\n" +
                "你清了清嗓子，装作粗鲁的声音大声道:“开门，我是查水表的！”里面的人愣了一下:“前天不是刚查过吗?”你听完急中生智:“是.." +
                "是前天刚查过，但今天不一样，你怎么那么多废话啊?”身为一个艺术生（播音主持与广播电视编导），这点演技和反应力还是可以" +
                "有的，何况还是隔着门的。对面犹豫了一下，终于打开了门。（测试成功，结局C）", "C"));

        //TODO 结果项
        selections.put("29",new Content("第二十九题:\n" +
                "你的大声求救显然没有什么效果，里面的人听到你的求救后立即就没了声音。而且你的敲门声立即引来了许多丧尸。这时你听到了里" +
                "面似乎有不寻常的动静，情急之下，你加大敲门的力度并用力拧门把手，只听咔嚓一声，把手被你拧掉了。你推门而入，映入眼帘的" +
                "却是一个丧尸撕咬一个男人的画面！而你身后也涌现了许多丧尸！（测试失败）"));

        selections.put("30",new Content("第三十题:\n" +
                "你果断的逃离了现场，还好你跑的够快，丧尸们没追上来。此时的大街可以说是一片混乱，你看着眼前的情景，突然有些不知所措，" +
                "就在这时，不远处有人叫了你的名字，你一回头，发现竟然是自己的父母和小妹！你急忙跑了过去:“你们怎么在这儿?”于是你的父亲" +
                "和你快速的交谈了事情的经过。你点点头，于是你们一家四口开始了长途逃难之旅，好在父亲是散打教练，身体强壮，又会开车。" +
                "你们一家人有惊无险的逃出了A市市区，但继续逃难，问题就来了。你打算使用何种交通工具前往郊区?",
                new Option("a","小轿车（跳转到第四十七题）","47"),
                new Option("b","三厢面包（跳转到第四十八题）","48")));

        //TODO 结果项
        selections.put("31",new Content("第三十一题:\n" +
                "你第一次这么迫切的希望一扇大门能为你敞开，可惜它没有。命运由自己掌握是末世法则中永恒不变的真理。（测试失败）"));

        selections.put("32",new Content("第三十二题:\n" +
                "你果断丢弃了这块沉重的砖头，虽然它看起来能保护你但这绝不是你现在最理想的武器，你玩命般地逃离了这条街道，所幸你没有受" +
                "到任何伤害，毫发无损的逃到了乡下老家，你的家人们也陆陆续续和你回合了。你决定将整个房子进行加固来防止丧尸的入侵。" +
                "只有这三个方案，你认为哪个更好?",
                new Option("a","彻底封死，挖出一个地下室通向外面（跳转到第四十一题）","41"),
                new Option("b","修成两层楼，上面住人并毁掉楼梯（跳转到第四十二题）","42"),
                new Option("c","在房子外围布下天罗地网，不重点加固房子本身（跳转到第四十九题）","49")));

        //TODO 直接跳转结果项
        selections.put("33",new Content("第三十三题:\n" +
                "你觉得没有砖头不行，但你还是低估了它的重量。它严重阻碍了你逃命的速度。虽然你最后还是丢弃了它。（跳转到结局D）这么快，玩完了。\n",
                "D"));

        //TODO 结果项
        selections.put("34",new Content("第三十四题:\n" +
                "你还是放走了女孩，因为你还没有丧心病狂到杀活人的地步，但末世有时就是这么残酷，人往往都是被逼出来的，女孩刚走了两步就" +
                "栽倒在地，口吐鲜血。迫不得已的你终于鼓足勇气要去杀掉她，因为她现在已经不算是活人了。你开始有点后悔当初为什么不把她关" +
                "在门外，但你明白，再遇到下一个类似的情况你依旧会伸出援手。因为你坚信未来的自己有困难时，其他人也会为你打开一扇门的。" +
                "终于你走到女孩面前。突然女孩睁开了眼睛，腮部一鼓，一缕鲜血就喷了你一脸，你卯足力气给了她致命一棍。但为时已晚，少量的" +
                "丧尸血液顺着眼睛侵入了你的身体，你被感染了。记住，在末世，对别人仁慈就是对自己最大的残忍。（测试失败）"));

        selections.put("35",new Content("第三十五题:\n" +
                "你看到了她慢慢变青，慢慢凹陷的眼眶，顿时一鼓巨大的危机感油然而生，你毫不犹豫的突然暴起，手起棍落，你感觉浑身的血液都" +
                "沸腾了起来。只听“砰”的一声，就像打高尔夫一样。一个血淋淋的高尔夫球冲天而起，滚出老远，鲜血顿时染红了一大片雪地。你再" +
                "也忍不住了，弯腰吐了起来。（直接跳转到第五十题）",
                "50"));

        selections.put("36",new Content("第三十六题:\n" +
                "你及时地控制住了学校，但要想在末世更好的生存，这些是远远不够的。成为首领就要担负更多的责任:食物、水、武器......毕竟" +
                "整整一个学校的幸存者可不是过家家的儿戏。当然更重要的还有人心。（跳转到第五十一题）",
                "51"));


        selections.put("37",new Content("第三十七题:\n" +
                "你悄悄地来到往日熟悉的教室，找到了正在学习的姜生，当逢自习课，全班同学都震惊地看着衣服上到处是血的你，你也认真的打量" +
                "着全班同学，接下来你就要选择一些人陪你度过整个末世，你如何选择?",
                new Option("a","能力强的人（跳转到第五十一题）","51"),
                new Option("b","你信得过的人（跳转到第五十二题）","52")));

        selections.put("38",new Content("第三十八题:\n" +
                "你决定固守学校，让它成为方圆百里最强大的一个幸存者基地，有必要时能等待遥遥无期的救援，说实话你还是很爱国的。现在，你" +
                "或许将面临人员分配的问题，食物物资分配的问题，武器分配的问题，住宿的问题等等等等......最重要的就是人心和其他幸存者队" +
                "伍的不友善试探。总之困难重重，稍有不慎就会让你辛辛苦苦创建的基地毁于一旦。但这要比到外面四处流浪强得多。因为你失败丢" +
                "的是基地，流浪者失败丢失的是命。（跳转到第五十二题）",
               "52"));

        //TODO 结果项
        selections.put("39",new Content("第三十九题:\n" +
                "你仰望着直升机，并做出一些和丧尸有区别的行为让飞行员发现你。只见直升机又是一连串子弹扫过，哒哒哒的雨点像一把锋利的镰" +
                "刀将丧尸们割麦子般一个个扫死，但飞行员丧心病狂，或许是杀红了眼，子弹打到你面前时并没有停下，而是一视同仁地扫了过去。" +
                "你一下子倒在地上，胸口中了致命的一弹。（测试失败）"));

        selections.put("40",new Content("第四十题:\n" +
                "你果断离开了现场，这些恐怖的战争机器还不是现在你能接触的，刚才那一串子弹差点儿把你吓得半死，如果再来一次说不定打伤了" +
                "我那该多亏啊。你心想着，远离了这里。（跳转到第五十三题）",
                "53"));

        //TODO 直接跳转结果项
        selections.put("41",new Content("第四十一题:\n" +
                "你只留下一个出口，虽然十分保险但缺乏灵活性，一但有丧尸从地下室出口侵入后果将不堪设想，但你的选择还是十分可行的。（结局B）",
                "B"));

        //TODO 直接跳转结果项
        selections.put("42",new Content("第四十二题:\n" +
                "你耗用了大量的力气拆掉了楼梯，并在二层囤积了大量的物资。前期虽然有效的躲过了许多的丧尸，但这并不是长久之计，物资会坐" +
                "吃山空，灵活才是王道。（结局D）",
                "D"));

        //TODO 直接跳转结果项
        selections.put("43",new Content("第四十三题:\n" +
                "虽然你们之间有些不愉快的过节，但在暗无天日的末世，这点小事几乎不足挂齿。你很宽容的上前帮助了他，然后你们两个合力将丧" +
                "尸杀掉了。他冷笑地看着你道:“小子，挺行啊！”你则清哼一声:“少废话，跟我来。”他耸耸肩，于是你们两个小心翼翼地绕过了丧" +
                "尸群，向更远处前进而去，显然你的宽容成功让你多了一个并不太靠谱的队友。（结局A）",
                "A"));

        selections.put("44",new Content("第四十四题:\n" +
                "你觉得自己报复他的机会终于来了，于是你假装上前和他打招呼，分散了他的注意力，他听到有人喊他的名字，一个失神，就立即处" +
                "于被动之中。然后你在关键时刻一刀砍断了丧尸的脖颈。碗口大的伤口噗噗地冒着血。你和他对视了一下，本来他想说什么讽刺你一" +
                "下，但你突然一个暴起吓了他一跳，菜刀在空中轮的呼呼作响，匆忙之下他来不及防御只能用手臂去挡，一番激烈的战斗，他受了很" +
                "重的伤，你也不好受，额头脸上全是淤青。他落荒而逃，此时的你:",
                new Option("a","不追了（结局D）","D"),
                new Option("b","追他，他挺不了太久（跳转到第五十四题）","54")));

        //TODO 结果项
        selections.put("45",new Content("第四十五题:\n" +
                "你猛地冲了上去，手起刀落，他半边手臂像破碎的积木一样破碎开来，他惨叫着，引起了众多附近游荡丧尸的注意。鲜血呲了一地，" +
                "你身上也全是血，刚才没有命中要害，可能是自己头一次挥刀的缘故。而他已经非常愤怒了，正在这时，他用剩余的力量猛地将你扑" +
                "倒，菜刀也脱手而飞。任凭你如何用力揍他，他都不肯松开你。这时，你惊骇的发现你被丧尸包围了!（测试失败）"));

        //TODO 直接跳转结果项
        selections.put("46",new Content("第四十六题:\n" +
                "你有惊无险的回到了家中，迎接你的是毫发无损的家人。亲情，这个在末世之中最珍贵的东西，往往能在最重要的时刻，像一壶甘醇" +
                "的老酒，沁人心扉。（结局A）",
               "A"));

        //TODO 直接跳转结果项
        selections.put("47",new Content("第四十七题:\n" +
                "轿车的速度比面包车快，能在路上甩掉许多不必要的麻烦，但轿车却无法提供给你足够多的空间，比如睡眠，扎营和装运物品。终究" +
                "不是最佳的逃生工具。（结局C）",
            "C"));

        //TODO 直接跳转结果项
        selections.put("48",new Content("第四十八题:\n" +
                "宽大的三厢面包车不仅能载你全家人，还能额外带一些重要的物品，足够的空间是小轿车所无法比拟的，但三厢终究没有轿车快，这" +
                "也是事实。也可能因此耽误许多时间和引来一些不必要的麻烦，但从长远角度来看，你的选择还是很明智的。（结局B）",
            "B"));

        selections.put("49",new Content("第四十九题:\n" +
                "你在房子的四周布下无数的陷阱等待丧尸来踩，但你要知道有一些陷阱是不抗用的，有些甚至是一次性的。综合来说有一部分可取性，" +
                "但绝不能把它当做你永久的住所。（结局C）",
            "C"));

        selections.put("50",new Content("第五十题:\n" +
                "你杀掉这个被感染的女孩后，整个人仿佛重新被洗涤了一次，你从来没想过自己有一天也会成为今天这样，剥夺别人的生命来保全自" +
                "己的生命。这才是真正的末世吗?（结局B）",
            "B"));

        selections.put("51",new Content("第五十一题:\n" +
                "你或许会成为一个出色的首领，或许你会选择和你的朋友们一起求生，但当你读到本题时，你已经有了一颗蓬勃的野心和无比顽强的" +
                "生存力量，你已经成功的领先于其他幸存者之上，但野心可以有，太大的野心有可能引火上身（结局B）",
            "B"));

        selections.put("52",new Content("第五十二题:\n" +
                "生存在这遍地丧尸的世界，时间久了会让人变的麻木不仁。生存，生存，这已经是一个永不磨灭的活火在每一个幸存者脑海中燃烧。" +
                "但在生存之余，你又能保持一个健康的灵魂，没有受到末世污浊的侵蚀，这往往能给你带来更多的机遇和力量。（结局A）",
            "A"));

        selections.put("53",new Content("第五十三题:\n" +
                "你很勇敢，这一点毋庸置疑。无论是从你选择枪械上还是面对危险时你都能勇敢的解决这一切。勇气来源于人的内心，或许就是这一" +
                "点点勇气，就能给你带来意想不到的收获。（结局C）",
            "C"));

        //-----------------------------------------------结果项-----------------------------------------
        selections.put("A",new Content("A.最后的幸存者（100％）\n" +
                "物竞天择，适者生存。无论遇到什么样的浩劫，你总是那个最后幸存下来的人，恭喜你，最（ke）后（pa）的幸存者。"));

        selections.put("B",new Content("强壮的幸存者（85％）\n" +
                "你有着顽强的生存能力，无论是处理什么问题你都有一套独特的办法，而且最后总能让自己满意。在末世，活下来是本能，活的风生" +
                "水起才是本事，你做到了。"));

        selections.put("C",new Content("勇敢的幸存者（65％）\n" +
                "你有着独挡一面的勇气和个人魅力，这种勇气往往能在关键时刻发挥出巨大的作用。勇气不是谁都可以拥有，有勇气却又能顽强的生" +
                "存下去才算真正的有勇有谋。心怀勇者之心在末世实在难能可贵。"));

        selections.put("D",new Content("普通的幸存者（50％）\n" +
                "首先我要恭喜你成功活到了这里，完成了测试，你已经打败了百分之八十未完成测试的人们，成为了一个真正的，能在末世生存下去" +
                "的幸存者，你合格了。但在幸存者的世界中，你依旧是最平凡的存在，努力让自己变的不平凡吧，生存是你的目标，让自己活的更滋" +
                "润才是王道。"));
    }

    @RequestMapping(value = "/play",produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String play(@RequestParam(defaultValue = "1")String key){
        Content content = selections.get(key);
        if(content == null){
            content = selections.get("1");
        }
        return  new Gson().toJson(content);
    }

    @RequestMapping(value = "")
    public String goHome(){
        return "default";
    }

    @RequestMapping(value = "/greet",method = RequestMethod.GET,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String echo(@RequestParam(defaultValue = "") String name,Model model){
        model.addAttribute("name",name);
        return  new Gson().toJson( new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(System.currentTimeMillis())
                + " " + I18nUtil.getMessage("author") + " replied from server: hello, "+ name);
    }

    /**
     * switch language(only support en|zh_CN until now)
     */
    @RequestMapping(value = "/language",method = RequestMethod.POST,produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String setLocale(@RequestParam(defaultValue = "") String language, HttpServletRequest request, HttpServletResponse response){
        if(StringUtils.isNotEmpty(language)){
            localeResolver.setLocale(request, response, new Locale(language));
        }
        //TODO
        // need to validate failed conditions:the language should in the scope of the configuration.


        return I18nUtil.getMessage("success");
    }
}