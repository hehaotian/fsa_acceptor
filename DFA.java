import java.io.*;
import java.util.*;

public class DFA {

   // the final state of the FSA
   private int finalState;
   
   // the inputs of each transition in order
   private ArrayList<Character> input;
   
   // the start and next states of each transition in order
   private ArrayList<int[]> trans;

   // builds the fsa
   public DFA(Scanner fsar) {
      this.input = new ArrayList<Character>();
      this.trans = new ArrayList<int[]>();
      this.finalState = finalState;
      
      if (fsar.hasNextInt()) {
         finalState = Integer.parseInt(fsar.nextLine());
         String line = "";
         int i = 0;
         while (fsar.hasNextLine()) {
            if (fsar.hasNextLine()) {
               line = fsar.nextLine();
               line = line.replaceAll("[^A-Za-z0-9]", "");
               trans.add(new int[2]);
               trans.get(i)[0] = Integer.parseInt(line.substring(0, 1));
               trans.get(i)[1] = Integer.parseInt(line.substring(1, 2));
               input.add(line.charAt(2));
               i++;
            }
         }
      }
   }
   
   public boolean check(String line) {
      int state = trans.get(0)[0];
      char label = input.get(0);
      for (int i = 0; i < line.length(); i ++) {
         for (int j = 0; j < input.size(); j ++) {
            if (state == trans.get(j)[0] && line.charAt(i) == input.get(j)) {
               state = trans.get(j)[1];
               label = input.get(j);
            }
         }
      }
      if (state == finalState && label == line.charAt(line.length() - 1)) {
         return true;
      }
      return false;
   }

}