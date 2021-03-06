package project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.service.entity.User;
import project.service.dao.PersistException;
import project.service.mysql.MySqlDaoFactory;
import project.service.mysql.MySqlUserDao;
import javax.validation.Valid;

@Controller
public class UserController {

    private MySqlDaoFactory factory = new MySqlDaoFactory();

    private MySqlUserDao userDao;

    @Transactional
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@Valid User user, Model model, Errors errors)
    {
        User u;
        if (errors.hasErrors()) {
            return "register/register";
        }
        try {
            if (userDao == null){userDao = new MySqlUserDao(factory, factory.getContext());}
            u = userDao.create();

            u.setNick(user.getNick());
            u.setFirstName(user.getFirstName());
            u.setLastName(user.getLastName());
            u.setPassword(user.getPassword());
            u.setAbout(user.getAbout());
            u.setEmail(user.getEmail());
            userDao.update(u);

            model.addAttribute("user", u);
        } catch (PersistException e) {
            e.getMessage();
        }
        return "register/successful";
    }


    @Transactional
    @RequestMapping(value = "/id", method = RequestMethod.GET)
    public String information(@RequestParam Integer id, Model model){

        try{
            if (userDao == null){userDao = new MySqlUserDao(factory, factory.getContext());}
            model.addAttribute("user", userDao.getByPK(id));
        }catch (PersistException e){
            e.getMessage();
        }
        return "register/successful";
    }


    @Transactional
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public String getAll(Model model){

        try {
            if (userDao == null){userDao = new MySqlUserDao(factory, factory.getContext());}
            model.addAttribute("userList", userDao.getAll());
        } catch (PersistException e) {
            e.printStackTrace();
        }

        return "user/users";
    }


    @Transactional
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public String delete(@RequestParam Integer id, @RequestParam String password, Model model){
        try{
            if(userDao == null){
                userDao = new MySqlUserDao(factory, factory.getContext());
            }
            User user = userDao.getByPK(id);
            if (user.getPassword().equals(password)) {
                userDao.delete(user);
                model.addAttribute("user", user);
            }
        }catch (PersistException e){
            e.getMessage();
        }
        return "redirect:/getAll";
    }
}