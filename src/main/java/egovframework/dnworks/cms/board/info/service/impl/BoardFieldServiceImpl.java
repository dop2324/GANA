package egovframework.dnworks.cms.board.info.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.board.info.service.BoardFieldService;
import egovframework.dnworks.cms.board.info.service.BoardFieldVo;

@Service("BoardFieldService")
public class BoardFieldServiceImpl extends EgovAbstractServiceImpl implements BoardFieldService 
{
	@Autowired
	private BoardFieldMapper mapper;

	@Override
	public int save(BoardFieldVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int delete(String bfd_id) {
		// TODO Auto-generated method stub
		if(mapper.afterDelete(bfd_id) == -1) return -2;
		
		return mapper.delete(bfd_id);
	}

	@Override
	public int afterDelete(String bfd_id) {
		// TODO Auto-generated method stub
		return mapper.afterDelete(bfd_id);
	}

	@Override
	public int setOrder(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.setOrder(map);
	}

	@Override
	public BoardFieldVo select(String bfd_id) {
		// TODO Auto-generated method stub
		return mapper.select(bfd_id);
	}

	@Override
	public List<BoardFieldVo> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectList(map);
	}

}
