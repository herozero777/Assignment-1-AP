package hamming;

import java.util.*;

public class Interface {
	
	public static void main(String args[]) {
		
		List<String[]> pathList = new ArrayList<String[]>();
		List<String> wordList = null;
		List<String> wordPath = null;
		String filePath = "C:/Users/Ismail/Documents/5th "
            		+ "Semester/Advance Programming/Assignment 1/json_text.txt";
		Dictionary dict = new Dictionary();
		wordList = dict.readJson(filePath);
		//
		wordPath = dict.getWordChain("cold", "warm");
		if (wordPath != null) {
			System.out.println("Hello");
			for (String word : wordPath) {
				//System.out.print(word + " -> ");
			}
		} else {
			System.out.println("path not found");
		}
		//
		wordPath = dict.getWordChain("warm", "cold");
		if (wordPath != null) {
			System.out.println("Hello");
			for (String word : wordPath) {
				//System.out.print(word + " -> ");
			}
		} else {
			System.out.println("path not found");
		}
		for (String word : wordList) {
			//System.out.println( word);
			for (String wd : wordList) {
				wordPath = dict.getWordChain(word, wd);
				if (wordPath != null) {						
					String strRay[] = new String[wordPath.size()];
					// Converting arraylist passed into string array
					for (int i = 0; i < wordPath.size(); i++) {
						strRay[i] = wordPath.get(i);
					}
					pathList.add(strRay);
				}
			}
		}
		// usingWord Path again
		// Printing
		for (String[] strRay : pathList) {
			for (String st : strRay) {
				System.out.print(" -> " + st);
			}
			System.out.println("");
		}
	}
}
