package egovframework.dnworks.cms.menu.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cmm.cipher.aes.AESUtil;
import egovframework.dnworks.cmm.util.Util;
import egovframework.dnworks.cms.menu.service.MenuAlarmLogService;
import egovframework.dnworks.cms.menu.service.MenuAlarmLogVo;

@Service("MenuAlarmLogService")
public class MenuAlarmLogServiceImpl extends EgovAbstractServiceImpl implements MenuAlarmLogService
{
	@Autowired
	private MenuAlarmLogMapper mapper;
	
	private MenuAlarmLogVo encrypt(MenuAlarmLogVo vo)
	{
		if(!Util.nvl(vo.getMnu_alarmTrgt()).equals("")) vo.setMnu_alarmTrgt(AESUtil.encrypt(vo.getMnu_alarmTrgt()));
		return vo;
	}
	
	private MenuAlarmLogVo decrypt(MenuAlarmLogVo vo)
	{
		if(!Util.nvl(vo.getMnu_alarmTrgt()).equals("")) vo.setMnu_alarmTrgt(AESUtil.decrypt(vo.getMnu_alarmTrgt()));
		return vo;
	}
	
	@Override
	public int insert(MenuAlarmLogVo vo) {
		// TODO Auto-generated method stub
		vo = this.encrypt(vo);
		return mapper.insert(vo);
	}

	@Override
	public int selectListCnt(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectListCnt(map);
	}

	@Override
	public List<MenuAlarmLogVo> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		List<MenuAlarmLogVo> returnList = null;
		List<MenuAlarmLogVo> list = mapper.selectList(map);
		
		if(list != null && list.size() > 0) {
			returnList = new ArrayList<>();
			for(MenuAlarmLogVo v:list) {
				v = this.decrypt(v);
				returnList.add(v);
			}
		}
		return returnList;
	}
}
