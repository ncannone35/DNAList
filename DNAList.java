import java.util.*;
import java.io.*;
public class DNAList extends Sequence {
	private static Sequence[] seq;
    public static void main(String[]args){
    	int size = Integer.parseInt(args[args.length - 2]);//gets from command line
    	seq = new Sequence[size];//creates sequence array
    	for(int i = 0; i < seq.length; i ++) {
    		Sequence fill = new Sequence(SType.EMPTY);//fills array with EMPTY enum
    		seq[i] = fill;
    	}
        try {
            Scanner scan = new Scanner(new FileInputStream(args[args.length - 1]));//from command line
            while (scan.hasNext()) {
            	String line = scan.nextLine();
            	StringTokenizer st = new StringTokenizer(line);
            	int count = st.countTokens();  
            	ArrayList <String>tokens = new ArrayList<String>();
                for (int i = 0; i <count; i++) 
                    tokens.add(st.nextToken());
            	if(tokens.size() == 1) 
            		printSeq(seq);
            	else if (tokens.get(0).equals("insert")) {//insert method
            				int pos = Integer.parseInt(tokens.get(1));
            				String type = tokens.get(2);
            				String sequence = tokens.get(3);
            				insert(pos, type, sequence);
            			}
            	else if(tokens.get(0).equals("remove")) {//remove
            			int pos = Integer.parseInt(tokens.get(1));
            			remove(pos);
            		}
            	else if(tokens.get(0).equals("print")) {//print
    				int pos = Integer.parseInt(tokens.get(1));
    				printPos(pos);
    			}
            	else if(tokens.get(0).equals("clip")) {//clip
            		int pos = Integer.parseInt(tokens.get(1));
            		int start = Integer.parseInt(tokens.get(2));
            		int end = Integer.parseInt(tokens.get(3));
            		clip(pos, start, end);		
            	}
            	else if(tokens.get(0).equals("copy")) {//copy
            		int pos1 = Integer.parseInt(tokens.get(1));
            		int pos2 = Integer.parseInt(tokens.get(2));
            		copy(pos1, pos2);
            	}
            	else if(tokens.get(0).equals("transcribe")) {//transcribe
            		int pos = Integer.parseInt(tokens.get(1));
            		transcribe(pos);
            	}
            }
                // read next line
            scan.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static LList sToList(String s) {//converts a string to a linked list
    	LList convert = new LList();
    	for(int i = 0; i < s.length(); i ++) {
    		convert = insert(convert, s.substring(i,i+1));
    	}
    	return convert;
    }
    public static void insert(int pos, String type, String sequence) {//insert method
    	if(type.equals("DNA")) {//if enum is DNA
    		int cnt = 0;
    		for(int i = 0; i < sequence.length() - 1; i ++) {
    			String cur = sequence.substring(i, i+1);
    			if(!cur.equals("A") && !cur.equals("C") && !cur.equals("G") && !cur.equals("T")){
                    System.out.println("Error occurred while inserting");
                    cnt = 1;
                    break;
                }
    		}
    		if(cnt != 1) { 
    			Sequence s = new Sequence(SType.DNA, sToList(sequence));
    			seq[pos] = s;
    		}
    	}
    	if(type.equals("RNA")) {//if enum is RNA
    		int cnt = 0;
    		for(int i = 0; i < sequence.length() - 1; i ++) {
    			String cur = sequence.substring(i, i +1);
    			if(!cur.equals("A") && !cur.equals("C") && !cur.equals("G") && !cur.equals("U")){
                    System.out.println("Error occurred while inserting");
                    cnt = 1;
                    break;
                }
    		}
    		if(cnt != 1) {
    			Sequence s = new Sequence(SType.RNA, sToList(sequence));
    			seq[pos] = s;
    		}
    	}
    }
    public static void remove(int pos) {//remove method
    	Sequence blank = new Sequence(SType.EMPTY);
    	if(seq[pos].getType() == SType.EMPTY) {
    		System.out.println("No sequence to remove at specified position");
    	}
    	else 
    		seq[pos] = blank;
    }
    public static void printSeq(Sequence[] s) {//prints all sequences
    	for(int i = 0; i < s.length; i ++) {
    		if(s[i].getType() != SType.EMPTY)
    		System.out.println(i + "\t" + s[i].getType() + "\t" + s[i].getList());
    	}
    }
    public static void printPos(int pos) {//prints at a specific position
    	if(seq[pos].getType() == SType.EMPTY) 
    		System.out.println("No sequence to print at specified position");
    	else {
    		System.out.println(seq[pos].getType() + "\t" + seq[pos].getList());
    	}
    }
    public static void clip(int pos, int start, int end) {//clip method
    	int count = 0;
    	if(seq[pos].getType() == SType.EMPTY)
    		System.out.println("No sequence to clip at specified position");
    	else {
    		if(start < 0) {
    			System.out.println("Invalid start index");
    			count++;
    		}
    		else if(start > seq[pos].getList().length()-1) { 
    			System.out.println("Start index is out of bounds");
    			count++;
    		}
    		if(end > seq[pos].getList().length()-1) {
    			System.out.println("End index is out of bounds");
    			count++;
    		}
    		if(count == 0) {
    			String curr = seq[pos].getList();
    			String clip = curr.substring(start, end+1);//clips
    			LList fin = sToList(clip);
    			seq[pos].setList(fin);
    		}
    	}
    }
    public static void copy(int pos1, int pos2) {//copy method
    	if(seq[pos1].getType() == SType.EMPTY) 
    		System.out.println("No sequence to copy at specified position");
    	else {
    		seq[pos2] = seq[pos1];
    	}	
    }
    public static void transcribe(int pos) {//transcribe
    	String trans = "";
    	if(seq[pos].getType() == SType.EMPTY)
    		System.out.println("No sequence to transcribe at specified position");
    	else if(seq[pos].getType() == SType.RNA)
    		System.out.println("Cannot transcribe RNA");
    	else {
    		String DNA = seq[pos].getList();
    		for(int i = DNA.length(); i > 0; i --) {//reverses order
    			String curr = DNA.substring(i-1,i);//changes letter
    			if(curr.equals("T"))
    				trans += "A";
    			else if(curr.equals("A"))
    				trans += "U";
    			else if(curr.equals("C"))
    				trans += "G";
    			else if(curr.equals("G"))
    				trans += "C";
    		}
    		seq[pos].setType(SType.RNA);
    		seq[pos].setList(sToList(trans));
    	}
    }
    
}
