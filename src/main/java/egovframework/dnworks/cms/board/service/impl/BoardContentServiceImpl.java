package egovframework.dnworks.cms.board.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.board.service.BoardContentService;
import egovframework.dnworks.cms.board.service.BoardContentVo;

@Service("BoardContentService")
public class BoardContentServiceImpl extends EgovAbstractServiceImpl implements  BoardContentService
{
	@Autowired
	private BoardContentMapper mapper;

	@Override
	public int save(BoardContentVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int delete(int con_sn) {
		// TODO Auto-generated method stub
		return mapper.delete(con_sn);
	}

	@Override
	public BoardContentVo select(int con_sn) {
		// TODO Auto-generated method stub
		return mapper.select(con_sn);
	}

	@Override
	public List<BoardContentVo> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectList(map);
	}

}
