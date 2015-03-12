package xu.main.java.distribute_crawler_web.util;

public class StringHandler {

	public static boolean isNullOrEmpty(String input) {
		return null == input || "".equals(input);
	}

	public static String nullToEmpty(Object o) {
		return null == o ? "" : o.toString();
	}
}
