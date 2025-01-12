package modelos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomArrayList extends ArrayList<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	public String escreve() {
		System.out.println("to string");

		StringBuilder string = new StringBuilder();
		int i = 1;
		for (String st : this) {
			string.append(st);
			int pulaLinha = 2;
			int verificador = this.size() - 1;
			if (pulaLinha % this.size() != 0)
				verificador = this.size();

			if (i % pulaLinha != 0 && i < verificador) {
				string.append("  --  ");
			} else {
				string.append("\n");
			}

			i++;
		}
		return string.toString();

	}

}
