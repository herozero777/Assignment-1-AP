package hamming;

import java.util.List;
import java.util.ArrayList;

public class PathClass {
	private int cost;
	private List<String> pathRay;
	
	PathClass (String word) {
		this.cost = 0;
		this.pathRay = new ArrayList<String>();
		this.pathRay.add(word);
	}
	PathClass (int cost, List<String> pathRay, String nextPath) {
		this.cost = cost;
		this.pathRay = new ArrayList<String>();
		this.pathRay.addAll(pathRay);
		this.pathRay.add(nextPath);
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
	public List<String> getPathRay() {
		return pathRay;
	}
	public void setPathRay(String word) {
		this.pathRay.add(word);
	}
}
