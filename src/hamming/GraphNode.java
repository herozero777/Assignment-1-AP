package hamming;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
	
	private String name;
	private List<String> child;
	
	GraphNode(String name) {
		this.name = name;
		this.child = new ArrayList<String>();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getChild() {
		return child;
	}
	public void setChild(String child) {
		this.child.add(child);
	}
}
