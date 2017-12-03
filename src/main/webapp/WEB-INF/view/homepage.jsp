<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
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
		<h1>Welcome in GAME Shop!</h1>
	</div>
	<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="container-fluid">
			<ul class="nav navbar-nav">
				<li class="active"><a href="${pageContext.request.contextPath}">Home</a></li>
			</ul>
			<sec:authorize var="loggedIn" access="isAuthenticated()" />
			<c:choose>
				<c:when test="${loggedIn}">

					<ul class="nav navbar-nav navbar-right">

						<li><a class="navbar-brand">Hello: <%=request.getUserPrincipal().getName()%></a></li>

						<li><a href="javascript:formSubmit()" class="navbar-brand">
								<c:url value="/logout" var="logoutUrl" /> Logout
						</a></li>

						<li><a
							href="${pageContext.request.contextPath}/cart/showCart"><span
								class=" glyphicon glyphicon-shopping-cart"></span> Cart</a></li>
					</ul>
				</c:when>
				<c:otherwise>
					<ul class="nav navbar-nav navbar-right">
						<li><a href="${pageContext.request.contextPath}/register"><span
								class="glyphicon glyphicon-user"></span> Sign Up</a></li>
						<li><a href="${pageContext.request.contextPath}/login"><span
								class="glyphicon glyphicon-log-in"></span> Login</a></li>
					</ul>
				</c:otherwise>
			</c:choose>

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

					<a href="${pageContext.request.contextPath}/products/allProducts/1"
						class="list-group-item">SHOW ALL</a>

					<c:forEach var="category" items="${categories}">

						<a href=<c:url value="/products/${category}"></c:url>
							class="list-group-item">${category}</a>

					</c:forEach>
				</div>
			</div>

			<h3>${productListLabel}</h3>

			<div class="col-md-9">
				<c:if test="${pages gt 0}">
					<ul class="pagination">
						<li class="disabled"><a
							href=<c:url value="/products/allProducts/${prevPage}"></c:url>>«</a></li>
						<c:forEach begin="1" end="${pages }" varStatus="loop">

							<li class="active"><a
								href=<c:url value="/products/allProducts/${loop.index}"></c:url>>${loop.index}
									<span class="sr-only">(current)</span>
							</a></li>

						</c:forEach>
						<li><a
							href=<c:url value="/products/allProducts/${nextPage}"></c:url>>»</a></li>
					</ul>
				</c:if>
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
									<h4>
										<a href="#">Price: ${product.unitPrice}</a>
									</h4>
									<c:if test="${loggedIn}">

										<form
											action="${pageContext.request.contextPath}/cart/addToCart"
											method="post">
											<div class="form-group">
												<input type="hidden" name="${_csrf.parameterName}"
													value="${_csrf.token}" />
												<button type="submit" class="btn btn-primary"
													name="productId" value="${product.productId}">Add
													to cart</button>
											</div>
										</form>

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