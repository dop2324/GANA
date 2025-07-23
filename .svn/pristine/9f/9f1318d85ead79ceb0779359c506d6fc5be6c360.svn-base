<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>


<c:if test="${linkList != null && fn:length(linkList) > 0 }">

	<c:forEach var="vo" items="${linkList}" varStatus="status">
	<c:if test="${vo.lnk_sttus == 1 }">
		
	    <div class="layer_popup" id="layorPopup_<c:out value="${vo.lnk_sn }" />" style="top: <c:out value="${vo.lnk_top }" />px; left: <c:out value="${vo.lnk_left }" />px; z-index: <c:out value="${status.count + 20}" />;">
	        <a href="<c:out value="/common/link/linkView.do?id=${vo.lnk_sn}" />" target="${vo.lnk_target}" <c:if test="${vo.lnk_target == '_blank'}">title="새창 열림"</c:if>>
	        	<c:if test="${vo.lnk_imgFileNm != ''}">
					<c:out value="${util:getImgTag(vo.getWebDir(), vo.lnk_imgFileNm, vo.lnk_width, vo.lnk_alt) }" escapeXml="false" />
				</c:if>
	        </a>
	        <c:if test="${vo.lnk_html != null }">
	        <div class="layer_detail">
	            <c:out value="${vo.lnk_html }" escapeXml="false" />
	        </div>
	        </c:if>
	        
	        <div class="layer_close">
	            <form name="pop_form01" class="clearfix">
	                <input type="checkbox" name="chkbox" id="popupCheck_<c:out value="${vo.lnk_sn }" />" value="checkbox">
	                <label for="popupCheck<c:out value="${vo.lnk_sn }" />">오늘 하루동안 보지 않기</label>
	                <a href="#self" onclick="closeLayorPop(<c:out value="${vo.lnk_sn }" />);">닫기</a>
	            </form>
	        </div>
	    </div>
	    
	</c:if>
	</c:forEach> 
	
	<script>
	function closeLayorPop(lnk_sn) {
	    if ($("#popupCheck_"+lnk_sn).is(":checked")) {
	        setCookie( "layorPopup_"+lnk_sn, "done" , 1 );
	    }
	    $("#layorPopup_"+lnk_sn).hide();
	}
	
	$(function()
	{
		cookiedata = document.cookie;
		<c:forEach var="vo" items="${linkList}" varStatus="status">
		<c:if test="${vo.lnk_sttus == 1 }">
	     if ( cookiedata.indexOf("<c:out value="layorPopup_${vo.lnk_sn}" />=done") < 0 ) {
	    	 $("#<c:out value="layorPopup_${vo.lnk_sn}" />").show();
	     }
	     else {
	    	 $("#<c:out value="layorPopup_${vo.lnk_sn}" />").hide();
	     }
	    </c:if>
	    </c:forEach> 
	});
	</script>

</c:if>