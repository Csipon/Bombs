package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.service.dao.PersistException;
import project.service.mysql.MySqlDaoFactory;
import project.service.mysql.MySqlProductDao;
import project.service.mysql.WrapMySqlProductDao;

/**
 * Created by HOUSE on 30.07.2016.
 */
@Controller
public class ProductController {
    private MySqlDaoFactory factory = new MySqlDaoFactory();
    private WrapMySqlProductDao productDao;

    @Transactional
    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String getAll(Model model){
        try {
            if (productDao == null){productDao = new WrapMySqlProductDao(factory, factory.getContext());}
            model.addAttribute("productList", productDao.getAll());
        } catch (PersistException e) {
            e.printStackTrace();
        }
        return "product/products";
    }

    @Transactional
    @RequestMapping(value = "/searchProduct", method = RequestMethod.GET)
    public String information(@RequestParam Integer id, Model model){
        try{
            if (productDao == null){productDao = new WrapMySqlProductDao(factory, factory.getContext());}
            model.addAttribute("product", productDao.getByPK(id));
        }catch (PersistException e){
            e.getMessage();
        }
        return "product/product";
    }

}
