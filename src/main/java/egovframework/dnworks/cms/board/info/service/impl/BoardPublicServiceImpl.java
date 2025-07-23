package egovframework.dnworks.cms.board.info.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.board.info.service.BoardPublicService;
import egovframework.dnworks.cms.board.info.service.BoardPublicVo;

@Service("BoardPublicService")
public class BoardPublicServiceImpl extends EgovAbstractServiceImpl implements BoardPublicService
{
	@Autowired
	private BoardPublicMapper mapper;

	@Override
	public int insert(BoardPublicVo vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int delete(BoardPublicVo vo) {
		// TODO Auto-generated method stub
		return mapper.delete(vo);
	}

	@Override
	public List<BoardPublicVo> selectList(String mnu_code) {
		// TODO Auto-generated method stub
		return mapper.selectList(mnu_code);
	}

}
