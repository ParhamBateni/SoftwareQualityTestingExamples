package W01_SpecificationBasedTesting;


import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class ArrayUtilsTest {

    @Test
    public void testLastIndexOfInvalidInputs() {
        assertEquals(-1, ArrayUtils.lastIndexOf(null, 1, 0));
        assertEquals(-1, ArrayUtils.lastIndexOf(new int[]{1, 2}, -1, 0));
        assertEquals(-1, ArrayUtils.lastIndexOf(new int[]{1, 2}, 1, -1));
    }

    @Test
    public void testLastIndexOfIndexNotFound() {
        assertEquals(-1, ArrayUtils.lastIndexOf(new int[]{1, 2}, 3, 0));
    }

    @Test
    public void testLastIndexOfIndexFound() {
        assertEquals(2, ArrayUtils.lastIndexOf(new int[]{1, 3, 3}, 3, 2));
        assertEquals(7, ArrayUtils.lastIndexOf(new int[]{1, 2, 3, 4, 1, 2, 3, 1}, 1, 10));
        assertEquals(4, ArrayUtils.lastIndexOf(new int[]{1, 2, 3, 4, 1, 2, 3, 1}, 1, 5));
    }

    @Test
    public void testIsSortedInvalidInput() {
        assertTrue(ArrayUtils.isSorted(null));
        assertTrue(ArrayUtils.isSorted(new int[]{}));
        assertTrue(ArrayUtils.isSorted(new int[]{0}));
    }


    @Test
    public void testIsSortedNotSorted() {
        assertFalse(ArrayUtils.isSorted(new int[]{1, 2, 3, 5, 4}));
        assertFalse(ArrayUtils.isSorted(new int[]{9, 2, 3, 4}));
        assertFalse(ArrayUtils.isSorted(new int[]{5, 4, 2}));
        assertFalse(ArrayUtils.isSorted(new int[]{1, 1, 6, 3, 3, 5, 4, 9}));
    }

    @Test
    public void testIsSortedIsSorted() {
        assertTrue(ArrayUtils.isSorted(new int[]{2, 3}));
        assertTrue(ArrayUtils.isSorted(new int[]{-2, -2, 2, 5, 5, 5, 7, 8}));
        assertTrue(ArrayUtils.isSorted(new int[]{-2, -2, 2, 5, 5, 5, 7, 8}));
        assertTrue(ArrayUtils.isSorted(new int[]{1, 1, 1, 1, 1, 1}));
    }
}
