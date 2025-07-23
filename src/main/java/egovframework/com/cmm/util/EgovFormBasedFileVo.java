package egovframework.com.cmm.util;

import java.io.Serializable;

/**
 * @Class Name  : EgovFormBasedFileVo.java
 * @Description : Form-based File Upload VO
 * @Modification Information
 * 
 *     수정일         수정자                   수정내용
 *     -------          --------        ---------------------------
 *   2009.08.26       한성곤                  최초 생성
 *
 * @author 공통컴포넌트 개발팀 한성곤
 * @since 2009.08.26
 * @version 1.0
 * @see 
 * 
 *  Copyright (C) 2008 by MOPAS  All right reserved.
 */
@SuppressWarnings("serial")
public class EgovFormBasedFileVo implements Serializable {
    /** 파일명 */
    private String fileName = "";
    /** ContextType */
    private String contentType = "";
    /** 하위 디렉토리 지정 */
    private String serverSubPath = "";
    /** 물리적 파일명 */
    private String physicalName = "";
    /** 파일 사이즈 */
    private long size = 0L;
    
    /** 파일 순서 **/
    private int inputIndex = 0;

	/** 추가 인풋타입 이름 */
    private String inputName = "";
    
    /** 추가 파일설명 **/
    private String fileDescription = "";
    
    private int fileType;

	/** 추가 대표설정 */
    private int firstYn = 0;
    
    
    /** 워터마크 **/
    private int watermark = 0;
    
    /* File extension */
    private String fileExtension = "";
    
    /** 파일 일련번호 */
	private int fileSn;
    
	public int getFileSn() {
		return fileSn;
	}
	public void setFileSn(int fileSn) {
		this.fileSn = fileSn;
	}
	/**
     * fileName attribute를 리턴한다.
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }
    /**
     * fileName attribute 값을 설정한다.
     * @param fileName the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    
    public void setFileExtension(String fileExtension) {
		this.fileExtension = fileExtension;
	}
    public String getFileExtension(){
    	if( this.fileName != null ){
    		 this.fileExtension = this.fileName.toLowerCase().substring(this.fileName.lastIndexOf(".")+1);
    	}
    	return this.fileExtension;
    }
    
    /**
     * contentType attribute를 리턴한다.
     * @return the contentType
     */
    public String getContentType() {
        return contentType;
    }
    /**
     * contentType attribute 값을 설정한다.
     * @param contentType the contentType to set
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
    /**
     * serverSubPath attribute를 리턴한다.
     * @return the serverSubPath
     */
    public String getServerSubPath() {
        return serverSubPath;
    }
    /**
     * serverSubPath attribute 값을 설정한다.
     * @param serverSubPath the serverSubPath to set
     */
    public void setServerSubPath(String serverSubPath) {
        this.serverSubPath = serverSubPath;
    }
    /**
     * physicalName attribute를 리턴한다.
     * @return the physicalName
     */
    public String getPhysicalName() {
        return physicalName;
    }
    /**
     * physicalName attribute 값을 설정한다.
     * @param physicalName the physicalName to set
     */
    public void setPhysicalName(String physicalName) {
        this.physicalName = physicalName;
    }
    /**
     * size attribute를 리턴한다.
     * @return the size
     */
    public long getSize() {
        return size;
    }
    /**
     * size attribute 값을 설정한다.
     * @param size the size to set
     */
    public void setSize(long size) {
        this.size = size;
    }
    
    
    
    
    public int getInputIndex() {
		return inputIndex;
	}
	public void setInputIndex(int inputIndex) {
		this.inputIndex = inputIndex;
	}
	public String getInputName() {
		return inputName;
	}
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
	public String getFileDescription() {
		return fileDescription;
	}
	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}
	public int getFileType() {
		return fileType;
	}
	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	public int getFirstYn() {
		return firstYn;
	}
	public void setFirstYn(int firstYn) {
		this.firstYn = firstYn;
	}
	public int getWatermark() {
		return watermark;
	}
	public void setWatermark(int watermark) {
		this.watermark = watermark;
	}
}
