package project.controller;

import project.service.entity.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by HOUSE on 10.08.2016.
 */
@WebServlet(urlPatterns = "/exit")
public class ExitController extends HttpServlet{
    private static final String NICK = "nick";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute(NICK);

        if(user != null){
            session.removeAttribute(NICK);
        }

        resp.sendRedirect("/index");
    }
}
