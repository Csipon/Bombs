package project.service;

import project.service.dao.Identified;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by HOUSE on 12.07.2016.
 */
public class User implements Identified<Integer> {
    private Integer id = null;
    @NotNull
    @Size(min = 2, max = 30)
    private String firstName, lastName;
    @NotNull
    @Size(min = 6, max = 16)
    private String password;
    @NotNull
    @Size(min = 2, max = 55)
    private String hobby;

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

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", password='" + password + '\'' +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}
