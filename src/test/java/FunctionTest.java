import Functional.*;
import org.junit.*;

public class FunctionTest extends Assert {
	static Function2<Object,Object,Integer> sum = (a, b) -> ((Integer) a) + ((Integer) b);
	static Function2<Object,Object,Integer> pow = (base, power) -> {
		int res = 1, n = (Integer) base, p = (Integer) power;
		for (; p-- > 0; res *= n); // linear time is enough for simple testing
		return res;
	};
	static Function1<Object,Integer> 		db  = (arg) -> sum.apply(arg, arg);
	static Function1<Object,Integer> 		sq  = (arg) -> pow.apply(arg, 2);
	

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