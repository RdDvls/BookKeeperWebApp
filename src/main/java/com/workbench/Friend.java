package com.workbench;

import javax.persistence.*;

/**
 * Created by RdDvls on 1/10/17.
 */
@Entity
@Table(name = "friends")
public class Friend {
    @Id
    @GeneratedValue
    int friendId;

    @ManyToOne
    Reader readers;

    @Column(nullable = false)
    private String friendName;

    @Column(nullable = false, unique = true)
    private String email;

    public Friend(Reader readers, String friendName, String email) {
        this.readers = readers;
        this.friendName = friendName;
        this.email = email;
    }

    public Friend() {
    }

    public Reader getReaders() {
        return readers;
    }

    public void setReaders(Reader readers) {
        this.readers = readers;
    }

    public String getFriendName() {
        return friendName;
    }

    public void setFriendName(String friendName) {
        this.friendName = friendName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
