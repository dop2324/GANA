package egovframework.dnworks.cms.board.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.board.service.BoardVodService;
import egovframework.dnworks.cms.board.service.BoardVodVo;

@Service("BoardVodService")
public class BoardVodServiceImpl extends EgovAbstractServiceImpl implements BoardVodService{
	
	@Autowired
	private BoardVodMapper mapper;

	@Override
	public int save(BoardVodVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int delete(int vod_sn) {
		// TODO Auto-generated method stub
		return mapper.delete(vod_sn);
	}

	@Override
	public List<BoardVodVo> selectList(int bod_sn) {
		// TODO Auto-generated method stub
		return mapper.selectList(bod_sn);
	}

}
