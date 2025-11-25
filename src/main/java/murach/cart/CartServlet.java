package murach.cart;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.annotation.WebServlet;

import murach.business.Cart;
import murach.business.LineItem;
import murach.business.Product;
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, 
                          HttpServletResponse response) 
                          throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if (action == null) {
            action = "cart"; 
        }
        
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
        }
        
        String url = "/index.jsp";
        
        if (action.equals("shop")) {
            url = "/index.jsp";
        } 
        else if (action.equals("cart")) {
            url = "/cart.jsp";
        }
        else if (action.equals("add")) {
            String productCode = request.getParameter("productCode");
           
            Product product = new Product();
            product.setCode(productCode);
            
            // Gan data dua tren productCode duoc gui tu index.jsp  
            if (productCode.equals("8601")) {
                product.setDescription("86 (the band) - True Life Songs and Pictures");
                product.setPrice(14.95);
            } else if (productCode.equals("pf01")) {
                product.setDescription("Paddlefoot - The first CD");
                product.setPrice(12.95);
            } else if (productCode.equals("pf02")) {
                product.setDescription("Paddlefoot - The second CD");
                product.setPrice(14.95);
            } else if (productCode.equals("jr01")) {
                product.setDescription("Joe Rut - Genuine Wood Grained Finish");
                product.setPrice(14.95);
            } else {
                product.setDescription("Unknown Product");
                product.setPrice(0.0);
            }
            // Them vao     
            LineItem lineItem = new LineItem(product, 1);
            cart.addItem(lineItem);

            session.setAttribute("cart", cart);
            url = "/cart.jsp";
        }
        else if (action.equals("update")) {
            String productCode = request.getParameter("productCode");
            String quantityString = request.getParameter("quantity");
            
            int quantity;
            try {
                quantity = Integer.parseInt(quantityString);
                if (quantity < 0) quantity = 1;
            } catch (NumberFormatException e) {
                quantity = 1;
            }
            
            // Cap nhat so luong
            for (LineItem item : cart.getItems()) {
                if (item.getProduct().getCode().equals(productCode)) {
                    if (quantity > 0) {
                        item.setQuantity(quantity);
                    } else {
                        cart.removeItem(item);
                    }
                    break;
                }
            }
            session.setAttribute("cart", cart);
            url = "/cart.jsp";
        }
        else if (action.equals("remove")) {
            String productCode = request.getParameter("productCode");
            
            // Khi xóa, Cart.java chỉ cần biết Product Code nên ta tạo đối tượng tạm
            Product product = new Product();
            product.setCode(productCode); 
            LineItem lineItem = new LineItem(product, 0); 
            
            cart.removeItem(lineItem);
            
            session.setAttribute("cart", cart);
            url = "/cart.jsp";
        }
        else if (action.equals("checkout")) {
            url = "";
        }
        
        getServletContext()
            .getRequestDispatcher(url)
            .forward(request, response);
    }
    
    @Override
    protected void doGet(HttpServletRequest request, 
                         HttpServletResponse response) 
                         throws ServletException, IOException {
        doPost(request, response);
    } 
}
