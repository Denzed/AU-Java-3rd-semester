package TrieTests;

import Trie.Trie;
import org.junit.Assert;
import org.junit.Test;

public class PrefixTest extends Assert {

	@Test
	public void testPrefixDoesNotExist() {
		Trie trie = new Trie();
		assertFalse(trie.add("LOLLOL"));
		assertTrue(trie.add("LOLLOL"));
		assertTrue(trie.contains("LOLLOL"));
		assertFalse(trie.contains("LOLLO"));
		assertEquals(1, trie.size());
		assertTrue(trie.remove("LOLLOL"));
		assertFalse(trie.remove("LOLLOL"));
		assertEquals(0, trie.size());
		assertFalse(trie.contains("LOLLOL"));
	}
	

	@Test
	public void testPrefixExists() {
		Trie trie = new Trie();
		assertFalse(trie.add("LOLLOL"));
		assertEquals(1, trie.howManyStartsWithPrefix("LOL"));
		assertFalse(trie.add("LOLLIPOP"));
		assertEquals(2, trie.howManyStartsWithPrefix("LOL"));
		assertEquals(0, trie.howManyStartsWithPrefix("O"));
		assertEquals(2, trie.size());
		assertTrue(trie.remove("LOLLOL"));
		assertEquals(1, trie.size());
		assertEquals(1, trie.howManyStartsWithPrefix("LOL"));
	}
}
