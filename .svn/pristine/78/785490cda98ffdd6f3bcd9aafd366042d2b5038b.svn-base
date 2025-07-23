<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<c:if test="${linkList != null && fn:length(linkList) > 0 }">

<div class="visual_slide swiper-container">

	<div class="swiper-wrapper">
		<c:forEach var="vo" items="${linkList}" varStatus="status">
		<c:if test="${vo.lnk_sttus == 1 }">
			<c:set var="lnkLink" value="${vo.lnk_linkUrl }" />
			<fmt:formatNumber var="no" minIntegerDigits="2" value="${status.count}" type="number"/>

			<div class="swiper-slide popup_<c:out value="${no}" />">
			<c:choose>
				<c:when test="${lnkLink != null }">
					<a href="<c:out value="/common/link/linkView.do?id=${vo.lnk_sn}" />" target="<c:out value="${vo.lnk_target}" />" <c:if test="${vo.lnk_target == '_blank'}">title="새창 열림"</c:if>>
	        	<c:if test="${vo.lnk_imgFileNm != ''}">
							<c:out value="${util:getImgTag(vo.getWebDir(), vo.lnk_imgFileNm, vo.lnk_width, vo.lnk_alt) }" escapeXml="false" />
							<%-- <img src="/upload/${vo.getWebDir()}/${vo.lnk_imgFileNm}"> --%>
						</c:if>
					</a>

					<c:if test="${vo.lnk_html != '' }">
			        <div class="layer_detail">
			            <c:out value="${util:htmlEncode(vo.lnk_html) }" escapeXml="false" />
			        </div>
			        </c:if>

				</c:when>
				<c:otherwise>
					<div class="text_area" style="background: url('<c:out value="${util:getImgSrc(vo.getWebDir(), vo.lnk_imgFileNm) }"/>') no-repeat bottom right;">
	                	<c:out value="${vo.lnk_cn }" escapeXml="false" />
	                </div>
				</c:otherwise>
			</c:choose>
			</div>

		</c:if>
		</c:forEach>
	</div>
</div>

<div class="swiper-control">
		<div class="swiper-button-prev"></div>
		<div class="swiper-button-next"></div>
		<div class="swiper-pagination"></div>
    <a href="JavaScript: void(0)" class="swiper-button-pause">정지</a>
</div>

</c:if>
