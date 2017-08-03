package com.ramostear.xbuilder.util;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesUtil {

private static PropertiesUtil propUtil = null;
	
	@SuppressWarnings("unused")
	private static Properties props = null;
	
	private static Map<String, Properties> propMap = null;
	
	private PropertiesUtil(){}
	
	public static PropertiesUtil getInstance(){
		if(propUtil == null){
			propUtil = new PropertiesUtil();
			propMap = new HashMap<String, Properties>();
		}
		return propUtil;
	}
	/**
	 * 获取配置文件
	 * @param propName
	 * @return
	 */
	public Properties getProperties(String propName){
		if(propMap.get(propName) != null){
			props = propMap.get(propName);
			return propMap.get(propName);
		}else{
			Properties prop = new Properties();
			try {
				prop.load(PropertiesUtil.class.getResourceAsStream("/"+propName+".properties"));
				props = prop;
				propMap.put(propName, prop);
				return prop;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 获取指定properties文件中的指定属性值
	 * @param propName
	 * @param key
	 * @return
	 */
	public String readValue(String propName,String key){
		Properties prop = getProperties(propName);
		String value = prop.getProperty(key);
		return value;
	}
	/**
	 * 
	 * @param propName
	 * @param key
	 * @param value
	 */
	public void writeProperties(String propName,String key,String value){
		try {
			OutputStream out = new FileOutputStream("src/main/resources/"+propName+".properties");
			Properties prop = this.getProperties(propName);
			prop.setProperty(key, value);
			prop.store(out, "update \""+key+"="+value+"\"");
			System.out.println("ok");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
}
