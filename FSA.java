import java.util.*;

public class FSA {
   private Graph<String, String> fsa;
   private String finalState;
   private String startState;
   
   public FSA(Scanner fsar) {
      this.finalState = finalState;
      fsa = new Graph<String, String>();
      
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
   
   
   public boolean check(String line) {
      
      Set<String> from = new HashSet<String>();
      Set<String> next = new HashSet<String>();
      from.add(startState);
      
      // for the given string, check each input (For loop 1) with every possible start
      // state (For loop 2) and the resulting next or final states (For loop 3)
      for(int i = 0; i < line.length(); i++) {
         
         for(String currentState : from) {
            // creates a map that contains all possible transitions from the current start state
            Map<String, Set<String>> transitions = fsa.getAllChildren(currentState);
            
            for(String nextState : transitions.keySet()) {
               // creates a set of all possible next states for each transition
               Set<String> nextStates = transitions.get(nextState);
               if(nextStates.contains(line.charAt(i) + "")) {
                  next.add(nextState);
               }
            }           
         }
         
         // no next states coming up before inputs run out
         if(next.isEmpty()) return false;
         
         // refresh the from and next sets of start and next states to move on
         from = next;
         next = new HashSet<String>();
      }
      
      // after all inputs are checked, check whether the last "start" state is the final state
      if(from.contains(finalState)) return true;
      
      return false;
   }
   
}