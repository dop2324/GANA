package egovframework.dnworks.cmm.cipher;

import java.security.SecureRandom;
import java.util.Base64;

import egovframework.dnworks.cmm.cipher.kisa.KISA_SHA256;
import egovframework.dnworks.cmm.cipher.kisa.SHA256;


public class SHA256Util 
{
	public static String getSalt() {
        //1. Random, salt 생성
        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[20];

        //2. 난수 생성
        sr.nextBytes(salt);

        //3. byte To String (10진수 문자열로 변경)
        StringBuffer sb = new StringBuffer();
        for(byte b : salt) {
            sb.append(String.format("%02x", b));
        }

        return sb.toString();
    }
	
	public static String getSHA256(String mem_pass, String salt) {
		String sha256_pwd="";
		String passSalt = mem_pass+salt;
		
		SHA256 s = new SHA256( passSalt.getBytes() );
		sha256_pwd = Base64.getEncoder().encodeToString(s.GetHashCode()) ;
		return sha256_pwd;
	}
	
	public static String getSHA256(String mem_pass) {
		String sha256_pwd="";
		SHA256 s = new SHA256( mem_pass.getBytes() );

		sha256_pwd = Base64.getEncoder().encodeToString(s.GetHashCode()) ;		
		return sha256_pwd;
	}
}
