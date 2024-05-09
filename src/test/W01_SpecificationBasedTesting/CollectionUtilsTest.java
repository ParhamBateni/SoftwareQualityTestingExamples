package W01_SpecificationBasedTesting;

import org.junit.Test;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class CollectionUtilsTest {
    @Test
    public void testContainsAnyEmpty() {
        Collection c1 = List.of();
        Collection c2 = List.of();
        Collection c3 = List.of(1, 2);
        assertTrue(!CollectionUtils.containsAny(c1, c2));
        assertTrue(!CollectionUtils.containsAny(c1, c3));
        assertTrue(!CollectionUtils.containsAny(c3, c1));
    }

    @Test
    public void testContainsAnyNoIntersection() {
        Collection c1 = List.of(1, 2);
        Collection c2 = List.of(3, 4, 5);
        assertTrue(!CollectionUtils.containsAny(c1, c2));
        assertTrue(!CollectionUtils.containsAny(c2, c1));
    }

    @Test
    public void testContainsAnyHasIntersection() {
        Collection c1 = List.of(1, 3);
        Collection c2 = List.of(3, 4, 5);
        assertTrue(CollectionUtils.containsAny(c1, c2));
        assertTrue(CollectionUtils.containsAny(c2, c1));
        assertTrue(CollectionUtils.containsAny(c1, c1));
    }
}
