package project.service.mail;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.Properties;

/**
 * Created by HOUSE on 27.07.2016.
 */
public class SessionCreator {

    private String smtpHost;
    private String smtpPort;
    private String userName;
    private String userPassword;
    private Properties sessionProperties;

    public SessionCreator(Properties configProperties) {
        smtpHost = configProperties.getProperty("mail.smtp.host");
        smtpPort = configProperties.getProperty("mail.smtp.port");
        userName = configProperties.getProperty("mail.user.name");
        userPassword = configProperties.getProperty("mail.user.password");

        sessionProperties = System.getProperties();
        sessionProperties.setProperty("mail.transport.protocol", "smtp");
        sessionProperties.setProperty("mail.smtp.host", smtpHost);
        sessionProperties.setProperty("mail.smtp.auth", "true");
        sessionProperties.put("mail.smtp.port", smtpPort);
        sessionProperties.put("mail.smtp.starttls.enable", "true");
        sessionProperties.put("mail.smtp.socketFactory.port", smtpPort);
        sessionProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        sessionProperties.put("mail.smtp.socketFactory.fallback", "true");
    }

    public Session createSession() {
        return Session.getDefaultInstance(sessionProperties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(userName, userPassword);
                    }
                });
    }
}
