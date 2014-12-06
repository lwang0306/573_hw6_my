package edu.upenn.cis573.hwk1;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

public class WriterTest {

	@Test
	public void testSafeCreateLogFile() throws IOException {
		File testLog = new File("testLog.txt");
		BufferedWriter bw = Writer.safeCreateLogFile(testLog);
		String expectedToWrite = "testSafeCreateLogFile";
		bw.write(expectedToWrite);
		bw.close();
		FileReader r = new FileReader(testLog);
		BufferedReader br = new BufferedReader(r);
		String wrote = br.readLine();
		br.close();
		assertEquals(expectedToWrite, wrote);
		testLog.delete();
	}

	@Test
	public void testWriteLogLine() throws IOException {
		File testLog = new File("testLog.txt");
		BufferedWriter bw = Writer.safeCreateLogFile(testLog);
		Writer.writeLogLine(bw, "try1.txt", "try2.txt", 100);
		bw.close();
		String expectedToWrite = "try1 try2 100";
		FileReader r = new FileReader(testLog);
		BufferedReader br = new BufferedReader(r);
		String wrote = br.readLine();
		br.close();
		assertEquals(expectedToWrite, wrote);
		testLog.delete();
	}

}
