package Functional;

@FunctionalInterface
public interface Function1<F,T> {
	abstract public T apply(F x);
	
	default public <T2> Function1<F,T2> compose(Function1<? super T,T2> g) {
		return (arg) -> g.apply(apply(arg));
	}
}
