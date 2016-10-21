package Functional;

public abstract class Function2<F1,F2,T> {
	abstract public <Arg1 extends F1,Arg2 extends F2> T apply(Arg1 arg1, Arg2 arg2);
	
	private class Compose<T2,Func extends Function1<? super T,T2>> extends Function2<F1,F2,T2> {
		Func g;
		
		private Compose(Func _g) {
			g = _g;
		}

		@Override
		public <Arg1 extends F1,Arg2 extends F2> T2 apply(Arg1 arg1, Arg2 arg2) {
			return g.apply(Function2.this.apply(arg1, arg2));
		}
	}
	
	public <T2,Func extends Function1<? super T,T2>> Function2<F1,F2,T2> compose(Func g) {
		return new Compose<T2,Func>(g);  
	}
	
	private class Bind1 extends Function1<F2,T> {
		F1 arg1;
		
		private <F extends F1> Bind1(F _arg1) {
			arg1 = _arg1;
		}

		@Override
		public <F extends F2> T apply(F arg2) {
			return Function2.this.apply(arg1, arg2);
		}
	}
	
	public Function1<F2,T> bind1(F1 arg1) {
		return new Bind1(arg1);  
	}
	
	private class Bind2 extends Function1<F1,T> {
		F2 arg2;
		
		private <F extends F2> Bind2(F _arg2) {
			arg2 = _arg2;
		}

		@Override
		public <F extends F1> T apply(F arg1) {
			return Function2.this.apply(arg1, arg2);
		}
	}
	
	public <F extends F2> Function1<F1,T> bind2(F arg1) {
		return new Bind2(arg1);  
	}
	
	private class Curry extends Function1<F1,Function1<F2,T>> {
		@Override
		public <F extends F1> Function1<F2,T> apply(F arg1) {
			return Function2.this.bind1(arg1);
		}
	}
	
	public Function1<F1,Function1<F2,T>> curry() {
		return new Curry();
	}
	
	private class Flip extends Function2<F2,F1,T> {
		@Override
		public <Arg2 extends F2,Arg1 extends F1> T apply(Arg2 arg2, Arg1 arg1) {
			return Function2.this.apply(arg1, arg2);
		}
	}
	
	public Function2<F2,F1,T> flip() {
		return new Flip();
	}
}
