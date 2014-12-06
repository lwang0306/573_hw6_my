package edu.upenn.cis573.hwk1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Reader {

	/**
	 * Given an absolute path of a file, read content and split every
	 * consecutive N-word.
	 * 
	 * @param fileName
	 *            The text file to read.
	 * @param N
	 *            The N of N-word.
	 * @return A HashMap whose key is N-word and value is the word frequency.
	 */
	static HashMap<String, Integer> readAFile(String fileName, int N) {
		ArrayList<String> wordsInAFile = new ArrayList<String>();
		// start to read a file into an ArrayList<String>
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(fileName);
		} catch (FileNotFoundException e1) {
			//terminate the program if a file cannot be found
			System.out.println(e1.getMessage());
			System.exit(0);
		}
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		try {
			boolean flag = true;
			while (flag) {
				String line = bufferedReader.readLine();
				if (line == null) {
					flag = false;
				} else {
					String[] wordsInALine = line.split("\\s+");
					for (int i = 0; i < wordsInALine.length; i++) {
						wordsInALine[i] = wordsInALine[i].replaceAll(
								"[\\p{Punct}\\s]+", "").toLowerCase();
					}
					wordsInAFile.addAll(Arrays.asList(wordsInALine));
				}
			}
		} catch (IOException e) {
			//terminate the program if the file cannot be read
			System.out.println(e.getMessage());
			System.exit(0);
		}
		// close the file
		try {
			fileReader.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.exit(0);
		}
		//delete empty String in case of an empty line is read
		for (int i = 0; i < wordsInAFile.size(); i++) {
			if (wordsInAFile.get(i).isEmpty()) {
				wordsInAFile.remove(i);
				i--;
			}
		}
		// transfer an ArrayList<String> into a HashMap<String, Integer> and
		// return it
		return compactNWords(wordsInAFile, N);
	}

	/**
	 * Compact every N words into an N-word.
	 * 
	 * @param words
	 *            The ArrayList of single words.
	 * @param N
	 *            The N of N-word.
	 * @return A HashMap whose key is N-word and value is the word frequency.
	 */
	static HashMap<String, Integer> compactNWords(ArrayList<String> words, int N) {
		HashMap<String, Integer> wordMap = new HashMap<String, Integer>();
		String NWords = "";
		// start to compact every N words into an N-word
		if (words.size() <= N) {
			for (int i = 0; i < words.size(); i++) {
				NWords = NWords.concat(words.get(i)).concat(" ");
			}
			wordMap.put(NWords, 1);
		} else {
			for (int i = 0; i <= (words.size() - N); i++) {
				for (int j = 0; j < N; j++) {
					NWords = NWords.concat(words.get(i + j)).concat(" ");
				}
				if (!wordMap.containsKey(NWords)) {
					wordMap.put(NWords, 1);
				} else {
					wordMap.put(NWords, wordMap.get(NWords) + 1);
				}
				NWords = "";
			}
		}
		return wordMap;
	}

	/**
	 * Read names of all text files in a given folder.
	 * 
	 * @param folderPath
	 *            The directory/folder to read.
	 * @return An ArrayList of file names.
	 */
	static ArrayList<String> readFilesInFolder(String folderPath) {
		ArrayList<String> listOfFileNames = new ArrayList<String>();
		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();
		// start to check file type and read name
		for (int i = 0; i < listOfFiles.length; i++) {
			File file = listOfFiles[i];
			if (file.isFile() && file.getName().endsWith(".txt")) {
				String fileName = file.getName();
				listOfFileNames.add(fileName);
			}
		}
		return listOfFileNames;
	}

}
