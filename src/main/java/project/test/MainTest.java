package project.test;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by HOUSE on 27.07.2016.
 */
public class MainTest {
    private static String mail_to = "oxeygenoxeygen@gmail.com";
    private static String gmailUsername = "csiponworkgroup@gmail.com";//using gmail server
    private static String gmailPassword = "wsgf1996";

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(gmailUsername, gmailPassword);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(gmailUsername));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mail_to));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler, \n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
