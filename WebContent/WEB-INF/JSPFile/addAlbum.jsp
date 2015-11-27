<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<form action="${pageContext.request.contextPath }/album" modelAttribute="albumUpForm" method="post" enctype="multipart/form-data">
		<input type="text" name="albumName">
		<input type="text" name="caption">
		<input type="file" name=files multiple> 
		
		<input type="submit" value="Add Image">
	</form>

</body>
</html>