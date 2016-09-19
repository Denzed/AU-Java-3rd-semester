package MapTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import myDataStructures.HashMap;

public class MapTest extends Assert {
	HashMap map;
	
	@Before
	@Test
	public void setUp() {
		map = new HashMap();
		assertNull(map.put("179", "School"));
		assertNull(map.put("AU", "Not school"));
	}
	
	@Test
	public void testSize() {
		assertEquals(2, map.size());
	}

	@Test
	public void testContains() {
		assertTrue(map.contains("179"));
		assertFalse(map.contains("57"));
		assertTrue(map.contains("AU"));
		assertFalse(map.contains("IFMO"));
	}

	@Test
	public void testGet() {
		assertEquals("School", map.get("179"));
		assertNull(map.get("57"));
		assertEquals("Not school", map.get("AU"));
		assertNull(map.get("IFMO"));
	}

	@Test
	public void testRemove() {
		assertEquals("School", map.remove("179"));
		assertNull(map.remove("179"));
		assertEquals("Not school", map.remove("AU"));
		assertNull(map.remove("IFMO"));
	}
}
