package Functional;

abstract public class Predicate<F> extends Function1<F,Boolean> {
	private class Or<Pred extends Predicate<? super F>> extends Predicate<F> {
		Pred g;
		
		private Or(Pred _g) {
			g = _g;
		}
		
		@Override
		public <Arg extends F> Boolean apply(Arg arg) {
			return Predicate.this.apply(arg) || g.apply(arg);
		}
	}
	
	public <Pred extends Predicate<? super F>> Predicate<F> or(Pred g) {
		return new Or<Pred>(g);
	}
	
	private class And<Pred extends Predicate<? super F>> extends Predicate<F> {
		Pred g;
		
		private And(Pred _g) {
			g = _g;
		}
		
		@Override
		public <Arg extends F> Boolean apply(Arg arg) {
			return Predicate.this.apply(arg) && g.apply(arg);
		}
	}
	
	public <Pred extends Predicate<? super F>> Predicate<F> and(Pred g) {
		return new And<Pred>(g);
	}
	
	private class Not extends Predicate<F> {
		@Override
		public <Arg extends F> Boolean apply(Arg arg) {
			return !Predicate.this.apply(arg);
		}
	}
	
	public Predicate<F> not() {
		return new Not();
	}
	
	private static class pConst<F> extends Predicate<F> {
		boolean c;
		
		private pConst(boolean _c) {
			c = _c;
		}
		
		@Override
		public <Arg extends F> Boolean apply(Arg arg) {
			return c;
		}
	}

	public static final Predicate<Object> ALWAYS_TRUE = new pConst<Object>(true);
	public static final Predicate<Object> ALWAYS_FALSE = new pConst<Object>(false);
}
