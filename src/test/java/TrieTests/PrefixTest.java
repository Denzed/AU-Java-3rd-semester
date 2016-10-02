package TrieTests;

import Trie.Bor;
import org.junit.Assert;
import org.junit.Test;

public class PrefixTest extends Assert {

	@Test
	public void testPrefixDoesNotExist() {
		Bor bor = new Bor();
		assertFalse(bor.add("LOLLOL"));
		assertTrue(bor.add("LOLLOL"));
		assertTrue(bor.contains("LOLLOL"));
		assertFalse(bor.contains("LOLLO"));
		assertEquals(1, bor.size());
		assertTrue(bor.remove("LOLLOL"));
		assertFalse(bor.remove("LOLLOL"));
		assertEquals(0, bor.size());
		assertFalse(bor.contains("LOLLOL"));
	}
	

	@Test
	public void testPrefixExists() {
		Bor bor = new Bor();
		assertFalse(bor.add("LOLLOL"));
		assertEquals(1, bor.howManyStartsWithPrefix("LOL"));
		assertFalse(bor.add("LOLLIPOP"));
		assertEquals(2, bor.howManyStartsWithPrefix("LOL"));
		assertEquals(0, bor.howManyStartsWithPrefix("O"));
		assertEquals(2, bor.size());
		assertTrue(bor.remove("LOLLOL"));
		assertEquals(1, bor.size());
		assertEquals(1, bor.howManyStartsWithPrefix("LOL"));
	}
}
