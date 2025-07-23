package egovframework.dnworks.cms.board.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cmm.cipher.aes.AESUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.board.service.BoardFieldValueService;
import egovframework.dnworks.cms.board.service.BoardFieldValueVo;

@Service("BoardFieldValueService")
public class BoardFieldValueServiceImpl extends EgovAbstractServiceImpl implements BoardFieldValueService
{
	@Autowired
	private BoardFieldValueMapper mapper;

	@Override
	public int save(BoardFieldValueVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}

	@Override
	public int delete(int bod_sn) {
		// TODO Auto-generated method stub
		return mapper.delete(bod_sn);
	}

	@Override
	public List<BoardFieldValueVo> selectList(int bod_sn) {
		// TODO Auto-generated method stub
		List<BoardFieldValueVo> rtnList = null;
		List<BoardFieldValueVo> list = mapper.selectList(bod_sn);
		
		if(list != null && list.size() > 0) {
			rtnList = new ArrayList<>();
			for(BoardFieldValueVo v:list) {
				if(v.getBfd_encYn() == 1) {
					if(!Util.nvl(v.getBfd_val()).equals("")) v.setBfd_val(AESUtil.decrypt(v.getBfd_val()));
				}
				rtnList.add(v);
			}
		}
		return rtnList;
	}

}
