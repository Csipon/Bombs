package project.controller;

import project.service.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by HOUSE on 14.08.2016.
 */
@WebServlet("/buy")
public class BuyController extends HttpServlet{
    private static final String PRODUCT_IN_BUCKET = "productInBucket";
    private static final String BOUGHT = "boughtProducts";
    private static final String PRICE = "price";
    private static final String PRICE_BOUGHT = "priceBought";
    private static final String PAGE_ERROR = "404";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        try{
            HttpSession session = req.getSession();
            Map<Product, Integer> products = (Map<Product, Integer>) session.getAttribute(PRODUCT_IN_BUCKET);

            if(products != null){
                float price  = 0;

                for (Map.Entry<Product, Integer> map : products.entrySet()){
                    price += (map.getKey().getPrice() * map.getValue());
                }
                session.removeAttribute(PRODUCT_IN_BUCKET);
                session.removeAttribute(PRICE);
                session.setAttribute(PRICE_BOUGHT, price);
                session.setAttribute(BOUGHT, products);

                resp.sendRedirect("/do.buy");
            }else {
                resp.sendRedirect("/checkLogin");
            }
            return;
        }catch (Exception e){
            e.printStackTrace();
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}
