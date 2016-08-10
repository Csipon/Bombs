package project.test;

import javax.mail.*;

/**
 * Created by HOUSE on 29.07.2016.
 */
public class Email_Autherticator extends Authenticator {
    String username = "xxxx@gmail";
    String password = "xxxxx";

    public Email_Autherticator() {
        super();
    }
    public Email_Autherticator(String user,String pwd){
        super();
        username = user;
        password = pwd;
    }

    public PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication(username,password);
    }
}
