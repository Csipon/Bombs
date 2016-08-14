package project.service.entity;

import project.service.dao.Identified;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by HOUSE on 12.07.2016.
 */
public class User implements Identified<Integer> {
    private Integer id = null;
    @NotNull
    @Size(min = 2, max = 16)
    private String firstName, lastName;
    @NotNull
    @Size(min = 6, max = 16)
    private String password;
    private String about;


    @Size(min = 2, max = 10)
    private String nick;
    private String email;

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }


}
