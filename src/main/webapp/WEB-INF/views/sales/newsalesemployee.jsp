<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Employee Signup Page</title>
<style>
.error {
color: #ff0000;
font-style: italic;
}
</style>

</head>
<body>
<P> Sales Employee Registration Form </P>
<form:form method="POST"  commandName="signupemployee"  action="${pageContext.request.contextPath}/sales/salesmanager/newsalesemployee/op1.html" >

 <table>
    <tr>
        <td><form:label path="firstName">firstName</form:label></td>
        <td><form:input class = "form-control" path="firstName"/></td>
        <td><form:errors path="firstName" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="lastName">lastName</form:label></td>
        <td><form:input class = "form-control" path="lastName" /></td>
        <td><form:errors path="lastName" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="userName">userName</form:label></td>
        <td><form:input class = "form-control" path="userName" /></td>
        <td><form:errors path="userName" cssClass="error" /></td>
    </tr>
    <tr>
        <td><form:label path="emailId">emailId</form:label></td>
        <td><form:input class = "form-control" path="emailId" /></td>
        <td><form:errors path="emailId" cssClass="error" /></td>
    </tr>
   
    <tr>
	    <td colspan="2">
	    	<div class ="pull-right">
				<button class="btn btn-large btn-primary" type="submit" value="submit">Submit</button>
			</div>
	          
	    </td>
    </tr>
</table>  
</form:form> 

