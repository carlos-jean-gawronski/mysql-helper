package br.com.main;

import java.util.ArrayList;
import java.util.HashMap;

import br.com.selects.SimpleSelection;

public class Test {

	public static void main(String[] args) {
		String database = "";
		String user = "";
		String password = "";
		String table = "";
		SimpleSelection ss = new SimpleSelection();
		ArrayList<HashMap<String, Object>> al = new ArrayList<HashMap<String, Object>>();
//		al = ss.selection(database, user, password, table, null);

		for (int i = 0; i < al.size(); i++) {
			
			HashMap<String, Object> tmp = al.get(i);
			
			for (String a : tmp.keySet()) {
				System.out.println("key: " + a + " value: " + tmp.get(a));
			}
		}
		/*
		 * Scanner s = new Scanner(System.in); String a = s.nextLine();
		 * 
		 * String[] a1 = a.split("\\s+");
		 * 
		 * for(int i = 0; i < a1.length; i++) { System.out.println(a1[i]); }
		 * 
		 * 
		 * String stat = "";
		 * 
		 * for(int i = 0; i < a1.length; i++) { if(i == (a1.length - 1)) { stat +=
		 * a1[i]; }else {
		 * 
		 * stat += a1[i] + ", "; } } System.out.println(stat);
		 */
	}

}
