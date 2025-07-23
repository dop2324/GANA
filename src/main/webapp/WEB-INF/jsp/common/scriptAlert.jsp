<%@ include file="/library/lib_base.jsp" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE HTML>
<html lang="ko">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" >
<title>${title }</title>
</head>
<body>
<script type="text/javascript" language="javascript">
//<![CDATA[
      <c:if test="${message != \"\"}">alert("${message}");</c:if>
      <c:if test="${location != \"\"}">${location};</c:if>      
//]]>
</script>
</body>
</html>