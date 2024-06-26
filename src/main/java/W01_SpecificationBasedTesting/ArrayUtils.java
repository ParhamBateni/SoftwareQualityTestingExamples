package W01_SpecificationBasedTesting;

public class ArrayUtils {

    /**
     * The index value when an element is not found in a list or array: {@code -1}.
     * This value is returned by methods in this class and can also be used in
     * comparisons with values returned by various method from
     * {@link java.util.List}.
     */
    public static final int INDEX_NOT_FOUND = -1;

    /**
     * <code>ArrayUtils</code> should not normally be instantiated.
     */
    private ArrayUtils() {
    }

    /**
     * Finds the last index of the given value in the array starting at the given
     * index.
     *
     * <p>
     * This method returns {@link #INDEX_NOT_FOUND} ({@code -1}) for a {@code null}
     * input array.
     *
     * <p>
     * A negative startIndex will return {@link #INDEX_NOT_FOUND} ({@code -1}). A
     * startIndex larger than the array length will search from the end of the
     * array.
     *
     * @param array       the array to traverse for looking for the object, may be
     *                    {@code null}
     * @param valueToFind the value to find
     * @param startIndex  the start index to traverse backwards from
     * @return the last index of the value within the array,
     * {@link #INDEX_NOT_FOUND} ({@code -1}) if not found or {@code null}
     * array input
     */
    public static int lastIndexOf(final int[] array, final int valueToFind, int startIndex) {
        if (array == null) {
            return INDEX_NOT_FOUND;
        }
        if (startIndex < 0) {
            return INDEX_NOT_FOUND;
        } else if (startIndex >= array.length) {
            startIndex = array.length - 1;
        }
        for (int i = startIndex; i >= 0; i--) {
            if (valueToFind == array[i]) {
                return i;
            }
        }
        return INDEX_NOT_FOUND;
    }

    /**
     * This method checks whether the provided array is sorted according to natural
     * ordering.
     *
     * @param array the array to check
     * @return whether the array is sorted according to natural ordering
     * @since 3.4
     */
    public static boolean isSorted(final int[] array) {
        if (array == null || array.length < 2) {
            return true;
        }
        int previous = array[0];
        final int n = array.length;
        for (int i = 1; i < n; i++) {
            final int current = array[i];
            if (previous > current) {
                return false;
            }
            previous = current;
        }
        return true;
    }
}
