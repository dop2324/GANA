package egovframework.dnworks.func.cal.service;

import java.util.List;
import java.util.Map;

public interface CalInfoService {
	 /**
     * 기관별 정산 데이터를 Toss API에서 조회하여 DB에 저장
     * @param orgUnqId 기관 고유 ID
     */
    void tossSettlement(String orgUnqId) throws Exception;
    
    void tossSettlementByDate(String orgUnqId, String startDate, String endDate) throws Exception;
    
    /**
     * 모든 기관 대상 정산 스케줄러 실행
     */
    void scheduleTossSettlementAll() throws Exception;
    
    /**
     * 정산 목록 개수 조회
     */
    int selectListCnt(Map<String, Object> param) throws Exception;

    /**
     * 정산 목록 조회
     */
    List<CalInfoVO> selectList(Map<String, Object> param) throws Exception;
}
