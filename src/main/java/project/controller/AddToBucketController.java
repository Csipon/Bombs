package project.controller;

import project.service.entity.Product;
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
    private static final String PRICE = "price";

    private MySqlDaoFactory factory = new MySqlDaoFactory();
    private MySqlProductDao productDao;
//    private MySqlUserDao userDao;
//    private MySqlBucketDao bucketDao;

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
                float price = 0;
                HttpSession session = req.getSession(true);
                Map<Product, Integer> oldProduct = (Map<Product, Integer>) session.getAttribute(PRODUCT_IN_BUCKET);
                if (oldProduct == null){
                    session.setAttribute(PRODUCT_IN_BUCKET, Collections.singletonMap(product, 1));
                    session.setAttribute(PRICE, product.getPrice());
                }else {
                    Map<Product, Integer> newBucket = new LinkedHashMap<>(oldProduct);
                    if (!newBucket.containsKey(product)){
                        newBucket.put(product, 1);
                    }else {
                        newBucket.put(product, newBucket.get(product) + 1);
                    }
                    session.setAttribute(PRODUCT_IN_BUCKET, newBucket);
                    for (Map.Entry<Product, Integer> map : newBucket.entrySet()){
                        price += (map.getKey().getPrice() * map.getValue());
                    }
                    session.setAttribute(PRICE, price);
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


//    =================================================================================================

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String idStr = req.getParameter(PARAM_ID);
//        if(idStr != null){
//            try{
//                if (productDao == null) {
//                    productDao = new MySqlProductDao(factory, factory.getContext());
//                    userDao = new MySqlUserDao(factory, factory.getContext());
//                    bucketDao = new MySqlBucketDao(factory, factory.getContext());
//                }
//                Integer id = Integer.parseInt(idStr);
//                HttpSession session = req.getSession(true);
//                User user = (User) session.getAttribute("nick");
//
//                if (user.getBucket().getId() <= 1){
//                    Bucket bucket = bucketDao.create();
//                    bucket.setProducts(id+"="+1);
//                    user.setBucket(bucket);
//                    userDao.update(user);
//                }else {
//                    Map<Integer, Integer> map = parseToMap(user.getBucket());
//                    if (!map.containsKey(id)){
//                        map.put(id, 1);
//                    }else {
//                        map.put(id, map.get(id) + 1);
//                    }
//                    user.setBucket(parseToBucket(map));
//                    userDao.update(user);
//                }
//                String newLocation = "./searchProduct?id=" + id;
//                resp.sendRedirect(newLocation);
//                return;
//            } catch (PersistException e) {
//                e.printStackTrace();
//            }
//            resp.sendRedirect(PAGE_ERROR);
//        }
//    }
//
//    private Map<Integer, Integer> parseToMap(Bucket bucket){
//        Map<Integer, Integer> map = new LinkedHashMap<>();
//        if (bucket.getProducts() != null && !bucket.getProducts().equals("")) {
//            String[] products = bucket.getProducts().split(",");
//            if (products)
//        }
//
//        return map;
//    }
}
