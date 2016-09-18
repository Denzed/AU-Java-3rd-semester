package myDataStructures;

public class LinkedListNode {
    private Object key, value;
    private LinkedListNode next;

    public LinkedListNode(Object newKey,
	    				  Object newValue) {
    	key = newKey;
        value = newValue;
        next = null;
    }

    public Object getKey() {
        return key;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object newValue) {
        value = newValue;
    }

    public LinkedListNode getNext() {
        return next;
    }

    public void setNext(LinkedListNode newNext) {
    	next = newNext;
    }
}