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
	<h2>Hello hommmiee  ${value }</h2>
	<h2><c:out value="${value }"></c:out></h2>
	<a  href="<c:out value='${pageContext.request.contextPath }/sign_up'></c:out>">Sign up</a>
	<a  href="<c:out value='${pageContext.request.contextPath }/sign_in'></c:out>">Sign in</a>
</body>
</html>