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

    @Autowired
    FriendRepository friends;

    @RequestMapping (path = "/books.json", method = RequestMethod.GET)
    public List<BookItem> getAllBooks(HttpSession session, Model model){
        if(session.getAttribute("user") != null){
            model.addAttribute("user",session.getAttribute("user"));
        }
        Reader currentReader = (Reader)session.getAttribute("user");
        ArrayList<BookItem> bookItemList = new ArrayList<>();
        Iterable<BookItem> allBooks = books.findByReaders(currentReader);
        for(BookItem bookItem : allBooks){
            bookItemList.add(bookItem);
        }
        return bookItemList;
    }

    @RequestMapping (path = "/addBook.json", method = RequestMethod.POST)
    public List<BookItem> addBook(HttpSession session, Model model, @RequestBody BookItem bookItem)throws Exception{
        Reader reader = (Reader) session.getAttribute("user");
        if(reader == null){
            throw new Exception("Must be logged in as a readers first");
        }
        bookItem.readers = reader;
        books.save(bookItem);
        return getAllBooks(session,model);
    }

    @RequestMapping (path = "/friends.json", method = RequestMethod.GET)
    public List <Friend> getAllFriends(HttpSession session, Model model){
        if(session.getAttribute("user") != null){
            model.addAttribute("user",session.getAttribute("user"));
        }
        Reader currentReader = (Reader)session.getAttribute("user");
        ArrayList<Friend> friendItemList = new ArrayList<>();
        Iterable<Friend> allFriends = friends.findByReaders(currentReader);
        for(Friend friend : allFriends){
            friendItemList.add(friend);
        }
        return friendItemList;
    }

    @RequestMapping (path = "/addFriend.json", method = RequestMethod.POST)
    public List <Friend> addFriend(HttpSession session, Model model, @RequestBody Friend friendItem)throws Exception{
        Reader reader = (Reader) session.getAttribute("user");
        if(reader == null){
            throw new Exception("Sign in as a readers first!");
        }
        friendItem.readers = reader;
        friends.save(friendItem);
        return getAllFriends(session,model);
    }



}
