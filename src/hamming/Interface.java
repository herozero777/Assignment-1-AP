package hamming;

import java.util.*;

public class Interface {
	
	public static void main(String args[]) {
		// Holds the maps
		List<Map<String, GraphNode>> bucketHolder = null;
		List<String> path = null;
		List<String[]> pathList = new ArrayList<String[]>();
		List<String> wordList = null;
		List<String> wordPath = null;
		String filePath = "json_text.txt";
		Dictionary dict = new Dictionary();
		wordList = dict.readJson(filePath);
		/*
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
		} */
		/* Wrong approach. Finding only best case paths
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
		}*/
		// Task 4
		Map<String, List<String>> minChain = new HashMap<String, List<String>>();
		int minChainSize = 100;
		// Task 5 
		Map<String, List<String>> maxChain = new HashMap<String, List<String>>();
		int maxChainSize = 0;
		// Task 6
		List<String> noChainWords = new ArrayList<String>();
		boolean noChain = true;
		//bucketHolder = dict.makeBuckets();
		bucketHolder = dict.makeGraph();
		for (Map<String, GraphNode> bucket : bucketHolder) {
			for (String src : bucket.keySet()) {			
				for (String dest : bucket.keySet()) {
					if (src.equals(dest)) {
						continue; }
					path = dict.aStarSearch(src, dest, bucket);
					if (path != null) {
						// Task 4
						if (minChainSize > path.size()) {
							minChainSize = path.size();
							minChain.put(src, new ArrayList<String>(path));
						// Task 5
						} else if (maxChainSize < path.size()) {
							maxChainSize = path.size();
							maxChain.put(src, new ArrayList<String>(path));
						}
						// Task 6
						noChain = false;
						//for (String p : path) {
							//System.out.print( p + "->");
						//}
					} else {
						//System.out.println(src + " - No x Path - " + dest);
					}
					//
				}
				// Task 4
				minChainSize = 100;
				// Task 5
				maxChainSize = 0;
				System.out.println("Shortest Chain: " + minChain.get(src));
				System.out.println("Shortest Chain: " + maxChain.get(src));
				// Task 6
				if (noChain) {
					noChainWords.add(src);
					noChain = true;
				}
				//for (String p : minChain.get(src)) {
				//	System.out.print( p + "->");
				//}
			}
			
		}
		
		// usingWord Path again
		// Printing
		/*for (String[] strRay : pathList) {
			for (String st : strRay) {
				System.out.print(" -> " + st);
			}
			System.out.println("");
		}*/
		
		/*
		 * bucketHolder = dict.makeGraph();
		for (Map<String, GraphNode> bucket : bucketHolder) {
			for (String src : bucket.keySet()) {
				
				for (String dest : bucket.keySet()) {
					//
					path = dict.aStarSearch(src, dest, bucket);
					if (path != null) {
						for (String p : path) {
							System.out.print( p + "->");
						}
					} else {
						System.out.println(src + " - No x Path - " + dest);
					}
					//
				}
			}
			
		} */
	}
}
