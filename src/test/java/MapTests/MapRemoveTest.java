package MapTests;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import myDataStructures.HashMap;

public class MapRemoveTest extends Assert {
	HashMap map;
	
	@Before
	@Test
	public void setUp() {
		map = new HashMap();
		assertNull(map.put("179", "School"));
		assertNull(map.put("AU", "Not school"));
	}

	@Test
	public void testRemove() {
		assertEquals("Not school", map.remove("AU"));
		assertEquals("School", map.remove("179"));
		assertNull(map.remove("179"));
		assertNull(map.remove("IFMO"));
	}
}
