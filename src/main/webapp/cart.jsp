<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head> 
        <meta charset="UTF-8">
        <title>Murach's Java Servlets and JSP</title>
        <link rel="stylesheet" href="styles/main.css" type="text/css"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head> 
    <body>
        <h1>Your cart</h1>
        <table>
            <tr>
                <th>Quantity</th>
                <th>Description</th>
                <th>Price</th>
                <th>Amount</th>
                <th>&nbsp;</th> 
            </tr>

            <c:choose>
                <%-- Vòng lặp duyệt qua từng sản phẩm --%>
                <c:forEach var="item" items="${sessionScope.cart.items}">
                    <tr>
                        <td>
                            <form action="cart" method="post">
                                <input type="hidden" name="action" value="update">
                                <input type="hidden" name="productCode" value="${item.product.code}">
                                <input type="text" name="quantity" value="${item.quantity}">
                                <input type="submit" value="Update">
                            </form>
                        </td>

                        <td>${item.product.description}</td>

                        <td class="right">$${item.product.price}</td>

                        <td class="right">${item.totalCurrencyFormat}</td>

                        <td>
                            <form action="cart" method="post">
                                <input type="hidden" name="action" value="remove">
                                <input type="hidden" name="productCode" value="${item.product.code}">
                                <input type="submit" value="Remove Item">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                
                <%-- Trường hợp giỏ hàng trống (tương ứng với lệnh else cũ) --%>
                <c:otherwise>
                    <tr>
                        <td colspan="5">Cart is empty</td>
                    </tr>
                </c:otherwise>
            </c:choose>
        </table>

        <p>To change the quantity, enter the new quantity and click on the Update button.</p>

        <form action="cart" method="post">
            <input type="hidden" name="action" value="shop">
            <input type="submit" value="Continue Shopping">
        </form> <br>

        <form action="cart" method="post">
            <input type="hidden" name="action" value="checkout">
            <input type="submit" value="Checkout">
        </form>
    </body>
</html>