package W08_AllTestingMethods;

import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArrayUtilsTest {

    @Example
    void testPrecondition() {
        assertEquals(ArrayUtils.INDEX_NOT_FOUND, ArrayUtils.search(null, 10, 10));
    }

    @Property
    void indexNotFoundProperty(@ForAll @Size(max = 50) List<Integer> array, @ForAll int valueToFind) {
        assertEquals(ArrayUtils.INDEX_NOT_FOUND, ArrayUtils.search(convertListToArray(array),
                valueToFind, array.lastIndexOf(valueToFind) + 1));
    }

    @Property
    void indexFoundProperty(@ForAll @Size(min = 1, max = 50) List<Integer> array, @ForAll @IntRange(min = 1, max = 50) int index) {
        index = index > array.size() - 1 ? array.size() - 1 : index;
        assertEquals(index, ArrayUtils.search(convertListToArray(array),
                array.get(index), array.subList(0, index).lastIndexOf(array.get(index)) + 1));
    }

    @Property
    void startIndexNegativeProperty(@ForAll @Size(min = 1, max = 50) List<Integer> array, @ForAll int valueToFind, @ForAll @IntRange(min = -50, max = 0) int index) {
        assertEquals(ArrayUtils.search(convertListToArray(array), valueToFind, index), ArrayUtils.search(convertListToArray(array), valueToFind, 0));
    }


    /**
     * Use this method to convert a list of integers to an array
     */
    private int[] convertListToArray(List<Integer> numbers) {
        int[] array = numbers.stream().mapToInt(x -> x).toArray();
        return array;
    }
}