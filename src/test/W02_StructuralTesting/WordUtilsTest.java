package W02_StructuralTesting;

import org.junit.Test;

import static org.junit.Assert.*;

public class WordUtilsTest {
    @Test
    public void testEmpty(){
        assertEquals(null, WordUtils.swapCase(null));
        assertEquals("",WordUtils.swapCase(""));
    }

    @Test
    public void testAllLowerCase(){
        assertEquals("a",WordUtils.swapCase("A"));
        assertEquals("aaa",WordUtils.swapCase("AAA"));
        assertEquals("abc",WordUtils.swapCase("ABC"));
    }

    @Test
    public void testAllUpperCase(){
        assertEquals("A",WordUtils.swapCase("a"));
        assertEquals("AAA",WordUtils.swapCase("aaa"));
        assertEquals("ABC",WordUtils.swapCase("abc"));
    }
    @Test
    public void testMixed(){
        assertEquals("AaAa",WordUtils.swapCase("aAaA"));
        assertEquals("aBcA",WordUtils.swapCase("AbCa"));
        assertEquals("AcaEW!?",WordUtils.swapCase("aCAew!?"));
    }
}