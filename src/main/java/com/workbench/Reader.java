package com.workbench;

import javax.persistence.*;

/**
 * Created by RdDvls on 1/4/17.
 */
@Entity
@Table(name = "readers")
public class Reader {
    @Id
    @GeneratedValue
    int readerId;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column (nullable = false, unique = true)
    private String userName;

    @Column (nullable = false)
    private String password;

    public Reader() {
    }

    public Reader(String firstName, String lastName, String userName, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
