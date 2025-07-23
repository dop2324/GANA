package egovframework.dnworks.cms.menu.service.impl;

import java.util.List;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cms.menu.service.PageService;
import egovframework.dnworks.cms.menu.service.PageVo;

@Service("PageService")
public class PageServiceImpl extends EgovAbstractServiceImpl  implements PageService
{
	@Autowired
	private PageMapper mapper;
	
	@Override
	public int save(PageVo vo) {
		// TODO Auto-generated method stub
		return mapper.save(vo);
	}
	
	@Override
	public int insert(PageVo vo) {
		// TODO Auto-generated method stub
		return mapper.insert(vo);
	}

	@Override
	public int update(PageVo vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int updateSttus(PageVo vo) {
		// TODO Auto-generated method stub
		int rtnVal = -1;
		if(mapper.disableSttus(vo) != -1) {
			rtnVal = mapper.enableSttus(vo);
		}
		return rtnVal;
	}
	
	@Override
	public int delete(int page_sn) {
		// TODO Auto-generated method stub
		return mapper.delete(page_sn);
	}
	
	@Override
	public int deleteMenu(String mnu_code) {
		// TODO Auto-generated method stub
		return mapper.deleteMenu(mnu_code);
	}

	@Override
	public PageVo select(int page_sn) {
		// TODO Auto-generated method stub
		return mapper.select(page_sn);
	}
	@Override
	public PageVo selectMenuCode(String mnu_code) {
		// TODO Auto-generated method stub
		return mapper.selectMenuCode(mnu_code);
	}

	@Override
	public List<PageVo> selectList(String mnu_code) {
		// TODO Auto-generated method stub
		return mapper.selectList(mnu_code);
	}

	@Override
	public int selectRefCnt(String mnu_code) {
		// TODO Auto-generated method stub
		return mapper.selectRefCnt(mnu_code);
	}
}
