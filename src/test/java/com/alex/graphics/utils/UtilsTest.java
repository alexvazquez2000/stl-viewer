package com.alex.graphics.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UtilsTest {

	@Test
	void testNull() {
		assertNull(Utils.firstCharToUpper(null));
	}
	
	@Test
	void testBlank() {
		assertEquals("", Utils.firstCharToUpper(""));
	}

	@Test
	void testOneChar() {
		assertEquals("A", Utils.firstCharToUpper("a"));
	}

	@Test
	void testFirstCharToUpper() {
		assertEquals("Alex", Utils.firstCharToUpper("alex"));
		assertEquals("AlEx", Utils.firstCharToUpper("AlEx"));
	}

}
