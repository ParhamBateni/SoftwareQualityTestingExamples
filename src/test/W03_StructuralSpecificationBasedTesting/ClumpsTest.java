package W03_StructuralSpecificationBasedTesting;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ClumpsTest {
    @Test
    public void testZeroClump() {
        assertEquals(0, Clumps.countClumps(null));
        assertEquals(0, Clumps.countClumps(new int[]{}));
        assertEquals(0, Clumps.countClumps(new int[]{1}));
        assertEquals(0, Clumps.countClumps(new int[]{1, 2, 3}));
    }

    @Test
    public void testOneClump() {
        assertEquals(1, Clumps.countClumps(new int[]{1, 1}));
        assertEquals(1, Clumps.countClumps(new int[]{1, 1, 1}));
    }

    @Test
    public void testManyClumps() {
        assertEquals(2, Clumps.countClumps(new int[]{3, 3, 3, 3, 2, 2}));
        assertEquals(3, Clumps.countClumps(new int[]{1, 1, 1, 2, 2, 4, 4, 6}));
        assertEquals(4, Clumps.countClumps(new int[]{1, 1, 3, 3, 3, 5, 5, 5, 6, 6, 7}));
    }
}
