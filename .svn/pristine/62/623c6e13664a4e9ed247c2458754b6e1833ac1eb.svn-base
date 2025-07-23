package egovframework.dnworks.cms.menu.service.impl;

import java.util.List;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.menu.service.PageVo;

@Mapper("PageMapper")
public interface PageMapper 
{
	public int save(PageVo vo);
	public int insert(PageVo vo);
	public int update(PageVo vo);
	
	public int disableSttus(PageVo vo);
	public int enableSttus(PageVo vo);
	
	public int delete(int page_sn);
	public int deleteMenu(String mnu_code);
	
	public PageVo select(int page_sn);
	public PageVo selectMenuCode(String mnu_code);
	
	public List<PageVo> selectList(String mnu_code);
	
	public int selectRefCnt(String mnu_code);
}
