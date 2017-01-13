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

}
