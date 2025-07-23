package egovframework.dnworks.cms.board.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.board.service.BoardDeptFileVo;
import egovframework.dnworks.cms.board.service.BoardDeptService;
import egovframework.dnworks.cms.board.service.BoardDeptVo;

@Service("BoardDeptService")
public class BoardDeptServiceImpl extends EgovAbstractServiceImpl implements BoardDeptService
{
	@Autowired
	private BoardDeptMapper mapper;
	
	@Autowired
	private BoardDeptFileMapper fileMapper;

	@Override
	public int save(BoardDeptVo vo) {
		// TODO Auto-generated method stub
		int rtnVal = mapper.save(vo);
		
		if(vo.getDept_sn() == -1) vo.setDept_sn(vo.getKey_sn());
		
		if(rtnVal != -1)
		{
			if(vo.getBoardDeptFileList() != null && vo.getBoardDeptFileList().size() > 0) {
				for(BoardDeptFileVo file:vo.getBoardDeptFileList()) {
					file.setDept_sn(vo.getDept_sn());
					fileMapper.save(file);
				}
			}
		}
		return rtnVal;
	}

	@Override
	public int delete(int dept_sn) {
		// TODO Auto-generated method stub
		return mapper.delete(dept_sn);
	}

	@Override
	public BoardDeptVo select(int dept_sn) {
		// TODO Auto-generated method stub
		BoardDeptVo vo = mapper.select(dept_sn);
		if(vo != null) {
			vo.setBoardDeptFileList(fileMapper.selectList(dept_sn));
		}
		return vo;
	}

	@Override
	public List<BoardDeptVo> selectList(int bod_sn) {
		// TODO Auto-generated method stub
		List<BoardDeptVo> rtnList = null;
		List<BoardDeptVo> list = mapper.selectList(bod_sn);
		if(list != null && list.size() > 0) {
			rtnList = new ArrayList<>();
			for(BoardDeptVo v:list) {
				v.setBoardDeptFileList(fileMapper.selectList(v.getDept_sn()));
				rtnList.add(v);
			}
		}
		return rtnList;
	}

}
