package egovframework.dnworks.cmm.synap;

import java.security.*;
import java.util.Timer;
import java.util.TimerTask;

import egovframework.com.cmm.service.EgovProperties;

import java.util.Calendar;
import java.io.File;

public class ConvertToHtml {
    
	//private final String SystemDir 		= EgovProperties.getProperty("Globals.CFG_SYSTEMDIR");
	private final String SYNAP_HOME		= EgovProperties.getProperty("Globals.SynapHome");
    private final String SN3HCV_HOME 	= SYNAP_HOME+"/sn3hcv/";
    private final String TEMPLATE 		= SN3HCV_HOME + "template";
    private final String MODULES 		= SN3HCV_HOME + "modules";
    /* 
     * 변환호출
     * inputFile : 변환대상 파일의 절대경로
     * outputPath : 변환된 HTML 파일 저장경로
     * resultName : 생성할 HTML 파일명 (ex; sample => sample.htm 파일 생성됨)
     * return : 0 (변환성공) 0이외의 값 (변환실패)
     */
    public int convertToHtml(String inputFile, String outputPath, String resultName) 
    {
        // 기존 변환결과 존재여부 확인
        File htmlFile = new File(outputPath + "/" + resultName + ".xml");
        File htmlDir = new File(outputPath + "/" + resultName + ".files");
        
        // 기존 변환결과가 존재하지 않을 경우 변환실행
        if (!htmlFile.exists() || !htmlDir.isDirectory()) 
        {
        	String cmdPath = String.format("-mod_path %s -t %s %s %s %s"
        									, MODULES
        									, TEMPLATE
        									, inputFile
        									, outputPath
        									, resultName
        									);
            try {
                Timer t = new Timer();
                
                System.out.println(SN3HCV_HOME + "sn3hcv_exe "+cmdPath);
                Process proc = Runtime.getRuntime().exec(SN3HCV_HOME + "sn3hcv_exe "+cmdPath);
   
                TimerTask killer = new TimeoutProcessKiller(proc);
                t.schedule(killer, 200000); // 200초 (변환이 200초 안에 완료되지 않으면 프로세스 종료)
   
                int exitValue = proc.waitFor();
                killer.cancel();
    
                return exitValue;
            } catch (Exception e) {
            	System.out.println("ConvertToHtml.java  convertToHtml Error ");
                return -1;
            }
        }
        else {
            return 0;   // 기존 변환결과가 존재함. 정상 변환으로 처리
        }
    }
 
    // 저장경로에 월별 폴더 생성
    public String makeMonthDir(String outputPath) 
    {
        Calendar cal = Calendar.getInstance();
        String dateString = String.format("%04d%02d", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1);
        File targetDir = new File(outputPath + File.separator + dateString);
        
        if(!targetDir.exists()) {
            targetDir.mkdirs();
        }
         
        return targetDir.getAbsolutePath();
    }
    
    public String nameGoMD5(String str)
    {
		String MD5 = ""; 
		try{
			MessageDigest md = MessageDigest.getInstance("MD5"); 
			md.update(str.getBytes()); 
			byte byteData[] = md.digest();
			StringBuffer sb = new StringBuffer(); 
			for(int i = 0 ; i < byteData.length ; i++){
				sb.append(Integer.toString((byteData[i]&0xff) + 0x100, 16).substring(1));
			}
			MD5 = sb.toString();
			
		}catch(NoSuchAlgorithmException e){
			System.out.println("ConvertToHtml.java nameGoMD5 Error ");
			MD5 = null; 
		}
		return MD5;
	}
}