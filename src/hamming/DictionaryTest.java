package hamming;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DictionaryTest {

	@Test
	public void testGetMaxWordLength() {
		// Setting prereqs
		List<String> wordList = new ArrayList<String>();
		String filePath = "C:/Users/Ismail/Documents/5th "
        		+ "Semester/Advance Programming/Assignment 1/dict.txt";
		Dictionary dict = new Dictionary();
		wordList = dict.readJson(filePath);
		// Testing the function now
		int maxLength = dict.getMaxWordLength();
		int expectR = 9;
		assertEquals(maxLength, expectR);
	}
	
	/*@Test
	public List<Map<String,GraphNode>> testMakeBuckets() {
		//
		List<String> wordList = new ArrayList<String>();
		String filePath = "C:/Users/Ismail/Documents/5th "
        		+ "Semester/Advance Programming/Assignment 1/dict.txt";
		Dictionary dict = new Dictionary();
		wordList = dict.readJson(filePath);
		// Testing the function
		
		return null;
	}*/

}
