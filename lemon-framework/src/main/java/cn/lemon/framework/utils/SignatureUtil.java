package cn.lemon.framework.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.lemon.framework.encrypt.Md5Util;
import cn.lemon.framework.encrypt.SHA1Util;

import com.google.common.collect.Maps;

/**
 * 数据签名公共类
 * @author lonyee
 */
public class SignatureUtil {
	private static Logger logger = LoggerFactory.getLogger(SignatureUtil.class);
	
	public static boolean verify(Map<String, String> params) {
		String sign = params.get("sign");
		String signMd5 = getSign(params, "sign", "key", "appsecret");
		logger.debug("sign:{}, signMd5:{}", sign, signMd5);
		return signMd5.equals(sign);
	}
	
	public static String getSign(Map<String, String> params) {
		return getSign(params, "sign", "key", "appsecret");
	}
	
	public static String getSign(Map<String, String> params, String... excludes) {
		return getSign(params, SignType.MD5, excludes);
	}

	public static String getSign(Map<String, String> params, SignType signType, String... excludes) {
		String sign = "";
		if (params == null) {
			return sign;
		}
		List<String> exList = excludes!=null? Arrays.asList(excludes): new ArrayList<String>();
		StringBuilder strBuilder = new StringBuilder();
		Map<String, String> sortedMap = sortMapByKey(params);
		for (String key: exList) {
			sortedMap.remove(key);
		}
		for(Entry<String, String> entry: sortedMap.entrySet()) {
			// 扔掉为空值和排除的参数
			if (entry.getValue()!= null && !entry.getValue().isEmpty()){
				strBuilder.append("&");
				strBuilder.append(entry.getKey());
				strBuilder.append("=");
				strBuilder.append(entry.getValue());
			}
		}
		if(params.get("key")!=null) {
			strBuilder.append("&key=");
			strBuilder.append(params.get("key"));
		}
		//logger.debug("getSign: {}", strBuilder.toString());
		if (strBuilder.length() >= 1) {
			// 去掉第一个&
			switch (signType) {
				case SHA1:
					sign = SHA1Util.getSHA1(strBuilder.deleteCharAt(0).toString());
					break;
				default:
					sign = Md5Util.getMD5(strBuilder.deleteCharAt(0).toString());
					break;
			}
		}
		return sign;
	}

	public static Map<String, String> sortMapByKey(Map<String, String> map) {
		if (map == null || map.isEmpty()) {
			return null;
		}
		Map<String, String> sortedMap = new TreeMap<String, String>(
				new Comparator<String>() {
					public int compare(String obj1, String obj2) {
						// 降序排序
						return obj1.compareTo(obj2);
					}
				});
		sortedMap.putAll(map);
		return sortedMap;
	}
	
	  /** 
	 * 从request中获得参数Map，并返回可读的Map
	 * @return 
	 */  
	public static Map<String, String> getParameterMap(Map<String, String[]> properties) {
		// 返回值Map
		Map<String, String> returnMap = Maps.newHashMap();
		Iterator<Entry<String, String[]>> entries = properties.entrySet().iterator();
		Map.Entry<String, String[]> entry;
		String name = "";
		String value = "";
		while (entries.hasNext()) {
			entry = (Map.Entry<String, String[]>) entries.next();
			name = (String) entry.getKey();
			Object valueObj = entry.getValue();
			if (null == valueObj) {
				value = "";
			} else if (valueObj instanceof String[]) {
				String[] values = (String[]) valueObj;
				for (int i = 0; i < values.length; i++) {
					value = values[i] + ",";
				}
				value = value.substring(0, value.length() - 1);
			} else {
				value = valueObj.toString();
			}
			returnMap.put(name, value);
		}
		return returnMap;
	}
	
	public enum SignType {
		MD5,
		SHA1
	}
}
