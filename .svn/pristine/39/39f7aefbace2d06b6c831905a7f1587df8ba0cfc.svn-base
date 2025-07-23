package egovframework.dnworks.func.cal.service.impl;

import java.util.List;
import java.util.Map;

import egovframework.dnworks.func.cal.service.CalInfoVO;
import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

@Mapper("CalInfoMapper")
public interface CalInfoMapper {

    /**
     * 정산 내역 저장
     */
    void insertCalInfo(CalInfoVO vo);

    /**
     * 중복 여부 확인용: 특정 기관 + 날짜 + 구분 으로 정산 내역 조회
     */
    CalInfoVO selectCalInfo(CalInfoVO vo);

    /**
     * 특정 기관/날짜/구분 기준으로 정산 내역 삭제 (재수집 시 사용)
     * @param param orgUnqId, calDt, calSeCd 포함 map
     */
    void deleteCalInfoByDate(Map<String, Object> param);

    /**
     * 정산 목록 개수 조회 (페이징용)
     */
    int selectListCnt(Map<String, Object> param);

    /**
     * 정산 목록 조회 (페이징용)
     */
    List<CalInfoVO> selectList(Map<String, Object> param);
}
