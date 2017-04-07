package cn.lemon.framework.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * ReflectUtil
 *
 * @author Eric
 * @date 16/5/27
 */
public class ReflectUtil {

    /**
     * 基础类型的集合,用于判断一个字段是否属于8大基础类型, 或者String, Date等可以映射到数据库列的类型
     */
    private final static Set<String> primitiveTypes = Sets.newHashSet(
            "java.lang.Integer",
            "java.lang.Double",
            "java.lang.Float",
            "java.lang.Long",
            "java.lang.Short",
            "java.lang.Byte",
            "java.lang.Boolean",
            "java.lang.Character",
            "java.lang.String",
            "java.util.Date",
            "int","double","long","short","byte","boolean","char","float");

    /**
     * 获取成员变量的修饰符
     * @param clazz
     * @param field
     * @return
     * @throws Exception
     */
    public static <T> int getFieldModifier(Class<T> clazz, String field) throws Exception {
        //getDeclaredFields可以获取所有修饰符的成员变量，包括private,protected等getFields则不可以
        Field[] fields = getFields(clazz);

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(field)) {
                return fields[i].getModifiers();
            }
        }
        throw new Exception(clazz+" has no field \"" + field + "\"");
    }

    /**
     * 获取成员方法的修饰符
     * @param clazz
     * @param method
     * @return
     * @throws Exception
     */
    public static <T> int getMethodModifier(Class<T> clazz, String method) throws Exception {

        //getDeclaredMethods可以获取所有修饰符的成员方法，包括private,protected等getMethods则不可以
        Method[] m = clazz.getMethods();  //clazz.getDeclaredMethods();

        for (int i = 0; i < m.length; i++) {
            if (m[i].getName().equals(m)) {
                return m[i].getModifiers();
            }
        }
        throw new Exception(clazz+" has no method \"" + m + "\"");
    }

    /**
     * 根据成员变量名称获取对象值
     * @param clazzInstance
     * @param field
     * @return 
     * @throws IllegalAccessException 
     * @throws IllegalArgumentException 
     * @throws NoSuchFieldException
     * @throws SecurityException
     */
    public static <T> Object getFieldValue(Object clazzInstance, Object field) throws IllegalArgumentException, IllegalAccessException {

        Field[] fields = getFields(clazzInstance.getClass());

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(field)) {
                //对于私有变量的访问权限，在这里设置，这样即可访问Private修饰的变量
                fields[i].setAccessible(true);
                return fields[i].get(clazzInstance);
            }
        }
        return null;
    }

    /**
     * 根据成员变量名称获取类值（默认值）
     * @param clazz
     * @param field
     * @return
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static <T> Object getFieldValue(Class<T> clazz, String field) throws IllegalArgumentException, IllegalAccessException, InstantiationException {

        Field[] fields = getFields(clazz);

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getName().equals(field)) {
                //对于私有变量的访问权限，在这里设置，这样即可访问Private修饰的变量
                fields[i].setAccessible(true);
                return fields[i].get(clazz.newInstance());
            }
        }

        return null;
    }

    /**
     * 利用反射设置指定对象的指定属性为指定的值
     * @param obj
     * @param fieldName
     * @param fieldValue
     * @return
     */
    public static void setFieldValue(Object obj, String fieldName, String fieldValue) throws IllegalArgumentException, IllegalAccessException {
		Field field = getField(obj.getClass(), fieldName);
		if (field != null) {
			field.setAccessible(true);
			field.set(obj, fieldValue);
		}
	}
    
    /**
     * 返回指定字段
     * @param clazz
     * @param fieldName
     * @return
     */
    public static <T> Field getField(Class<T> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            if (field == null) {
            	field = clazz.getSuperclass().getDeclaredField(fieldName);
            }
            return field;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取所有的成员变量 (包含父类的成员变量)
     * @param clazz
     * @return Field[]
     */
    public static <T> Field[] getFields(Class<T> clazz) {

    	Field[] superFields = clazz.getSuperclass().getDeclaredFields();
    	Field[] extFields = clazz.getDeclaredFields();
    	Field[] fields = new Field[superFields.length + extFields.length];
		System.arraycopy(superFields, 0, fields, 0, superFields.length);
		System.arraycopy(extFields, 0, fields, superFields.length, extFields.length);
		
        return fields;
    }
    
    /**
     * 获取所有的方法  (包含父类的方法 )
     * @param clazz
     * @return Field[]
     */
    public static <T> Method[] getMethods(Class<T> clazz){
    	Method[] superMethods = clazz.getSuperclass().getDeclaredMethods();
    	Method[] extMethods = clazz.getDeclaredMethods();
    	Method[] methods = new Method[superMethods.length + extMethods.length];
		System.arraycopy(superMethods, 0, methods, 0, superMethods.length);
		System.arraycopy(extMethods, 0, methods, superMethods.length, extMethods.length);
		
		return methods;
    }
    
    /**
     * 获取指定的方法 (包含父类的方法)
     * @param clazz
     * @return Method
     */
    public static <T> Method getMethod(Class<T> clazz, String methodName) {
    	Method[] methods = clazz.getDeclaredMethods();
    	for (Method m : methods)
		{
			if (m.getName().equals(methodName)) 
			{
				return m;
			}
		}
    	methods = clazz.getSuperclass().getDeclaredMethods();
    	for (Method m : methods)
		{
			if (m.getName().equals(methodName)) 
			{
				return m;
			}
		}
    	return null;
    }
    

    /**
     * 获取持久化模型的持久属性,判断根据是:
     * 1. Field的Modifiers==Private
     * 2. Field的Type属于基本类型
     * 
     * @param clazz
     * @return
     */
    public static <T> Field[] getPersistFields(Class<T> clazz) {
        Field[] fields = getFields(clazz);

        List<Field> result = Lists.newArrayList();

        for (int i = 0; i < fields.length; i++) {
            if (fields[i].getModifiers() != Modifier.PRIVATE) {
                continue;
            }

            if (!primitiveTypes.contains(fields[i].getType().getName())) {
                continue;
            }

            result.add(fields[i]);
        }

        return result.toArray(new Field[]{});
    }
}
