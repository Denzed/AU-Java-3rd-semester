package myDataStructures;
import myDataStructures.LinkedListNode;

public class LinkedList {
    private LinkedListNode head;
    
    public LinkedList() {
        head = null;
    }

    public void insert(LinkedListNode newHead) {
    	newHead.setNext(head);
        head = newHead;
    }
    
    public boolean contains(Object key) {
    	for (LinkedListNode cur = head; cur != null; cur = cur.getNext()) {
    		if (cur.getKey().equals(key)) {
    			return true;
    		}
    	}
    	return false;
    }
    
    public Object get(Object key) {
    	for (LinkedListNode cur = head; cur != null; cur = cur.getNext()) {
    		if (cur.getKey().equals(key)) {
    			return cur.getValue();
    		}
    	}
    	return null;
    }
    
    public Object put(Object key, Object value) {
    	for (LinkedListNode cur = head; cur != null; cur = cur.getNext()) {
    		if (cur.getKey().equals(key)) {
    			Object prevValue = cur.getValue();
    			cur.setValue(value);
    			return prevValue;
    		}
    	}
    	insert(new LinkedListNode(key, value));
    	return null;
    }

    public Object remove(Object key) {
    	for (LinkedListNode cur = head, prev = null; cur != null; prev = cur, cur = cur.getNext()) {
    		if (cur.getKey().equals(key)) {
    			if (prev != null) {
    				prev.setNext(cur.getNext());
    			} else {
    				head = cur.getNext();
    			}
    			return cur.getValue();
    		}
    	}
    	return null;
    }
    
    public void clear() {
    	head = null;
    }
}