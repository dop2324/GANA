package egovframework.dnworks.cms.board.info.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.board.info.service.BoardGroupService;
import egovframework.dnworks.cms.board.info.service.BoardGroupVo;

@Service("BoardGroupService")
public class BoardGroupServiceImpl extends EgovAbstractServiceImpl implements BoardGroupService
{
	@Autowired
	private BoardGroupMapper mapper;

	@Override
	public int save(BoardGroupVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int delete(int bgp_sn) {
		// TODO Auto-generated method stub
		return mapper.delete(bgp_sn);
	}

	@Override
	public BoardGroupVo select(int bgp_sn) {
		// TODO Auto-generated method stub
		return mapper.select(bgp_sn);
	}

	@Override
	public List<BoardGroupVo> selectList(String mnu_code) {
		// TODO Auto-generated method stub
		return mapper.selectList(mnu_code);
	}

}
