<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="Sform"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Propic upload</h1>
	
	<c:choose>
		<c:when test="${proPic!=null}">
			<img alt="image" src="${pageContext.request.contextPath }/staticresources/${proPic}">
		</c:when>
		<c:otherwise>
			<img alt="Image" src="${pageContext.request.contextPath }/staticresources/images/proPic/common.png">
		</c:otherwise>
	</c:choose>
	
		
	
	<form action="${pageContext.request.contextPath }/avatar" method="post" enctype="multipart/form-data">
		<input type="file" name="file">
		${error}
		<input type="submit" value="upload">
		<%-- <input type="hidden"  name="${_csrf.parameterName}"   value="${_csrf.token}"/> --%>
	</form>
	
	
	<c:if test="${noskip==null}"><a href="${pageContext.request.contextPath }/home">skip</a></c:if>
	
	
	
</body>
</html>