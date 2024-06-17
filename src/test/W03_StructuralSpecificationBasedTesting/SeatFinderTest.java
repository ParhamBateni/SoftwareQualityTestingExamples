package W03_StructuralSpecificationBasedTesting;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class SeatFinderTest {
    public static Stream<Arguments> illegalGenerator() {
        return Stream.of(
                Arguments.of("prices and taken arrays cannot be null", null, new boolean[]{}, 10),
                Arguments.of("prices and taken arrays cannot be null", new double[]{}, null, 10),
                Arguments.of("prices and taken arrays do not have the same length", new double[]{1}, new boolean[]{}, 10),
                Arguments.of("prices and taken arrays do not have the same length", new double[]{}, new boolean[]{false}, 10),
                Arguments.of("The number of seats has to be at least 1", new double[]{1}, new boolean[]{false}, -1),
                Arguments.of("The number of seats has to be at least 1", new double[]{1}, new boolean[]{false}, 0)
        );
    }

    public static Stream<Arguments> generator() {
        return Stream.of(
                Arguments.of(new double[]{1, 2, 3}, new boolean[]{false, true, false}, 1, 1),
                Arguments.of(new double[]{1, 2, 3}, new boolean[]{true, true, true}, 3, 0),
                Arguments.of(new double[]{1, 2, 3}, new boolean[]{false, false, false}, 5, 6),
                Arguments.of(new double[]{100, 200, 400, 900}, new boolean[]{true, true, false, false}, 2, 1295)
        );
    }

    @ParameterizedTest
    @MethodSource("illegalGenerator")
    public void testGetCheapestPriceIllegalArguments(String exceptionMessage, double[] prices, boolean[] taken, int numberOfSeats) {
        assertThrows(exceptionMessage, IllegalArgumentException.class, () -> SeatFinder.getCheapestPrice(prices, taken, numberOfSeats));
    }


    @ParameterizedTest
    @MethodSource("generator")
    public void testGetCheapestPrice(double[] prices, boolean[] taken, int numberOfSeats, double expected) {
        assertEquals(expected, SeatFinder.getCheapestPrice(prices, taken, numberOfSeats), 0.01);
    }

}