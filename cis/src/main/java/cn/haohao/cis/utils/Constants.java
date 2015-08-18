package cn.haohao.cis.utils;

public class Constants {
	
	/**
	 * 侧边菜单选择样式的class
	 */
	public final static String ACTIVE_CLASS = PropertiesUtils.getProperties().getProperty("active_class");
	
	public final static String LOGINED_USER_BEAN_NAME = "loginedUser";
	
	public final static String DEFAULT_PASSWORD = PropertiesUtils.getProperties().getProperty("default_password");
	
	public final static String USER_LEVEL_A = "A";
	public final static String USER_LEVEL_B = "B";
	public final static String USER_LEVEL_C = "C";
	public final static String USER_LEVEL_D = "D";
	public final static String USER_LEVEL_E = "E";
	public final static String USER_LEVEL_X = "X";
}
