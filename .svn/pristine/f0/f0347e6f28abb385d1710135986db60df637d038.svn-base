package egovframework.dnworks.func.cmm.listener;

import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import egovframework.dnworks.func.cmm.utl.SessionKey;

public class MoaSessionListener implements HttpSessionListener {

	public static final ConcurrentHashMap<String, HttpSession> sessionMap = new ConcurrentHashMap<>();
	
    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        HttpSession session = se.getSession();
        String loginId = (String) session.getAttribute(SessionKey.MOA_MEMB_ID.getKey());

        if (loginId != null) {
            sessionMap.remove(loginId);
        }
    }
}
