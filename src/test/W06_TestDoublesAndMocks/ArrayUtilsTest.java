package W06_TestDoublesAndMocks;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;

public class ArrayUtilsTest {
    @Test
    public void testReverse() {
        int[] arr1 = new int[]{};
        int[] arr2 = new int[]{1};
        int[] arr3 = new int[]{1, 2, 3, 4};
        ArrayUtils.reverse(null, 1, 10);
        ArrayUtils.reverse(arr1, 0, 0);
        assertArrayEquals(new int[0], arr1);
        ArrayUtils.reverse(arr2, 0, 1);
        assertArrayEquals(new int[]{1}, arr2);
        ArrayUtils.reverse(arr3, 0, 2);
        assertArrayEquals(new int[]{2, 1, 3, 4}, arr3);
        ArrayUtils.reverse(arr3, -2, 2);
        assertArrayEquals(new int[]{1, 2, 3, 4}, arr3);
        ArrayUtils.reverse(arr3, 2, 10);
        assertArrayEquals(new int[]{1, 2, 4, 3}, arr3);
        ArrayUtils.reverse(arr3, 1, 1);
        assertArrayEquals(new int[]{1, 2, 4, 3}, arr3);
        ArrayUtils.reverse(arr3, 1, 0);
        assertArrayEquals(new int[]{1, 2, 4, 3}, arr3);
        ArrayUtils.reverse(arr3, 0, 6);
        assertArrayEquals(new int[]{3, 4, 2, 1}, arr3);
        ArrayUtils.reverse(arr3, 0, 3);
        assertArrayEquals(new int[]{2, 4, 3, 1}, arr3);
    }
}