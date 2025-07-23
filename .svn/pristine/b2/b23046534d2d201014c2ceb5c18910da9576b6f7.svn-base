package egovframework.dnworks.cmm;

import java.io.*;
import java.util.*;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.cmm.util.Util;

public class FileManager 
{
	public FileManager(){}
	
	public synchronized static boolean existsFile(String where, String subDir, String fileName)
	{
		return existsFile(where+subDir+fileName);
	}
	public synchronized static boolean existsFile(String fileName)
	{
		boolean flag = false;
		File file = new File(fileName);
		if(file.exists()) {
			flag = true;
		}
		
		return flag;
	}
	
	/* **********************************************************************
	 * Create Directory (Create nested Directory)
	 * **********************************************************************/
	public synchronized static boolean createDir(String dirPath, String dirName)
	{
		return createDir(dirPath + "/" + dirName);
	}
	
	public synchronized static boolean createDir(String dirPath)
	{
		return createDir(new File(dirPath));
	}	

	public synchronized static boolean createDir(File dir)
	{
		boolean flag = false;

		if(dir.exists()) {
			flag = true;
		}else{
			dir.setExecutable(false, true);
			dir.setReadable(true);
			dir.setWritable(false, true);
			
			flag = dir.mkdirs();
		}

		return flag;
	}

	/* **********************************************************************
	 * Delete Directory (Delete nested Directory and File)
	 * **********************************************************************/
	public synchronized static boolean deleteDir(String dirPath, String dirName)
	{
		return deleteDir(dirPath + "/" + dirName);
	}
	
	public synchronized static boolean deleteDir(String dirPath)
	{
		return deleteDir(new File(dirPath));
	}
	
	public synchronized static boolean deleteDir(File dir)
	{
		if(dir.exists()) {
			File[] files = dir.listFiles(); 
			
			if(files != null) {
				for(File file:files) {
					if(file.isDirectory()) {
						deleteDir(file);			// Recursive call
					}else{
						file.delete();
					}
				}
			}

			dir.delete();
		}
		
		return !dir.exists();
	}
	
	/* **********************************************************************
	 * Move Directory (Move nested Directory and File)
	 * **********************************************************************/
	public synchronized static File moveDir(String fromDirPath, String toDirPath)
	{
		File foDir	= new File(fromDirPath);
		File toDir	= new File(toDirPath);

		// 하위 디렉토리 포함하여 이동되지만..존재하지 않는 디렉토리를 만들지는 않는다.
		foDir.renameTo(toDir);
		
		return toDir;
	}
	
	/* **********************************************************************
	 * Copy Directory (Copy nested Directory and File)
	 * **********************************************************************/
	public synchronized static File copyDir(String fromDirPath, String toDirPath)
	{
		File foDir	= new File(fromDirPath);
		File toDir	= new File(toDirPath);
		
		if(foDir.exists()) {
			File[] files = foDir.listFiles(); 
			if(files != null) {
				for(File file:files) {
					if(file.isDirectory()) {
						copyDir(file.getAbsolutePath(), String.format("%s/%s", toDir.getAbsolutePath(), file.getName()));
					}else{
						ioFile(fromDirPath, toDirPath, file.getName(), file.getName(), false);
					}
				}
			}
		}
		
		return toDir;
	}
	
	
	/* **********************************************************************
	 * Create File
	 * **********************************************************************/
	public synchronized static boolean createFile(String dirPath, String fileName)
	{
		return createFile(dirPath + "/" + fileName);
	}
	
	public synchronized static boolean createFile(String filePath)
	{
		return createFile(new File(filePath));
	}
	
	public synchronized static boolean createFile(File file)
	{
		boolean flag = false;
		
		try {
			if(file.exists()) {
				flag = true;
			}else{
				flag = file.createNewFile();
			}
		}catch(IOException e) {
			System.out.println("FileManager.java createFile Error ");
		}
		
		return flag;
	}
	
	
	
	/* **********************************************************************
	 * Delete File
	 * **********************************************************************/
	public synchronized static boolean deleteFile(String dirPath, String fileName)
	{
		return deleteFile(dirPath + fileName);
	}
	
	public synchronized static boolean deleteFile(String filePath)
	{
		return deleteFile(new File(filePath));
	}
	
	public synchronized static boolean deleteFile(File file)
	{
		boolean flag = false;
		
		if(file.exists()) {
			flag = file.delete();
			
			//디렉토리파일 없을때 지운다
			if(file != null) {
				if(file.getParentFile() != null) {
					File[] path = file.getParentFile().listFiles();
					if(path != null && path.length == 0) deleteDir(file.getParentFile());
				}
			}
		}else{
			flag = true;
		}
			
		return flag;
	}
	
	/* **********************************************************************
	 * Move File
	 * **********************************************************************/
	public synchronized static File moveFile(String fromDirPath, String toDirPath, String fileName)
	{
		File foFile	= new File(String.format("%s/%s", fromDirPath, fileName));
		File toFile	= new File(String.format("%s/%s", toDirPath, fileName));
		
		// 파일이동시 디렉토리가 있어야 된다.(중간경로 포함)
		createDir(toDirPath);
		
		foFile.renameTo(toFile);
		
		return toFile;
	}
	
