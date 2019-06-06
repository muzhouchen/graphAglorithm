package graphAlgorithm;

import java.util.*;

public class Demo {
	
	public static void main(String args[]) {
		String a = "1,2";
		String b = "1,2";
		Set set = new HashSet();
		set.add(a);
		if(set.contains(b)) {
			System.out.println("same");
		}
		
	}

}
