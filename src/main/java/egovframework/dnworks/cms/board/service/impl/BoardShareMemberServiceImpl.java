package egovframework.dnworks.cms.board.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.board.service.BoardShareMemberService;
import egovframework.dnworks.cms.board.service.BoardShareMemberVo;

@Service("BoardShareMemberService")
public class BoardShareMemberServiceImpl extends EgovAbstractServiceImpl implements BoardShareMemberService
{
	@Autowired
	private BoardShareMemberMapper mapper;

	@Override
	public int insert(BoardShareMemberVo vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int delete(int bod_sn) {
		// TODO Auto-generated method stub
		return mapper.delete(bod_sn);
	}

	@Override
	public List<BoardShareMemberVo> selectList(int bod_sn) {
		// TODO Auto-generated method stub
		return mapper.selectList(bod_sn);
	}
}
