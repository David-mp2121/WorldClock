package modelos;

import java.util.HashSet;

public class CustomSet extends HashSet<String> {

	@Override
	public String toString() {
		
		StringBuilder string = new StringBuilder();
		int i =1;
		for (String st : this) {
			string.append(st).append("  --  ");
			i++;
		if(i%4==0) {
			string.append("\n");
		}
		
		}
		
		return string.toString();
		
		
		
	}
	
	
	
	
	
}
