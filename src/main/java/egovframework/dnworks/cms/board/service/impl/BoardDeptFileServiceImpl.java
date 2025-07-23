package egovframework.dnworks.cms.board.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.board.service.BoardDeptFileService;
import egovframework.dnworks.cms.board.service.BoardDeptFileVo;

@Service("BoardDeptFileService")
public class BoardDeptFileServiceImpl extends EgovAbstractServiceImpl implements BoardDeptFileService
{
	@Autowired
	private BoardDeptFileMapper mapper;

	@Override
	public int save(BoardDeptFileVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int delete(int file_sn) {
		// TODO Auto-generated method stub
		return mapper.delete(file_sn);
	}

	@Override
	public BoardDeptFileVo select(int file_sn) {
		// TODO Auto-generated method stub
		return mapper.select(file_sn);
	}

	@Override
	public List<BoardDeptFileVo> selectList(int dept_sn) {
		// TODO Auto-generated method stub
		return mapper.selectList(dept_sn);
	}

}
