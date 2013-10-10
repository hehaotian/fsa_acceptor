import java.util.Scanner;


public class FSAMain {
	public static void main(String[] args) throws Exception {
		//<used for debuging>
		args = new String[2];
		args[0] = "2\n(0 (1 \"a\"))\n(1 (1 \"a\"))\n(1 (2 \"b\"))\n(2 (2 \"b\"))";
		args[1] = "\"a\" \"a\" \"b\"\n\"a\" \"a\"\n\"b\"\n\"a\" \"b\" \"a\"\n\"a\" \"c\"";
		debug("=== the fsa: ===");
		debug(args[0]);
		debug("=== input: ===");
		debug(args[1]);
		debug("=== results: ===");
		//</used for debugging>
		if(args.length != 2) {
			// first argument is finite state machine second is input string
			throw new Exception("The number of inputs should be 2");
		}
		String fsaNotation = args[0];
		String input = args[1];
		FSA fsa = new FSA(fsaNotation);
		Scanner getEachLine = new Scanner(input);
		while(getEachLine.hasNextLine()){
			String curLine = getEachLine.nextLine();
			if(fsa.accept(curLine)) {
				System.out.println(curLine+" => YES");
			} else {
				System.out.println(curLine+" => NO");
			}
		}
		getEachLine.close();
	}
	
	public static String debug(Object s) {
		System.out.println(s.toString());
		return s.toString();
	}
}
