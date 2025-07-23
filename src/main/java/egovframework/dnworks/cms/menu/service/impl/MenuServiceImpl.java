package egovframework.dnworks.cms.menu.service.impl;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import egovframework.com.cmm.service.EgovProperties;
import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cms.board.info.service.impl.BoardInfoMapper;
import egovframework.dnworks.cms.menu.service.MenuService;
import egovframework.dnworks.cms.menu.service.MenuVo;
import egovframework.dnworks.cms.menu.service.PermissionService;
import egovframework.dnworks.cms.menu.service.PermissionVo;
import egovframework.dnworks.cms.menu.service.SiteVo;

@Service("MenuService")
public class MenuServiceImpl extends EgovAbstractServiceImpl  implements MenuService 
{
	@Autowired
    private MenuMapper mapper;
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private SiteMapper siteMapper;
	
	@Autowired
	private PageMapper pageMapper;
	
	@Autowired
	private BoardInfoMapper boardInfoMapper;

	@Override
	public int save(MenuVo vo, int cmd, int lowApply) {
		// TODO Auto-generated method stub
		int rtnVal = mapper.save(vo);
		
		if(rtnVal != -1) {
			
			if(cmd == Code.Prm.PrmIns.getCode()) {
				this.insertOption(vo, lowApply);
			}else if(cmd == Code.Prm.PrmUpd.getCode()) {
				this.updateOption(vo, lowApply);
			}
		}
		
		return rtnVal;
	}
	
	private void updateOption(MenuVo vo, int lowApply)
	{
		//메뉴 비활성 시 하위메뉴 같이 처리
		if(vo.getMnu_sttus() == 0) {
			this.sttusSubMenu(vo.getMnu_code());
		}
		
		//부서 하위적용
		if(lowApply == 1) {
			mapper.updateSubDeptInfo(vo);
		}
	}
	
	private void insertOption(MenuVo vo, int lowApply)
	{
		//기본권한 등록
		PermissionVo permVo = new PermissionVo();
		permVo.setMnu_code(vo.getMnu_code());
		permVo.setPrm_alwAdm(1);
		permVo.setPrm_alwDel(1);
		permVo.setPrm_alwUpd(1);
		permVo.setPrm_alwRpl(1);
		permVo.setPrm_alwIns(1);
		permVo.setPrm_alwSel(1);
		permVo.setPrm_alwLst(1);
		permVo.setPrm_dnyAdm(0);
		permVo.setPrm_dnyDel(0);
		permVo.setPrm_dnyUpd(0);
		permVo.setPrm_dnyRpl(0);
		permVo.setPrm_dnyIns(0);
		permVo.setPrm_dnySel(0);
		permVo.setPrm_dnyLst(0);
		permVo.setPrm_mdfcnID(vo.getMnu_mdfcnID());
		
		//전체관리자 그룹
		permVo.setPrm_id(EgovProperties.getProperty("Site.grp.AdminID"));
		permissionService.save(permVo, 0);
		
		//전체관리자 id
		permVo.setPrm_id(EgovProperties.getProperty("Site.mem.AdminID"));
		permissionService.save(permVo, 0);

		//기본 권한(사용자)
		permVo.setPrm_alwAdm(0);
		permVo.setPrm_alwDel(0);
		permVo.setPrm_alwUpd(0);
		permVo.setPrm_alwRpl(0);
		permVo.setPrm_alwIns(0);
		permVo.setPrm_alwSel(1);
		permVo.setPrm_alwLst(1);
		
		Map<String, Object> map = new HashMap<>();
		map.put("site_code", vo.getSite_code());
		SiteVo siteVo = siteMapper.select(map);
		
		if(siteVo != null) {
			//관리자 사이트 확인 (사용자 일때만 실행)
			if(siteVo.getSite_mngYn() == 0 && siteVo.getSite_useYn() == 1) {
				// 회원
				permVo.setPrm_id("GRP_004");
				permissionService.save(permVo, 0);
			}
		}		
	}

	@Override
	public int update(MenuVo vo) {
		// TODO Auto-generated method stub
		return mapper.update(vo);
	}

	@Override
	public int sttusSubMenu(String menu_code) {
		// TODO Auto-generated method stub
		return mapper.sttusSubMenu(menu_code);
	}

	@Override
	public int updateOrder(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.updateOrder(map);
	}

	@Override
	public int delete(String mnu_code) {
		// TODO Auto-generated method stub
		int rtnVal = 0;
		
		//하위메뉴 확인
		if(mapper.selectSubMenuCnt(mnu_code) > 0) {
			return -2;
		}
		
		//참조페이지 확인
		if(pageMapper.selectRefCnt(mnu_code) > 0) {
			return -3;
		}else {
			pageMapper.deleteMenu(mnu_code);
		}
		
		//게시판 확인
		if(boardInfoMapper.select(mnu_code) != null) {
			return -4;
		}
		if(boardInfoMapper.select(mnu_code) != null) {
			if(boardInfoMapper.delete(mnu_code) == 0) {
				return -5;
			}
		}
		
		//순서조정
		if(mapper.deleteOrder(mnu_code) == -1) {
			return -6;
		}
		
		//메뉴 삭제
		rtnVal = mapper.delete(mnu_code);
		if(rtnVal == 0) {
			return -7;
		}
		
		return rtnVal;
	}
	
	@Override
	public int deleteOrder(String menu_code) {
		// TODO Auto-generated method stub
		return mapper.deleteOrder(menu_code);
	}
	
	@Override
	public int deleteRtnValue(String menu_code) {
		// TODO Auto-generated method stub
		return mapper.deleteRtnValue(menu_code);
	}
	
	@Override
	public String selectMaxMnuCode(String menu_code) {
		// TODO Auto-generated method stub
		return mapper.selectMaxMnuCode(menu_code);
	}

	@Override
	public MenuVo select(String menu_code) {
		// TODO Auto-generated method stub
		return mapper.select(menu_code);
	}
	
	@Override
	public LinkedHashMap<String, MenuVo> selectTree(Map<String, Object> map) {
		// TODO Auto-generated method stub
		LinkedHashMap<String, MenuVo> menus = new LinkedHashMap<>();
		List<MenuVo> mvo = mapper.selectTree(map);
		
		for(MenuVo m:mvo){
			menus.put(m.getMnu_code(), m);
		}	
		return menus;
	}
	
	/*
	@Override
	public List<MenuVo> selectTree(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectTree(map);
	}
	*/

	@Override
	public List<MenuVo> selectPath(Map<String, Object> map) {
		// TODO Auto-generated method stub
		
		return mapper.selectPath(map);
	}

	@Override
	public List<String> selectPrmAdminList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectPrmAdminList(map);
	}

	@Override
	public List<String> selectPrmUserList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return mapper.selectPrmUserList(map);
	}

	@Override
	public int updateMdfcnDt(String menu_code) {
		// TODO Auto-generated method stub
		return mapper.updateMdfcnDt(menu_code);
	}

	@Override
	public List<MenuVo> selectMdfcnDtList(String site_code) {
		// TODO Auto-generated method stub
		return mapper.selectMdfcnDtList(site_code);
	}
}
