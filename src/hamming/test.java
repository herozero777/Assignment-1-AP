/*import java.util.HashMap;
import java.util.Queue;
import java.util.HashSet;
import java.util.LinkedList;

public class WordLadder {

//solution function
public static int ladderLength(String start, String end, HashSet dict) {
// saving the graph nodes on hash map.
HashMap nodes = new HashMap();
// adding the start and the end words to the dict
dict.add(start);
dict.add(end);
for (String word : dict) {
	nodes.put(word, new GraphNode(word));
	}
	// update each node’s adjacents according to one character different relation
	Object[] dictArray = dict.toArray();
	for (int i = 0; i < dictArray.length; i++) {
		for (int j = i + 1; j < dictArray.length; j++) {
			if (isNeighbor((String) dictArray[i], (String) dictArray[j])) {
				nodes.get((String) dictArray[i]).childs
				.add(nodes.get((String) dictArray[j]));
				nodes.get((String) dictArray[j]).childs.add(nodes
						.get((String) dictArray[i]));
}
}
}

// Run BFS on the Graph and take the dist generated as result
HashMap result = BFS(nodes, start);

// Return the distance of the end word node from the start word node
return result.get(end);

}

// BFS function
public static HashMap BFS( HashMap nodes, String start) {

HashMap visited = new HashMap();
HashMap dist = new HashMap();
for (String key : nodes.keySet()) {
visited.put(key, 0);
dist.put(key, 0);
}
Queue q = new LinkedList();
q.add(start);
visited.put(start, 1);
while (!q.isEmpty()) {
String dequeued = q.remove();
GraphNode curNode = nodes.get(dequeued);
LinkedList currAdjs = curNode.childs;
for (int i = 0; i < currAdjs.size(); i++) {
GraphNode adj = (GraphNode) currAdjs.get(i);
if (visited.get(adj.word) == 0) {
visited.put(adj.word, 1);
dist.put(adj.word, dist.get(dequeued) + 1);
q.add(adj.word);
}

}
}
return dist;

}

// check if two words differ by one character
public static boolean isNeighbor(String a, String b) {
assert a.length() == b.length();
int differ = 0;
for (int i = 0; i 1)
return false;
}
return true;
}

public static void main(String[] args) {
// dict = [“hot”,”dot”,”dog”,”lot”,”log”] result 5;
HashSet dict = new HashSet();
dict.add(“hot”);
dict.add(“dot”);
dict.add(“dog”);
dict.add(“lot”);
dict.add(“log”);
System.out.println(ladderLength(“hit”, “cog”, dict));
}

}

class GraphNode {
String word;
LinkedList childs;

public GraphNode(String word) {
this.word = word;
childs = new LinkedList();
}
} */