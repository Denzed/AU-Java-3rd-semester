package TrieTests;

import Trie.Trie;
import org.junit.Assert;
import org.junit.Test;
import java.io.PipedOutputStream;
import java.io.IOException;
import java.io.PipedInputStream;

public class SerializeTest extends Assert {

	@Test
	public void test() throws IOException {
		String sample[] = {"GREEK", "QUESTION", "MARK",
		                   "LOOKS", "LIKE", "SEMICOLON"};
		Trie trie = new Trie();
		for (String s: sample) {
			assertFalse(trie.add(s));
		}
		PipedInputStream in = new PipedInputStream();
		PipedOutputStream out = new PipedOutputStream(in);
		trie.serialize(out);
		Trie result = new Trie();
		result.deserialize(in);
		assertTrue(trie.equals(result));
	}
}