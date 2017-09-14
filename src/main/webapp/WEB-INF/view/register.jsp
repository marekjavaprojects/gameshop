<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css">
<!-- Website Font style -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<!-- Google Fonts -->
<link href='https://fonts.googleapis.com/css?family=Passion+One'
	rel='stylesheet' type='text/css'>
<link href='https://fonts.googleapis.com/css?family=Oxygen'
	rel='stylesheet' type='text/css'>

<title>Register</title>
</head>
<body>
<body>
	<div class="container">
		<div class="row main">
			<div class="panel-heading">
				<div class="panel-title text-center">
					<h1 class="title">Game Shop</h1>
					<hr />
				</div>
			</div>
			<div class="main-login main-center">
				<form:form method="POST" modelAttribute="user"
					action='${pageContext.request.contextPath}/register/processForm'>
					<spring:bind path="username">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="username" class="cols-sm-2 control-label">Username</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-users fa" aria-hidden="true"></i></span> <input
										type="text" class="form-control" name="username" id="username"
										placeholder="Enter your Username" />

								</div>
								<form:errors path="username"></form:errors>
							</div>
						</div>
					</spring:bind>
					<spring:bind path="password">
						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="password" class="cols-sm-2 control-label">Password</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
										type="password" class="form-control" path="password"
										name="password" id="password"
										placeholder="Enter your Password" />
								</div>
							</div>
							<form:errors path="password"></form:errors>

						</div>
					</spring:bind>
					<spring:bind path="passwordConfirm">

						<div class="form-group ${status.error ? 'has-error' : ''}">
							<label for="confirm" class="cols-sm-2 control-label">Confirm
								Password</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-lock fa-lg" aria-hidden="true"></i></span> <input
										type="password" class="form-control" path="passwordConfirm"
										name="passwordConfirm" id="confirm"
										placeholder="Confirm your Password" />
								</div>
							</div>
							<form:errors path="passwordConfirm"></form:errors>
						</div>
					</spring:bind>
					<spring:bind path="email">

						<div class="form-group">
							<label for="email" class="cols-sm-2 control-label">Your
								Email</label>
							<div class="cols-sm-10">
								<div class="input-group">
									<span class="input-group-addon"><i
										class="fa fa-envelope fa" aria-hidden="true"></i></span> <input
										type="text" class="form-control" path="email" name="email"
										id="email" placeholder="Enter your Email" />
								</div>
							</div>
						</div>
					</spring:bind>
					<div class="form-group ">
						<input type="submit" value="Register"
							class="btn btn-primary btn-lg btn-block login-button" /> or <a
							href="<c:url value='/' />">Cancel</a>
					</div>
				</form:form>
			</div>
		</div>
	</div>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/bootstrap.js"></script>
</body>


</html>

