package person.marlon.diamond.common.enums;

public enum DeviceEnum {
	ANDROID("Android"),IPHONE("iPhone"),IPAD("iPad"),IPOD("iPod"),
	DESKTOP("Desktop"),BROWSER("Browser");

	private String value;
	private DeviceEnum(String value) { this.value = value; }

	public String getValue() {
		return this.value;
	}
}
