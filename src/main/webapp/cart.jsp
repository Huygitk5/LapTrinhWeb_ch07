<%@page import="murach.business.Cart"%>
<%@page import="murach.business.LineItem"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

            <% 
                // Lấy giỏ hàng từ Session
                Cart cart = (Cart) session.getAttribute("cart");

                if (cart != null && cart.getItems().size() > 0) {
                    for (LineItem item : cart.getItems()) {
            %>
            <tr>
                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="update">
                        <input type="hidden" name="productCode" value="<%= item.getProduct().getCode() %>">
                        <input type="text" name="quantity" value="<%= item.getQuantity() %>">
                        <input type="submit" value="Update">
                    </form>
                </td>

                <td><%= item.getProduct().getDescription() %></td>

                <td class="right">$<%= item.getProduct().getPrice() %></td>

                <td class="right"><%= item.getTotalCurrencyFormat() %></td>

                <td>
                    <form action="cart" method="post">
                        <input type="hidden" name="action" value="remove">
                        <input type="hidden" name="productCode" value="<%= item.getProduct().getCode() %>">
                        <input type="submit" value="Remove Item">
                    </form>
                </td>
            </tr>
            <% 
                    } 
                } else {
            %>
                <tr>
                    <td colspan="5" >Cart is empty</td>
                </tr>
            <% } %>
        </table>

        <p>To change the quantity, enter the new quantity and click on the Update button.</p>

        <form action="cart" method="post">
            <input type="hidden" name="action" value="shop">
            <input type="submit" value="Continue Shopping">
        </form> <br>

        <form action="#" method="post">
            <input type="submit" value="Checkout">
        </form>
    </body>
</html>
