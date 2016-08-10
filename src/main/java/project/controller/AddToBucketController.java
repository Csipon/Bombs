package project.controller;

import project.service.Product;
import project.service.dao.PersistException;
import project.service.mysql.MySqlDaoFactory;
import project.service.mysql.MySqlProductDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by HOUSE on 30.07.2016.
 */
@WebServlet(urlPatterns = "/addToBucket")
public class AddToBucketController extends HttpServlet{
    private static final String PARAM_ID = "id";
    private static final String PAGE_ERROR = "404";
    private static final String PRODUCT_IN_BUCKET = "productInBucket";

    private MySqlDaoFactory factory = new MySqlDaoFactory();
    private MySqlProductDao productDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter(PARAM_ID);
        if(idStr != null){
            try{
                if (productDao == null) {
                    productDao = new MySqlProductDao(factory, factory.getContext());
                }
                Integer id = Integer.parseInt(idStr);
                Product product = productDao.getByPK(id);

                HttpSession session = req.getSession(true);
                Map<Product, Integer> oldProduct = (Map<Product, Integer>) session.getAttribute(PRODUCT_IN_BUCKET);
                if (oldProduct == null){
                    session.setAttribute(PRODUCT_IN_BUCKET, Collections.singletonMap(product, 1));
                }else {
                    Map<Product, Integer> newBucket = new LinkedHashMap<>(oldProduct);
                    if (!newBucket.containsKey(product)){
                        newBucket.put(product, 1);
                    }else {
                        newBucket.put(product, newBucket.get(product) + 1);
                    }
                    session.setAttribute(PRODUCT_IN_BUCKET, newBucket);
                }
                String newLocation = "./searchProduct?id=" + id;
                resp.sendRedirect(newLocation);
                return;
            } catch (PersistException e) {
                e.printStackTrace();
            }
            resp.sendRedirect(PAGE_ERROR);
        }
    }
}
