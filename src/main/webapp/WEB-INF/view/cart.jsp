<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/cart.css"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<div class="container">

		<table id="cart" class="table table-hover table-condensed">
			<c:forEach var="product" items="${cart.products}" varStatus="i">

				<thead>
					<tr>
						<th style="width: 50%">Product</th>
						<th style="width: 10%">Price</th>
						<th style="width: 8%">Quantity</th>
						<th style="width: 22%" class="text-center">Subtotal</th>
						<th style="width: 10%"></th>
					</tr>
				</thead>
				
				<tbody>
					<tr>
						<td data-th="Product">
							<div class="row">
								<div class="col-sm-2 hidden-xs">
									<img src="http://placehold.it/100x100" alt="..."
										class="img-responsive" />
								</div>
								<div class="col-sm-10">
								
									<h4 class="nomargin">${product.id}</h4>
									<p>Quis aute iure reprehenderit in voluptate velit esse
										cillum dolore eu fugiat nulla pariatur. Lorem ipsum dolor sit
										amet.</p>
								</div>
							</div>
						</td>
						<td data-th="Price">$1.99</td>
						<td data-th="Quantity"><input type="number"
							class="form-control text-center" value="1"></td>
						<td data-th="Subtotal" class="text-center">1.99</td>
						<td class="actions" data-th="">
							<button class="btn btn-info btn-sm">
								<i class="fa fa-refresh"></i>
							</button>
							<button class="btn btn-danger btn-sm">
								<i class="fa fa-trash-o"></i>
							</button>
						</td>
					</tr>
			</c:forEach>

			</tbody>

			<tfoot>
				<tr class="visible-xs">
					<td class="text-center"><strong>Total 1.99</strong></td>
				</tr>
				<tr>
					<td><a href="#" class="btn btn-warning"><i
							class="fa fa-angle-left"></i> Continue Shopping</a></td>
					<td colspan="2" class="hidden-xs"></td>
					<td class="hidden-xs text-center"><strong>Total $1.99</strong></td>
					<td><a href="#" class="btn btn-success btn-block">Checkout
							<i class="fa fa-angle-right"></i>
					</a></td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>