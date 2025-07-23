package egovframework.dnworks.func.cmm.utl;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import egovframework.com.cmm.util.EgovStringUtil;

public enum JavaScriptUtil {

	INSTANCE;
	
	public static final Logger LOGGER;
	
	static {
		LOGGER = Logger.getLogger(JavaScriptUtil.class.getName());
	}
	
	public static void flushJSAlert(HttpServletResponse response, String contents) {
		
		Writer writer = null;
		
		try {
			response.setContentType("text/html; charset=UTF-8");
			writer = response.getWriter();
			writer.write(contents);
		} catch (IOException e) {
			LOGGER.error("IOException - can not write response content");
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				LOGGER.error("IOException - can not close writer object");
			}
		} 
	}
	
	public static String createJsAlertContent(String msg, String returnPath) {
		StringBuilder content = new StringBuilder();
		content.append("<script>");
		
		if (EgovStringUtil.isNotBlank(msg))
			content.append("alert('" + msg + "');"); 
		
		if (EgovStringUtil.isNotBlank(returnPath))
			content.append(returnPath);
		
		content.append("</script>");
		return content.toString();
	}
	
	public static void flushJsAlertAndLocation(HttpServletResponse response, String msg, String returnUrl) {
		flushJSAlert(response, createJsAlertContent(msg, "location.href='"+returnUrl+"';"));
	}
	
	public static void flushJsAlertAndHistoryBack(HttpServletResponse response, String msg) {
		flushJSAlert(response, createJsAlertContent(msg, "history.back(-1);"));
	}

	public static void flushJsAlertAndPopupClose(HttpServletResponse response, String msg) {
		flushJSAlert(response, createJsAlertContent(msg, "window.close();"));
	}
}
