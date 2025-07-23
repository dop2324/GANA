package egovframework.dnworks.func.memb.service;

import java.util.List;
import java.util.Map;

public interface MembLogService {

	void insert(MembLogVO insertVO) throws Exception;

	int selectListCnt(Map<String, Object> map) throws Exception;

	List<MembInfoVO> selectList(Map<String, Object> map) throws Exception;

	// 오래된 로그데이터 정기삭제
	void removeLogData() throws Exception;
}
