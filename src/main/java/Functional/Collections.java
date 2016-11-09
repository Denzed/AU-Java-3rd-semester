package Functional;

import java.util.LinkedList;

public class Collections {
	static public <T,F> LinkedList<F> map(Function1<? super T,F> func, Iterable<T> a) {
		LinkedList<F> result = new LinkedList<>();
		for (T elem: a) {
			result.addLast(func.apply(elem));
		}
		return result;
	}
	
	static public <T> LinkedList<T> filter(Predicate<? super T> pred, Iterable<T> a) {
		LinkedList<T> result = new LinkedList<>();
		for (T elem: a) {
			if (pred.apply(elem)) {
				result.addLast(elem);
			}
		}
		return result;
	}
	
	static public <T> LinkedList<T> takeWhile(Predicate<? super T> pred, Iterable<T> a) {
		LinkedList<T> result = new LinkedList<>();
		for (T elem: a) {
			if (!pred.apply(elem)) {
				break;
			}
			result.addLast(elem);
		}
		return result;
	}
	
	static public <T> LinkedList<T> takeUnless(Predicate<? super T> pred, Iterable<T> a) {
		return takeWhile(pred.not(), a);
	}
	
	static public <T,U> T foldl(Function2<? super T,
			    						  ? super U,
			    						  ? extends T> func, T baseValue, Iterable<U> a) {
		for (U elem: a) {
			baseValue = func.apply(baseValue, elem);
		}
		return baseValue;
	}
	
	static public <T,U> T foldr(Function2<? super U,
										  ? super T,
										  ? extends T> func, T baseValue, Iterable<U> a) {
		Function1<? super T,? extends T> cur = (arg) -> arg; // identity  
		for (U elem: a) {
			cur = func.bind1(elem).compose(cur);
		}
		return cur.apply(baseValue);
	}
}