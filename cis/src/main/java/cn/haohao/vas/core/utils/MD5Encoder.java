package cn.haohao.vas.core.utils;

import java.security.MessageDigest;

/**
 * MD5加密
 * 
 * @author jevons.zheng
 * @since 2009-07-25
 * @version 1.0
 */
public class MD5Encoder {

	public final static String encode(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };
		try {
			byte[] strTemp = s.getBytes();
			MessageDigest mdTemp = MessageDigest.getInstance("MD5");
			mdTemp.update(strTemp);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			return null;
		}
	}

	public static void main(String[] args) {
		String code = args != null && args.length > 0 && args[0] != null ? args[0] : "11111111";
		System.out.println(MD5Encoder.encode(code));
	}
}
