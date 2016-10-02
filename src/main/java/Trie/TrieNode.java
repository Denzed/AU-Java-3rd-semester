package Trie;

public class TrieNode {
	public final static int ALPHABET = 26;
	public final static char FIRST_LETTER = 'A';
	
	private TrieNode next[];
	private boolean isTerminal;
	private int terminalsInSubtree;
	
	public TrieNode() {
		next = new TrieNode[ALPHABET];
		clear();
	}

	public void clear() {
		for (int i = 0; i < ALPHABET; ++i) {
			next[i] = null;
		}
		isTerminal = false;
		terminalsInSubtree = 0;
	}

	public boolean getTerminal() {
		return isTerminal;
	}
	
	public void setTerminal(boolean newValue) {
		isTerminal = newValue;
	}
    
    public int getTerminalsInSubtree() {
    	return terminalsInSubtree;
    }
    
    public void setTerminalsInSubtree(int newValue) {
    	terminalsInSubtree = newValue;
    }
	
	private int getInd(char c) {
		return c - FIRST_LETTER;
	}
	
	public TrieNode getNext(char c) {
		return next[getInd(c)];
	}
	
	public void setNext(char c, TrieNode newReference) {
		next[getInd(c)] = newReference;
	}
	
	public TrieNode addNext(char c) {
		return next[getInd(c)] = new TrieNode();
	}
    
    public boolean equals(TrieNode other) {
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

	public String getValidNextChars() {
		int nextCount = 0;
		for (int i = 0; i < ALPHABET; i++) {
			if (next[i] != null) {
				nextCount++;
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < ALPHABET && nextCount > 0; i++) {
			if (next[i] != null) {
				sb.append((char) (FIRST_LETTER + i));
			}
		}
		return sb.toString();
	}
}
