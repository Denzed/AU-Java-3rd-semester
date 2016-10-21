package Functional;

import java.util.LinkedList;

public class Collections {
	static private <T> LinkedList<T> makeEmptyLinkedList() {
		return new LinkedList<>();
	}
	
	static public <T,A extends Iterable<T>,
				   F,Func extends Function1<? super T,F>> LinkedList<F> map(Func func, A a) {
		LinkedList<F> result = makeEmptyLinkedList();
		for (T elem: a) {
			result.addLast(func.apply(elem));
		}
		return result;
	}
	
	static public <T,A extends Iterable<T>,
					 Pred extends Predicate<? super T>> LinkedList<T> filter(Pred pred, A a) {
		LinkedList<T> result = makeEmptyLinkedList();
		for (T elem: a) {
			if (pred.apply(elem)) {
				result.addLast(elem);
			}
		}
		return result;
	}
	
	static public <T,A extends Iterable<T>,Pred extends Predicate<? super T>> LinkedList<T> takeWhile(Pred pred, A a) {
		LinkedList<T> result = makeEmptyLinkedList();
		for (T elem: a) {
			if (!pred.apply(elem)) {
				break;
			}
			result.addLast(elem);
		}
		return result;
	}
	
	static public <T,A extends Iterable<T>,Pred extends Predicate<? super T>> LinkedList<T> takeUnless(Pred pred, A a) {
		return takeWhile(pred.not(), a);
	}
	
	static public <T,U,A extends Iterable<U>,
	 			     Func extends Function2<? super T,
	 			   						    ? super U,
	 			   						    ? extends T>> T foldl(Func func, T baseValue, A a) {
		for (U elem: a) {
			baseValue = func.apply(baseValue, elem);
		}
		return baseValue;
	}
	
	static private class Identity<T> extends Function1<T,T> {
		@Override
		public <Arg extends T> T apply(Arg arg) {
			return arg;
		}
		
	}
	
	static public <T,U,A extends Iterable<U>,
					 Func extends Function2<? super U,? super T,? extends T>> T foldr(Func func, T baseValue, A a) {
		Function1<? super T,? extends T> cur = new Identity<T>();  
		for (U elem: a) {
			cur = func.bind1(elem).compose(cur);
		}
		return cur.apply(baseValue);
	}	
}