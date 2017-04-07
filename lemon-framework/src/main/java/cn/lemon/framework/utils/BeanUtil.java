package cn.lemon.framework.utils;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

public class BeanUtil {
	private static Logger logger = LoggerFactory.getLogger(BeanUtil.class);
	
	/**
	 * 将当前对象列表转换成对应的实体列表，更新相同字段、保留差异数据
	 * 
	 * @param list 当前对象列表
	 * @param voclazz 生成对象class
	 * @return 生成对象列表
	 */
	@SuppressWarnings("unchecked")
	public static <T, M> List<T> getBeanList(List<M> list, Class<T> voclazz) {
		if (list==null || list.size()==0) {
			return Lists.newArrayList();
		}
		List<T> voList = Lists.newArrayList();
		for (M bean : list) {
			T t = null;
			if (bean instanceof Map) {
				t = toBeanValues((Map<String, Object>)bean, voclazz);
			} else if (bean instanceof Arrays) {
				t = toBeanValues((Object[])bean, voclazz);
			} else if (bean instanceof Serializable) {
				t = toBeanValues(bean, voclazz);
			}			
			voList.add(t);
		}
		return voList;
	}
	
	/**
	 * 将当前对象转换成对应的实体类型
	 * <p>
	 * 一般用于vo对实体的数据更新
	 * </p>
	 * 
	 * @param voclazz 生成对象class
	 * @return 生成对象
	 */
	public static <M, T> T toBeanValues(M object, Class<T> voclazz) {
		T entityvo = null;
		try {
			entityvo = voclazz.newInstance();
			return toBeanValues(object, entityvo);
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * 将当前实体转换成对应的实体类型，更新相同字段、保留差异数据
	 * <p>
	 * 一般用于vo对实体的数据更新
	 * </p>
	 * 
	 * @param entity 当前实体
	 * @param entityvo 需转换生成的对象
	 * @return 生成对象
	 */
	public static <M, T> T toBeanValues(M entity, T entityvo) {
		if (null == entity) {
			return null;
		}
		Class<?> clazz = entity.getClass();
		Class<?> voclazz = entityvo.getClass();
		Field[] fields = ReflectUtil.getPersistFields(clazz);
		for (Field f : fields) {
			try {
				String upperName = Character.toUpperCase(f.getName().charAt(0)) + f.getName().substring(1);
				String setterName = "set" + upperName;
				String getterName = "get" + upperName;
				Method method = ReflectUtil.getMethod(clazz, getterName);
				Method methodvo = ReflectUtil.getMethod(voclazz, setterName);
				if (method == null) {
					method = ReflectUtil.getMethod(clazz, f.getName());
				}
				if (methodvo == null) {
					methodvo = ReflectUtil.getMethod(voclazz, setterName);
				}
				if (method != null && methodvo != null) {
					Object val = method.invoke(entity, null);
					if (null != val) {
						methodvo.invoke(entityvo, val);
					}
				}
			}
			catch (Exception ex) {
				logger.warn("BeanUtil set entity [{}] value error. {}", f.getName(), ex.getMessage());
			}
		}
		return entityvo;
	}
	
	/**
	 * 将当前Map对象转换成对应的实体类型
	 * 
	 * @param map 当前map对象
	 * @param voclazz 生成对象class
	 * @return 生成对象
	 */
	public static <T> T toBeanValues(Map<String, Object> map, Class<T> voclazz) {
		T entityvo = null;
		try {
			entityvo = voclazz.newInstance();
			return toBeanValues(map, entityvo);
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * 将当前Map对象转换成对应的实体类型
	 * 
	 * @param map 当前map对象
	 * @param entityvo 需转换生成的对象
	 * @return 生成对象
	 */
	public static <T> T toBeanValues(Map<String, Object> map, T entityvo) {
		if (null == map || map.size() == 0) {
			return null;
		}
		Class<?> voclazz = entityvo.getClass();
		Field[] fields = ReflectUtil.getPersistFields(voclazz);
		for (Field field : fields) {
			String fieldName = field.getName();
			if (map.containsKey(fieldName)) {
				try {
					String upperName = Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
					String setterName = "set" + upperName;
					Method method = ReflectUtil.getMethod(voclazz, setterName);
					Object object = map.get(fieldName);
					method.invoke(entityvo, object);
				}
				catch (Exception ex) {
					logger.warn("BeanUtil set map [{}] value error. {}", fieldName, ex.getMessage());
				}
			}
		}
		return entityvo;
	}

	/**
	 * 将当前对象数组转换成对应的实体类型
	 * 
	 * @param voclazz 生成对象class
	 * @return 生成对象
	 */
	public static <T> T toBeanValues(Object[] arrayObject, Class<T> voclazz) {
		T entityvo = null;
		try {
			entityvo = voclazz.newInstance();
			return toBeanValues(arrayObject, entityvo);
		}
		catch (Exception ex) {
			return null;
		}
	}
	
	/**
	 * 将当前对象数组转换成对应的实体类型，按索引位置更新
	 * 
	 * @param entityvo 需转换生成的对象
	 * @return 生成对象
	 */
	public static <T> T toBeanValues(Object[] arrayObject, T entityvo) {
		if (null == arrayObject) { return null; }
		//获取标准类型参数
		Class<?> voclazz = entityvo.getClass();
		Field[] fields = ReflectUtil.getPersistFields(voclazz); 
		for (int i=0; i < fields.length; i++){
			try{
				if (i == arrayObject.length){
					break; //超出长度跳出循环
				}
				Field f = fields[i];
				String upperName = Character.toUpperCase(f.getName().charAt(0)) + f.getName().substring(1);
				String setterName = "set" + upperName;
				Method methodvo = null;
				methodvo = ReflectUtil.getMethod(voclazz, setterName);
				if (methodvo != null){
					methodvo.invoke(entityvo, arrayObject[i]);
				}
			}
			catch (Exception ex){
				logger.warn("BeanUtil set object [{}] value error. {}", fields[i].getName(), ex.getMessage());
			}
		}
		return entityvo;
	}
	
	/*public static void main(String[] arg) {
		List<BasicEntityBean> list = Lists.newArrayList();
		BasicEntityBean basicEntityBean = new BasicEntityBean();
		basicEntityBean.setId(Long.valueOf("22222"));
		basicEntityBean.setUsable(true);
		list.add(basicEntityBean);
		list.add(basicEntityBean);
		list.add(basicEntityBean);
				
		List<BasicEntityBean> voList = BeansUtil.getBeanList(list, BasicEntityBean.class);
		logger.debug("voList size:" + voList.size());
		for(BasicEntityBean mp: voList){
			logger.debug("getId:{}, getUsable:{}.", ((BasicEntityBean)mp).getId(), ((BasicEntityBean)mp).getUsable());
		}
	}*/
}
