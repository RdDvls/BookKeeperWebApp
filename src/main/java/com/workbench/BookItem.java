package com.workbench;

import javax.persistence.*;

/**
 * Created by RdDvls on 1/4/17.
 */
@Entity
@Table(name="books")
public class BookItem {
    @Id
    @GeneratedValue
    int Bookid;

    @ManyToOne
    Reader readers;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String title;


    public BookItem() {
    }

    public BookItem(Reader readers, String author, String title) {
        this.readers = readers;
        this.author = author;
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
