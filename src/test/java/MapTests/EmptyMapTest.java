package MapTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import myDataStructures.HashMap;

public class EmptyMapTest extends Assert {
	HashMap map;
		
	@Before
	@Test
	public void setUp() {
		map = new HashMap();
	}

	@Test
	public void testGet() {
		assertNull(map.get("179"));
	}
	
	@Test
	public void testContains() {
		assertFalse(map.contains("179"));
	}

	@Test
	public void testSize() {
		assertEquals(0, map.size());
	}
}
