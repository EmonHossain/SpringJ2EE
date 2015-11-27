<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="Sform"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- jquery -->
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<!-- moment -->
	<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.10.6/moment.min.js"></script>
<!-- qunit -->
	<!--  <script src="../bower_components/qunit/qunit/qunit.js"></script> -->
	<script type="text/javascript" src="${pageContext.request.contextPath }/staticresources/plugin/js/combodate.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath }/staticresources/custom/JS/custom.js"></script>
<title>Insert title here</title>
</head>
<body>
	<Sform:form action="${pageContext.request.contextPath }/sign_up" method="post" commandName="registration">
		<label>First Name</label> 
		<Sform:input type="text" path="firstName" name="firstName" placeholder="First name"/><Sform:errors path="firstName" cssClass=""></Sform:errors><br>
		<label>Last Name</label>
		<Sform:input type="text" path="lastName" name="lastName"/><Sform:errors path="lastName" cssClass=""></Sform:errors><br>
		<label>Email</label>
		<Sform:input type="text" path="email" name="email"/><Sform:errors path="email" cssClass=""></Sform:errors><br>
		<label>Password</label>
		<Sform:input type="password" path="password" name="password"/><Sform:errors path="password" cssClass=""></Sform:errors><br>
		<label>Birthday</label>
		
		<Sform:input id="date" data-format="DD-MM-YYYY" data-template="D MMM YYYY" name="date" path="date" value="01-01-2000" type="text"/>
    	<Sform:errors path="date" cssClass=""></Sform:errors><br>
		
		<Sform:select path="gender" name="gender">
			<option value="">Gender</option>
			<option value="Male">Male</option>
			<option value="Female">Female</option>
		</Sform:select><Sform:errors path="gender" cssClass=""></Sform:errors><br>
		 
		<Sform:select path="country" name="country">
			<option value="">Country</option>
			<option value="BD">Bangladesh</option>
			<option value="USA">USA</option>
		</Sform:select><Sform:errors path="country" cssClass=""></Sform:errors><br> 
		
		<Sform:select path="skill" name="skill">
			<option value="">Skill</option>
			<option value="Ameture">Ameture</option>
			<option value="Professionl">Professional</option>
		</Sform:select><Sform:errors path="skill" cssClass=""></Sform:errors><br> 
		
		<input type="submit" value="sign up">
	</Sform:form>
</body>

    
</html>