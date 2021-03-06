package indexSearch;

import java.util.List;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class Search {
	
	private static class ValueComparator implements Comparator<Map.Entry<String, Integer>>{
		public int compare(Map.Entry<String, Integer> m, Map.Entry<String, Integer> n) {
			return n.getValue()-m.getValue();
		}
	}
	//Map<Integer,String> sources;
	//HashMap<String, HashSet<Integer>> index;
	HashMap<String,HashMap<String,Integer>> index;
	Search(){
		//sources = new HashMap<Integer,String>();
        //index = new HashMap<String, HashSet<Integer>>();
		index = new HashMap<String,HashMap<String,Integer>>();
    }
	public Object search(String keywords, int id, List<String> list) throws NumberFormatException, IOException {
		String[] keys = keywords.split(" ");// keyword respectively
		List<String> List = list;
		String[] fileList = new String[9];
		//read corresponding file according to the helper id
		for(int i=0;i<3;i++) {
			if(list.get(i+(id-1)*3)!=null) {
				fileList[i] = list.get(i+(id-1)*3);
			}
			
		}
		for(String file: fileList) {
			if(file != null) {
				getAns(file);
			}			
		}
		//FileInputStream inputStream = new FileInputStream("index.txt");
		int l = keys.length;
		if(index.isEmpty()) {
			return "not found";
		}
		for(String key: keys) {
			if(!index.containsKey(key)) {
				l -= 1;
			}
		}
		if(l == 0) {
			return "not found";
		}
		HashMap<String,HashMap<String,Integer>> res = new HashMap<String,HashMap<String,Integer>>();
		for(String key: keys) {
			if(index.get(key) != null) {
				res.put(key,index.get(key));
				//res.addAll(index.get(key));
				//res.add(l);
			}
			
			//res.retainAll(index.get(key));
		}
		System.out.println(res);
		if(res.size() == 0) {
			//System.out.println("Not found");
            return res;
		}
		
		return res;
	}
	public void getAns(String file) throws NumberFormatException, IOException {
		FileInputStream inputStream = new FileInputStream(file);
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String tmp = "";
		int line = 1;
		while((tmp = bufferedReader.readLine()) != null) {
			String[] s = tmp.split(" ");
			int length = s.length;
			//if(line > (id-1)*10 && line < id*10) {
				
			//HashSet<Integer> hs = new HashSet<Integer>();
			HashMap<String,Integer> hm = new HashMap<String,Integer>();
			for(int i=1;i<length;i++) {
				//hs.add(Integer.parseInt(s[i]));
				String[] str = s[i].split(",");
				//System.out.println(s[i]);
				hm.put(str[0], Integer.parseInt(str[1]));
			}	
			if(index.containsKey(s[0])) {
				//HashSet<Integer> t = new HashSet<Integer>();
				HashMap<String,Integer> t = new HashMap<String,Integer>();
				t = index.get(s[0]); 
				for(Map.Entry<String, Integer> entry: t.entrySet()) {
					hm.put(entry.getKey(), entry.getValue());
				}
				//hm.addAll(t);
			}
			List<Map.Entry<String,Integer>> list = new ArrayList<>();
			list.addAll(hm.entrySet());
			Search.ValueComparator vc = new ValueComparator();
			Collections.sort(list, vc);
			//hm = (HashMap)list;
			LinkedHashMap<String,Integer> t = new LinkedHashMap<String,Integer>();
			for(Entry<String, Integer> l:list) {
				t.put(l.getKey(), l.getValue());
			}
			index.put(s[0], t);
			//}
			line += 1;
		}
		//term
		//docId
		//freq
	}
	
//	public static void main(String[] args){
//		Search s =  new Search();
//		Scanner in = new Scanner(System.in);
//		String ss = in.nextLine();
//		//System.out.println(s.search(ss,1));
//		try {
//			List<String> l = new ArrayList();
//			l.add("index.txt");
//			l.add("index2.txt");
//			l.add("index3.txt");
//			System.out.println(s.search(ss, 1,l));
//		} catch (NumberFormatException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		//System.out.println(s.index);
//	}
}
