package project.service.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by HOUSE on 27.07.2016.
 */
public class MailThread extends Thread{
    private MimeMessage message;
    private String sendToEmail;
    private String mailSubject;
    private String mailText;
    private Properties properties;

    public MailThread(String sendToEmail, String mailSubject, String mailText, Properties properties) {
        this.sendToEmail = sendToEmail;
        this.mailSubject = mailSubject;
        this.mailText = mailText;
        this.properties = properties;
    }

    private void init() {
        // объект почтовой сессии
        Session mailSession = new SessionCreator(properties).createSession();
//        mailSession.setDebug(true);
        // создание объекта почтового сообщения
        message = new MimeMessage(mailSession);
        try {
            message.setSubject(mailSubject);
            message.setContent(mailText, "text/html");
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(sendToEmail));
            message.saveChanges();
        } catch (AddressException e) {
            System.err.print("Incorrect address:" + sendToEmail + " " + e);
        } catch (MessagingException e) {
            System.err.print("Error generating message" + e);
        }
    }

    public void run() {
        init();
        try {
            // отправка почтового сообщения
            Transport.send(message, message.getAllRecipients());
        } catch (MessagingException e) {
            System.err.print("Error sending a message " + e);
            e.printStackTrace();
            // in log file
        }
    }
}
