package TrieTests;

import Trie.Trie;
import org.junit.Assert;
import org.junit.Test;

public class CornerCasesTest extends Assert {

	@Test
	public void emptyPrefix() {
		Trie trie = new Trie();
		assertFalse(trie.add(""));
		assertTrue(trie.contains(""));
		assertFalse(trie.add("LOL"));
		assertEquals(2, trie.howManyStartsWithPrefix(""));
		assertEquals(1, trie.howManyStartsWithPrefix("L"));
		assertEquals(2, trie.size());
		assertTrue(trie.remove(""));
		assertTrue(trie.contains("LOL"));
		assertEquals(1, trie.size());
		assertEquals(1, trie.howManyStartsWithPrefix(""));
	}
	

	@Test
	public void wholeWordPrefixLookup() {
		Trie trie = new Trie();
		assertFalse(trie.add("LOLLOL"));
		assertEquals(1, trie.howManyStartsWithPrefix("LOL"));
		assertEquals(1, trie.howManyStartsWithPrefix("LOLLOL"));
		assertEquals(0, trie.howManyStartsWithPrefix("LOLLOLEA"));
	}
}
