package egovframework.dnworks.cms.board.info.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.board.info.service.BoardShareService;
import egovframework.dnworks.cms.board.info.service.BoardShareVo;

@Service("BoardShareService")
public class BoardShareServiceImpl extends EgovAbstractServiceImpl implements BoardShareService
{
	@Autowired
	private BoardShareMapper mapper;

	@Override
	public int insert(BoardShareVo vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int delete(BoardShareVo vo) {
		// TODO Auto-generated method stub
		return mapper.delete(vo);
	}

	@Override
	public List<BoardShareVo> selectList(String mnu_code) {
		// TODO Auto-generated method stub
		return mapper.selectList(mnu_code);
	}

}
