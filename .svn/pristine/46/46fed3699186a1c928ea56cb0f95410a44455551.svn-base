package egovframework.dnworks.cms.link.service.impl;

import java.util.List;
import java.util.Map;

import org.egovframe.rte.psl.dataaccess.mapper.Mapper;

import egovframework.dnworks.cms.link.service.LinkGroupVo;

@Mapper("LinkGroupMapper")
public interface LinkGroupMapper 
{
	public int save(LinkGroupVo vo);
	public int delete(int lgp_sn);
	
	public LinkGroupVo select(int lgp_sn);
	public List<LinkGroupVo> selectList(Map<String, Object> map);
}
