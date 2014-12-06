package edu.upenn.cis573.hwk1;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

public class ReaderTest {

	HashMap<String, Integer> expected = new HashMap<String, Integer>();
	ArrayList<String> wordsInAFile = new ArrayList<String>();

	@Before
	public void setUp() throws Exception {

		expected.put("i want to be a ", 1);
		expected.put("want to be a cowboy ", 2);
		expected.put("to be a cowboy she ", 1);
		expected.put("be a cowboy she wants ", 1);
		expected.put("a cowboy she wants to ", 1);
		expected.put("cowboy she wants to be ", 1);
		expected.put("she wants to be a ", 1);
		expected.put("wants to be a cowboy ", 1);
		expected.put("to be a cowboy but ", 1);
		expected.put("be a cowboy but he ", 1);
		expected.put("a cowboy but he doesnt ", 1);
		expected.put("cowboy but he doesnt want ", 1);
		expected.put("but he doesnt want to ", 1);
		expected.put("he doesnt want to be ", 1);
		expected.put("doesnt want to be a ", 1);

		wordsInAFile.add("i");
		wordsInAFile.add("want");
		wordsInAFile.add("to");
		wordsInAFile.add("be");
		wordsInAFile.add("a");
		wordsInAFile.add("cowboy");
		wordsInAFile.add("she");
		wordsInAFile.add("wants");
		wordsInAFile.add("to");
		wordsInAFile.add("be");
		wordsInAFile.add("a");
		wordsInAFile.add("cowboy");
		wordsInAFile.add("but");
		wordsInAFile.add("he");
		wordsInAFile.add("doesnt");
		wordsInAFile.add("want");
		wordsInAFile.add("to");
		wordsInAFile.add("be");
		wordsInAFile.add("a");
		wordsInAFile.add("cowboy");
	}

	@Test
	public void testReadAFile() {
		HashMap<String, Integer> map = Reader.readAFile("try1.txt", 5);
		assertEquals(expected, map);
	}

	@Test
	public void testCompactNWords() {
		assertEquals(expected, Reader.compactNWords(wordsInAFile, 5));
	}

	@Test
	public void testReadFilesInFolder() {
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add("try1.txt");
		fileNames.add("try2.txt");
		fileNames.add("try3.txt");
		fileNames.add("try4.txt");
		assertEquals(fileNames, Reader.readFilesInFolder("."));
	}

}
