<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


	<c:choose>
		<c:when test="${menuVo.mnu_ty == 'page' }">
			<c:import url="/SiteManager/cms/menu/page/page_view.do?${menuVo.mnu_param }" >
				<c:param name="menuVo" 	value="${menuVo}" />
			</c:import>
		</c:when>
		
		<c:when test="${menuVo.mnu_ty == 'board' }">
			<c:set var="bodView" value="" />
			
			<c:import url="/SiteManager/cms/board/board.do?${menuVo.mnu_param }" >
				<c:param name="siteVo" 		value="${siteVo}" />	
				<c:param name="tmnuVo" 		value="${tmnuVo}" />	
				<c:param name="pmnuVo" 		value="${pmnuVo}" />
				<c:param name="menuVo" 		value="${menuVo}" />
				<c:param name="prmVal" 		value="${prmVal}" />		
			</c:import>
			<%--
			<c:if test="${bod_uid != 0 }"><c:set var="bodView" value="&bod_uid=${bod_uid}&cmd=${cmd}" /></c:if>
			<iframe id="iFrm02" name="iFrm02" src="${managerDir }/programs/board/board.do?${menu.mnu_parameter }" class="iFrm" style="width:100%;height:700px;"></iframe>
			 --%>
		</c:when>
		
		<c:otherwise>
			<c:import url="${menuVo.mnu_linkUrl}?${menuVo.mnu_param }" >
				<c:param name="siteVo" 					value="${siteVo}" />	
				<c:param name="tmnuVo" 					value="${tmnuVo}" />	
				<c:param name="pmnuVo" 					value="${pmnuVo}" />
				<c:param name="menuVo" 					value="${menuVo}" />
				<c:param name="prmVal" 					value="${prmVal}" />
				<c:param name="managerDir"				value="${managerDir }" />			
			</c:import>
		</c:otherwise>
	</c:choose>