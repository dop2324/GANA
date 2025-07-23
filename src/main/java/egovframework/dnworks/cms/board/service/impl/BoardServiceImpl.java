package egovframework.dnworks.cms.board.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cmm.cipher.aes.AESUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.info.service.BoardShareService;
import egovframework.dnworks.cms.board.info.service.BoardShareVo;
import egovframework.dnworks.cms.board.service.BoardContentService;
import egovframework.dnworks.cms.board.service.BoardContentVo;
import egovframework.dnworks.cms.board.service.BoardFieldValueService;
import egovframework.dnworks.cms.board.service.BoardFieldValueVo;
import egovframework.dnworks.cms.board.service.BoardFileService;
import egovframework.dnworks.cms.board.service.BoardFileVo;
import egovframework.dnworks.cms.board.service.BoardService;
import egovframework.dnworks.cms.board.service.BoardShareMemberService;
import egovframework.dnworks.cms.board.service.BoardShareMemberVo;
import egovframework.dnworks.cms.board.service.BoardVo;
import egovframework.dnworks.cms.board.service.BoardVodService;
import egovframework.dnworks.cms.board.service.BoardVodVo;

@Service("BoardService")
public class BoardServiceImpl extends EgovAbstractServiceImpl implements BoardService
{
	
	@Autowired
	private BoardMapper mapper;
	
	@Autowired
	private BoardVodService boardVodService;
	
	@Autowired
	private BoardFieldValueService boardFieldValueService;
	
	@Autowired
	private BoardShareService boardShareService;
	
	@Autowired
	private BoardShareMemberService boardShareMemberService;
	
	@Autowired
	private BoardFileService boardFileService;
	
	@Autowired
	private BoardContentService boardContentService;
	
	private BoardVo encrypt(BoardVo vo)
	{
		if(!Util.nvl(vo.getBoardContentVo().getCon_pw()).equals("")) 	vo.getBoardContentVo().setCon_pw(	AESUtil.encrypt(vo.getBoardContentVo().getCon_pw()));
		if(!Util.nvl(vo.getBoardContentVo().getCon_eml()).equals("")) 	vo.getBoardContentVo().setCon_eml(	AESUtil.encrypt(vo.getBoardContentVo().getCon_eml()));
		if(!Util.nvl(vo.getBoardContentVo().getCon_telno()).equals("")) vo.getBoardContentVo().setCon_telno(AESUtil.encrypt(vo.getBoardContentVo().getCon_telno()));
		if(!Util.nvl(vo.getBoardContentVo().getCon_zip()).equals("")) 	vo.getBoardContentVo().setCon_zip(AESUtil.encrypt(vo.getBoardContentVo().getCon_zip()));
		if(!Util.nvl(vo.getBoardContentVo().getCon_addr()).equals("")) 	vo.getBoardContentVo().setCon_addr(AESUtil.encrypt(vo.getBoardContentVo().getCon_addr()));
		return vo;
	}
	
	private BoardVo decrypt(BoardVo vo)
	{
		//con_pw 비밀번호 복호화 안함
		if(!Util.nvl(vo.getBoardContentVo().getCon_eml()).equals("")) 	vo.getBoardContentVo().setCon_eml(	AESUtil.decrypt(vo.getBoardContentVo().getCon_eml()));
		if(!Util.nvl(vo.getBoardContentVo().getCon_telno()).equals("")) vo.getBoardContentVo().setCon_telno(AESUtil.decrypt(vo.getBoardContentVo().getCon_telno()));
		if(!Util.nvl(vo.getBoardContentVo().getCon_zip()).equals("")) 	vo.getBoardContentVo().setCon_zip(AESUtil.decrypt(vo.getBoardContentVo().getCon_zip()));
		if(!Util.nvl(vo.getBoardContentVo().getCon_addr()).equals("")) 	vo.getBoardContentVo().setCon_addr(AESUtil.decrypt(vo.getBoardContentVo().getCon_addr()));
		return vo;
	}
	
