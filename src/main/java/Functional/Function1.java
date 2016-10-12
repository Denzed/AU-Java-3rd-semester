package Functional;

public abstract class Function1<F,T> {
	abstract public T apply(F arg);
	
	private class Compose<T2> extends Function1<F,T2> {
		Function1<T,T2> g;
		
		private Compose(Function1<T,T2> _g) {
			g = _g;
		}

		@Override
		public T2 apply(F arg) {
			return g.apply(Function1.this.apply(arg));
		}
	}
	
	public Function1<F,?> compose(Function1<T,?> g) {
		return new Compose<>(g);  
	}
}
