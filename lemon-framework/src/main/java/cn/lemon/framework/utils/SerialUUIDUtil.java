package cn.lemon.framework.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


/**
 * 全局唯一UUID生成
 * @author lonyee
 */
public class SerialUUIDUtil {
	
	// 要使用生成 64进制 的字符  
	private String[] chars = new String[] { "a" , "b" , "c" , "d" , "e" , "f" , "g" , "h" ,  
           "i" , "j" , "k" , "l" , "m" , "n" , "o" , "p" , "q" , "r" , "s" , "t" ,  
           "u" , "v" , "w" , "x" , "y" , "z" , "0" , "1" , "2" , "3" , "4" , "5" ,  
           "6" , "7" , "8" , "9" , "A" , "B" , "C" , "D" , "E" , "F" , "G" , "H" ,  
           "I" , "J" , "K" , "L" , "M" , "N" , "O" , "P" , "Q" , "R" , "S" , "T" ,  
           "U" , "V" , "W" , "X" , "Y" , "Z"  
    };
	
	private static class UUIdGenHolder {
        private static final SerialUUIDUtil instance = new SerialUUIDUtil();
    }
    
    public static SerialUUIDUtil instance(){
        return UUIdGenHolder.instance;
    }
    
    /**
     * 生成UUID
     */
    public synchronized String nextId() {
    	return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
    
    /**
     * 生成短UUID
     */
    public synchronized String nextShortId() {
    	String hex = nextId();
    	return this.hexTo64(hex);
    }
    
    private String hexTo64(String hex) {
        StringBuffer r = new StringBuffer();
        int hexLen = hex.length();
        int subHexLen = hexLen/8;
        for(int i = 0;i < subHexLen;i++){
            String outChars = "";
            int j = i+1;
            String subHex = hex.substring(i*8,j*8);
            long idx = Long.valueOf("3FFFFFFF",16) & Long.valueOf(subHex,16);
            for(int k = 0;k < 3;k++){
                int index = (int)(Long.valueOf("0000003D",16) & idx);
                outChars += chars[index];
                idx = idx >> 5;
            }
            r.append(outChars);
        }
        return r.toString();
    }
    
    /*public static void main(String[] arg) {
    	Set<String> set = new HashSet<>();
    	List<String> set2 = new ArrayList<>();
    	for (int i=0; i<10000000; i++) {
    		// 打印出结果
        	String aResult = SerialUUIDUtil.instance().nextShortId();
        	if (set.contains(aResult)) {
        		set2.add(aResult);
        	}
        	set.add(aResult);
        	System.out.println(aResult);
    	}
    	System.out.println(set2.size());
    }*/
}
