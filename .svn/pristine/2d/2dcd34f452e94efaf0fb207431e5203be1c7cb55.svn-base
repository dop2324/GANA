package egovframework.dnworks.cmm;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PageNavi 
{
	private int totalCount = 0;
	private int pageSize = 10;
	private int pageNo = 1;
	private int blockSize = 10;
	
	private String fstNavi		= "처음";
	private String prvNavi		= "이전";
	private String nxtNavi		= "다음";
	private String lstNavi		= "마지막";
	private String delimiter	= "";
	
	private String fstNaviCssClass	= "";
	private String prvNaviCssClass	= "";
	private String nxtNaviCssClass	= "";
	private String lstNaviCssClass	= "";
	private String pageNoCssClass	= "";
	private String numberCssClass	= "";
	
	private String linkPage = "";
	private String linkParm = "";
	
	public PageNavi(){}	
	
	public int getTotalPageCount()		{return (int)((totalCount - 1) / pageSize) + 1;}
	public int getTotalCount()			{return totalCount;}
	public int getPageSize()			{return pageSize;}
	public int getPageNo()				{return pageNo;}
	//public int getPageNo()				{return pageNo;}
	
	public String getFstNavi()			{return fstNavi;}
	public String getPrvNavi()			{return prvNavi;}
	public String getNxtNavi()			{return nxtNavi;}
	public String getLstNavi()			{return lstNavi;}
	public String getDelimiter()		{return delimiter;}

	public String getFstNaviCssClass()	{return fstNaviCssClass;}
	public String getPrvNaviCssClass()	{return prvNaviCssClass;}
	public String getNxtNaviCssClass()	{return nxtNaviCssClass;}
	public String getLstNaviCssClass()	{return lstNaviCssClass;}
	public String getPageNoCssClass()	{return pageNoCssClass;}
	public String getNumberCssClass()	{return numberCssClass;}
	
	public String getLinkPage()			{return linkPage;}
	public String getLinkParm()			{return linkParm;}
	
	
	
	public void setTotalCount(int totalCount)				{this.totalCount = totalCount;}
	public void setPageSize(int pageSize)					{this.pageSize = pageSize;}
	public void setPageNo(int pageNo)						{this.pageNo = pageNo;}
	
	public void setFstNavi(String fstNavi)					{this.fstNavi = fstNavi;}
	public void setPrvNavi(String prvNavi)					{this.prvNavi = prvNavi;}
	public void setNxtNavi(String nxtNavi)					{this.nxtNavi = nxtNavi;}
	public void setLstNavi(String lstNavi)					{this.lstNavi = lstNavi;}
	public void setDelimiter(String delimiter)				{this.delimiter = delimiter;}
	
	public void setFstNaviCssClass(String fstNaviCssClass)	{this.fstNaviCssClass = fstNaviCssClass;}
	public void setPrvNaviCssClass(String prvNaviCssClass)	{this.prvNaviCssClass = prvNaviCssClass;}
	public void setNxtNaviCssClass(String nxtNaviCssClass)	{this.nxtNaviCssClass = nxtNaviCssClass;}
	public void setLstNaviCssClass(String lstNaviCssClass)	{this.lstNaviCssClass = lstNaviCssClass;}
	public void setPageNoCssClass(String pageNoCssClass)	{this.pageNoCssClass = pageNoCssClass;}
	public void setNumberCssClass(String numberCssClass)	{this.numberCssClass = numberCssClass;}
	
	public void setLinkPage(String linkPage)				{this.linkPage = linkPage;}
	public void setLinkParm(String linkParm)				{this.linkParm = linkParm;}

	
	private String strLinkUrl	= "";
	private String strCssClass	= "";
	
	private String makeNaviLink(int pageNo, String title, String cssClass, String linkNo) throws Exception
	{
		strLinkUrl	= "";
		strCssClass = "";
		
		if(pageNo != 0)
		{
			strLinkUrl = String.format("href=\"%s?pageNo=%d&%s\"", linkPage, pageNo, linkParm);
		}
		
		if(!cssClass.equals(""))
		{
			strCssClass = String.format("class=\"%s\"", cssClass);
		}
		
		return String.format("<a %s %s title=\"%s\">%s</a>\n", this.replaceLinkUrl(strLinkUrl), strCssClass, title, linkNo);
	}
	
	private String replaceLinkUrl(String linkUrl)
	{
		linkUrl = replaceRegex(linkUrl, "&amp;", "&");
		return replaceRegex(linkUrl, "&", "&amp;");
	}
	
	public String replaceRegex(String srcString, String rgxPattern, String rplString)
	{
		Pattern pattern = Pattern.compile(rgxPattern);
		Matcher matcher = pattern.matcher(srcString);
		return matcher.replaceAll(rplString);
	}
	
	public String getPageNaviTag() throws Exception
	{
		int blockFirstPageNo = (int)((pageNo - 1) / blockSize) * blockSize + 1;

		int i = 0;
		
		String fstNaviTag = "";
		String prvNaviTag = "";
		StringBuffer pageNumTag = new StringBuffer();
		String nxtNaviTag = "";
		String lstNaviTag = "";
		
		if(blockFirstPageNo == 1)
		{
			fstNaviTag = makeNaviLink(0, "첫 페이지", fstNaviCssClass, fstNavi);
			prvNaviTag = makeNaviLink(0, "이전 10페이지", prvNaviCssClass, prvNavi);
		}
		else
		{
			fstNaviTag = makeNaviLink(1, "첫 페이지", fstNaviCssClass, fstNavi);
			prvNaviTag = makeNaviLink(blockFirstPageNo - blockSize, "이전 10페이지", prvNaviCssClass, prvNavi);			
		}
		
		for(i = blockFirstPageNo; i <= getTotalPageCount() && i < (blockFirstPageNo + blockSize); i++)
		{
			if(i != blockFirstPageNo) pageNumTag.append(String.format("%s", delimiter));
			
			if(i == pageNo)
			{
				if(pageNoCssClass.equals(""))
				{
					pageNumTag.append("<strong title=\"현재 페이지\">"+i+"</strong>");
				}
				else
				{
					pageNumTag.append(String.format("<div class=\"%s\">%d</div>\n", pageNoCssClass, i));
				}
			}
			else
			{
				pageNumTag.append(makeNaviLink(i, String.format("%d 페이지로 이동", i), numberCssClass, String.valueOf(i)));
			}
		}
		
		if(i > getTotalPageCount())
		{
			nxtNaviTag = makeNaviLink(0, "다음 10페이지", nxtNaviCssClass, nxtNavi);
			lstNaviTag = makeNaviLink(0, "끝 페이지", lstNaviCssClass, lstNavi);
		}
		else
		{
			nxtNaviTag = makeNaviLink(blockFirstPageNo + blockSize, "다음 10페이지", nxtNaviCssClass, nxtNavi);
			lstNaviTag = makeNaviLink(getTotalPageCount(), "끝 페이지", lstNaviCssClass, lstNavi);
		}
		
		return String.format("%s %s %s %s %s", fstNaviTag, prvNaviTag, pageNumTag.toString(), nxtNaviTag, lstNaviTag);
	}

	
	public String getPageNaviTag(int totalCnt, int pageSize, int pageNo, String linkUrl, String linkParam) throws Exception
	{
		this.setTotalCount(totalCnt);
		this.setPageSize(pageSize);
		this.setPageNo(pageNo);
		this.setLinkPage(linkUrl);
		this.setLinkParm(linkParam);
		this.setFstNaviCssClass("arrow first");
		this.setPrvNaviCssClass("arrow prev");
		this.setNxtNaviCssClass("arrow next");
		this.setLstNaviCssClass("arrow last"); 	
		
		return this.getPageNaviTag();
	}
	
	public String getPageNaviTag(int blockSize, int totalCnt, int pageSize, int pageNo, String linkUrl, String linkParam) throws Exception
	{
		this.blockSize = blockSize;
		this.setTotalCount(totalCnt);
		this.setPageSize(pageSize);
		this.setPageNo(pageNo);
		this.setLinkPage(linkUrl);
		this.setLinkParm(linkParam);
		this.setFstNaviCssClass("arrow first");
		this.setPrvNaviCssClass("arrow prev");
		this.setNxtNaviCssClass("arrow next");
		this.setLstNaviCssClass("arrow last"); 	
		
		return this.getPageNaviTag();
	}
}
