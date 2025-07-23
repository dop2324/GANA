<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<div id="total_search">

	<c:if test="${ctgry == '' }">
	<div id="search_second">
		<p>검색어 <span>"<c:out value="${kwd }" />"</span>에 대한 <span>총 <span id="totalCntTop">0</span>건</span>의 검색결과를 찾았습니다.</p>
	</div>
	</c:if>

	<div class="cMain">

		<div class="cMenu" id="Smenu">
			<ul>
				<li<c:if test="${ctgry == '' }"> 			class="on"</c:if>><a href="<c:out value="?kwd=${kwd }" />"><span>통합검색</span></a></li>
				<li<c:if test="${ctgry == 'page' }"> 		class="on"</c:if>><a href="<c:out value="?ctgry=page&kwd=${kwd }" />"><span>웹페이지검색</span></a></li>
				<li<c:if test="${ctgry == 'board' }"> 		class="on"</c:if>><a href="<c:out value="?ctgry=board&kwd=${kwd }" />"><span>게시판검색</span></a></li>
				<li<c:if test="${ctgry == 'org' }"> 		class="on"</c:if>><a href="<c:out value="?ctgry=org&kwd=${kwd }" />"><span>직원검색</span></a></li>
			</ul>
			<div class="cM_expression"></div>
		</div>

		<div class="nav-wrap">
			<nav>
				<ul class="gnb">
					<li class="ctgr"><a href="<c:out value="?kwd=${kwd }" />">통합검색</a></li>
					<li class="ctgr"><a href="<c:out value="?ctgry=page&kwd=${kwd }" />">웹페이지검색</a></li>
					<li class="ctgr"><a href="<c:out value="?ctgry=board&kwd=${kwd }" />">게시판검색</a></li>
					<li class="ctgr"><a href="<c:out value="?ctgry=org&kwd=${kwd }" />">직원검색</a></li>
				</ul>
			</nav>
			<button type="button" title="카테고리 더보기" class="open">
				<span class="ico-set dot"></span>
				<span class="nav-close">닫기</span>
			</button>
		</div>
		
		<div class="cLeft" id="Scon">
			<c:choose>
				<c:when test="${ctgry == '' }">

					<div class="integration_sc">
						<ul class="Cmenu_Title">
							<li>통합검색</li>
							<li>총 <span id="totalCnt" class="totalCntClass">0</span>건</li>
						</ul>
						<div class="integration_box">
							<div class="integration_txt2">
								<ul>
									<li><a href="<c:out value="?ctgry=page&kwd=${kwd }" />">	<span>웹페이지검색</span> (<span class="pageCnt">0</span>)</a></li>
									<li><a href="<c:out value="?ctgry=board&kwd=${kwd }" />">	<span>게시판검색</span> (<span class="boardCnt">0</span>)</a></li>
									<li><a href="<c:out value="?ctgry=org&kwd=${kwd }" />">		<span>직원검색</span> (<span class="orgCnt">0</span>)</a></li>
								</ul>
							</div>
						</div>
					</div>
					
					<%-- 페이지 --%>
					<c:import url="/WebContent/search/page.do">
						<c:param name="ctgry" 		value="page"	/>
						<c:param name="kwd" 		value="${kwd }"	/>
						<c:param name="mnu_nm"  	value="웹페이지"	/>
						<c:param name="pageSize"  	value="3"	/>
					</c:import>
					
					<%-- 게시판 --%>
					<c:import url="/WebContent/search/board.do">
						<c:param name="ctgry" 		value="board"	/>
						<c:param name="kwd" 		value="${kwd }"	/>
						<c:param name="mnu_nm"  	value="게시판"	/>
						<c:param name="pageSize"  	value="3"	/>
					</c:import>

          			<%-- 직원 --%>
          			<c:import url="/WebContent/search/org.do">
						<c:param name="ctgry" 		value="org"	/>
						<c:param name="kwd" 		value="${kwd }"	/>
						<c:param name="mnu_nm"  	value="직원"	/>
						<c:param name="pageSize"  	value="3"	/>
					</c:import>

				</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${ctgry == 'page'}">
							<c:import url="/WebContent/search/page.do">
								<c:param name="ctgry" 		value="page"	/>
								<c:param name="kwd" 		value="${kwd }"	/>
								<c:param name="mnu_nm"  	value="웹페이지"	/>
								<c:param name="pageSize"  	value="10"	/>
							</c:import>
						</c:when>
						<c:when test="${ctgry == 'board'}">
							<c:import url="/WebContent/search/board.do">
								<c:param name="ctgry" 		value="board"	/>
								<c:param name="kwd" 		value="${kwd }"	/>
								<c:param name="mnu_nm"  	value="게시판"	/>
								<c:param name="pageSize"  	value="10"	/>
							</c:import>
						</c:when>
						<c:when test="${ctgry == 'org'}">
							<c:import url="/WebContent/search/org.do">
								<c:param name="ctgry" 		value="org"	/>
								<c:param name="kwd" 		value="${kwd }"	/>
								<c:param name="mnu_nm"  	value="직원"	/>
							</c:import>
						</c:when>
					</c:choose>
				</c:otherwise>
			</c:choose>

		</div>
	</div>
</div>
