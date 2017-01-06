package com.workbench;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BookKeeperApplicationTests {
	@Autowired
	ReaderRepository readers;

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

}
