package edu.upenn.cis573.hwk1;

import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ComparatorTest {

	String fileName1 = "try1.txt";
	String fileName2 = "try2.txt";
	String fileName3 = "try3.txt";
	String fileName4 = "try4.txt";

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testFindCommonNWords() {
		ArrayList<String> found = Comparator.findCommonNWords(fileName1,
				fileName2, 5);
		ArrayList<String> expected = new ArrayList<String>();
		expected.add("want to be a cowboy ");
		expected.add("wants to be a cowboy ");
		expected.add("doesnt want to be a ");
		assertTrue(expected.containsAll(found) && found.containsAll(expected));
	}

	@Test
	public void testCountCommonNWords() {
		assertEquals(3, Comparator.countCommonNWords(fileName1, fileName2, 5));
	}

	@Test
	public void testCompareAndGetResult() {
		HashMap<String, Integer> expected = new HashMap<String, Integer>();
		expected.put("try1 try2", 3);
		expected.put("try1 try3", 15);
		expected.put("try1 try4", 3);
		expected.put("try2 try3", 17);
		expected.put("try2 try4", 17);
		expected.put("try3 try4", 17);
		HashMap<String, Integer> actual = Comparator.compareAndGetResult(".",
				"testLog.txt", 5, 1);
		assertEquals(expected, actual);
		File testLog = new File("testLog.txt");
		testLog.delete();
	}

}
