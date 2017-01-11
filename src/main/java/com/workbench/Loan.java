package com.workbench;

import javax.persistence.*;

/**
 * Created by RdDvls on 1/10/17.
 */
@Entity
@Table(name = "loans")
public class Loan {
    @Id
    @GeneratedValue
    int loanID;

    @OneToOne
    BookItem books;

    @OneToOne
    Friend friend;

    @Column
    boolean loanedIn;

    @Column
    boolean loanedOut;

    public Loan() {
    }

    public Loan(BookItem books, Friend friend, boolean loanedIn, boolean loanedOut) {

        this.books = books;
        this.friend = friend;
        this.loanedIn = loanedIn;
        this.loanedOut = loanedOut;
    }

    public BookItem getBooks() {
        return books;
    }

    public void setBooks(BookItem books) {
        this.books = books;
    }

    public Friend getFriend() {
        return friend;
    }

    public void setFriend(Friend friend) {
        this.friend = friend;
    }

    public boolean isLoanedIn() {
        return loanedIn;
    }

    public void setLoanedIn(boolean loanedIn) {
        this.loanedIn = loanedIn;
    }

    public boolean isLoanedOut() {
        return loanedOut;
    }

    public void setLoanedOut(boolean loanedOut) {
        this.loanedOut = loanedOut;
    }
}
