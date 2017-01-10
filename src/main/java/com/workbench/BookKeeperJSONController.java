package com.workbench;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by RdDvls on 1/4/17.
 */
@RestController
public class BookKeeperJSONController {
    @Autowired
    BookRepository books;

    @RequestMapping (path = "/books.json", method = RequestMethod.GET)
    public List<Book> getAllBooks(HttpSession session, Model model){
        if(session.getAttribute("user") != null){
            model.addAttribute("user",session.getAttribute("user"));
        }
        Reader currentReader = (Reader)session.getAttribute("user");
        ArrayList<Book> bookList = new ArrayList<>();
        Iterable<Book> allBooks = books.findByReaders(currentReader);
        for(Book book : allBooks){
            bookList.add(book);
        }
        return bookList;
    }

    @RequestMapping (path = "/addBook.json", method = RequestMethod.POST)
    public List<Book> addBook(HttpSession session, Model model, @RequestBody Book bookItem)throws Exception{
        Reader reader = (Reader) session.getAttribute("user");
        if(reader == null){
            throw new Exception("Must be logged in as a reader first");
        }
        bookItem.readers = reader;
        books.save(bookItem);
        return getAllBooks(session,model);
    }


}
