<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/staticresources/lib/jquery-1.11.3.js"></script>


		<!-- Script start here -->
	
	<script type="text/javascript">
		function giveLike(name){
			//address = ${pageContext.request.contextPath}/like;
			//alert(name);
			/* $.getJSON("like", {name: name}, function (data) {
		        alert("kjdfhhhhhhhhhhhhhhhhhhhhhhhh......");
		      }); */
		     
			 $.ajax({
			        url: 'like',
			        data: ({name : name}),
			        success: function(data) {
			        	$("#"+name).html(data);
			          	alert(data);
			        }
			      });

		}
	</script>
	

</head>
<body>
	
    <!-- <h1 id="liked"></h1> -->
    
	<a href="<c:url value='/photo'></c:url>">Add Photo</a><br>
	<a href="<c:url value='/album'></c:url>">Add Album</a>

	<a href="<c:url value='/j_spring_security_logout'></c:url>">sign out</a><br>
	Name : <a href="${pageContext.request.contextPath}/${reg.getAddress()}">${reg.getF_name()} ${reg.getL_name()}</a>
	<c:out  value="${reg.getF_name()}"></c:out>
	Name : <c:out  value="${reg.getL_name()}"></c:out>
	Name : <c:out  value="${reg.getDob()}"></c:out>
	Name : <c:out  value="${reg.getCountry()}"></c:out>
	Name : <c:out  value="${reg.getGender()}"></c:out>
	Name : <c:out  value="${reg.getSkill()}"></c:out>
	
	
	
	<c:choose>
		<c:when test="${reg.getPic()!=null }">
			<a href="${pageContext.request.contextPath }/avatar?change=true">Change profile picture</a>
		</c:when>
		<c:otherwise>
			<a href="${pageContext.request.contextPath }/avatar">upload profile picture</a>
		</c:otherwise>
	</c:choose>
	
	<br><br>
	//<br>
	//<br>
	//
	//<br>
	
	<c:choose>
		<c:when test="${reg.getPic()!=null }">
			<img alt="Image" src="${pageContext.request.contextPath }/staticresources/${reg.getPic()}">
		</c:when>
		<c:otherwise>
			<img alt="Image" src="${pageContext.request.contextPath }/staticresources/images/proPic/common.png">
		</c:otherwise>
	</c:choose>
	
	
	<h1>Uploaded Photo</h1><br>
	
	<c:forEach var="image" items="${images }" >
	
		<c:out value="Image id : "></c:out>
		<c:out value="${image.getImageId() }"></c:out><br>
		<c:out value="Upload date : "></c:out>
		<c:out value="${image.getUploadDate() }"></c:out><br>
		<c:out value="Real name : "></c:out>
		<c:out value="${image.getRealName() }"></c:out><br>
		<c:out value="${image.getUserId() }"></c:out><br>
		
		
		<c:if test="${image.getAlbumName()!=null}">	
			<c:out value="Album name : "></c:out> <a href="${pageContext.request.contextPath }/album/photos?alb=${image.getAlbumName()}&n=${image.getUserId()}"> <c:out value="${image.getAlbumName()}"></c:out></a><br>
			
			<%-- <a href="/photos" onclick="JavaScript: handleThisLink();">${image.getAlbumName()}</a>
			<form name="mydataform" id="mydataform" action="${pageContext.request.contextPath }/album/photos" method="post">
				<input type="hidden" name="albName" value="" />
				<input type="hidden" name="userId" value="" />
			</form>
			
			<script type="text/javascript">
				function handleThisLink() {
					// access the hidden element in which you wish to pass the value of the parameter
					dojo.byId("albName").value = ${image.getAlbumName()};
					dojo.byId("userId").value = ${image.getUserId() };
					document.forms['mydataform'].submit();
				}
			</script> --%>
			
		</c:if>
		
		<c:if test="${image.getCaption()!=null}">
			<c:out value="Caption : "></c:out><c:out value="${image.getCaption() }"></c:out><br>
		</c:if>
		
		<img alt="Image" src="${pageContext.request.contextPath }/staticresources/<c:out value="${image.getDir() }"></c:out>"><br>
		
		<a onclick="giveLike('${image.getImageId() }')">like</a>
		<button onclick="giveLike('${image.getImageId() }')">Hit</button>
		Like : <p><b id="${image.getImageId() }"></b> ${image.getLikeCounter()}</p>
	
		
	</c:forEach>
	
</body>
</html>