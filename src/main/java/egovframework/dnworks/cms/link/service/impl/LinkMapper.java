package egovframework.dnworks.cms.link.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.link.service.LinkVo;

@Mapper("LinkMapper")
public interface LinkMapper 
{
	public int save(LinkVo vo);
	public int delete(int lnk_sn);
	
	public LinkVo select(int lnk_sn);
	public int selectListCnt(Map<String, Object> map);
	public List<LinkVo> selectList(Map<String, Object> map);

	public List<LinkVo> selectDateList();
	public List<LinkVo> selectMainList(List<Integer> item);
}
