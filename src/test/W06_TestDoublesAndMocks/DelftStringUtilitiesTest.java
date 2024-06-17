package W06_TestDoublesAndMocks;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class DelftStringUtilitiesTest {
    @Test
    public void testBaseCase() {
        assertNull(DelftStringUtilities.substringsBetween(null, "a", "b"));
        assertNull(DelftStringUtilities.substringsBetween("a", null, "b"));
        assertNull(DelftStringUtilities.substringsBetween("a", "", "b"));
        assertNull(DelftStringUtilities.substringsBetween("a", "a", null));
        assertNull(DelftStringUtilities.substringsBetween("a", "a", ""));
        assertArrayEquals(new String[0], DelftStringUtilities.substringsBetween("", "a", "b"));
    }

    @Test
    public void testNoSubstrings() {
        assertNull(DelftStringUtilities.substringsBetween("abcdedf", "qs", "rd"));
        assertNull(DelftStringUtilities.substringsBetween("abcdedf", "ab", "edg"));
        assertNull(DelftStringUtilities.substringsBetween("abcdedf", "ab", "ac"));
        assertNull(DelftStringUtilities.substringsBetween("abcdedf", "abd", "df"));
        assertNull(DelftStringUtilities.substringsBetween("abcdeghij", "abcdef", "ij"));
    }

    @Test
    public void testHasSubstring() {
        assertArrayEquals(List.of("cd").toArray(), DelftStringUtilities.substringsBetween("abcdedfgh", "ab", "ed"));
        assertArrayEquals(List.of("c", "fgh").toArray(), DelftStringUtilities.substringsBetween("abcedabfghed", "ab", "ed"));
        assertArrayEquals(List.of("fgh").toArray(), DelftStringUtilities.substringsBetween("abcdefghijk", "de", "ij"));
        assertArrayEquals(List.of("").toArray(), DelftStringUtilities.substringsBetween("abcdedf", "cd", "ed"));
//        assertEquals(List.of("ced","ededf").toArray(),DelftStringUtilities.substringsBetween("agbcghededfgh","gh","gh"));
    }
}