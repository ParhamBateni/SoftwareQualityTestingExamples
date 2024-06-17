package W08_AllTestingMethods;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ZigZagTest {
    ZigZag zigZag = new ZigZag();

    @Test
    public void testPreconditions() {
        assertThrows(IllegalArgumentException.class, () -> zigZag.zigzag("", 1), "1 <= s.length <= 1000");
        assertThrows(IllegalArgumentException.class, () -> zigZag.zigzag("a".repeat(1001), 1), "1 <= s.length <= 1000");
        assertThrows(IllegalArgumentException.class, () -> zigZag.zigzag("abc", 0), "1 <= numRows <= 1000");
        assertThrows(IllegalArgumentException.class, () -> zigZag.zigzag("abc", 1001), "1 <= numRows <= 1000");
    }

    @Test
    public void testZigZag() {
        assertEquals("abc", zigZag.zigzag("abc", 1));
        assertEquals("a\nb\nc", zigZag.zigzag("abc", 4));
        assertEquals("ac\nb", zigZag.zigzag("abc", 2));
        assertEquals("ace\nbdf", zigZag.zigzag("abcdef", 2));
        assertEquals("a e\nbdf\nc", zigZag.zigzag("abcdef", 3));
        assertEquals("a\n".repeat(1000).trim(), zigZag.zigzag("a".repeat(1000), 1000));
        assertEquals("a", zigZag.zigzag("a", 10));
    }
}