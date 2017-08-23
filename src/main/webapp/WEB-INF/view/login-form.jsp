<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Login Form</title>
<link href="${pageContext.request.contextPath}/resources/css/app.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.css"
	rel="stylesheet">
<style>
.error {
	color: red;
	font-weight: bold;
}
</style>
</head>

<body>
	<div class="generic-container">

		<div class="well lead">User Login Form</div>
		<form name='loginForm' action="<c:url value='/login' />" method='POST'
			class="form-horizontal">

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable">User name</label>
					<div class="col-md-7">
						<input type="text" name="username" class="form-control input-sm" />
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable">Password</label>
					<div class="col-md-7">
						<input type="password" name="password"
							class="form-control input-sm" />

					</div>
				</div>
			</div>


			<div class="row">

				<div class="form-actions floatRight">

					<input name="login" class="btn btn-primary btn-sm" type="submit" />

					or <a href="<c:url value='/' />">Cancel</a>

				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>

	</div>
</body>
</html>