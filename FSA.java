import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class FSA {
	private Graph<String, String> fsa;
	private Set<String> finalStates;
	private String startState;
	
	
	public FSA(String fsaNotation) {
		fsa = new Graph<String, String>();
		finalStates = new HashSet<String>();
		//startState = new HashSet<String>();
		buildFSA(fsaNotation);
	}
	
	private void buildFSA(String fsaNotation) {
		Scanner s = new Scanner(fsaNotation);
		int lineNum = 1;
		while(s.hasNextLine()) {
			String curLine = s.nextLine();
			System.out.println("One line just get in: "+curLine);
			/*
			if(lineNum == 1) {
				finalState = curLine;
			} else {
				String[] parseResult = parseOneLine(curLine);
				if(lineNum == 2) {
					
				} else {
					
				}
			}
			*/
			if(!curLine.contains("(")) {
				finalStates.add(curLine.replace(" ", "").substring(0, 1));
			} else {
				String[] parseResult = parseOneLine(curLine);
				if(lineNum == 2) {
					startState = parseResult[0];
				}
				fsa.addNode(parseResult[0]);
				fsa.addNode(parseResult[1]);
				fsa.addEdge(parseResult[0], parseResult[1], parseResult[2]);
			}
			
			lineNum++;
		}
		
	}
	
	private String[] parseOneLine(String oneLine) {
		//Map<String, String> ret = new HashMap<String, String>();
		//System.out.println("one line before parsing: "+ oneLine);
		oneLine = oneLine.replaceAll("[^a-zA-Z0-9]", " ");
		oneLine = oneLine.replaceAll("[\\s]+", " ").trim();
		String[] ret = oneLine.split(" "); // may have problems here
		//System.out.println("oneLine after parse: "+oneLine);
		//System.out.println("one line after parsing: ");
		/*
		for(String cur : ret) {
			System.out.print(cur+";");
		}
		*/
		return ret;
	}
	
	public boolean accept(String input) {
		input = input.replace("\"", "").trim();
		FSAMain.debug("input: "+input);
		//input = input.replaceAll("[\\s]*", " ");
		String[] sequence = input.split(" ");
		//FSAMain.debug("The sequence: "+sequence.toString());
		/*
		for(String cur : sequence) {
			System.out.print("this: "+cur);
		}
		System.out.println();
		*/
		Set<String> curStates = new HashSet<String>();
		Set<String> toStates = new HashSet<String>();
		curStates.add(startState);
		/*
		for(String cur : sequence) {
			fsa.getAllChildren(curState)
			i++
		}
		*/
		
		for(int i = 0; i < sequence.length; i++) {
			/*
			Map<String, Set<String>> transitTo = fsa.getAllChildren(curState);
			for(String child : transitTo.keySet()) {
				Set<String> edges = transitTo.get(child);
				
			}
			*/
			FSAMain.debug("current character: "+sequence[i]);
			for(String thisCurState : curStates) {
				Map<String, Set<String>> transitTo = fsa.getAllChildren(thisCurState);
				
				for(String child : transitTo.keySet()) {
					Set<String> edges = transitTo.get(child);
					if(edges.contains(sequence[i])) {
						toStates.add(child);
					}
				}
			}
			if(toStates.isEmpty()) {
				FSAMain.debug("NO because no forwarding states");
				return false;
			} /*else if() {   // final state set has intersect with toState
				return true;
			}*/
			//<check if final state set has intersect with toState>
			/*
			for(String finalState : finalStates) {
				if(toState.contains(finalState)) {
					return true;
				}
			}
			*/
			//</check if final state set has intersect with toState>
			Set<String> temp = curStates;
			curStates = toStates;
			temp.clear();
			toStates = temp;
			//toState = new HashSet<String>();
		}
		for(String finalState : finalStates) {
			if(curStates.contains(finalState)) {
				return true;
			}
		}
		FSAMain.debug("NO because not reach final state");
		return false;
	}
}
