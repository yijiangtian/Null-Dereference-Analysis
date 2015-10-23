package PAVpointerAnalysisPackage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Data {

	private HashMap<String, ArrayList<String>> hm = new HashMap<String, ArrayList<String>>();
	private HashMap<String, Boolean> marked = new HashMap<String, Boolean>();
	private HashMap<String, ArrayList<HashMap<String, ArrayList<String>>>> pp = new HashMap<String, ArrayList<HashMap<String, ArrayList<String>>>>();

	
	//TODO: the following will most probably not work
	public boolean comparehash(HashMap<String, ArrayList<String>> hm1, HashMap<String, ArrayList<String>> hm2) {
		return hm1.equals(hm2);
	}

	public void add_to_var(String index, String value) {
		ArrayList<String> values = hm.get(index);

		if (values == null) {
			values = new ArrayList<String>();
			values.add(value);
			hm.put(index, values);
		} else {
			if (!values.contains(value)) {
				values.add(value);
			}
		}
		sort(index);
	}

	public void add_pp(String index, HashMap<String, ArrayList<String>> hm) {
		ArrayList<HashMap<String, ArrayList<String>>> al = pp.get(index);
		if (al == null) {
			al = new ArrayList<HashMap<String, ArrayList<String>>>();
			al.add(hm);
			pp.put(index, al);
		} else {
			if (!al.contains(hm)) {
				al.add(hm);
			}
		}
	}
	
	public void add(String pp, int col, String variable, String pointsto)
	{
		
	}

	public Boolean removenull(String index) {
		ArrayList<String> values = hm.get(index);
		if (values != null) {
			remove(index, "null");
		}
		return false;
	}

	public ArrayList<String> get(String index) {
		ArrayList<String> v = hm.get(index);
		print(v);
		if (v != null) {
			return hm.get(index);
		}
		return null;
	}

	public ArrayList<String> get(String index, Boolean noprint) {
		ArrayList<String> v = hm.get(index);
		if (v != null && noprint) {
			return hm.get(index);
		}
		return null;
	}

	// i1 =i1 + (i2 join i3)
	// only distinct values are stored
	public void join(String index1, String index2, String index3) {
		ArrayList<String> s2 = hm.get(index2);
		ArrayList<String> s3 = hm.get(index3);
		if (s2 != null) {
			for (String value : s2) {
				add_to_var(index1, value);
			}
		}
		if (s3 != null) {
			for (String value : s3) {
				add_to_var(index1, value);
			}
		}
		sort(index1);
	}

	public void remove(String index) {
		hm.remove(index);
	}

	public Boolean remove(String index, String value) {
		ArrayList<String> v = hm.get(index);
		if (v != null) {
			v.remove(value);
			return true;
		}
		return false;
	}

	public void print(String index) {
		ArrayList<String> al = hm.get(index);
		if (al == null)
			return;
		System.out.print(index + "->");
		print(al);
	}

	public void print(ArrayList<String> values) {
		if (values == null)
			System.out.println("{}");
		else {
			String op = "{";
			for (String value : values) {
				op = op + value + ", ";
			}
			op = op.substring(0, op.length() - 2);
			op += "}";
			System.out.println(op);
		}
	}

	public void sort(String index) {
		ArrayList<String> al = hm.get(index);
		if (al != null)
			Collections.sort(al);
	}

	public void mark(String index) {
		marked.put(index, true);
	}

	public void unmark(String index) {
		marked.put(index, false);
	}

	public Boolean marked(String index) {
		Boolean ret = marked.get(index);
		if (ret != null)
			return ret;
		return false; // TODO: change this according to logic
	}
}