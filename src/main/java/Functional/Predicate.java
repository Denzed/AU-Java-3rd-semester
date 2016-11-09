package Functional;

@FunctionalInterface
public interface Predicate<F> extends Function1<F,Boolean> {
	public static final Predicate<Object> ALWAYS_TRUE = (arg) -> true;
	public static final Predicate<Object> ALWAYS_FALSE = (arg) -> false;
	
	default public Predicate<F> or(Predicate<? super F> g) {
		return (arg) -> (apply(arg) || g.apply(arg));
	}
	
	default public Predicate<F> and(Predicate<? super F> g) {
		return (arg) -> (apply(arg) && g.apply(arg));
	}
	
	default public Predicate<F> not() {
		return (arg) -> !apply(arg);
	}
}
