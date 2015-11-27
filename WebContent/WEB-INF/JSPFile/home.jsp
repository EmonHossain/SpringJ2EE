<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<!-- Script start here -->
	
	<script type="text/javascript">
		function giveLike(){
			alert("hello");
		}
	</script>
	
	<a href="" onclick="giveLike()">like</a>

	<h1>HOMMMMEEEEE</h1>
	<a href="<c:url value='/j_spring_security_logout'></c:url>">sign out</a><br>
	<a href="<c:url value='/photo'></c:url>">Add Photo</a><br>
	<a href="<c:url value='/album'></c:url>">Add Album</a>
	
	<br><br>
	<c:choose>
		<c:when test="${name.getPic()!=null }">
			<img alt="Image" src="${pageContext.request.contextPath }/staticresources/${name.getPic()}">
		</c:when>
		<c:otherwise>
			<img alt="Image" src="${pageContext.request.contextPath }/staticresources/images/proPic/common.png">
		</c:otherwise>
	</c:choose>
	<a href="${pageContext.request.contextPath }/${name.getAddress()}">${name.getF_name()} ${name.getL_name()}</a>
	<c:out value="${name.getSkill() }"></c:out>
</body>
</html>