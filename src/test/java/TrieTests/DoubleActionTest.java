package TrieTests;

import Trie.Trie;
import org.junit.Assert;
import org.junit.Test;

public class DoubleActionTest extends Assert {

	@Test
	public void doubleInsert() {
		String sample = "NICETOMEETYOU";
		Trie bor = new Trie();
		assertFalse(bor.add(sample));
		assertEquals(1, bor.size());
		assertTrue(bor.add(sample));
		assertEquals(1, bor.size());
		assertTrue(bor.contains(sample));
	}
	

	@Test
	public void doubleRemove() {
		String sample = "ORNOT";
		Trie bor = new Trie();
		assertFalse(bor.add(sample));
		assertEquals(1, bor.size());
		assertTrue(bor.remove(sample));
		assertEquals(0, bor.size());
		assertFalse(bor.remove(sample));
		assertEquals(0, bor.size());
	}
}
