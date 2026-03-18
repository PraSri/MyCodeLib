import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LargestAssociation {
	
	public static void main(String[] args) {
		List<String[]> items= Arrays
				.asList(new String[]{ "item1", "item2"}, new String[]{ "item3", "item4"}, new String[]{ "item4", "item5"});
		findAssociation(items);
	}
	static List<String> findAssociation(List<String[]> items) {

	    Map<String,String> inputMap=new HashMap<>();
	    for(String[] edges:items){
	        inputMap.put(edges[0],edges[1]);
	    }

	    Map<String, ArrayList<String>> connectedMap=new HashMap<>();

	    for(String vertex:inputMap.keySet()){
	        LinkedList<String> queue=new LinkedList<>();
	        queue.add(vertex);
	        connectedMap.put(vertex,new ArrayList<String>(Collections.singleton(vertex)));
	        while(!queue.isEmpty()){
	            String v=queue.poll();
	            connectedMap.get(vertex).add(inputMap.get(v));
	            if(inputMap.containsKey(inputMap.get(v))){
	                queue.add(inputMap.get(v));
	            }
	        }

	    }
	    System.out.println(connectedMap);
	    ArrayList<ArrayList<String>> values= new ArrayList<>(connectedMap.values());
	    System.out.println(values);
	    ArrayList<String> maxList=new ArrayList<>();
	    int maxCount=0;
	    for(ArrayList<String> s: values){
	        if(s.size()>maxCount){ 
	            maxCount=s.size();
	            maxList=s;
	        }else if(s.size()==maxCount){ 
	            Collections.sort(maxList);
	            Collections.sort(s);
	            if(s.get(0).compareTo(maxList.get(0))<0){
	                maxCount=s.size();
	                maxList=s;
	            }
	        }
	    }
	    System.out.println("maxList" + maxList);
	    return maxList;
	}
}
