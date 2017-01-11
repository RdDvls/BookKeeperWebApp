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
    Reader reader;

    @Column(nullable = false)
    private String friendName;

    @Column(nullable = false, unique = true)
    private String email;

    public Friend(Reader reader, String friendName, String email) {
        this.reader = reader;
        this.friendName = friendName;
        this.email = email;
    }
}
