package W04_PropertyBasedTesting;

import org.junit.Test;

import static org.junit.Assert.*;

public class SplittingTest {
    @Test
    public void testBaseCase(){
        assertFalse(Splitting.canBalance(null));
        assertFalse(Splitting.canBalance(new int[] {}));
        assertFalse(Splitting.canBalance(new int[] {0}));
        assertFalse(Splitting.canBalance(new int[] {1,2}));
    }

    @Test
    public void testCanBalance(){
        assertTrue(Splitting.canBalance(new int[] {1,2,3,0,6}));
        assertTrue(Splitting.canBalance(new int[] {8, 2, 2, 3, 1}));
        assertFalse(Splitting.canBalance(new int[]{1,2,3,4,6}));
        assertTrue(Splitting.canBalance(new int[] {0,0,0,0}));
    }
}