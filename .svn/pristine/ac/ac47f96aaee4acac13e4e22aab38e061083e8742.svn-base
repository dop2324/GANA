package egovframework.transfer.task.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.MailTemplateService;
import egovframework.dnworks.func.memb.service.MembInfoVO;
import egovframework.dnworks.func.memb.service.MembService;
import egovframework.transfer.task.service.MembLngtmService;

@Service("MembLngtmService")
public class MembLngtmServiceImpl extends EgovAbstractServiceImpl implements MembLngtmService {

	@Autowired
	MembService membService;
	
	@Resource(name = "mailSender")
	private JavaMailSender mailSender; 
	
	@Autowired
	private MailTemplateService mailTemplateService;
	
	private static final String TAG = "MembSignUpController";
	private static final Logger LOGGER = LoggerFactory.getLogger("ServiceInfoLogger");
		
	@Override
	public void lngtmSenderMail() throws Exception {
		long indexActionStartTime = System.currentTimeMillis();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		// 1차안내메일 대상자 목록 추출
		map.put("srchLngtm", "FRST");
		List<MembInfoVO> sndList1 = this.membService.selectLngtmList(map);
		String sndIds1 = "";
		for(MembInfoVO vo : sndList1) {
			// 메일발송
			this.sendEmail(vo.getEmlAddr(), vo.getMembNm(), vo.getMembId(), "FRST");
			
			map.put("membUnqId", vo.getMembUnqId());
			this.membService.updateLngtmMemb(map);
			
			sndIds1 += vo.getMembId()+",";
		}
		
		LOGGER.info("["+TAG+"] 1차 메일 대상자 수[{}] , 내역[{}]", sndList1.size(), sndIds1);
		
		// 2차안내메일 대상자 목록 추출
		map.put("srchLgntm", "SCND");
		List<MembInfoVO> sndList2 = this.membService.selectLngtmList(map);
		String sndIds2 = "";
		for(MembInfoVO vo : sndList2) {
			// 메일발송
			this.sendEmail(vo.getEmlAddr(), vo.getMembNm(), vo.getMembId(), "SCND");
			
			map.put("membUnqId", vo.getMembUnqId());
			this.membService.updateLngtmMemb(map);
			
			sndIds2 += vo.getMembId()+",";
		}
		LOGGER.info("["+TAG+"] 2차 메일 대상자 수[{}] , 내역[{}]", sndList2.size(), sndIds2);
		
		long indexActionEndTime = System.currentTimeMillis();
		LOGGER.info("["+TAG+"] 장기미사용자 처리! ACTION 처리시간 : " + ((indexActionEndTime - indexActionStartTime) / 1000.0D));
	}
	
	private void sendEmail(String emailTo, String userName, String userId, String lngtm) {
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
			
			// 수신자
			helper.setTo(emailTo);
			helper.setSubject("울산모아회원 장기미사용자 안내메일");
			
			// 메일내용
			Map<String, Object> model = new HashMap<>();
			model.put("userName", userName);
			model.put("userId", userId);
			
			String tempNm = "lngtm_"+lngtm+".ftl";
			String htmlContent = mailTemplateService.parseTemplate(tempNm, model);
			helper.setText(htmlContent, true);
			
			// 메일발송
			//this.mailSender.send(message);
			
		} catch(Exception e) {
			e.printStackTrace();
			LOGGER.info("["+TAG+"][ERROR] " + e.toString());
		}
	}

}
