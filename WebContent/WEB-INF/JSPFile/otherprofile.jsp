<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Others</h2>
	<sec:authorize access="isAuthenticated()" ><a href="<c:url value='/j_spring_security_logout'></c:url>">sign out</a></sec:authorize><br>
	Name : <a href="${pageContext.request.contextPath}/${reg.getAddress()}">${reg.getF_name()} ${reg.getL_name()}</a>
	<c:out  value="${reg.getF_name()}"></c:out>
	Name : <c:out  value="${reg.getL_name()}"></c:out>
	Name : <c:out  value="${reg.getDob()}"></c:out>
	Name : <c:out  value="${reg.getCountry()}"></c:out>
	Name : <c:out  value="${reg.getGender()}"></c:out>
	Name : <c:out  value="${reg.getSkill()}"></c:out>
	<br><br>
	//<br>
	//<br>
	//
	//<br>
	
	<c:choose>
		<c:when test="${reg.getPic()!=null}"><img alt="Image" src="${pageContext.request.contextPath}/staticresources/${reg.getPic()}"></c:when>
		<c:otherwise><img alt="Image" src="${pageContext.request.contextPath }/staticresources/images/proPic/common.png"></c:otherwise>
	</c:choose>
	
	
</body>
</html>