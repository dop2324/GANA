<%@ include file="/library/lib_base.jsp" %>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<script>
function setCodeSn(code_sn) {
	
	this.parm_codeSn = code_sn;
	viewTab("");
}

function openTree(code_sn) {
	d.closeAll();
	d.openTo(code_sn, true);
	//setCodeSn(code_sn);
}
var parm_codeSn = "<c:out value="${code_sn}" />";
d = new dTree("d");
<c:forEach var="vo" items="${cmmncdTree}" varStatus="status">
d.add("<c:out value="${vo.code_sn}" />"
		, "<c:out value="${vo.code_uprSn != null ? vo.code_uprSn:'-1'}" />"
		, "<c:out value="${vo.code_nm}" />(<c:out value="${vo.code_sn}" />) <c:if test="${vo.code_val != null}"><c:out value=" [${vo.code_val}]" /></c:if><c:out value="${vo.code_sttus == 0 ? '(중지)':''}" />"
		, "javascript:setCodeSn('<c:out value="${vo.code_sn}" />');"
		, "", "", "", "", ""
		, <c:out value="${vo.code_sttus}" />
		);
</c:forEach>
document.write(d);
d.aNodes.forEach((item, index) => {
    if(item.id == this.parm_codeSn) {
    	openTree(this.parm_codeSn);
    	d.s(index);
    }
});
d.clearCookie();
</script>