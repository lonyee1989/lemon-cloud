package cn.lemon.framework.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 下划线/驼峰格式互转
 *
 * @author lonyee
 * @date 2016-05-14
 */
public class Underline2Camel {
    public static String underline2Camel(String line) {
        return underline2Camel(line, true);
    }

    public static String camel2Underline(String line) {
        return camel2Underline(line, false);
    }

    public static String underline2Camel(String line,boolean smallCamel){
        if(line==null||"".equals(line)){
            return "";
        }
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(smallCamel&&matcher.start()==0?Character.toLowerCase(word.charAt(0)):Character.toUpperCase(word.charAt(0)));
            int index=word.lastIndexOf('_');
            if(index>0){
                sb.append(word.substring(1, index).toLowerCase());
            }else{
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    public static String camel2Underline(String line, boolean upperCase){
        if(line==null||"".equals(line)){
            return "";
        }
        line=String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb=new StringBuffer();
        Pattern pattern=Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher=pattern.matcher(line);
        while(matcher.find()){
            String word=matcher.group();
            sb.append(upperCase?word.toUpperCase():word.toLowerCase());
            sb.append(matcher.end()==line.length()?"":"_");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String field = "testField";
        System.out.println(Underline2Camel.camel2Underline(field));
    }
}