	/* **********************************************************************
	 * Copy File
	 * **********************************************************************/
	public synchronized static File copyFile(String fromDirPath, String toDirPath, String fileName)
	{
		return ioFile(fromDirPath, toDirPath, fileName, fileName, false);
	}
	public synchronized static File copyFile(String fromDirPath, String toDirPath, String fileName, String copyFileName)
	{
		return ioFile(fromDirPath, toDirPath, fileName, copyFileName, false);
	}
	
	/* **********************************************************************
	 * IO File
	 * **********************************************************************/
	private synchronized static File ioFile(String fromDirPath, String toDirPath, String fileName, String copyFileName, boolean isMove)
	{
		int i = 0;
		File foFile	= new File(String.format("%s/%s", fromDirPath, fileName));
		File toFile	= new File(String.format("%s/%s", toDirPath, copyFileName));
		
		createDir(toDirPath);

		if(foFile.isFile()) {
			String fName = "";
			String fExt = "";
			
			while(toFile.exists()) {
				fName = toFile.getName();
				fExt = Util.getFileExt(toFile.getName());
				toFile = new File(String.format("%s/%s(%d).%s", toDirPath, fName, i++, fExt));
			}
			
			FileInputStream streamFrom = null;
			FileOutputStream streamTo = null;
			
			try {
				streamFrom	= new FileInputStream(foFile);
				streamTo	= new FileOutputStream(toFile);
				
				byte[] buffer = new byte[4096];
				int len = 0;
				
				while((len = streamFrom.read(buffer)) != -1) {
					streamTo.write(buffer, 0, len);
				}
				
				if(isMove) {
					foFile.delete();
				}
			}catch(IOException e){
				System.out.println("FileManager.java ioFile Error ");
			}finally{
				if(streamFrom != null) try{streamFrom.close();}catch(IOException e){System.out.println("FileManager.java ioFile streamFrom Error ");}
				if(streamTo != null) try{streamTo.close();}catch(IOException e){System.out.println("FileManager.java ioFile streamTo Error ");}
			}
		}
		
		return toFile;
	}
		
	
	/* **********************************************************************
	 * Return Directory list
	 * **********************************************************************/
	static ArrayList<File> dirList = null;
	
	public synchronized static ArrayList<File> getDirList(String dirPath)
	{
		dirList = new ArrayList<File>();
		makeDirList(new File(dirPath));
		return dirList;
	}
	
	/* **********************************************************************
	 * Make Directory list 
	 * **********************************************************************/
	private synchronized static void makeDirList(File dir)
	{
		if(dir.exists()) {
			dirList.add(dir);
			
			File[] files = dir.listFiles(); 
			
			if(files != null) {
				for(File file:files) {
					if(file.isDirectory()) {
						makeDirList(file);			// Recursive call
					}else{
						//dirList.add(file);
					}
				}
			}
		}
	}
	
	/* **********************************************************************
	 * make File
	 * **********************************************************************/
	public synchronized static boolean makeFile(String CFG_SYSTEMDIR , String fileWebPath, String content) throws IOException
	{
		String fileFullPath = CFG_SYSTEMDIR + fileWebPath;
		return FileManager.makeFile(fileFullPath, content);
	}
	public static boolean makeFile(String fileFullPath, String content) throws IOException
	{
		boolean flag = false;

		File file = new File(fileFullPath) ;
		OutputStreamWriter osw = null;
		BufferedWriter output = null;
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos, EgovProperties.getProperty("site.html.meta.charset"));
			output = new BufferedWriter(osw);		
			output.write(content);
            flag = true;
        }catch(Exception e){
			flag = false;
			System.out.println("FileManager.java makeFile Error ");
        }finally{
			if(output != null) try{output.close();}catch(IOException e){System.out.println("FileManager.java output output Error ");}
			if(osw != null) try{osw.close();}catch(IOException e){System.out.println("FileManager.java makeFile osw Error ");}
			if(fos != null) try{fos.close();}catch(IOException e){System.out.println("FileManager.java makeFile fos Error ");}
		}
		return flag;
	}
	/* **********************************************************************
	 * Read File
	 * **********************************************************************/
	public synchronized static String readFile(String CFG_SYSTEMDIR , String fileWebPath)
	{
		String fileFullPath = CFG_SYSTEMDIR + fileWebPath;
		return FileManager.readFile(fileFullPath);
	}
	public synchronized static String readFile(String fileFullPath) 
	{
		StringBuffer sb = null;
		File file = new File(fileFullPath);
		
		if(file.isFile())
		{
			sb = new StringBuffer();
			FileInputStream fis = null;
			InputStreamReader isr = null;
			BufferedReader br = null;
			
			try
			{
				fis = new FileInputStream(file);
				isr = new InputStreamReader(fis, EgovProperties.getProperty("site.html.meta.charset"));
				br = new BufferedReader(isr);
	
				String lineText = "";
	
				while((lineText = br.readLine()) != null)
				{
					sb.append(lineText+"\n");
				}
			}catch (UnsupportedEncodingException e) {
				System.out.println("FileManager readFile UnsupportedEncodingException");
			}catch (FileNotFoundException e){
				System.out.println("FileManager readFile FileNotFoundException");
			}catch (IOException e){
				System.out.println("FileManager readFile IOException");
			}finally{
				if(br != null) try{br.close();}catch(IOException e){}
				if(isr != null) try{isr.close();}catch(IOException e){}
				if(fis != null) try{fis.close();}catch(IOException e){}
			}
		}

		return sb != null ? sb.toString():null;

	}
}
