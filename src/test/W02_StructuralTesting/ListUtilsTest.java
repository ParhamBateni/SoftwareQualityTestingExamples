package W02_StructuralTesting;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ListUtilsTest {
    @Test
    public void testInvalidInput(){
        assertThrows(NullPointerException.class,()->{
            ListUtils.intersection(null,List.of());});
        assertThrows(NullPointerException.class,()->{ListUtils.intersection(List.of(),null);});
        assertThrows(NullPointerException.class,()->{ListUtils.intersection(null,null);});
    }

    @Test
    public void testBoundaries(){

    }
    @Test
    public void testNoIntersection(){
        assertEquals(List.of(),ListUtils.intersection(List.of(),List.of(1,2,3)));
        assertEquals(List.of(),ListUtils.intersection(List.of(1,2,3),List.of()));
        assertEquals(List.of(),ListUtils.intersection(List.of(1,2,3),List.of(5,6,7,8)));
        assertEquals(List.of(),ListUtils.intersection(List.of(5,6,7,8),List.of(1,2,3)));
    }
    @Test
    public void testHasIntersection(){
        assertEquals(List.of(1,2,3),ListUtils.intersection(List.of(1,2,3),List.of(1,2,3)));
        assertEquals(List.of(1,2,3),ListUtils.intersection(List.of(1,2,3),List.of(1,2,3,4,5,6)));
        assertEquals(List.of(1,2,3),ListUtils.intersection(List.of(1,2,3,4,5,6),List.of(1,2,3)));
        assertEquals(List.of(1,2,3),ListUtils.intersection(List.of(1,2,3,9,10),List.of(1,2,3,4,5,6)));
        assertEquals(List.of(1,2,3),ListUtils.intersection(List.of(1,2,3,4,5,6),List.of(1,2,3,9,10)));
        assertEquals(List.of(1,2,3),ListUtils.intersection(List.of(1,2,2,2,3,3,1),List.of(1,2,3,4,5,6)));
        assertEquals(List.of(1,2,3),ListUtils.intersection(List.of(1,2,3,4,5,6),List.of(1,2,2,2,3,3,1)));
        assertEquals(List.of(true),ListUtils.intersection(List.of(true,false),List.of(true)));
    }
}