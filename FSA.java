import java.util.*;

public class FSA {
	private Graph<String, String> fsa;
	private String finalState;
	private String startState;
	
	
	public FSA(Scanner fsar) {
		this.finalState = finalState;
      fsa = new Graph<String, String>();
      buildFSA(fsar);
	}
	
	private void buildFSA(Scanner fsar) {
      if (fsar.hasNextInt()) {
         finalState = fsar.nextLine();
      } else {
         System.out.println("Wrong Carmel FSA format!");
      }
      int number = 2;
		while(fsar.hasNextLine()) {
         if (fsar.hasNextLine()) {
            String line = fsar.nextLine();
            line = line.replaceAll("[^A-Za-z0-9]", "");
            if (number == 2) {
               startState = line.substring(0, 1);
            }
			   fsa.addNode(line.substring(0, 1));
			   fsa.addNode(line.substring(1, 2));
			   fsa.addEdge(line.substring(0, 1), line.substring(1, 2), line.substring(2, 3));
		   } else {
            System.out.println("Wrong Carmel FSA format!");
         }
         number ++;
      }
	}
	
	
	public boolean check(String line) { // line: 01a
		
      String[] transition = line.split("");

		Set<String> from = new HashSet<String>();
		Set<String> next = new HashSet<String>();
		
      from.add(startState);
      
		for(int i = 1; i < transition.length; i++) {
			for(String thisCurState : from) {
				Map<String, Set<String>> transitTo = fsa.getAllChildren(thisCurState);
				
				for(String child : transitTo.keySet()) {
					Set<String> edges = transitTo.get(child);
					if(edges.contains(transition[i])) {
						next.add(child);
					}
				}
			}
			if(next.isEmpty()) {
				return false;
			}
			Set<String> temp = from;
			from = next;
			temp.clear();
			next = temp;
		}
		if(from.contains(finalState)) {
				return true;
		}
		return false;
	}
}