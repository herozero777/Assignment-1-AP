package hamming;

import java.util.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Collection;
import java.util.List;
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
	
	// @param two words to be compared
	// @return true if hamming distance between the words is one
	public boolean isWordChild(String word1, String word2) {
		if (word1.length() != word2.length())
		{
			return false;
		}
		int dist = 0;
		
		for (int i = 0; i < word1.length(); i++)
		{	
			if (word1.charAt(i) != word2.charAt(i)) {
				dist++;
			}
		}
		return dist == 1;
	}
	/*
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
	} */
	
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
	// check via printing
	// @return bucketHolder who's each element is a map containing specific char.length words
	public List<Map<String,GraphNode>> makeBuckets() {
		System.out.println("makeBuckets runnin");
		int max = getMaxWordLength();
		System.out.println("max: " + max);
		List<Map<String, GraphNode>> bucketHolder = new ArrayList<Map<String, GraphNode>>();
		for (int i = 1; i <= max; i++) {
			Map<String, GraphNode> tempMap = new HashMap<String, GraphNode>();
			for (String word : wordList) {
				// fetching words of same lengths
				if (word.length() == i) {
					// <word, { (word) (dynamic ArrayList) } >
					tempMap.put(word, new GraphNode(word));
					//System.out.println("word in List map<str,GN: >" + tempMap.get(word).getName()); // testing
				}
			}
			bucketHolder.add(tempMap);
		}
		return bucketHolder;	
	}
	
	// Creates graph using buckets.
	// public void makeGraph() {
	public List<Map<String, GraphNode>> makeGraph() {
		List<Map<String, GraphNode>> bucketHolder = makeBuckets();

		for (Map<String, GraphNode> bucket : bucketHolder) {
			for (GraphNode parent : bucket.values()) {
				for (GraphNode child : bucket.values()) {
					if (isWordChild(parent.getName(), child.getName())) {
						parent.setChild(child.getName());
					}
				}
			}
			for (GraphNode parent : bucket.values()) {
				// catch for isolated words task
				if ( parent.getChild().size() == 0)
					System.out.println("Forever Alone: "+parent.getName());
				for (String bacha : parent.getChild()) {
					System.out.println("bacha of: "+parent.getName()+ "-> "
						+ bacha);
				}
			}
		}
		return bucketHolder;
	}
	
	// @return Length of the longest word
	public int getMaxWordLength() {
		int max = 0;
		for (String word : wordList) {
			// chance for optimization
			if (word.length() > max) {
				max = word.length();
			}
		}
		return max;
	}
	
	
	// Heuristic for the A* search
	// Assumptions: words will have equal length
	// @param two words. Left one is child under consideration, Right one is destination
	// @return expected cost from this child to the destination word
	public int aStarHeuristic(String word1, String word2) {
		/*if (word1.length() != word2.length())
		{
			return -1;
		} */
		int dist = 0;
		
		for (int i = 0; i < word1.length(); i++)
		{	
			if (word1.charAt(i) != word2.charAt(i)) {
				dist++;
			}
		}
		return dist;
	}
	
	// aStartSearch (head is the element with least cost. I think)
	// @param two words. Left is source, Right is destination
	// @return an ArrayList / String[] of path.
	public List<String> aStarSearch(String srcWord, String destWord, Map<String, GraphNode> map) {
		PathClass path;
		int pathSize = 0;
		int totalCost = 0;
		String currentWord = null;
		GraphNode node = null;
		
		Set<String> explored = new HashSet<String>();
		// Creation of frontier
		PriorityQueue<PathClass> pQue = new PriorityQueue<PathClass>(1, 
				new Comparator<PathClass>(){
			// overriding compare method
			public int compare(PathClass path1, PathClass path2) {
				if (path1.getCost() > path2.getCost()) {
					return 1;
				} else if (path1.getCost() < path2.getCost()) {
					return -1;
				} else {
					return 0;
				}
			}
		} );
		// Adding initial state
		pQue.add(new PathClass(srcWord));
		
		// while pQue is not empty
		while(! pQue.isEmpty()) {
			// pop from frontier
			path = pQue.poll();
			// Picking last state from path
			pathSize = path.getPathRay().size() - 1;
			currentWord = path.getPathRay().get(pathSize);
			// adding state in explored
			explored.add(currentWord);
			// Goal test
			if (currentWord.equals(destWord)) {
				List<String> pathToReturn = new ArrayList<String>();
				pathToReturn.addAll( path.getPathRay() );
				
				/*System.out.println("");
				for (String p : path.getPathRay()) {
					System.out.print( p + "->");
				}
				System.out.println(""); */
				return pathToReturn;
			}
			node = map.get(currentWord);
			// for a in actions(a) (actions of word from graphNode)
			for (String child : node.getChild() ) {
				// if state not in explored
				if (explored.contains(child)) {
					continue;
				} else {
					// add (path + p) in PQ
					totalCost = path.getCost() + aStarHeuristic(currentWord, child);
					pQue.add(new PathClass(totalCost ,path.getPathRay(),child));
				}
			}
		}
		//System.out.println("No solution found");
		return null;
	}
}






