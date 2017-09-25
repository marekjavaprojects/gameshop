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

		<form action="${pageContext.request.contextPath}/updateCart"
			method="post">
			<table id="cart" class="table table-hover table-condensed">

				<thead>
					<tr>
						<th style="width: 50%">Product</th>
						<th style="width: 10%">Price</th>
						<th style="width: 8%">Quantity</th>
						<th style="width: 22%" class="text-center">Subtotal</th>
						<th style="width: 10%"></th>
					</tr>
				</thead>
				<c:forEach var="cartItem" items="${cart.cartItems}">

					<tbody>
						<tr>
							<td data-th="Product">
								<div class="row">
									<div class="col-sm-2 hidden-xs">
										<img
											src="${pageContext.request.contextPath}/resources/games_images${cartItem.pathToImage}"
											class="img-responsive" />
									</div>
									<div class="col-sm-10">
										<h4 class="nomargin">${cartItem.productName}</h4>
									</div>
								</div>
							</td>
							<td data-th="Price">${cartItem.unitPrice}</td>
							<td data-th="Quantity"><input type="number"
								class="form-control text-center" name="quantity" value="${cartItem.quantity}" ></td>
							<td data-th="Subtotal" class="text-center">${cartItem.totalPrice}</td>
							<td class="actions" data-th="">
								<button class="btn btn-danger btn-sm">
									<i class="fa fa-trash-o"></i>
								</button>
							</td>
						</tr>
				</c:forEach>

				</tbody>

				<tfoot>
					<tr class="visible-xs">
						<td class="text-center"><strong>Total
								${cart.totalPrice}</strong></td>
					</tr>
					<tr>
						<td><a href="${pageContext.request.contextPath}"
							class="btn btn-warning"><i class="fa fa-angle-left"></i>
								Continue Shopping</a></td>
						<td colspan="2" class="hidden-xs"></td>
						<td class="hidden-xs text-center"><strong>Total
								${cart.totalPrice}</strong></td>
						<td><input type="hidden" name="${_csrf.parameterName}"
							value="${_csrf.token}" /> <input
							class="btn btn-success btn-block" type="submit" value="Update">
						</td>
						<td><a href="#" class="btn btn-success btn-block">Checkout
								<i class="fa fa-angle-right"></i>
						</a></td>
					</tr>
				</tfoot>
			</table>
		</form>
	</div>
</body>
</html>