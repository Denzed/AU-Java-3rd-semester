import Functional.*;
import java.util.HashMap;
import org.junit.*;

public class PredicateTest extends Assert {
	private static final HashMap<Boolean,Predicate<Object>> booleanConstants = new HashMap<Boolean,Predicate<Object>>(); 
	static {
		booleanConstants.put(true, Predicate.ALWAYS_TRUE);
		booleanConstants.put(false, Predicate.ALWAYS_FALSE); 
	}
	
	Predicate<Object> undefined = (arg) -> {
		Integer fail = 1 / 0; // in order not to throw exceptions
		return fail != 0;
	};

	@Test
	public void testOr() {
		for (Boolean key1: booleanConstants.keySet()) {
			Predicate<Object> p1 = booleanConstants.get(key1);
			for (Boolean key2: booleanConstants.keySet()) {
				Predicate<Object> p2 = booleanConstants.get(key2);
				assertEquals(key1 || key2, p1.or(p2).apply(true));
			}
		}
	}

	@Test
	public void testAnd() {
		for (Boolean key1: booleanConstants.keySet()) {
			Predicate<Object> p1 = booleanConstants.get(key1);
			for (Boolean key2: booleanConstants.keySet()) {
				Predicate<Object> p2 = booleanConstants.get(key2);
				assertEquals(key1 && key2, p1.and(p2).apply(true));
			}
		}
	}
	
	@Test
	public void testLaziness() {
		assertTrue(booleanConstants.get(true).or(undefined).apply(true));
		assertFalse(booleanConstants.get(false).and(undefined).apply(true));
	}
	
	@Test
	public void testNot() {
		assertTrue(booleanConstants.get(false).not().apply(true));
		assertFalse(booleanConstants.get(true).not().apply(true));
		assertTrue(booleanConstants.get(false).not().apply(false));
		assertFalse(booleanConstants.get(true).not().apply(false));
	}
}
