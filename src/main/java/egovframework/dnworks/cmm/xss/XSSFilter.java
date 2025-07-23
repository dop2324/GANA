package egovframework.dnworks.cmm.xss;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import egovframework.dnworks.cmm.util.Util;


public class XSSFilter implements Filter 
{
	private List<String> excludedDirList;
	private List<String> excludedUrlList;
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		String excludedDir = filterConfig.getInitParameter("excludedDir");
		if(excludedDir != null) {
			excludedDirList = Arrays.asList(excludedDir.split(","));
		}
		
		String excludedUrls = filterConfig.getInitParameter("excludedUrls");
		if(excludedUrls != null) {
			excludedUrlList = Arrays.asList(excludedUrls.split(","));
		}
	}

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		
		String currentDir = "";
		String path = ((HttpServletRequest) request).getServletPath();
		String[] arrPath = path.split("/");

		currentDir = arrPath[1];
		currentDir = String.format("/%s", Util.replaceRegex(currentDir, "\\w*\\.\\w*", ""));
		
		boolean excludeFlag = false;
		if(excludedDirList != null) {
			if(excludedDirList.contains(currentDir)) {
				excludeFlag = true;
			}
		}
		
		if(excludedUrlList != null) {
			if(excludedUrlList.contains(path)) {
				excludeFlag = true;
			}
		}
		
		if(!excludeFlag) {
			chain.doFilter(new XSSRequestWrapper(httpServletRequest), response);
		}else {
			chain.doFilter(request, response);
		}
	}
	
	@SuppressWarnings("unused")
	private boolean isFileUploadRequest(HttpServletRequest request){
		return request.getMethod().equalsIgnoreCase("POST") && request.getContentType().startsWith("multipart/form-data");
	}


}
