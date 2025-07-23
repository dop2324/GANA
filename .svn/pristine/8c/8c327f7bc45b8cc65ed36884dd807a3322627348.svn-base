package egovframework.dnworks.cms.board.service.impl;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.board.service.BoardVoteService;
import egovframework.dnworks.cms.board.service.BoardVoteVo;

@Service("BoardVoteService")
public class BoardVoteServiceImpl extends EgovAbstractServiceImpl implements BoardVoteService
{
	@Autowired
	private BoardVoteMapper mapper;

	@Override
	public int save(BoardVoteVo vo) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public Integer selectVoteType(BoardVoteVo vo) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.selectVoteType(vo);
	}

	@Override
	public int checkBoardVote(BoardVoteVo vo) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.checkBoardVote(vo);
	}

	@Override
	public BoardVoteVo selectSumVoteCnt(int con_sn) {
		// TODO Auto-generated method stub
		return mapper.selectSumVoteCnt(con_sn);
	}

}
