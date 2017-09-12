<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Game Shop</title>
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/shop-homepage.css"
	rel="stylesheet">
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
<script>
	function formSubmit() {
		document.getElementById("logoutForm").submit();
	}
</script>
</head>
<body>
	<div class="page-header" align="center">
		<h1>
			Welcome in GAME Shop!
			<c:if test="${not empty cart}">
				<c:out value="${cart.products}" />
			</c:if>
		</h1>
	</div>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">WebSiteName</a>
			</div>
			<ul class="nav navbar-nav">
				<li class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
			</ul>

			<c:if test="${empty username}">
				<ul class="nav navbar-nav navbar-right">
					<li><a href="${pageContext.request.contextPath}/register"><span
							class="glyphicon glyphicon-user"></span> Sign Up</a></li>
					<li><a href="${pageContext.request.contextPath}/login"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</ul>
			</c:if>
			<c:if test="${not empty username}">
				<ul class="nav navbar-nav navbar-right">

					<li><a class="navbar-brand">Hello: ${username }</a></li>

					<li><a href="javascript:formSubmit()" class="navbar-brand">
							<c:url value="/logout" var="logoutUrl" /> Logout
					</a></li>

					<li><a href="#"><span
							class=" glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
				</ul>
			</c:if>
			<form action="${pageContext.request.contextPath}/products/search"
				method="get" class="navbar-form navbar-left">
				<div class="form-group">
					<input type="text" class="form-control" placeholder="Search"
						name=productName>
				</div>
				<button type="submit" class="btn btn-default">Search</button>
			</form>
			<form action="${logoutUrl}" method="post" id="logoutForm">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>
		</div>
	</div>
	</nav>
	<div class="container">

		<div class="row">

			<div class="col-md-3">
				<div class="list-group">

					<a href="${pageContext.request.contextPath}/products/allProducts"
						class="list-group-item">SHOW ALL</a>

					<c:forEach var="category" items="${categories}">

						<a href=<c:url value="/products/${category}"></c:url>
							class="list-group-item">${category}</a>

					</c:forEach>
					<form action="${pageContext.request.contextPath}/products/search"
						method="get">

						<input type="text" class="form-control"
							placeholder="Search for..." name="productName"> <span
							class="input-group-btn">
							<button class="btn btn-default" type="submit">Search</button>

						</span>
					</form>

				</div>

			</div>

			<h3>${productListLabel}</h3>


			<div class="col-md-9">

				<div class="row">
					<c:forEach var="product" varStatus="status" items="${products}">
						<div class="col-sm-4 col-lg-4 col-md-4">
							<div class="thumbnail">
								<img
									src="${pageContext.request.contextPath}/resources/games_images${product.pathToImage}"
									alt="" style="max-height: 141px; max-width: 106px;">
								<div class="caption">
									<h4>
										<a href="#">${product.productName}</a>
									</h4>
									<h4 class="pull-right">${product.unitPrice}</h4>
									<c:if test="${not empty username}">

										<p>
											<a
												href="${pageContext.request.contextPath}/addToCart/${product.productId}"
												class="list-group-item">Add to cart</a>
										</p>
									</c:if>
								</div>
							</div>

						</div>
					</c:forEach>

				</div>

			</div>
		</div>

	</div>
	<!-- /.container -->

	<div class="container">

		<hr>

		<!-- Footer -->
		<footer>
		<div class="row">
			<div class="col-lg-12">
				<p>Copyright &copy; Marek</p>
			</div>
		</div>
		</footer>

	</div>
	<!-- /.container -->
	<!-- jQuery -->
	<script src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>