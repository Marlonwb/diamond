/****************************************
* name:zcityData
* tips:城市数据
****************************************/
var zcityData = {
"0":["自然科学与工程技术","人文社会科学"],
"0_0":["基础科学","工程科技Ⅰ辑","工程科技Ⅱ辑","农业科技","医药卫生科技","信息科技"],
"0_1":["哲学与人文科学","社会科学Ⅰ辑","社会科学Ⅱ辑","经济与管理科学"],
"0_0_0":["基础科学综合","自然科学理论与方法","数学","非线性科学与系统科学","力学","物理学","生物学","天文学","自然地理学测绘学","气象学","海洋学","地质学","地球物理学","资源科学"],
"0_0_1":["综合科技A类综合","化学","无机化工","有机化工","燃料化工","一般化学工业","石油天然气工业","材料科学","矿业工程","金属学及金属工艺","冶金工业","轻工业手工业","一般服务业","安全科学与灾害防治","环境科学与资源利用"],
"0_0_2":["综合科技B类综合","工业通用技术及设备","机械工业","仪器仪表工业","航空航天科学与工程","武器工业与军事技术","铁路运输","公路与水路运输","汽车工业","船舶工业","水利水电工程","建筑科学与工程","动力工程","核科学技术","新能源","电力工业"],
"0_0_3":["农业综合","农业基础科学","农业工程","农艺学","植物保护","农作物","园艺","林业","畜牧与动物医学","蚕蜂与野生动物保护","水产和渔业"],
"0_0_4":["医药卫生综合","医药卫生方针政策与法律法规研究","医学教育与医学边缘科学","预防医学与卫生学","中医学","中药学","中西医结合","基础医学","临床医学","感染性疾病及传染病","心血管系统疾病","呼吸系统疾病","消化系统疾病","内分泌腺及全身性疾病","外科学","泌尿科学","妇产科学","儿科学","神经病学","精神病学","肿瘤学","眼科与耳鼻咽喉科","口腔科学","皮肤病与性病","特种医学","急救医学","军事医学与卫生","药学","生物医学工程"],
"0_0_5":["电子信息科学综合","无线电电子学","电信技术","计算机硬件技术","计算机软件及计算机应用","互联网技术","自动化技术","新闻与传媒","出版","图书情报与数字图书馆","档案及博物馆"],
"0_1_0":["文史哲综合","文艺理论","世界文学","中国文学","中国语言文字","外国语言文字","音乐舞蹈","戏剧电影与电视艺术","美术书法雕塑与摄影","地理","文化","史学理论","世界历史","中国通史","中国民族与地方史志","中国古代史","中国近现代史","考古","人物传记","哲学","逻辑学","伦理学","心理学","美学","宗教"],
"0_1_1":["政治军事法律综合","马克思主义","中国共产党","政治学","中国政治与国际政治","思想政治教育","行政学及国家行政管理","政党及群众组织","军事","公安","法理、法史","宪法","行政法及地方法制","民商法","刑法","经济法","诉讼法与司法制度","国际法"],
"0_1_2":["教育综合","社会科学理论与方法","社会学及统计学","民族学","人口学与计划生育","人才学与劳动科学","教育理论与教育管理","学前教育","初等教育","中等教育","高等教育","职业教育","成人教育与特殊教育","体育"],
"0_1_3":["经济与管理综合","宏观经济管理与可持续发展","经济理论及经济思想史","经济体制改革","经济统计","农业经济","工业经济","交通运输经济","企业经济","旅游","文化经济","信息经济与邮政经济","服务业经济","贸易经济","财政与税收","金融","证券","保险","投资","会计","审计","市场研究与信息","管理学","领导学与决策学","科学研究管理"],



};
/****************************************
* name:zcity
* tips:城市联动
****************************************/
function zcity(id){
	var cityIni='';
	var cityRange='';
	var zcityItem='.zcityItem';
	var itemLevel='item-level';
	var zcityItemHead='.zcityItem-head';
	var zcityItemMain='.zcityItem-main';
	var currentValue='.currentValue';
	var citylist='.citylist';
	var cityitem='.cityitem';
	var cityTips='.cityTips'
	var cityDefaultTips='请选择';//默认提示
	/****************************************
	* name:cityIni
	* tips:初始化
	****************************************/
	if($(id).attr('city-range')!=null && $(id).attr('city-range')!=undefined && $(id).attr('city-range')!=''){
		cityRange=strToJson($(id).attr('city-range'));
		var leavelStart=parseInt(cityRange.level_start,10);
		var leavelEnd=parseInt(cityRange.level_end,10);
		if(leavelEnd<leavelStart){
			alertTips('warning','城市区间配置最大值不能小于最小值！');
			return;
		}else{
			$(id).html('');
			if($(id).attr('city-ini')!=null && $(id).attr('city-ini')!=undefined && $(id).attr('city-ini')!=''){
				//有初始值
				cityIni=$(id).attr('city-ini').split(",");
				for(var i=0;i<=leavelEnd-leavelStart;i++){
					var currCityIni=cityIni[i];
					if(currCityIni==undefined){
						currCityIni='';
					}
					$(id).append(addItemHtml(i+leavelStart,currCityIni));	
					if(i==0){
						iniLevel(i+leavelStart);//插入层级数据	
					}else{
						iniNameIndexVal(i+leavelStart,cityIni[i]);
					}
				}
			}else{
				//没有初始值
				for(var i=0;i<=leavelEnd-leavelStart;i++){
					$(id).append(addItemHtml(i+leavelStart,''));
					if(i==0){
						iniLevel(i+leavelStart);//插入层级数据		
					}else{

					}
				}
			}
		}
	}
 	/***************************************
	* name: clickTargetOut
	* tips: 点击DIV之外隐藏Target
	****************************************/
	$(document).on('click',function(e){
		if($(e.target).parents(id).length == 0){
			$(id+' '+zcityItem).removeClass('on');
        }
	});	
	/****************************************
	* name:zcityItemHead
	* tips:点击头部
	****************************************/
	$(document).on('click',id+' '+zcityItemHead,function(){
		if($(this).parents(zcityItem).hasClass('on')){
			$(this).parents(zcityItem).removeClass('on');
		}else{
			$(id+' '+zcityItem).removeClass('on');
			$(this).parents(zcityItem).addClass('on');
		}
		setPosition(this);
	});
	/****************************************
	* name:setPosition
	* tips:设置下拉位置
	****************************************/
	function setPosition(target){
		var diffBottomH=50;//与底部相差高度
		var drapDownMainMinWidth=70;//下拉内容的最小宽度
		var windowH=$(window).height();
		var windowT=$(window).scrollTop();
		var headH=$(target).outerHeight();
		var headW=$(target).outerWidth();
		var mainH=$(target).siblings('.zcityItem-main').outerHeight();
		var mainW=0;
		//监听主区域滚动
		$(window).on('scroll',function(){
			$('.zcityGroup .zcityItem').removeClass('on');
		});
		$(window).resize(function(){
			$('.zcityGroup .zcityItem').removeClass('on');
		});	
		if(headW<drapDownMainMinWidth){
			$(target).siblings('.zcityItem-main').css({'width':drapDownMainMinWidth});
			mainW=drapDownMainMinWidth;
		}else{
			$(target).siblings('.zcityItem-main').css({'width':headW});
			mainW=headW;
		}
		var showTop=$(target).offset().top;
		var showLeft=$(target).offset().left;
		var bodyScrollTop=$('body').scrollTop();
		if(windowH+windowT>showTop+mainH+diffBottomH){//判断向上还是向下
			$(target).siblings('.zcityItem-main').css({'left':showLeft-(mainW-headW),'top':showTop+headH-bodyScrollTop});
		}else{
			$(target).siblings('.zcityItem-main').css({'left':showLeft-(mainW-headW),'top':showTop-mainH-bodyScrollTop});
		}
	}
	/****************************************
	* name:iniLevel
	* tips:初始化层级数据
	****************************************/
	function iniLevel(level){
		$(id).find(zcityItem+'['+itemLevel+'="'+level+'"]'+' '+citylist).html('');//清空
		/****************************************
		* name:each
		* tips:遍历数据 - 获得对应层级数据
		****************************************/
		var curLength=0;
		var curItemLi='';
		$.each(zcityData,function(name,value){
			//console.log(name);
			//console.log(value);
			if(strAppearNumber('_',name)==level-1){
				curLength=value.length;
				for(var u=0;u<curLength;u++){
					curItemLi=curItemLi+'<li class="cityitem" values="'+value[u]+'">'+value[u]+'</li>';
				}
			}
		});
		$(id).find(zcityItem+'['+itemLevel+'="'+level+'"]'+' '+citylist).html(curItemLi);//重新赋值
	}
	/****************************************
	* name:iniNameIndexVal
	* tips:初始化名称索引对应数组值
	****************************************/
	function iniNameIndexVal(level,cityName){
		var nameIndex=0;
		$(id).find(zcityItem+'['+itemLevel+'="'+level+'"]'+' '+citylist).html('');//清空
		/****************************************
		* name:each
		* tips:遍历数据 - 获得对应层级数据
		****************************************/
		$.each(zcityData,function(name,value){
			//console.log(name);
			//console.log(value);
			if(strAppearNumber('_',name)==level-1){
				curLength=value.length;
				for(var u=0;u<curLength;u++){
					if(value[u]==cityName){
						nameIndex=name;
						return;
					}
				}
			}
		});
		var curItemLi='';
		var curLength=zcityData[nameIndex].length;
		for(var k=0;k<curLength;k++){
			curItemLi=curItemLi+'<li class="cityitem" values="'+zcityData[nameIndex][k]+'">'+zcityData[nameIndex][k]+'</li>';
		}
		$(id).find(zcityItem+'['+itemLevel+'="'+level+'"]'+' '+citylist).html(curItemLi);//重新赋值
	}
	/****************************************
	* name:iniSonLeavel
	* tips:初始化子级数组值
	****************************************/
	function iniSonLeavel(level,cityName){
		var sonLevel=parseInt(level,10)+1;
		var nameIndex=0;
		$(id).find(zcityItem+'['+itemLevel+'="'+level+1+'"]'+' '+citylist).html('');//清空
		/****************************************
		* name:each
		* tips:遍历数据 - 获得对应层级数据
		****************************************/
		$.each(zcityData,function(name,value){
			//console.log(name);
			//console.log(value);
			if(strAppearNumber('_',name)==level-1){
				curLength=value.length;
				for(var u=0;u<curLength;u++){
					if(value[u]==cityName){
						nameIndex=name+'_'+u;
						return;
					}
				}
			}
		});
		var curItemLi='';
		var curLength=zcityData[nameIndex].length;
		for(var k=0;k<curLength;k++){
			curItemLi=curItemLi+'<li class="cityitem" values="'+zcityData[nameIndex][k]+'">'+zcityData[nameIndex][k]+'</li>';
		}
		$(id).find(zcityItem+'['+itemLevel+'="'+sonLevel+'"]'+' '+citylist).html(curItemLi);//重新赋值
	}
	/****************************************
	* name:addItemHtml
	* tips:动态添加ITEMHTML
	****************************************/
	function addItemHtml(itemLevel,iniVal){
		var curHtml='<div class="zcityItem" item-level="'+itemLevel+'">'+
						'<div class="zcityItem-head">'+
						     '<input type="text" class="currentValue" name="majors" readonly value="'+iniVal+'" placeholder="请选择">'+
						'</div>'+
						'<div class="zcityItem-main">'+
						     '<div class="cityContainer">'+
								  '<div class="cityTips">'+cityDefaultTips+'</div>'+
								  '<ul class="citylist"></ul>'+
						     '</div>'+
						'</div>'+
				    '</div>';
		return curHtml;
	}
	/****************************************
	* name:cityitem
	* tips:点击值
	****************************************/
	$(document).on('click',cityitem,function(){
		var curCityItemValue=$(this).attr('values');
		$(this).parents(zcityItem).removeClass('on');
		$(this).parents(zcityItem).find(currentValue).val(curCityItemValue);
		if($(this).parents(id).attr('city-range')==undefined||$(this).parents(id).attr('city-range')==null||$(this).parents(id).attr('city-range')==''){
			return;
		}
		var curCityRange=strToJson($(this).parents(id).attr('city-range'));
		var curLeavelStart=parseInt(curCityRange.level_start,10);
		var curLeavelEnd=parseInt(curCityRange.level_end,10);
		var curItemLeavel=parseInt($(this).parents(zcityItem).attr(itemLevel),10);
		if(curItemLeavel<curLeavelEnd){
			//清空所有子级赋值
			for(var p=curItemLeavel;p<curLeavelEnd;p++){
				$(id).find(zcityItem+'['+itemLevel+'="'+(p+1)+'"]').find(currentValue).val('');//清空
				$(id).find(zcityItem+'['+itemLevel+'="'+(p+1)+'"]'+' '+citylist).html('');//清空
			}
			//重新赋值相邻子级
			iniSonLeavel(curItemLeavel,curCityItemValue);
		}
	});
	/****************************************
	* name:cityTips
	* tips:点击默认提示
	****************************************/
	$(document).on('click',cityTips,function(){
		$(this).parents(zcityItem).find(currentValue).val('');
		$(this).parents(zcityItem).removeClass('on');
	});
	/****************************************
	* name:strAppearNumber
	* tips:字符串出现次数
	****************************************/
	function strAppearNumber(findStr,sourceStr){
		findStr=eval("/"+findStr+"/ig");
		if(sourceStr.match(findStr)==null||sourceStr.match(findStr)==undefined){
			return 0;
		}else{
			return sourceStr.match(findStr).length;
		}
	}
	/***************************************
	* name: strToJson
	* tips: 字符串转Json
	****************************************/
	function strToJson(str){ 
		var json = eval('(' + str + ')'); 
		return json; 
	} 
}
/****************************************
* name:zcityrun
* tips:城市联动运行
****************************************/
function zcityrun(id){
	var size=$(id).size();
	for(var i=0;i<size;i++){
		var curZcityId=$(id).eq(i).attr('zcity-id','zcity'+i);
	}
	for(var u=0;u<size;u++){
		zcity(id+'[zcity-id="zcity'+u+'"]');
	}
}
