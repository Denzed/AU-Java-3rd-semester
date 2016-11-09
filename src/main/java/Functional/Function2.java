package Functional;

@FunctionalInterface
public interface Function2<F1,F2,T> {
	abstract public T apply(F1 arg1, F2 arg2);
	
	default public <T2> Function2<F1,F2,T2> compose(Function1<? super T,T2> g) {
		return (arg1, arg2) -> g.apply(apply(arg1, arg2));  
	}

	default public Function1<F2,T> bind1(F1 arg1) {
		return (arg2) -> apply(arg1, arg2);  
	}
	
	default public Function1<F1,T> bind2(F2 arg2) {
		return (arg1) -> apply(arg1, arg2);  
	}
	
	default public Function1<F1,Function1<F2,T>> curry() {
		return (arg1) -> ((arg2) -> apply(arg1, arg2));
	}
}
