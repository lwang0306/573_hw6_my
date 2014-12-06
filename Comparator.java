package edu.upenn.cis573.hwk1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class Comparator {

	/**
	 * Find common N-word between two files.
	 * 
	 * @param fileName1
	 *            The 1st file to compare.
	 * @param fileName2
	 *            The 2nd file to compare.
	 * @param N
	 *            The N of N-word.
	 * @return An ArrayList of all common N-word.
	 */
	static ArrayList<String> findCommonNWords(String fileName1,
			String fileName2, int N) {
		// initialize an ArrayList to hold common N-word
		ArrayList<String> commonNWords = new ArrayList<String>();
		// read two files into two HashMaps
		HashMap<String, Integer> wordMap1 = Reader.readAFile(fileName1, N);
		HashMap<String, Integer> wordMap2 = Reader.readAFile(fileName2, N);
		// obtain key set
		Set<String> keySet1 = wordMap1.keySet();
		Set<String> keySet2 = wordMap2.keySet();
		// find common N-word and add to ArrayList
		for (String key : keySet1) {
			if (keySet2.contains(key)) {
				commonNWords.add(key);
			}
		}
		return commonNWords;
	}

	/**
	 * Count common N-word between two files.
	 * 
	 * @param fileName1
	 *            The 1st file to compare.
	 * @param fileName2
	 *            The 2nd file to compare.
	 * @param N
	 *            The N of N-word.
	 * @return The number of common N-word.
	 */
	static int countCommonNWords(String fileName1, String fileName2, int N) {
		return findCommonNWords(fileName1, fileName2, N).size();
	}

	/**
	 * The method to count common N-word between each pair of files in a folder,
	 * write log file and give result over threshold.
	 * 
	 * @param folderPath
	 *            The directory of all text files.
	 * @param logFileName
	 *            The absolute path of log file.
	 * @param N
	 *            The N of N-word.
	 * @param T
	 *            The threshold over which to show the result.
	 * @return A HashMap of pairs of files whose number of common N-word is over
	 *         the threshold.
	 */
	static HashMap<String, Integer> compareAndGetResult(String folderPath,
			String logFileName, int N, int T) {
		// initialize a HashMap with key of pair of files and value of number of
		// common N-word
		HashMap<String, Integer> result = new HashMap<String, Integer>();
		// delete log file if it exists
		File logFile = new File(logFileName);
		if (logFile.exists()) {
			logFile.delete();
		}
		// read all file names from the folder
		ArrayList<String> listOfFileNames = Reader
				.readFilesInFolder(folderPath);
		// assign a BufferedWriter to the log file
		BufferedWriter writer = Writer.safeCreateLogFile(logFile);
		// start to compare each pair of files
		if (listOfFileNames.size() < 2) {
			System.out
					.println("Less than two files in this directory, cannot compare.");
			System.exit(0);
		} else {
			String fileName1 = null;
			String fileName2 = null;
			for (int i = 0; i < (listOfFileNames.size() - 1); i++) {
				fileName1 = listOfFileNames.get(i);
				for (int j = 1; i + j < listOfFileNames.size(); j++) {
					fileName2 = listOfFileNames.get(i + j);
					int commons = Comparator.countCommonNWords(
							Helper.relaToAbsPath(folderPath, fileName1),
							Helper.relaToAbsPath(folderPath, fileName2), N);
					// log the result of the pair of files
					Writer.writeLogLine(writer, fileName1, fileName2, commons);
					// save result over threshold into the HashMap
					if (commons >= T) {
						String key = (fileName1 + " " + fileName2).replaceAll(
								".txt", "");
						result.put(key, commons);
					}
				}

			}
		}
		// close the log file
		try {
			writer.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		return result;
	}

}
