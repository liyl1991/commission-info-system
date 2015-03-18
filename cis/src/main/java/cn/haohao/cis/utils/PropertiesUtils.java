package cn.haohao.cis.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PropertiesUtils {

	private static String PROPERTIES_FILE_NAME = "cfg/cis.properties";
	private static Properties properties = new Properties();
	protected static Log logger = LogFactory.getLog(PropertiesUtils.class);
	
	public static Properties getProperties() {
		return properties;
	}

	public static Properties getProperties(String classPath){
		InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(classPath);
		Properties pro = new Properties();
		try {
			pro.load(in);
		} catch (IOException e) {
			logger.error(e);
		}
		return pro;
	}
	
	static {
		InputStream in = PropertiesUtils.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE_NAME);
		try {
			properties.load(in);
		} catch (IOException e) {
			logger.error(e);
		}
	}

}