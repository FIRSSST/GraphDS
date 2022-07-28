package misc;

public class Pair<T,U> {
	private T p1;
	private U p2;
	
	public Pair(T p1, U p2) {
		this.p1 = p1;
		this.p2 = p2;
	}
	
	public T getFirst() {
		return p1;
	}
	public void setFirst(T p1) {
		this.p1 = p1;
	}
	public U getSecond() {
		return p2;
	}
	public void setSecond(U p2) {
		this.p2 = p2;
	}
	public String toString() {
		String s = "";
		String p = "";
		if (p1 == null) {
			s = "null";
		} else {
			s = p1.toString();
		}
		if (p2 == null) {
			p = "null";
		} else {
			p = p2.toString();
		}
		
		return s + " " + p;
	}
}
