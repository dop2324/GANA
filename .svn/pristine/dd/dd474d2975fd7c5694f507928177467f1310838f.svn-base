package egovframework.transfer.mcs.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import egovframework.transfer.mcs.service.McsService;
import egovframework.transfer.mcs.service.McsVO;

@Component
public class McsComponent {

	@Autowired
	McsService mcsService;
	
	private static final String userId = "_yes";
	private static final String callback = "052120";
	
	// MMS 단건 발송
	public void sendMMS(String destNm, String destHp, String mmsMsg, String callback) throws Exception {
		String destInfo = destNm+"^"+destHp;
		
		this.sendMMS(userId, 0, "", "울산모아통합예약", destInfo, mmsMsg, callback);
	}
	public void sendMMS(String destNm, String destHp, String mmsMsg) throws Exception {
		String destInfo = destNm+"^"+destHp;
		
		this.sendMMS(userId, 0, "", "울산모아통합예약", destInfo, mmsMsg, callback);
	}
	
	public void sendMMS(String userId, int schdeuleType, String sendDate, String subject, String destInfo, String mmsMsg, String callback) throws Exception {
		McsVO insertVO = new McsVO();
		insertVO.setUserId(userId);
		insertVO.setScheduleType(schdeuleType);
		insertVO.setSubject(subject);
		insertVO.setDestInfo(destInfo);
		insertVO.setDestCount(1);
		insertVO.setMmsMsg(mmsMsg);
		insertVO.setCallback(callback);
		this.mcsService.sendMMS(insertVO);
	}
}
