// Haotian He
// Student No. 1261169
// October 10, 2013
// LING 570
// Instructor: Fei Xia

import java.io.*;
import java.util.*;

public class fsa_acceptor2 {

   public static void main(String[] args) throws IOException {
   
      // reads files from the arguments the command line gives
      File fsafile = new File(args[0]);
      File texfile = new File(args[1]);
      
      Scanner fsar = new Scanner(fsafile);
      Scanner text = new Scanner(texfile);
      
      // creates a new FSA according to the given fsa file
      FSA fsa = new FSA(fsar);
      
      String line = "";
      String oriLine = "";
      
      // reads string lines from the ex file
      while (text.hasNextLine()) {
         if (text.hasNextLine()) {
            line = text.nextLine();
            oriLine = line;
            
            // trims the string lines into plain letter strings
            line = trim(line);
            
            // checks whether the string line passes through the created fsa and prints the results
            if (fsa.check(line)) {
               System.out.println(oriLine + " => yes");
            } else {
               System.out.println(oriLine + " => no");
            }
         } else {
            System.out.println("No test strings in this file.");
         }
      }  
   }
   
   // trims the string lines into plain letter strings
   public static String trim(String line) {
      line = line.replaceAll("[^A-Za-z]", "");
      return line;
   }

}