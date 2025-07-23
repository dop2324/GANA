<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" %>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<c:import url="/EgovPageLink.do?link=WebContent/common/inc_head" />
<title>팝업</title>
</head>
<body>


<c:choose>
	<c:when test="${vo == null }">
		<script>
			팝업 내용이 없습니다
		</script>
	</c:when>
	<c:otherwise>
	
		<script>
		$(function(){
			$("title").text("<c:out value="${vo.lnk_nm}" />");
			self.moveTo(<c:out value="${vo.lnk_left}" />, <c:out value="${vo.lnk_top}" />);
		});
		
		function closePopup(lnk_sn) {
		    if ($("#popupCheck_"+lnk_sn).is(":checked")) {
		        setCookie( "layorPopup_"+lnk_sn, "done" , 1 );
		    }
		    $("#layorPopup_"+lnk_sn).hide();
		}
		</script>
	
		<div id="pop_wp">
			
		  	<div>
		  		<a href="<c:out value="/common/link/linkView.do?id=${vo.lnk_sn}" />" target="${vo.lnk_target}" <c:if test="${vo.lnk_target == '_blank'}">title="새창 열림"</c:if>>
		  		<c:if test="${vo.lnk_imgFileNm != ''}">
					<c:out value="${util:getImgTag(vo.getWebDir(), vo.lnk_imgFileNm, vo.lnk_width, vo.lnk_alt) }" escapeXml="false" />
				</c:if>
				</a>
		  	</div>
		  	
			<c:if test="${vo.lnk_html != null }">
	        <div class="layer_detail">
	            <c:out value="${vo.lnk_html }" escapeXml="false" />
	        </div>
	        </c:if>
		  	
		  	<div class="layer_close">
	            <form name="pop_form01" class="clearfix">
	                <input type="checkbox" name="chkbox" id="popupCheck_<c:out value="${vo.lnk_sn }" />" value="checkbox">
	                <label for="popupCheck<c:out value="${vo.lnk_sn }" />">오늘 하루동안 보지 않기</label>
	                <a href="#self" onclick="closePopup(<c:out value="${vo.lnk_sn }" />);">닫기</a>
	            </form>
	        </div>
		</div>		
	</c:otherwise>
</c:choose>



</body>
</html>
