package W03_StructuralSpecificationBasedTesting;

import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class CollectionUtilsTest {

    @ParameterizedTest
    @MethodSource("generator")
    public void testIsEqualCollection(Collection<?> c1 , Collection<?> c2, boolean expected){
        assertEquals(expected,CollectionUtils.isEqualCollection(c1,c2));
    }
    public static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(List.of(1,2,3), List.of(1,2),false),
                Arguments.of(List.of(2,3),List.of(2,3,4),false),
                Arguments.of(List.of(1,3,4),List.of(3,1,4),true),
                Arguments.of(List.of(1,2,3),List.of(1,2,3),true),
                Arguments.of(List.of(),List.of(1,2,3),false),
                Arguments.of(List.of(1,2,3),List.of(),false),
                Arguments.of(List.of(),List.of(),true),
                Arguments.of(List.of(1,2,3,3,3),List.of(1,2,2,2,2),false),
                Arguments.of(List.of(1,2,3,3,3),List.of(1,2,2,3,3),false),
                Arguments.of(List.of(1,2,3),List.of("1","2","3"),false)
        );
    }


}