package TrieTests;

import Trie.Bor;
import org.junit.Assert;
import org.junit.Test;

public class BasicTest extends Assert {

	@Test
	public void Size() {
		Bor bor = new Bor();
		assertFalse(bor.add("LOLLOL"));
		assertTrue(bor.contains("LOLLOL"));
		assertFalse(bor.contains("OLOLO"));
		assertEquals(1, bor.size());
		assertTrue(bor.remove("LOLLOL"));
		assertEquals(0, bor.size());
		assertFalse(bor.contains("LOLLOL"));
	}
	

	@Test
	public void Prefix() {
		Bor bor = new Bor();
		assertFalse(bor.add("LOLLOL"));
		assertEquals(1, bor.howManyStartsWithPrefix("LOL"));
		assertEquals(0, bor.howManyStartsWithPrefix("O"));
		assertEquals(1, bor.size());
		assertTrue(bor.remove("LOLLOL"));
		assertEquals(0, bor.size());
		assertEquals(0, bor.howManyStartsWithPrefix("LOL"));
	}
}
