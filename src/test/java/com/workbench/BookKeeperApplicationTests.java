package com.workbench;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookKeeperApplicationTests {
	@Autowired
	ReaderRepository readers;

	@Autowired
	FriendRepository friends;

	@Autowired
	BookRepository books;

	@Autowired
	LoanRepository loans;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testInsertUser() {
		try {
			Reader testReader = new Reader("TestFirstName", "TestLastName", "TestUsername", "TestPassword");
			readers.save(testReader);
			assertEquals(1, readers.count());
			assertEquals("TestUsername", readers.findByUserName("TestUsername").getUserName());

		}finally{
			readers.deleteAll();
			assertEquals(0,readers.count());
		}
	}

	@Test
	public void testRetrieveReader(){
		Reader testReader = new Reader();
		testReader.setFirstName("TestFirstName");
		testReader.setLastName("TestLastName");
		testReader.setUserName("TestUsername");
		testReader.setPassword("TestPassword");
		try{
			testReader = readers.save(testReader);
			assertEquals(1, readers.count());
			Reader retrievedReader = readers.findByUserName(testReader.getUserName());
			assertEquals(testReader.getFirstName(),retrievedReader.getFirstName());
		}finally {
			readers.deleteAll();
			assertEquals(0, readers.count());
		}
	}

	@Test
	public void testRetrieveFriendList(){
		Reader testReader = new Reader();
		testReader.setFirstName("TestFirstName");
		testReader.setLastName("TestLastName");
		testReader.setUserName("TestUserName");
		testReader.setPassword("TestPassword");
		Friend testFriend1 = new Friend();
		testFriend1.setFriendName("TestFriendName1");
		testFriend1.setEmail("TestEmail1@TestEmail.com");
		testFriend1.setReaders(testReader);
		Friend testFriend2 = new Friend();
		testFriend2.setFriendName("TestFriendName2");
		testFriend2.setEmail("TestEmail2@TestEmail.com");
		testFriend2.setReaders(testReader);
		try{
			testReader = readers.save(testReader);
			testFriend1 = friends.save(testFriend1);
			testFriend2 = friends.save(testFriend2);
			assertEquals(1, readers.count());
			assertEquals(2,friends.count());
			ArrayList<Friend> friendItemList = new ArrayList<>();
			Iterable<Friend> allFriends = friends.findByReaders(testReader);
			for(Friend friend : allFriends){
				friendItemList.add(friend);
			}
			assertNotNull(friendItemList);
			assertEquals(2, friendItemList.size());
			System.out.println(friendItemList.get(0).getFriendName());
			System.out.println(friendItemList.get(1).getFriendName());

		}finally {
			friends.deleteAll();
			readers.deleteAll();
			assertEquals(0,readers.count());
			assertEquals(0,friends.count());
		}

	}
	@Test
	public void testCreateLoan(){
		Reader testReader = new Reader();
		testReader.setFirstName("TestReaderFirstName");
		testReader.setLastName("TestReaderLastName");
		testReader.setUserName("TestReaderUsername");
		testReader.setPassword("TestReaderPassword");
		Friend testFriend = new Friend();
		testFriend.setFriendName("TestFriend");
		testFriend.setEmail("TestFriendEmail");
		testFriend.setReaders(testReader);
		BookItem testBook = new BookItem();
		testBook.setReaders(testReader);
		testBook.setAuthor("TestBookAuthor");
		testBook.setTitle("TestTitle");
		try{
			testReader = readers.save(testReader);
			testFriend = friends.save(testFriend);
			testBook = books.save(testBook);
			assertEquals(1, readers.count());
			assertEquals(1, friends.count());
			Loan testLoan = new Loan();
			testLoan.setBooks(testBook);
			testLoan.setFriend(testFriend);
			testLoan.setLoanedIn(true);
			testLoan.setLoanedOut(false);
			loans.save(testLoan);
			assertEquals(1, loans.count());
		}finally {
			loans.deleteAll();
			friends.deleteAll();
			books.deleteAll();
			readers.deleteAll();
			assertEquals(0, loans.count());
			assertEquals(0, friends.count());
			assertEquals(0, books.count());
			assertEquals(0, readers.count());
		}

	}

	@Test
	public void testRetrieveLoans(){
		Reader testReader = new Reader();
		testReader.setFirstName("TestReaderFirstName");
		testReader.setLastName("TestReaderLastName");
		testReader.setUserName("TestReaderUsername");
		testReader.setPassword("TestReaderPassword");
		Friend testFriend1 = new Friend();
		testFriend1.setFriendName("TestFriend1");
		testFriend1.setEmail("TestFriendEmail1");
		testFriend1.setReaders(testReader);
		Friend testFriend2 = new Friend();
		testFriend2.setFriendName("TestFriend2");
		testFriend2.setEmail("TestFriendEmail2");
		testFriend2.setReaders(testReader);
		BookItem testBook = new BookItem();
		testBook.setReaders(testReader);
		testBook.setAuthor("TestBookAuthor1");
		testBook.setTitle("TestTitle1");
		BookItem testBook2 = new BookItem();
		testBook2.setReaders(testReader);
		testBook2.setAuthor("TestBookAuthor2");
		testBook2.setTitle("TestTitle2");
		try{
			testReader = readers.save(testReader);
			testFriend1 = friends.save(testFriend1);
			testFriend2 = friends.save(testFriend2);
			testBook = books.save(testBook);
			testBook2 = books.save(testBook2);
			assertEquals(1, readers.count());
			assertEquals(2, friends.count());
			Loan testLoan1 = new Loan();
			testLoan1.setBooks(testBook);
			testLoan1.setFriend(testFriend1);
			testLoan1.setLoanedIn(true);
			testLoan1.setLoanedOut(false);
			Loan testLoan2 = new Loan();
			testLoan2.setBooks(testBook2);
			testLoan2.setFriend(testFriend2);
			testLoan2.setLoanedIn(false);
			testLoan2.setLoanedOut(false);
			loans.save(testLoan1);
			loans.save(testLoan2);
			assertEquals(2, loans.count());
			ArrayList<Loan> loanItemList = new ArrayList<>();
			Iterable<Loan> allLoans = loans.findAll();
			for(Loan loan : allLoans){
				loanItemList.add(loan);
			}
			assertNotNull(loanItemList);
			System.out.println(loanItemList.get(0).getBooks().getReaders().getUserName());
			System.out.println(loanItemList.get(1).getFriend().getFriendName());
		}finally {
			loans.deleteAll();
			friends.deleteAll();
			books.deleteAll();
			readers.deleteAll();
			assertEquals(0, loans.count());
			assertEquals(0, friends.count());
			assertEquals(0, books.count());
			assertEquals(0, readers.count());
		}
	}

}
