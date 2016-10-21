import Functional.*;
import org.junit.*;

public class FunctionTest extends Assert {
	private class Square extends Function1<Integer,Integer> {
		@Override
		public Integer apply(Integer x) {
			return x * x;
		}
	}
	
	private class Double extends Function1<Integer,Integer> {
		@Override
		public Integer apply(Integer x) {
			return 2 * x;
		}
	}

	Double db = new Double();
	Square sq = new Square();
	
	private class Sum extends Function2<Integer,Integer,Integer> {
		@Override
		public <Arg1 extends Integer, Arg2 extends Integer> Integer apply(Arg1 arg1, Arg2 arg2) {
			return arg1 + arg2;
		}
	}
	
	private class Pow extends Function2<Integer,Integer,Integer> {
		@Override
		public <Arg1 extends Integer, Arg2 extends Integer> Integer apply(Arg1 arg1, Arg2 arg2) {
			int res = 1, n = arg1, p = arg2;
			for (; p-- > 0; res *= n); // linear time is enough for simple testing
			return res;
		}
	}

	Sum sum = new Sum();
	Pow pow = new Pow();


	@Test
	public void composeTest() {
		assertEquals((Integer) 100, (db.compose(sq).apply(5)));
		assertEquals((Integer) 50, (sq.compose(db).apply(5)));
		assertEquals((Integer) 16, (sum.compose(sq).apply(2, 2)));
	}

	@Test
	public void bindTest() {
		assertEquals((Integer) 100, (sum.bind1(5).compose(sq).apply(5)));
		assertEquals((Integer) 50, (pow.bind2(2).compose(db).apply(5)));
	}
	
	@Test
	public void curryTest() {
		assertEquals((Integer) 100, pow.curry().apply(10).apply(2));
	}
}