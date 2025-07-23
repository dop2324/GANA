package egovframework.dnworks.cmm.cipher.aes;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.cmm.util.Util;


public class AESUtil {
	private static String key128 = EgovProperties.getProperty("Globals.128Key");
	private static String key256 = EgovProperties.getProperty("Globals.EncryptKey");
	
	public static String gnrtEncKey(int len) {
	    byte[] key = new byte[len];
	    new SecureRandom().nextBytes(key);
	    return Base64.getEncoder().encodeToString(key);
	  }
	
	public static String rightStr(String str, int length, char fillChar) {
		if (str.length() < length) {
			str = rpad(str, length, fillChar);
		}
		str = str.substring(str.length()- length, str.length());
		return new String(str);
	}
	public static String leftStr(String str, int length, char fillChar) {
		if (str.length() < length) {
			str = rpad(str, length, fillChar);
		}
		str = str.substring(0, length);
		return new String(str);
	}
	
	public static String rpad(String str, int length, char fillChar) {
		if (str.length() > length) return str;
		char[] chars = new char[length];
		Arrays.fill(chars, fillChar);
		System.arraycopy(str.toCharArray(), 0, chars, 0, str.length());
		return new String(chars);
	}
	
	public static String encrypt(String value) {
		return encrypt(key128, value);
	}
	public static String encrypt(String key, String value) {
		if(!Util.nvl(value).equals("")) {
			AES128 aes = new AES128(rpad(key, 16, '0'));
			return aes.encrypt(value);
		}
		return "";
	}
	public static String decrypt(String value) {
		return decrypt(key128, value);
	}
	public static String decrypt(String key, String value) {
		if(!Util.nvl(value).equals("")){
			AES128 aes = new AES128(rpad(key, 16, '0'));
			return aes.decrypt(value);
		}
		return "";
	}
	
	public static String encrypt256(String value) throws Exception {
		return encrypt256(key256, value);
	}
	public static String encrypt256(String key, String value) throws Exception {
		if(!Util.nvl(value).equals("")) {
			AES256 aes = new AES256(rpad(key, 16, '0'));
			return aes.encrypt(value);
		}
		
		return "";
	}
	public static String decrypt256(String value) throws Exception {
		return decrypt256(key256, value);
	}
	public static String decrypt256(String key, String value) throws Exception {
		if(!Util.nvl(value).equals("")) {
			AES256 aes = new AES256(rpad(key, 16, '0'));
			return aes.decrypt(value);
		}
		return "";
	}
}
