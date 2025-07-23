package egovframework.dnworks.cmm.util;

import java.util.*;
import java.util.regex.*;

public class MaskUtil 
{
	private static final String PHONE_NUM_PATTERN = "(01[016789])(\\d{3,4})(\\d{4})";
	private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	                                            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	public static String getMaskid(String name)
	{
		String rtnVal = "";
		String first = "";
		String last = "";
		String midStr = "";
		int len = name.length();
		
		if(len > 4)
		{
			first = name.substring(0, 3);
			last = name.substring(len-2, len);
			int mid = len-4;
			for(int i=0; i<mid; i++) {midStr += "*";}
			
			rtnVal = String.format("%s%s%s", first, midStr, last);
		}
		else
		{
			first = name.substring(0, 2);
			last = name.substring(len-2, len);
			rtnVal = String.format("%s*%s", first, last);
		}
		
		return rtnVal;
	}		
	
	public static String getMaskName(String name)
	{
		String rtnVal = "";
		String first = "";
		String last = "";
		
		String midStr = "";
		int len = name.length();
		if(len > 3)
		{
			first = name.substring(0, 1);
			last = name.substring(len-1, len);
			for(int i = 0; i < (len-2); i++) {midStr += "*";}
			rtnVal = String.format("%s%s%s", first, midStr, last);
		}
		else
		{
			first = name.substring(0, 1);
			last = name.substring(len-1, len);
			rtnVal = String.format("%s*%s", first, last);
			//rtnVal = String.format("%s**", first);
		}

		
		return rtnVal;
	}
		/**
	    * 이메일이든, 휴대폰번호든 각 포맷에 맞게 마스킹된 결과값 리턴해주는 함수
	    * 포맷이 맞지 않을 경우 인풋으로 들어온 값 그대로 리턴
	    *
	    * public은 이거 하나! valid check류의 메서드들도 추후 필요하면 public으로 바꿀 예정
	    *
	    * @param id
	    * @return maskedId
	    */
	   public static String getMasked(String id) {
	      if (isEmail(id)) 
	      {
	         return getMaskedEmail(id);
	      } 
	      else if (isPhoneNum(id)) 
	      {
	         return getMaskedPhoneNum(id);
	      }
	      return id;
	   }

	   /**
	    * 이메일 포맷 Validator
	    * @param str
	    * @return isValidEmailFormat
	    */
	   private static boolean isEmail(final String str) {
	      return isValid(EMAIL_PATTERN, str);
	   }

	   /**
	    * 휴대폰 번호 포맷 Validator
	    * @param str
	    * @return isValidCellPhoneNumFormat
	    */
	   private static boolean isPhoneNum(final String str) {
	      return isValid(PHONE_NUM_PATTERN, str);
	   }

	   /**
	    * 문자열이 정규식에 맞는 포맷인지 체크
	    * @param regex
	    * @param target
	    * @return isValid
	    */
	   private static boolean isValid(final String regex, final String target) {
	      Matcher matcher = Pattern.compile(regex).matcher(target);
	      return matcher.matches();
	   }

	   /**
	    * 이메일 주소 마스킹 처리
	    * @param email
	    * @return maskedEmailAddress
	    */
	   private static String getMaskedEmail(String email) {
	      /*
	      * 요구되는 메일 포맷
	      * {userId}@domain.com
	      * */
	      String regex = "\\b(\\S+)+@(\\S+.\\S+)";
	      Matcher matcher = Pattern.compile(regex).matcher(email);
	      if (matcher.find()) {
	         String id = matcher.group(1); // 마스킹 처리할 부분인 userId
	         /*
	         * userId의 길이를 기준으로 세글자 초과인 경우 뒤 세자리를 마스킹 처리하고,
	         * 세글자인 경우 뒤 두글자만 마스킹,
	         * 세글자 미만인 경우 모두 마스킹 처리
	         */
	         int length = id.length();
	         if (length < 3) {
	            char[] c = new char[length];
	            Arrays.fill(c, '*');
	            return email.replace(id, String.valueOf(c));
	         } else if (length == 3) {
	            return email.replaceAll("\\b(\\S+)[^@][^@]+@(\\S+)", "$1**@$2");
	         } else {
	            return email.replaceAll("\\b(\\S+)[^@][^@][^@]+@(\\S+)", "$1***@$2");
	         }
	      }
	      return email;
	   }

	   /**
	    * 휴대폰 번호 마스킹 처리
	    * @param phoneNum
	    * @return maskedCellPhoneNumber
	    */
	   private static String getMaskedPhoneNum(String phoneNum) {
	      /*
	      * 요구되는 휴대폰 번호 포맷
	      * 01055557777 또는 0113339999 로 010+네자리+네자리 또는 011~019+세자리+네자리 이!지!만!
	      * 사실 0107770000 과 01188884444 같이 가운데 번호는 3자리 또는 4자리면 돈케어
	      * */
	      String regex = "(01[016789])(\\d{3,4})\\d{4}$";
	      Matcher matcher = Pattern.compile(regex).matcher(phoneNum);
	      if (matcher.find()) {
	         String replaceTarget = matcher.group(2);
	         char[] c = new char[replaceTarget.length()];
	         Arrays.fill(c, '*');
	         return phoneNum.replace(replaceTarget, String.valueOf(c));
	      }
	      return phoneNum;
	   }

}
