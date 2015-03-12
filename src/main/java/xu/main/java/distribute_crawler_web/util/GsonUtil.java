package xu.main.java.distribute_crawler_web.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonUtil {
	
	private static Gson gson  = new GsonBuilder().disableHtmlEscaping().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
	
	public static String toJson(Object o){		
		return gson.toJson(o);
	}
	
	public static <T> T fromJson(String json, Class<T> clazz){		
		return gson.fromJson(json, clazz);
	}
}
