package cn.lemon.framework.utils;

import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil {
	private static Logger logger = LoggerFactory.getLogger(JsonUtil.class);

	public static String writeValue(Object object) {
		StringWriter result = new StringWriter();
		try {
			ObjectMapper mapper = new JsonMapper();
			mapper.writeValue(result, object);
		}
		catch (Exception e) {
			logger.warn("write json value error:"+ e.getMessage());
		}
		return result.toString().length()!=0 ? result.toString(): "{}";
	}
	
	public static <T> T readValue(String json, Class<T> clazz) {
		T result = null;
		try {
			if (json!=null) {
				ObjectMapper mapper = new JsonMapper();
				result = mapper.readValue(json, clazz);
			}
		}
		catch (Exception e) {
			logger.warn("read json value error:"+ e.getMessage());
		}
		return result;
	}
}
