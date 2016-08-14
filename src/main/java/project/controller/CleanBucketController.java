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
 * Created by HOUSE on 01.08.2016.
 */
@WebServlet(urlPatterns = "/cleanBucket")
public class CleanBucketController  extends HttpServlet{
    private static final String PARAM_ID = "id";
    private static final String PRODUCT_IN_BUCKET = "productInBucket";
    private static final String PAGE_ERROR = "404";
    private static final String PRICE = "price";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if (idStr != null){
            Integer id = Integer.parseInt(idStr);
            HttpSession session = req.getSession(true);
            Map<Product, Integer> map =(Map<Product, Integer>) session.getAttribute(PRODUCT_IN_BUCKET);
            float price = 0;
            if (map != null){
                Product temp = null;
                for (Map.Entry<Product, Integer> m : map.entrySet()){
                    if (m.getKey().getId() == id){
                        temp = m.getKey();
                        break;
                    }
                }
                if (temp != null){
                    int count = map.get(temp) - 1;
                    if (count > 0) {
                        map.put(temp, count);
                    }else {
                        map.remove(temp);
                    }
                    session.setAttribute(PRODUCT_IN_BUCKET, map);
                    for (Map.Entry<Product, Integer> maps : map.entrySet()){
                        price += (maps.getKey().getPrice() * maps.getValue());
                    }
                    if(price > 0) {
                        session.setAttribute(PRICE, price);
                    }else {
                        session.removeAttribute(PRICE);
                    }
                }
            }
            String newLocation = "./searchProduct?id=" + id;
            resp.sendRedirect(newLocation);
            return;
        }
        resp.sendRedirect(PAGE_ERROR);
    }
}
