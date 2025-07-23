package egovframework.dnworks.cms.board.service.impl;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.board.service.BoardVoteVo;

@Mapper("BoardVoteMapper")
public interface BoardVoteMapper 
{
	public int save(BoardVoteVo vo);
	public Integer selectVoteType(BoardVoteVo vo);
	public int checkBoardVote(BoardVoteVo vo);
	
	public BoardVoteVo selectSumVoteCnt(int con_sn);
}
