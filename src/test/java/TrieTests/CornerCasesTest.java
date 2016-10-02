package TrieTests;

import Trie.Bor;
import org.junit.Assert;
import org.junit.Test;

public class CornerCasesTest extends Assert {

	@Test
	public void emptyPrefix() {
		Bor bor = new Bor();
		assertFalse(bor.add(""));
		assertTrue(bor.contains(""));
		assertFalse(bor.add("LOL"));
		assertEquals(2, bor.howManyStartsWithPrefix(""));
		assertEquals(1, bor.howManyStartsWithPrefix("L"));
		assertEquals(2, bor.size());
		assertTrue(bor.remove(""));
		assertTrue(bor.contains("LOL"));
		assertEquals(1, bor.size());
		assertEquals(1, bor.howManyStartsWithPrefix(""));
	}
	

	@Test
	public void wholeWordPrefixLookup() {
		Bor bor = new Bor();
		assertFalse(bor.add("LOLLOL"));
		assertEquals(1, bor.howManyStartsWithPrefix("LOL"));
		assertEquals(1, bor.howManyStartsWithPrefix("LOLLOL"));
		assertEquals(0, bor.howManyStartsWithPrefix("LOLLOLEA"));
	}
}
