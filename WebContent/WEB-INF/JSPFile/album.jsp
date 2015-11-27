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
	<h1>Hello</h1>
	
	<c:forEach var="image" items="${images }" >
	
		<c:out value="Image id : "></c:out>
		<c:out value="${image.getImageId() }"></c:out><br>
		<c:out value="Upload date : "></c:out>
		<c:out value="${image.getUploadDate() }"></c:out><br>
		<c:out value="Real name : "></c:out>
		<c:out value="${image.getRealName() }"></c:out><br>
		<c:out value="${image.getUserId() }"></c:out><br>
		
		
		<c:if test="${image.getAlbumName()!=null}">	
			<c:out value="Album name : ${image.getAlbumName()} "></c:out><br>
		</c:if>
		
		<c:if test="${image.getCaption()!=null}">
			<c:out value="Caption : "></c:out><c:out value="${image.getCaption() }"></c:out><br>
		</c:if>
		
		<img alt="Image" src="${pageContext.request.contextPath }/staticresources/<c:out value="${image.getDir() }"></c:out>"><br>
	</c:forEach>


</body>
</html>