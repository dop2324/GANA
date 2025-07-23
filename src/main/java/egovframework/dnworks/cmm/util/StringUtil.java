package egovframework.dnworks.cmm.util;

public class StringUtil 
{
	/* **********************************************************************
	 * Substring method
	 * **********************************************************************/
	
	public static String substringNVar(String srcString, int columnSize)
	{
		/* *************************************************************
		 * 1.널처리하고 앞뒤공백 없엔다.
		 * 2.빈값이라면 공백하나 넣는다.(오라클에서는 빈값이 널로 인식되기 때문이다.)
		 * 3.특수문자 처리한다.
		 * *************************************************************/
		//srcString = stripSpecial(String.format("%1s", nvl(srcString)));	
		srcString = String.format("%1s", Util.nvl(srcString));
		
		columnSize = srcString.length() > columnSize ? columnSize : srcString.length();  
			
		return srcString.substring(0, columnSize);
	}
	
	// Substring For euc-kr varchar2 (unicode character : 2byte)
	public static String substringVarU2(String srcString, int columnSize)
	{ 
		StringBuffer sb = new StringBuffer();
		int tempByteLength = 0;
		
		/* *************************************************************
		 * 1.널처리하고 앞뒤공백 없엔다.
		 * 2.빈값이라면 공백하나 넣는다.(오라클에서는 빈값이 널로 인식되기 때문이다.)
		 * 3.특수문자 처리한다.
		 * *************************************************************/
		//srcString = stripSpecial(String.format("%1s", nvl(srcString)));
		srcString = String.format("%1s", Util.nvl(srcString));
		
		
		for(int i=0; i<srcString.length(); i++)
		{
			// 한글자 마다 차지 하는 바이트 길이만큼 더한다.
			tempByteLength += srcString.substring(i, i+1).getBytes().length;
			
			if(tempByteLength > columnSize) break;
			
			sb.append(srcString.substring(i, i+1));
		}
		
		return sb.toString();
	}
	
	// Substring For utf-8 varchar2 (unicode character : 3byte)
	public static String substringVarU3(String srcString, int columnSize)
	{
		StringBuffer sb = new StringBuffer();
		int tempByteLength = 0;
		
		/* *************************************************************
		 * 1.널처리하고 앞뒤공백 없엔다.
		 * 2.빈값이라면 공백하나 넣는다.(오라클에서는 빈값이 널로 인식되기 때문이다.)
		 * 3.특수문자 처리한다.
		 * *************************************************************/
		srcString = String.format("%1s", Util.nvl(srcString));
		
		for(int i=0; i<srcString.length(); i++)
		{
			// 한글자 마다 차지 하는 바이트 길이만큼 더하되
			// 오라클 utf-8은 한글이 3byte차지한다.
			switch(srcString.substring(i, i+1).getBytes().length)
			{
				case 1 : tempByteLength += 1; break;
				case 2 : tempByteLength += 3; break;
			}
						
			if(tempByteLength > columnSize) break;
			
			sb.append(srcString.substring(i, i+1));
		}
		
		return sb.toString();
	}
}
