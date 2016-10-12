import Functional.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import org.junit.*;

public class CollectionsTest extends Assert {
	LinkedList<String> list = new LinkedList<String>(Arrays.asList("en", "tänään", "huolia", "aio"));
	LinkedList<String> emptyList = new LinkedList<>();
	
	private class Concatenate extends Function2<String,String,String> {
		public String apply(String a, String b) {
			return String.format("%s %s", a, b);
		}
	}

	@Test
	public void foldTest() {
		Concatenate f = new Concatenate();
		assertEquals("! en tänään huolia aio", Collections.foldl(f, "!", list));
		assertEquals("Hei!", Collections.foldl(f, "Hei!", emptyList));
		assertEquals("en tänään huolia aio !", Collections.foldr(f, "!", list));
	}
	
	private class IsHuolia extends Predicate<String> {
		@Override
		public Boolean apply(String s) {
			return s.equals("huolia");
		}
	}
	
	@Test
	public void takeTest() {
		IsHuolia p = new IsHuolia();
		assertEquals(Arrays.asList("en", "tänään"), Collections.takeUnless(p, list));
		assertEquals(emptyList, Collections.takeWhile(p, list));
		
	}
	
	private class LengthIsSix extends Predicate<String> {
		@Override
		public Boolean apply(String arg) {
			return arg.length() == 6;
		}
	}
	
	@Test
	public void filterTest() {
		LengthIsSix p = new LengthIsSix();
		assertEquals(Arrays.asList("tänään", "huolia"), Collections.filter(p, list));
	}
	
	@Test
	public void mapTest() {
		List<String> tmp = Arrays.asList("en !", "tänään !", "huolia !", "aio !");
		LinkedList<String> expected_LinkedList = new LinkedList<String>(tmp);
		assertEquals(expected_LinkedList, Collections.map(new Concatenate().bind2("!"), list));
	}
}
