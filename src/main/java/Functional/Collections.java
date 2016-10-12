package Functional;

import java.util.LinkedList;

public class Collections {
	static private <T> LinkedList<T> makeEmptyLinkedList() {
		return new LinkedList<>();
	}
	
	static public <T,A extends Iterable<T>,F> LinkedList<F> map(Function1<T,F> func, A a) {
		LinkedList<F> result = makeEmptyLinkedList();
		System.err.println("OK\n");
		for (T elem: a) {
			result.add(func.apply(elem));
		}
		return result;
	}
	
	static public <T,A extends Iterable<T>> LinkedList<T> filter(Predicate<T> pred, A a) {
		LinkedList<T> result = makeEmptyLinkedList();
		for (T elem: a) {
			if (pred.apply(elem)) {
				result.add(elem);
			}
		}
		return result;
	}
	
	static public <T,A extends Iterable<T>> LinkedList<T> takeWhile(Predicate<T> pred, A a) {
		LinkedList<T> result = makeEmptyLinkedList();
		for (T elem: a) {
			if (!pred.apply(elem)) {
				break;
			}
			result.add(elem);
		}
		return result;
	}
	
	static public <T,A extends Iterable<T>> LinkedList<T> takeUnless(Predicate<T> pred, A a) {
		return takeWhile(pred.not(), a);
	}

	// LinkedList is required because otherwise it is pointless (I mean use of unordered Collections)	
	static public <T,A extends LinkedList<T>> T foldl(Function2<T,T,T> func, T baseValue, A a) {
		for (T elem: a) {
			baseValue = func.apply(baseValue, elem);
		}
		return baseValue;
	}

	static public <T,A extends LinkedList<T>> T foldr(Function2<T,T,T> func, T baseValue, A a) {
		java.util.Collections.reverse(a);
		baseValue = foldl(func.flip(), baseValue, a);
		java.util.Collections.reverse(a);
		return baseValue;
	}
}
