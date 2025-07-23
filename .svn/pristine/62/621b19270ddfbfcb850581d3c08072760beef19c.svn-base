package egovframework.dnworks.cms.board.info.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.board.info.service.BoardFieldService;
import egovframework.dnworks.cms.board.info.service.BoardGroupService;
import egovframework.dnworks.cms.board.info.service.BoardInfoService;
import egovframework.dnworks.cms.board.info.service.BoardInfoVo;
import egovframework.dnworks.cms.board.info.service.BoardPublicService;
import egovframework.dnworks.cms.board.info.service.BoardShareService;

@Service("BoardInfoService")
public class BoardInfoServiceImpl extends EgovAbstractServiceImpl implements BoardInfoService {
	
	@Autowired
	private BoardInfoMapper mapper;
	
	@Autowired
	private BoardGroupService boardGroupService;
	
	@Autowired
	private BoardFieldService boardFieldService;
	
	@Autowired
	private BoardPublicService boardPublicService;
	
	@Autowired
	private BoardShareService boardShareService;
	
	@Autowired
	private BoardHeadColumnMapper boardHeadColumnMapper;

	@Override
	public int save(BoardInfoVo vo) {
		// TODO Auto-generated method stub
		int rtn = mapper.save(vo);
		
		if(rtn != -1) {
			boardHeadColumnMapper.save(vo.getBoardHeadColumnVo());
		}
		return rtn;
	}

	@Override
	public int delete(String mnu_code) {
		// TODO Auto-generated method stub
		boardHeadColumnMapper.delete(mnu_code);
		
		return mapper.delete(mnu_code);
	}

	@Override
	public BoardInfoVo select(String mnu_code) {
		// TODO Auto-generated method stub
		BoardInfoVo vo = mapper.select(mnu_code);
		
		if(vo != null) 
		{
			vo.setBoardHeadColumnVo(boardHeadColumnMapper.select(mnu_code));
			vo.setBoardGroupList(boardGroupService.selectList(mnu_code));
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mnu_code"	, mnu_code);
			map.put("bfd_sttus"	, 1);
			vo.setBoardFieldList(boardFieldService.selectList(map));
			
			vo.setBoardPublicList(boardPublicService.selectList(mnu_code));
			vo.setBoardShareList(boardShareService.selectList(mnu_code));
		}
		return vo;
	}

	@Override
	public int checkBoard(String mnu_code) {
		// TODO Auto-generated method stub
		return mapper.checkBoard(mnu_code);
	}

	@Override
	public List<BoardInfoVo> selectRssList(String site_code) {
		// TODO Auto-generated method stub
		return mapper.selectRssList(site_code);
	}

}
