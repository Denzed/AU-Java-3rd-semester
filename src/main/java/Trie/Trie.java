package Trie;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Trie implements TrieInterface, StreamSerializable {
	private TrieNode root;
	
	public Trie() {
		root = new TrieNode();
	}
	
	private boolean addHelper(TrieNode cur, String element, int index) {
		if (index == element.length()) {
			boolean wasTerminal = cur.getTerminal();
			cur.setTerminal(true);
			if (!wasTerminal) {
				cur.setTerminalsInSubtree(cur.getTerminalsInSubtree() + 1);
			}
			return wasTerminal;
		}
		char c = element.charAt(index);
		TrieNode nxt = cur.getNext(c);
		if (nxt == null) {
			nxt = cur.addNext(c);
		}
		boolean found = addHelper(nxt, element, index + 1);
		if (!found) {
			cur.setTerminalsInSubtree(cur.getTerminalsInSubtree() + 1);
		}
		return found;
	}
	
    public boolean add(String element) {
    	return addHelper(root, element, 0); 
    }
	
    public boolean contains(String element) {
    	TrieNode cur = root;
    	for (int i = 0; i < element.length() && cur != null; i++) {
    		cur = cur.getNext(element.charAt(i));
    	}
    	return (cur == null ? false : cur.getTerminal());
    }
    
    private boolean removeHelper(TrieNode cur, String element, int index) {
    	if (index == element.length()) {
    		boolean wasTerminal = cur.getTerminal();
    		if (wasTerminal) {
    			cur.setTerminal(false);
    			cur.setTerminalsInSubtree(cur.getTerminalsInSubtree() - 1);
    		}
    		return wasTerminal;
    	}
    	TrieNode nxt = cur.getNext(element.charAt(index));
    	if (nxt == null) {
    		return false;
    	}
    	boolean removed = removeHelper(nxt, element, index + 1);
    	if (nxt.getTerminalsInSubtree() == 0) {
    		cur.setNext(element.charAt(index), null);
    	}
    	if (removed) {
    		cur.setTerminalsInSubtree(cur.getTerminalsInSubtree() - 1);
    	}
    	return removed;
    }
    
    public boolean remove(String element) {
    	return removeHelper(root, element, 0);
    }
    
    public int size() {
    	return root.getTerminalsInSubtree();
    }
    
    public int howManyStartsWithPrefix(String prefix) {
    	TrieNode cur = root;
    	for (int i = 0; i < prefix.length() && cur != null; i++) {
    		cur = cur.getNext(prefix.charAt(i));
    	}
    	return (cur == null ? 0 : cur.getTerminalsInSubtree());
    }
    
    public boolean equals(Trie other) {
    	return (root == other.root || root.equals(other.root));
    }
    
    private void serializeHelper(TrieNode cur, OutputStream out) throws IOException {
    	try {
    		out.write(cur.getTerminal() ? 1 : 0);
    		for (char c: cur.getValidNextChars().toCharArray()) {
    			TrieNode nxt = cur.getNext(c);
    			if (nxt != null) {
    				out.write(c);
	    			serializeHelper(nxt, out);
    			}
    		}
    		out.write(0);
    	} catch (IOException e) {
    		System.err.println("Couldn't serialize due to IOException");
    		throw e;
    	}
    }
    
    public void serialize(OutputStream out) throws IOException {
    	serializeHelper(root, out);
    }
    
    private void deserializeHelper(TrieNode cur, InputStream in) throws IOException {
    	try {
    		cur.clear();
	    	cur.setTerminal(in.read() == 1 ? true : false);
	    	cur.setTerminalsInSubtree(cur.getTerminal() ? 1 : 0);
	    	for (int c; (c = in.read()) > 0; ) {
	    		TrieNode nxt = cur.addNext((char) c);
	    		deserializeHelper(nxt, in);
	    		cur.setTerminalsInSubtree(cur.getTerminalsInSubtree() 
	    								+ nxt.getTerminalsInSubtree());
	    	}
	    } catch (IOException e) {
			System.err.println("Couldn't serialize due to IOException");
			throw e;
		}
    }
    
    public void deserialize(InputStream in) throws IOException {
    	deserializeHelper(root, in);
    }
}
