package edu.upenn.cis573.hwk1;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

public class Helper {

	/**
	 * Add directory before a file name.
	 * 
	 * @param folderPath
	 *            The directory to be added.
	 * @param fileName
	 *            The file name to be added.
	 * @return The absolute path of the file.
	 */
	static String relaToAbsPath(String folderPath, String fileName) {
		if (folderPath.endsWith("/")) {
			return (folderPath + fileName);
		} else {
			return (folderPath + "/" + fileName);
		}
	}

	/**
	 * Pretty print the result in a HashMap in a form of key1 value1 key2 value2
	 * key3 value3
	 * 
	 * @param map
	 *            The HashMap to be printed.
	 * @return A pretty String to print.
	 */
	static String prettyPrintMap(HashMap<String, Integer> map) {
		// obtain an ArrayList of sorted values from large to small
		ArrayList<Integer> allValues = new ArrayList<Integer>(map.values());
		Collections.sort(allValues);
		Collections.reverse(allValues);
		ArrayList<Integer> sortedValues = new ArrayList<Integer>();
		for (Integer value : allValues) {
			if (!sortedValues.contains(value)) {
				sortedValues.add(value);
			}
		}
		// build a pretty String to print
		StringBuilder sb = new StringBuilder();
		for (Integer value : sortedValues) {
			Iterator<Entry<String, Integer>> iter = map.entrySet().iterator();
			while (iter.hasNext()) {
				Entry<String, Integer> entry = iter.next();
				if (entry.getValue() == value) {
					sb.append(entry.getKey());
					sb.append(" ");
					sb.append(entry.getValue());
					sb.append("\n");
				}
			}
		}
		return sb.toString();
	}

	/**
	 * Parse a String into int and check if it is a positive int.
	 * 
	 * @param str
	 *            The String to be parse.
	 * @return A positive int if the String is a String of positive integer.
	 */
	static int getPositiveNum(String str) {
		int num = 0;
		try {
			num = Integer.parseInt(str);
		} catch (NumberFormatException e) {
			// Terminate the program if the String is not an integer.
			System.out.println("N or/and T is not in integer.");
		}
		if (num <= 0) {
			// Terminate the program if the number if not positive.
//			System.out.println("N or/and T is not positive.");
			try {
				throw new Exception("N or/and T is not positive.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		return num;
	}

	/**
	 * Terminate the program if a folder path is not absolute.
	 * 
	 * @param folderPath
	 *            The directory to be checked.
	 */
	static void checkFolderPathExist(String folderPath) {
		File path = new File(folderPath);
		if (!path.exists()) {
			// Terminate the program if a folder path is not absolute.
			try {
				throw new Exception("Please restart and enter an absolute path in the first place.");
			} catch (Exception e) {
				System.out.println(e.getMessage());
				System.exit(0);
			}
		}
	}

	/**
	 * Terminate the program if the log file is not writable.
	 * 
	 * @param logFileName
	 *            The log file to be created and checked.
	 */
	/**
	 * @param logFileName
	 */
	static void checkLogFileWritable(String logFileName) {
		File logFile = new File(logFileName);
		try {
			// try to create a test file
			logFile.createNewFile();
		} catch (IOException e) {
			// terminate the program is the directory does not exist or is
			// read-only
			System.out.println("Cannot write in this directory.");
		}
		// delete the test file (will be re-create when logging is needed)
		logFile.delete();
	}

}
