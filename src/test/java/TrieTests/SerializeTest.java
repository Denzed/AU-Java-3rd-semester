package TrieTests;

import Trie.Bor;
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
		Bor bor = new Bor();
		for (String s: sample) {
			assertFalse(bor.add(s));
		}
		PipedInputStream in = new PipedInputStream();
		PipedOutputStream out = new PipedOutputStream(in);
		bor.serialize(out);
		Bor result = new Bor();
		result.deserialize(in);
		assertTrue(bor.equals(result));
	}
}