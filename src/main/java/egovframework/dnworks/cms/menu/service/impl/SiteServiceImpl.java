package egovframework.dnworks.cms.menu.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.egovframe.rte.fdl.cmmn.EgovAbstractServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import egovframework.dnworks.cmm.Code;
import egovframework.dnworks.cms.menu.service.MenuService;
import egovframework.dnworks.cms.menu.service.MenuVo;
import egovframework.dnworks.cms.menu.service.SiteService;
import egovframework.dnworks.cms.menu.service.SiteVo;

@Service(value="SiteService")
public class SiteServiceImpl extends EgovAbstractServiceImpl implements SiteService
{
	@Autowired
    private SiteMapper mapper;
	
	@Autowired
	private MenuService menuService;

	@Override
	public int save(SiteVo vo, int cmd) throws DataAccessException {
		// TODO Auto-generated method stub
		int rtnVal = mapper.save(vo);
		
		if(rtnVal != -1) {
			
			if(cmd == Code.Prm.PrmIns.getCode()) {
				//메뉴 root 입력
				MenuVo menuVo = new MenuVo();
				menuVo.setMnu_code(vo.getSite_code());
				menuVo.setSite_code(vo.getSite_code());
				menuVo.setMnu_nm(vo.getSite_nm());
				menuVo.setMnu_nmKr(vo.getSite_nm());
				menuVo.setMnu_desc(String.format("%s 사이트 메뉴", menuVo.getMnu_nm()));
				
				menuVo.setMnu_ty("root");
				menuVo.setMnu_target("_self");
				menuVo.setMnu_param(String.format("mnu_code=%s", menuVo.getMnu_code()));
				menuVo.setMnu_linkUrl(String.format("%s/main.do", vo.getSite_directory()));
				
				menuVo.setMnu_privacy("N");
				menuVo.setMnu_visibleGnb(1);
				menuVo.setMnu_visibleSnb(1);
				menuVo.setMnu_visibleTab(0);
				menuVo.setMnu_visibleAuth(0);
				menuVo.setMnu_sttus(0);
				
				menuVo.setMnu_cclTy(0);
				menuVo.setMnu_cclGrade(0);
				menuVo.setMnu_secKybdYn(0);
				menuVo.setMnu_uptCycl(0);
				menuVo.setMnu_regID(vo.getSite_regID());
				menuVo.setMnu_mdfcnID(vo.getSite_regID());
				
				menuService.save(menuVo, 4, 0);
			}
		}
		
		return rtnVal;
	}

	@Override
	public int delete(String site_code) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.delete(site_code);
	}

	@Override
	public int deleteUseYn(String site_code) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.deleteUseYn(site_code);
	}
	
	@Override
	public int deleteSiteUseYn(String site_code) throws DataAccessException {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("site_code", site_code);
		
		//site_useYn = 9로 업데이트
		int rtnVal = mapper.deleteSiteUseYn(site_code);
		
		if(rtnVal != -1) {
			
			//SiteVo siteVo = mapper.select(map);
			
			//1 메뉴 비활성
			menuService.sttusSubMenu(site_code);
			
			//2 회원 비활성
			mapper.memberDisable(site_code);
		}
		return rtnVal;
	}

	@Override
	public int existNm(String site_nm) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.existNm(site_nm);
	}

	@Override
	public int existDir(String site_directory) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.existDir(site_directory);
	}

	@Override
	public int existMngYn() {
		// TODO Auto-generated method stub
		return mapper.existMngYn();
	}

	@Override
	public SiteVo selectDir(String site_directory) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.selectDir(site_directory);
	}

	@Override
	public SiteVo select(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.select(map);
	}

	@Override
	public List<SiteVo> selectPrmList(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.selectPrmList(map);
	}

	@Override
	public List<SiteVo> selectList(Map<String, Object> map) throws DataAccessException {
		// TODO Auto-generated method stub
		return mapper.selectList(map);
	}
}
