package Functional;

public abstract class Function1<F,T> {
	abstract public <Arg extends F> T apply(Arg arg);
	
	public class Compose<T2,Func extends Function1<? super T,T2>> extends Function1<F,T2> {
		Func g;
		
		private Compose(Func _g) {
			g = _g;
		}

		@Override
		public <Arg extends F> T2 apply(Arg arg) {
			return g.apply(Function1.this.apply(arg));
		}
	}
	
	public <T2,Func extends Function1<? super T,T2>> Compose<T2,Func> compose(Func g) {
		return new Compose<T2,Func>(g);
	}
}
