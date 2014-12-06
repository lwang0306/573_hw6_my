package edu.upenn.cis573.hwk1;

import java.util.HashMap;

public class Main {

	/**
	 * A method of plagiarism detection by looking for common words and phrases
	 * between documents in a corpus.
	 * 
	 * @param args
	 *            args[0] - The name of directory to read all text files.
	 *            args[1] - The path of a log file. args[2] - The N of common
	 *            N-word. args[3] - The threshold when showing result.
	 */
	public static void main(String[] args) {
		
		if (args.length != 4) {
			System.out.println("The number of runtime arguments is not correct.");
			System.exit(0);
		}

		// obtain command line arguments from the user
		String folderPath = args[0];
		String logFileName = args[1];
		String strN = args[2];
		String strT = args[3];

		// check validity and handle some arguments
		Helper.checkFolderPathExist(folderPath);
		Helper.checkLogFileWritable(logFileName);
		int N = Helper.getPositiveNum(strN);
		int T = Helper.getPositiveNum(strT);

		// Compare all text files in the given folder, log in log file and give
		// result
		HashMap<String, Integer> result = Comparator.compareAndGetResult(
				folderPath, logFileName, N, T);

		// Pretty print out the result
		System.out.println(Helper.prettyPrintMap(result));

	}
}
