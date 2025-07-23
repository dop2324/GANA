package egovframework.dnworks.cmm.cipher.aes;

import java.io.UnsupportedEncodingException;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class AES128 {
    private String ips;
    private Key keySpec;
    
    private String secretKey;
    
	public void setSecretKey(String key) {
		this.secretKey = key;
	}

	public AES128(String secretKey) {
        try {
            byte[] keyBytes = new byte[16];
            byte[] b = secretKey.getBytes("UTF-8");
            System.arraycopy(b, 0, keyBytes, 0, keyBytes.length);
            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
            this.ips = secretKey.substring(0, 16);
            this.keySpec = keySpec;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
 
    public String encrypt(String str) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(ips.getBytes()));
 
            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
 
            return Base64.getEncoder().encodeToString(encrypted);
            
        } catch (Exception e) {
        	System.out.println("AES128.java  encrypt Error ");
        }
        return null;
    }
 
    public String decrypt(String str) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ips.getBytes("UTF-8")));
 
            byte[] byteStr = Base64.getDecoder().decode(str.getBytes());
            String Str = new String(cipher.doFinal(byteStr), "UTF-8");
 
            return Str;
        } catch ( NumberFormatException nfe ) {
	        return "Not a Valid AES Encrypted String";
        } catch (Exception e) {
        	System.out.println("AES128.java  decrypt Error ");
        }
        
        return null;
    }
}
