package MapTests;
import org.junit.Before;
import org.junit.Test;
import myDataStructures.HashMap;

public class SmallBucketMapTest extends MapTest {

	@Before
	@Test
	public void setUp() {
		map = new HashMap(1);
		assertNull(map.put("179", "School"));
		assertNull(map.put("AU", "Not school"));
	}
}
