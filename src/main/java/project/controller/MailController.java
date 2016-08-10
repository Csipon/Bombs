package project.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.service.mail.MailThread;
import project.service.mail.SessionCreator;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by HOUSE on 27.07.2016.
 */
@Controller
public class MailController  extends HttpServlet{

    @RequestMapping(value = "/to.mail", method = RequestMethod.GET)
    public String toMail(){
        return "mail/sendmail";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String doSend(@RequestParam String to, @RequestParam String subject, @RequestParam String body)
    {
        FileInputStream inputStream = null;
        Properties properties = null;
        try {
            inputStream = new FileInputStream("C:\\Users\\HOUSE\\IdeaProjects\\Bombs\\src\\main\\resources\\mail.properties");
            properties = new Properties();
            properties.load(inputStream);
            MailThread mailOperator = new MailThread(to, subject, body, properties);
            mailOperator.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "mail/send";
    }
}
