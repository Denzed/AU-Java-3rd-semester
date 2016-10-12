package Functional;

public abstract class Function2<F1,F2,T> {
	abstract public T apply(F1 arg1, F2 arg2);
	
	private class Compose<T2> extends Function2<F1,F2,T2> {
		Function1<T,T2> g;
		
		private Compose(Function1<T,T2> _g) {
			g = _g;
		}

		@Override
		public T2 apply(F1 arg1, F2 arg2) {
			return g.apply(Function2.this.apply(arg1, arg2));
		}
	}
	
	public Function2<F1,F2,?> compose(Function1<T,?> g) {
		return new Compose<>(g);  
	}
	
	private class Bind1 extends Function1<F2,T> {
		F1 arg1;
		
		private Bind1(F1 _arg1) {
			arg1 = _arg1;
		}

		@Override
		public T apply(F2 arg2) {
			return Function2.this.apply(arg1, arg2);
		}
	}
	
	public Function1<F2,T> bind1(F1 arg1) {
		return new Bind1(arg1);  
	}
	
	private class Bind2 extends Function1<F1,T> {
		F2 arg2;
		
		private Bind2(F2 _arg2) {
			arg2 = _arg2;
		}

		@Override
		public T apply(F1 arg1) {
			return Function2.this.apply(arg1, arg2);
		}
	}
	
	public Function1<F1,T> bind2(F2 arg1) {
		return new Bind2(arg1);  
	}
	
	private class Curry extends Function1<F1,Function1<F2,T>> {
		@Override
		public Function1<F2,T> apply(F1 arg1) {
			return Function2.this.bind1(arg1);
		}
	}
	
	public Function1<F1,Function1<F2,T>> curry() {
		return new Curry();
	}
	
	private class Flip extends Function2<F2,F1,T> {
		@Override
		public T apply(F2 arg2, F1 arg1) {
			return Function2.this.apply(arg1, arg2);
		}
	}
	
	public Function2<F2,F1,T> flip() {
		return new Flip();
	}
}
