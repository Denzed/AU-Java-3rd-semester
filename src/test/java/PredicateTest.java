import Functional.*;
import java.util.HashMap;
import org.junit.*;

public class PredicateTest extends Assert {
	HashMap<Boolean,Predicate<Object>> booleanConstants = new HashMap<Boolean,Predicate<Object>>(); 
	{
		booleanConstants.put(true, Predicate.ALWAYS_TRUE);
		booleanConstants.put(false, Predicate.ALWAYS_FALSE); 
	}

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
	public void testNot() {
		assertEquals(true, booleanConstants.get(false).not().apply(true));
		assertEquals(false, booleanConstants.get(true).not().apply(true));
		assertEquals(true, booleanConstants.get(false).not().apply(false));
		assertEquals(false, booleanConstants.get(true).not().apply(false));
	}

	
}
