package TrieTests;

import Trie.Trie;
import org.junit.Assert;
import org.junit.Test;

public class DoubleActionTest extends Assert {

	@Test
	public void doubleInsert() {
		String sample = "NICETOMEETYOU";
		Trie trie = new Trie();
		assertFalse(trie.add(sample));
		assertEquals(1, trie.size());
		assertTrue(trie.add(sample));
		assertEquals(1, trie.size());
		assertTrue(trie.contains(sample));
	}
	

	@Test
	public void doubleRemove() {
		String sample = "ORNOT";
		Trie trie = new Trie();
		assertFalse(trie.add(sample));
		assertEquals(1, trie.size());
		assertTrue(trie.remove(sample));
		assertEquals(0, trie.size());
		assertFalse(trie.remove(sample));
		assertEquals(0, trie.size());
	}
}
