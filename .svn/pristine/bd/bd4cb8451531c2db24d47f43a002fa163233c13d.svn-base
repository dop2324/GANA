<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


	<%-- Content --%>
	<c:choose>
		<c:when test="${menuVo.mnu_ty == 'menu' }">
			<c:import url="/EgovPageLink.do?link=/WebContent/common/inc_comingSoon" />
		</c:when>
		<c:when test="${menuVo.mnu_ty == 'page' }">
			<c:import url="/WebContent/cms/menu/page/pageView.do?${menuVo.mnu_param }" >
				<c:param name="menuVo" 	value="${menuVo}" />
			</c:import>
		</c:when>
		<c:when test="${menuVo.mnu_ty == 'page+program' }">
			<c:import url="/WebContent/cms/menu/page/pageView.do?${menuVo.mnu_param }" >
				<c:param name="menuVo" 	value="${menuVo}" />
			</c:import>
			
			<c:import url="${menu.mnu_linkUrl}?${menu.mnu_parameter }" >
				<c:param name="siteVo" 		value="${siteVo}" />	
				<c:param name="tmnuVo" 		value="${tmnuVo}" />	
				<c:param name="pmnuVo" 		value="${pmnuVo}" />
				<c:param name="menuVo" 		value="${menuVo}" />
				<c:param name="prmVal" 		value="${prmVal}" />
				<c:param name="publicDir"	value="${publicDir }" />	
			</c:import>
		</c:when>
		<c:when test="${menuVo.mnu_ty == 'page+board' }">
			<c:import url="/WebContent/cms/menu/page/pageView.do?${menuVo.mnu_param }" >
				<c:param name="menuVo" 	value="${menuVo}" />
			</c:import>
			
			<c:import url="/WebContent/cms/board/board.do?${menuVo.mnu_param }" >
				<c:param name="siteVo" 		value="${siteVo}" />	
				<c:param name="tmnuVo" 		value="${tmnuVo}" />	
				<c:param name="pmnuVo" 		value="${pmnuVo}" />
				<c:param name="menuVo" 		value="${menuVo}" />
				<c:param name="prmVal" 		value="${prmVal}" />
				<c:param name="publicDir"	value="${publicDir }" />			
			</c:import>
		</c:when>
		
		<c:when test="${menuVo.mnu_ty == 'board' }">
			<c:set var="bodView" value="" />
			<c:import url="/WebContent/cms/board/board.do?${menuVo.mnu_param }" >
				<c:param name="siteVo" 		value="${siteVo}" />	
				<c:param name="tmnuVo" 		value="${tmnuVo}" />	
				<c:param name="pmnuVo" 		value="${pmnuVo}" />
				<c:param name="menuVo" 		value="${menuVo}" />
				<c:param name="prmVal" 		value="${prmVal}" />
				<c:param name="publicDir"	value="${publicDir }" />			
			</c:import>
		</c:when>
		
		<c:otherwise>
			<c:import url="${menuVo.mnu_linkUrl}?${menuVo.mnu_param }" >
				<c:param name="siteVo" 		value="${siteVo}" />	
				<c:param name="tmnuVo" 		value="${tmnuVo}" />	
				<c:param name="pmnuVo" 		value="${pmnuVo}" />
				<c:param name="menuVo" 		value="${menuVo}" />
				<c:param name="prmVal" 		value="${prmVal}" />
				<c:param name="publicDir"	value="${publicDir }" />			
			</c:import>
		</c:otherwise>
	</c:choose>
	
	
	<%-- 공공 누리 --%>
	<c:import url="/EgovPageLink.do?link=/WebContent/common/inc_ccl" />
	
	<%-- 페이지 만족도 --%>
	<c:import url="/EgovPageLink.do?link=/WebContent/common/inc_menuGratify" />
	