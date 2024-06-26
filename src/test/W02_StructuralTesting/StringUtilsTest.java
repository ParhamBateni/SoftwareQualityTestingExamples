package W02_StructuralTesting;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {
    @Test
    public void testBasicInputs() {
        assertNull(StringUtils.repeat(null, 1));
        assertEquals("", StringUtils.repeat("a", 0));
        assertEquals("", StringUtils.repeat("", 2));
        assertEquals("ab", StringUtils.repeat("ab", 1));
        assertEquals("aa", StringUtils.repeat("a", 2));
        assertEquals(8193, StringUtils.repeat("a", 8193).length());
        assertEquals(8192, StringUtils.repeat("a", 8192).length());
        assertEquals("abab", StringUtils.repeat("ab", 2));
    }

    @Test
    public void testAdvancedInputs() {
        assertEquals("abcdabcdabcdabcd", StringUtils.repeat("abcd", 4));
    }
}