package egovframework.transfer.task.service;

/**
 * 장기미사용 이용자 계정 잠금 스케줄러
 * */
public interface MembLngtmService {

	/**
	 * 장기미사용이용자 메일발송
	 * 1차메일 대상자 : 마지막로그인 365일이 경과한 사용자
	 * 2차메일 대상자 : 마지막로그인 730일이 경과하고 1차메일 발송이력이 있는 사용자
	 * */
	public void lngtmSenderMail() throws Exception;
	
}
