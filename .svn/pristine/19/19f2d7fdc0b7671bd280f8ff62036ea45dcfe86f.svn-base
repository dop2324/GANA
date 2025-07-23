package egovframework.dnworks.cms.board.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.board.service.BoardFileService;
import egovframework.dnworks.cms.board.service.BoardFileVo;

@Service("BoardFileService")
public class BoardFileServiceImpl extends EgovAbstractServiceImpl implements BoardFileService{
	
	@Autowired
	private BoardFileMapper mapper;

	@Override
	public int save(BoardFileVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int delete(int file_sn) {
		// TODO Auto-generated method stub
		return mapper.delete(file_sn);
	}

	@Override
	public BoardFileVo select(int file_sn) {
		// TODO Auto-generated method stub
		return mapper.select(file_sn);
	}

	@Override
	public List<BoardFileVo> selectList(int bod_sn) {
		// TODO Auto-generated method stub
		return mapper.selectList(bod_sn);
	}

}
