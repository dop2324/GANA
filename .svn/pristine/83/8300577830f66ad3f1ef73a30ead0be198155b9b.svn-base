package egovframework.dnworks.cmm.handler;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;


import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;

import egovframework.dnworks.cmm.util.IPUtil;
import egovframework.dnworks.cmm.util.StringUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.LogErrorService;
import egovframework.dnworks.cms.menu.service.LogErrorVo;

@ControllerAdvice
public class GlobalExceptionHandler 
{
	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
	@Autowired
	private LogErrorService logErrorService;

	private void exceptionLog(Exception ex, HandlerMethod handlerMethod, HttpServletRequest request)
	{
		logger.debug("/// exceptionLog ////////////////////////////////");
		logger.debug("exceptionLog", ex);
		
		Class ControllerName 	= handlerMethod.getMethod().getDeclaringClass();
		String MethodName 		= handlerMethod.getMethod().getName();
		StackTraceElement[] ste = ex.getStackTrace();
		
	    Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

		StringBuffer str = new StringBuffer();
		str.append(" QueryString : ").append( request.getAttribute("javax.servlet.forward.query_string") ).append("\n");		
		str.append(" Referer : ").append( request.getHeader("Referer") ).append("\n");
		
	    int lastIndex = ste.length > 3 ? 3:(ste.length-1);
	    int count = 1;
	    for (int i = 0; i < lastIndex ; i++) 
	    {
	        String className = ste[i].getClassName();
	        String methodName = ste[i].getMethodName();
	        int lineNumber = ste[i].getLineNumber();
	        String fileName = ste[i].getFileName();

	        str.append("\n").append("[" +count++ + "]")
	            .append("className :").append(className).append("\n")
	            .append("methodName :").append(methodName).append("\n")
	            .append("fileName :").append(fileName).append("\n")
	            .append("lineNumber :").append(lineNumber).append("\n")
	            .append("message :").append(ex.getMessage()).append("\n")
	            .append("cause :").append(ex.getCause()).append("\n");
	    }	    

	    LogErrorVo vo = new LogErrorVo();
		vo.setMnu_code(			Util.nvl(request.getParameter("mnu_code"), "0"));
		vo.setErr_date(			formatter.format(date));
		vo.setErr_code(			Util.nvl(HttpStatus.INTERNAL_SERVER_ERROR.value()));
		vo.setErr_msg(			StringUtil.substringNVar( ex.getMessage() ,256));
		vo.setErr_controller( 	StringUtil.substringNVar( ControllerName.toString(),256 ));
		vo.setErr_method(		StringUtil.substringNVar( MethodName.toString(),256 ));
		vo.setErr_uri(			StringUtil.substringNVar( String.valueOf( request.getAttribute("javax.servlet.forward.request_uri") ) ,256 ));
		vo.setErr_inc(			StringUtil.substringNVar( String.valueOf(request.getAttribute("javax.servlet.include.request_uri")),256 ));
		vo.setErr_ip(			IPUtil.getXForwardedFor(request));
		vo.setErr_detail(		str.toString());
		
	    logErrorService.insert(vo);
	}
	
	@ExceptionHandler(Exception.class)
    public ModelAndView handleException(Exception ex, HandlerMethod handlerMethod, HttpServletRequest request) {
		
		logger.debug("Exception");
		
		this.exceptionLog(ex, handlerMethod, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("error/error500");
		
		return model;
    }
	
	@ExceptionHandler(RuntimeException.class)
    public ModelAndView handleRuntimeException(RuntimeException ex, HandlerMethod handlerMethod, HttpServletRequest request) {
		
		logger.debug("RuntimeException");
		this.exceptionLog(ex, handlerMethod, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("error/error500");
		
		return model;
    }
	
	@ExceptionHandler(DataAccessException.class)
    public ModelAndView handleDataAccessException(DataAccessException ex, HandlerMethod handlerMethod, HttpServletRequest request) {
		
		logger.debug("DataAccessException");
		this.exceptionLog(ex, handlerMethod, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("error/error500");
		
		return model;
    }
	
	@ExceptionHandler(SQLException.class)
    public ModelAndView handleSQLExceptionException(SQLException ex, HandlerMethod handlerMethod, HttpServletRequest request) {
		
		logger.debug("SQLException");
		this.exceptionLog(ex, handlerMethod, request);
		ModelAndView model = new ModelAndView();
		model.setViewName("error/error500");
		
		return model;
    }
}
