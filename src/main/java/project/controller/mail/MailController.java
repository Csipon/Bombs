package project.controller.mail;


import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.model.Draft;
import com.google.api.services.gmail.model.Message;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import project.service.mail.GmailQuickstart;
import project.service.mail.SendEmail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServlet;
import java.io.IOException;


/**
 * Created by HOUSE on 27.07.2016.
 */
@Controller
public class MailController  extends HttpServlet {
    final static Logger logger = Logger.getLogger(MailController.class);

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String doSend(@RequestParam String to, @RequestParam String subject, @RequestParam String body) {
        logger.info("->Request Param = " +to + "| " + subject  + " | "  + body);
        try {
            MimeMessage mimeMessage
                    = SendEmail.createEmail(to, "me", subject, body);
            logger.info("->MimeMessage = " + mimeMessage.getReplyTo() + "| " + mimeMessage.getFrom() + "| "
                    + mimeMessage.getSubject()   + " | "  + mimeMessage.getMessageID());

            Gmail service
                    = GmailQuickstart.getGmailService();
            logger.info(service.getApplicationName());

            Message message
                    = SendEmail.sendMessage(service, "oxeygenoxeygen@gmail.com", mimeMessage);
            logger.info(message.getSnippet());
        } catch (MessagingException | IOException e) {
            logger.error("->Exeption someting wrong!!! ", e);
            e.printStackTrace();
        }

        return "mail/send";
    }
}
