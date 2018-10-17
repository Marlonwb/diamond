package person.marlon.diamond.common.enums;

public enum BrowserEnum {
	SAFARI("safari"),CHROME("chrome"),FIREFOX("firefox"),OPERA("opera"),EDGE("edge"),IE11("rv:11.0"),
	IE10("mise 10."),IE10x("msie 8.0; windows nt 5.2; trident/6.0"),IE9("mise 9.0"),IE8("mise 8.0"),
	IE7("mise 7.0"),IE6("mise 6.0"),MAXTHON( "maxthon"),QQ("qqbrowser"),GREEN("greenbrowser"),SE360("360se"),
	OTHER("others");

	private String value;

	private BrowserEnum(String value) { this.value = value; }

	public String getValue() {
		return this.value;
	}
}