	@Override
	public Integer save(BoardVo vo) throws DataAccessException {
		// TODO Auto-generated method stub
		vo = this.encrypt(vo);
		

		Map<String, Object> map = new HashMap<>();
		map.put("cmd"			, vo.getCmd());
		map.put("mnu_code"		, vo.getMnu_code());
		map.put("bod_sn"		, vo.getBod_sn());
		map.put("bod_ttl"		, vo.getBod_ttl());
		map.put("bod_readCnt"	, vo.getBod_readCnt());
		map.put("bod_startDate"	, vo.getBod_startDate());
		map.put("bod_endDate"	, vo.getBod_endDate());
		map.put("bod_lock"		, vo.getBod_lock());
		map.put("bod_notice"	, vo.getBod_notice());
		map.put("bod_sttus"		, vo.getBod_sttus());
		map.put("bgp_sn"		, vo.getBgp_sn());
		map.put("dept_id"		, vo.getDept_id());
		map.put("dept_nm"		, vo.getDept_nm());
		map.put("bod_pushGroup"	, vo.getBod_pushGroup());
		map.put("bod_regIP"		, vo.getBod_regIP());
		map.put("bod_regID"		, vo.getBod_regID());
		
		map.put("con_id"		, vo.getBoardContentVo().getCon_id());
		map.put("con_nm"		, vo.getBoardContentVo().getCon_nm());
		map.put("con_pw"		, vo.getBoardContentVo().getCon_pw());
		map.put("con_eml"		, vo.getBoardContentVo().getCon_eml());
		map.put("con_telno"		, vo.getBoardContentVo().getCon_telno());
		map.put("con_regip"		, vo.getBoardContentVo().getCon_regip());
		map.put("con_cn"		, vo.getBoardContentVo().getCon_cn());
		map.put("con_addCn"		, vo.getBoardContentVo().getCon_addCn());
		map.put("con_point"		, vo.getBoardContentVo().getCon_point());
		map.put("con_html"		, vo.getBoardContentVo().getCon_html());
		map.put("con_zip"		, vo.getBoardContentVo().getCon_zip());
		map.put("con_addr"		, vo.getBoardContentVo().getCon_addr());
		map.put("con_regDI"		, vo.getBoardContentVo().getCon_regDI());
		map.put("con_linkUrl"	, vo.getBoardContentVo().getCon_linkUrl());
		map.put("con_regDt"		, vo.getBoardContentVo().getCon_regDt());
		map.put("rtnBodSn"		, 0);
		
		int rtnVal = Util.nvl(mapper.save(map), 0);
		int rtnBodSn = Util.nvl(map.get("rtnBodSn"), -1);

		if(rtnBodSn != -1)
		{
			vo.setBod_sn(rtnBodSn);
			
			//file
			if(vo.getBoardFileList() != null && vo.getBoardFileList().size() > 0) {
				for(BoardFileVo file:vo.getBoardFileList()) {
					file.setBod_sn(vo.getBod_sn());
					boardFileService.save(file);
				}
			}
			
			// 사용자 필드입력
			if(vo.getBoardFieldValueList() != null) {
				for(BoardFieldValueVo bfv:vo.getBoardFieldValueList()) {
					bfv.setBod_sn(vo.getBod_sn());
					boardFieldValueService.save(bfv);
				}				
			}
						
			//vod
			if(vo.getBoardVodList() != null) {
				if(vo.getBoardVodList().size() > 0) {
					for(BoardVodVo vod:vo.getBoardVodList()) {
						vod.setBod_sn(vo.getBod_sn());
						int r = boardVodService.save(vod);
					}
				}
			}
			
			// 공유 게시물 입력(일괄삭제/일괄입력)
			List<BoardShareVo> boardShareVoList =  boardShareService.selectList(vo.getMnu_code());
			if(boardShareVoList != null && boardShareVoList.size() > 0) {
				boardShareMemberService.delete(vo.getBod_sn());
				if(vo.getBoardShareMemberList() != null) {
					for(BoardShareMemberVo shareVo:vo.getBoardShareMemberList()) {
						shareVo.setBod_sn(vo.getBod_sn());
						boardShareMemberService.insert(shareVo);
					}
				}
			}
			
			//comment 게시물 이동 시 사용
			if(vo.getBoardContentList() != null) {
				if(vo.getBoardContentList().size() > 0) {
					for(BoardContentVo comm:vo.getBoardContentList()) {
						comm.setBod_sn(vo.getBod_sn());
						boardContentService.save(comm);
					}
				}
			}
						
		}
		return rtnBodSn;
	}

	@Override
	public int updateSttus(int bod_sn) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.updateSttus(bod_sn);
	}

	@Override
	public int deleteSttus(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.deleteSttus(map);
	}

	@Override
	public Integer delete(int bod_sn) throws DataAccessException {
		// TODO Auto-generated method stub
		Integer rtnVal = 0;
		try {
			rtnVal = mapper.delete(bod_sn);
			rtnVal = 1;
		}catch(DataAccessException e) {
			rtnVal = 0;
		}

		return rtnVal;
	}

	@Override
	public int updateReadCnt(int bod_sn) {
		// TODO Auto-generated method stub
		return mapper.updateReadCnt(bod_sn);
	}

	@Override
	public int updateCommentCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.updateCommentCnt(map);
	}

	@Override
	public Map<String, Object> select(int bod_sn) throws DataAccessException {
		// TODO Auto-generated method stub
		Map<String, Object> map = mapper.select(bod_sn);
		if(map != null)
		{
			map.put("con_cn"	, Util.nvl(map.get("con_cn")));
			//if(!Util.nvl(map.get("con_pw")).equals("")) map.put("con_pw"		, AESUtil.decrypt(Util.nvl(map.get("con_pw"))));
			
			if(!Util.nvl(map.get("con_eml")).equals("")) 	map.put("con_eml"	, AESUtil.decrypt(Util.nvl(map.get("con_eml"))));
			if(!Util.nvl(map.get("con_telno")).equals(""))  map.put("con_telno"	, AESUtil.decrypt(Util.nvl(map.get("con_telno"))));
			if(!Util.nvl(map.get("con_zip")).equals(""))  	map.put("con_zip"	, AESUtil.decrypt(Util.nvl(map.get("con_zip"))));
			if(!Util.nvl(map.get("con_addr")).equals("")) 	map.put("con_addr"	, AESUtil.decrypt(Util.nvl(map.get("con_addr"))));
			
			map.put("boardFileList"			, boardFileService.selectList(bod_sn));
			map.put("boardVodList"			, boardVodService.selectList(bod_sn));
			map.put("boardFieldValueList"	, boardFieldValueService.selectList(bod_sn));
			map.put("boardShareMemberList"	, boardShareMemberService.selectList(bod_sn));
		}
		return map;
	}

	@Override
	public Integer selectListCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectListCnt(map);
	}

	@Override
	public List<Map<String, Object>> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectList(map);
	}

	@Override
	public Map<String, Object> selectPreNxt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectPreNxt(map);
	}
}
