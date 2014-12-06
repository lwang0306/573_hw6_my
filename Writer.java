package edu.upenn.cis573.hwk1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Writer {

	/**
	 * Write a line in the log file.
	 * 
	 * @param writer
	 *            The BufferedWriter to write this log file.
	 * @param fileName1
	 *            The name of the 1st file.
	 * @param fileName2
	 *            The name of the 2nd file.
	 * @param commons
	 *            The number of common N-word in these two files.
	 */
	static void writeLogLine(BufferedWriter writer, String fileName1,
			String fileName2, int commons) {
		try {
			String content = (fileName1 + " " + fileName2 + " " + commons)
					.replaceAll(".txt", "");
			writer.write(content + "\n");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
	}

	/**
	 * Safely create a log file.
	 * 
	 * @param logFile
	 *            The absolute path of the log file.
	 * @return A BufferedWriter assigned to the log file, if successfully
	 *         created.
	 */
	static BufferedWriter safeCreateLogFile(File logFile) {
		BufferedWriter writer = null;
		// create a log file only if the directory exists and is writable
		try {
			logFile.createNewFile();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		// assign a BufferedWriter to the log file
		try {
			writer = new BufferedWriter(new FileWriter(logFile));
		} catch (IOException e1) {
			System.out.println(e1.getMessage());
			System.exit(0);
		}
		return writer;
	}

}
