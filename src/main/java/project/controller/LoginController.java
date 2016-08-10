package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.service.User;
import project.service.dao.PersistException;
import project.service.mysql.MySqlDaoFactory;
import project.service.mysql.MySqlUserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Created by HOUSE on 01.08.2016.
 */

@WebServlet(urlPatterns = "/log")
//@Controller
public class LoginController  extends HttpServlet{
    private static final String NAME = "name";
    private static final String NICK = "nick";
    private static final String PASSWORD = "password";
    private static final String PAGE_ERROR = "404";

    private MySqlDaoFactory factory = new MySqlDaoFactory();
    private MySqlUserDao userDao = null;



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter(NAME);
        String password = req.getParameter(PASSWORD);
        if (name != null){
            try {
                if(password != null) {
                    userDao = new MySqlUserDao(factory, factory.getContext());
                    List<User> userList = userDao.getAll();
                    HttpSession session = req.getSession(true);
                    boolean isFlag = false;
                    for (User u : userList) {
                        if (u.getFirstName().equalsIgnoreCase(name) && u.getPassword().equals(password)) {
                            session.setAttribute(NICK, u);
                            isFlag = true;
                            break;
                        }
                    }
                    if (isFlag) {
                        String newLocation = "/home";
                        resp.sendRedirect(newLocation);
                    }else {
                        resp.sendRedirect(PAGE_ERROR);
                    }
                }else {
                    resp.sendRedirect(PAGE_ERROR);
                }
            } catch (PersistException e) {
                e.printStackTrace();
            }
        }else {
            resp.sendRedirect(PAGE_ERROR);
        }
    }

//
//    @RequestMapping(value = "/log", method = RequestMethod.POST)
//    public String log (@RequestParam String name , @RequestParam String password, Model model){
//        if (name != null && password != null){
//            try {
//                userDao = new MySqlUserDao(factory, factory.getContext());
//                List<User> userList = userDao.getAll();
//                for (User u : userList){
//                    if (u.getFirstName().equalsIgnoreCase(name) && u.getPassword().equals(password)){
//                        model.addAttribute("pers", u);
//                        break;
//                    }
//                }
//            } catch (PersistException e) {
//                e.printStackTrace();
//            }
//        }
//        return "redirect:/index";
//    }
}
