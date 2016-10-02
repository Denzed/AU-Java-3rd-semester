package Trie;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Bor implements Trie, StreamSerializable {
	final static int ALPHABET = 26;
	final static char FIRST_LETTER = 'A';
	
	Bor next[];
	boolean isTerminal;
	int terminalsInSubtree;
	
	public Bor() {
		next = new Bor[ALPHABET];
		for (int i = 0; i < ALPHABET; ++i) {
			next[i] = null;
		}
		isTerminal = false;
		terminalsInSubtree = 0;
	}
	
	private int getInd(char c) {
		return c - FIRST_LETTER;
	}
	
	private Bor getNext(char c) {
		return next[getInd(c)];
	}
	
	private Bor addNext(char c) {
		return next[getInd(c)] = new Bor();
	}
	
	private boolean addHelper(String element, int index) {
		if (index == element.length()) {
			boolean wasTerminal = isTerminal;
			isTerminal = true;
			if (!wasTerminal) {
				terminalsInSubtree++;
			}
			return wasTerminal;
		}
		char c = element.charAt(index);
		Bor nxt = getNext(c);
		if (nxt == null) {
			nxt = addNext(c);
		}
		boolean found = nxt.addHelper(element, index + 1);
		if (!found) {
			terminalsInSubtree++;
		}
		return found;
	}
	
    public boolean add(String element) {
    	return addHelper(element, 0); 
    }
    
    public boolean contains(String element) {
    	Bor cur = this;
    	for (int i = 0; i < element.length() && cur != null; i++) {
    		cur = cur.getNext(element.charAt(i));
    	}
    	return (cur == null ? false : cur.isTerminal);
    }
    
    private boolean removeHelper(String element, int index) {
    	if (index == element.length()) {
    		boolean wasTerminal = isTerminal;
    		if (wasTerminal) {
    			isTerminal = false;
    			terminalsInSubtree--;
    		}
    		return wasTerminal;
    	}
    	Bor nxt = getNext(element.charAt(index));
    	if (nxt == null) {
    		return false;
    	}
    	boolean removed = nxt.removeHelper(element, index + 1);
    	if (nxt.terminalsInSubtree == 0) {
    		next[getInd(element.charAt(index))] = null;
    	}
    	if (removed) {
    		terminalsInSubtree--;
    	}
    	return removed;
    }
    
    public boolean remove(String element) {
    	return removeHelper(element, 0);
    }
    
    public int size() {
    	return terminalsInSubtree;
    }
    
    public int howManyStartsWithPrefix(String prefix) {
    	Bor cur = this;
    	for (int i = 0; i < prefix.length() && cur != null; i++) {
    		cur = cur.getNext(prefix.charAt(i));
    	}
    	return (cur == null ? 0 : cur.terminalsInSubtree);
    }
    
    public boolean equals(Bor other) {
    	if (other == null || 
    			isTerminal != other.isTerminal ||
    			terminalsInSubtree != other.terminalsInSubtree) {
    		return false;
    	}
    	for (int i = 0; i < ALPHABET; ++i) {
    		if ((next[i] == null && other.next[i] != null) ||
    				(next[i] != null && !next[i].equals(other.next[i]))) {
    			return false;
    		}
    	}
    	return true;
    }
    
    public void serialize(OutputStream out) throws IOException {
    	try {
    		out.write(isTerminal ? 1 : 0);
	    	for (char i = 0; i < ALPHABET; ++i) {
	    		if (next[i] != null) {
	    			out.write(i + 1);
	    			next[i].serialize(out);
	    		}
	    	}
    		out.write(0);
    	} catch (IOException e) {
    		System.err.println("Couldn't serialize due to IOException");
    		throw e;
    	}
    }
    
    public void deserialize(InputStream in) throws IOException {
    	try {
	    	for (int i = 0; i < ALPHABET; i++) {
	    		next[i] = null;
	    	}
	    	isTerminal = (in.read() == 1 ? true : false);
	    	terminalsInSubtree = (isTerminal ? 1 : 0);
	    	for (int c; (c = in.read()) > 0; ) {
	    		Bor nxt = addNext((char) (FIRST_LETTER + c - 1));
	    		nxt.deserialize(in);
	    		terminalsInSubtree += nxt.size();
	    	}
	    } catch (IOException e) {
			System.err.println("Couldn't serialize due to IOException");
			throw e;
		}
    }
}
