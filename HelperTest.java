package edu.upenn.cis573.hwk1;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

public class HelperTest {

	@Test
	public void testRelaToAbsPath() {
		assertEquals("/dir1/dir2/file1.txt",
				Helper.relaToAbsPath("/dir1/dir2", "file1.txt"));
		assertEquals("/dir1/file1.txt",
				Helper.relaToAbsPath("/dir1/", "file1.txt"));
	}

	@Test
	public void testPrettyPrintMap() {
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		map.put("key1", 1);
		map.put("key3", 3);
		map.put("key2", 2);
		String expected = "key3 3\nkey2 2\nkey1 1\n";
		assertEquals(expected, Helper.prettyPrintMap(map));
	}

	@Test
	public void testGetPositiveNum() {
		assertEquals(4, Helper.getPositiveNum("4"));
		assertEquals(100, Helper.getPositiveNum("100"));
	}

}
