package Functional;

abstract public class Predicate<F> extends Function1<F,Boolean> {
	private class Or extends Predicate<F> {
		Predicate<F> g;
		
		private Or(Predicate<F> _g) {
			g = _g;
		}
		
		@Override
		public Boolean apply(F arg) {
			return Predicate.this.apply(arg) || g.apply(arg);
		}
	}
	
	public Predicate<F> or(Predicate<F> g) {
		return new Or(g);
	}
	
	private class And extends Predicate<F> {
		Predicate<F> g;
		
		private And(Predicate<F> _g) {
			g = _g;
		}
		
		@Override
		public Boolean apply(F arg) {
			return Predicate.this.apply(arg) && g.apply(arg);
		}
	}
	
	public Predicate<F> and(Predicate<F> g) {
		return new And(g);
	}
	
	private class Not extends Predicate<F> {
		@Override
		public Boolean apply(F arg) {
			return !Predicate.this.apply(arg);
		}
	}
	
	public Predicate<F> not() {
		return new Not();
	}
	
	private class pTrue extends Predicate<F> {
		@Override
		public Boolean apply(F arg) {
			return true;
		}
	}

	public Predicate<F> ALWAYS_TRUE = new pTrue();
	public Predicate<F> ALWAYS_FALSE = ALWAYS_TRUE.not(); 
}
