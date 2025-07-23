package egovframework.dnworks.cms.menu.service;

import java.util.List;

public interface PageService 
{
	public abstract int save(PageVo vo);
	public abstract int insert(PageVo vo);
	public abstract int update(PageVo vo);
	
	public abstract int updateSttus(PageVo vo);
	
	public abstract int delete(int page_sn);
	public abstract int deleteMenu(String mnu_code);
	
	public abstract PageVo select(int page_sn);
	public abstract PageVo selectMenuCode(String mnu_code);
	
	public abstract List<PageVo> selectList(String mnu_code);
	
	public abstract int selectRefCnt(String mnu_code);
}
