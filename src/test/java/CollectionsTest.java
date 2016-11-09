import Functional.*;

import java.util.Arrays;
import java.util.List;

import org.junit.*;

public class CollectionsTest extends Assert {
	List<String> list = Arrays.asList("en", "tänään", "huolia", "aio"),
				 empty = java.util.Collections.emptyList();
	Function2<Object,Object,String> concat = (a, b) -> String.format("%s %s", (String) a, (String) b);

	@Test
	public void foldTest() {
		assertEquals("! en tänään huolia aio", Collections.foldl(concat, "!", list));
		assertEquals("Hei!", Collections.foldl(concat, "Hei!", empty));
		assertEquals("en tänään huolia aio !", Collections.foldr(concat, "!", list));
	}
	
	@Test
	public void takeTest() {
		Predicate<Object> isHuolia = (s) -> ((String) s).equals("huolia");
		assertEquals(Arrays.asList("en", "tänään"), Collections.takeUnless(isHuolia, list));
		assertEquals(empty, Collections.takeWhile(isHuolia, list));
		
	}

	@Test
	public void filterTest() {
		Predicate<Object> lengthIsSix = (s) -> (((String) s).length() == 6);
		assertEquals(Arrays.asList("tänään", "huolia"), Collections.filter(lengthIsSix, list));
	}
	
	@Test
	public void mapTest() {
		List<String> expectedLinkedList = Arrays.asList("en !", "tänään !", "huolia !", "aio !");
		assertEquals(expectedLinkedList, Collections.map(concat.bind2("!"), list));
	}
}
