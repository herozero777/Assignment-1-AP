package hamming;

import java.util.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import java.io.*;

public class Dictionary {
	
	List<String> wordList = new ArrayList<String>();
	// @param path of json file
	// This function loads the words from json stream
	// @return: wordList
	public List<String> readJson(String filePath) {
		
		List<String> words = new ArrayList<String>();
		JSONParser parser = new JSONParser();
		
		try {
			 
            Object obj = parser.parse(new FileReader(filePath));
            JSONObject jsonObject = (JSONObject) obj;
            Set<String> wrd = jsonObject.keySet();
            // Adding words to the wordList
            Iterator<String> iterator = wrd.iterator();
            while (iterator.hasNext()) {
            	wordList.add(iterator.next());
                //System.out.println(iterator.next());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		return wordList;
	}
	
	// @param: Two words from the dictionary (src, dest)
	// @return: an ArrayList which stroes the path from src to dest word else null
	public List<String> getWordChain(String word1, String word2) {
	//public void getWordChain() {
		List<String> wordPath = new ArrayList<String>();
		//String word1 = "abcd";
		wordPath.add(word1);
		//String word2 = "warm";
		int count = 4;
		boolean change = true;
		
		if (word1.length() != word2.length())
        {
           return null;
        }
		StringBuilder wtemp = new StringBuilder(word1);
		while(change) {
			if (wtemp.toString().equalsIgnoreCase(word2)) {
				break;
			} else {
				change = false;
			}
			for (int i = 0; i < word1.length(); i++)
			{	
				if (wtemp.charAt(i) != word2.charAt(i)) {
					wtemp.setCharAt(i, word2.charAt(i));
					// checking if it exits in dictionary or not
					if (!checkDictionary(wtemp)) {
						//System.out.println("word not: " + wtemp);
						wtemp.setCharAt(i, word1.charAt(i));
					} else {
						//System.out.println("correct word: " + wtemp);
						wordPath.add(wtemp.toString());
						change = true;
					}
					//System.out.println(checkDictionary(wtemp));
					//System.out.println(wtemp);
				}
			}
		}
		if (!wtemp.toString().equalsIgnoreCase(word2))
			return null;
		//System.out.println("Word 1 after loop: " + wtemp);
		//System.out.println("Word for loop: " + word2);
		return wordPath;
	}
	
	// @param: word to be checked by the dictionary
	// @return true if found; false otherwise
	public boolean checkDictionary(StringBuilder checkWord) {
		String tempWord = checkWord.toString();
		for (String word : wordList) {
			if (tempWord.equalsIgnoreCase(word)) {
				return true;
			}
		}
		return false;
	}
	
}






