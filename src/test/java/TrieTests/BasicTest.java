package TrieTests;

import Trie.Trie;
import org.junit.Assert;
import org.junit.Test;

public class BasicTest extends Assert {

	@Test
	public void Size() {
		Trie trie = new Trie();
		assertFalse(trie.add("LOLLOL"));
		assertTrue(trie.contains("LOLLOL"));
		assertFalse(trie.contains("OLOLO"));
		assertEquals(1, trie.size());
		assertTrue(trie.remove("LOLLOL"));
		assertEquals(0, trie.size());
		assertFalse(trie.contains("LOLLOL"));
	}
	

	@Test
	public void Prefix() {
		Trie trie = new Trie();
		assertFalse(trie.add("LOLLOL"));
		assertEquals(1, trie.howManyStartsWithPrefix("LOL"));
		assertEquals(0, trie.howManyStartsWithPrefix("O"));
		assertEquals(1, trie.size());
		assertTrue(trie.remove("LOLLOL"));
		assertEquals(0, trie.size());
		assertEquals(0, trie.howManyStartsWithPrefix("LOL"));
	}
}
