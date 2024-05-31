package W04_PropertyBasedTesting;

import net.jqwik.api.*;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LeapYearTest {
    @Provide
    Arbitrary<Integer> P1() {
        return Arbitraries.integers().filter(x -> x % 400 == 0);
    }

    @Provide
    Arbitrary<Integer> P2() {
        return Arbitraries.integers().filter(x -> x % 400 != 0 && x % 100 == 0);
    }

    @Provide
    Arbitrary<Integer> P3() {
        return Arbitraries.integers().filter(x -> x % 400 != 0 && x % 100 != 0 && x % 4 == 0);
    }

    @Provide
    Arbitrary<Integer> P4() {
        return Arbitraries.integers().filter(x -> x % 400 != 0 && x % 100 != 0 && x % 4 != 0);
    }

    @Property
    void yearMultipleOf400(@ForAll("P1") int year) {
        assertTrue(LeapYear.isLeapYear(year));
    }

    @Property
    void yearMultipleOf100(@ForAll("P2") int year) {
        assertFalse(LeapYear.isLeapYear(year));
    }

    @Property
    void yearMultipleOf4(@ForAll("P3") int year) {
        assertTrue(LeapYear.isLeapYear(year));
    }

    @Property
    void yearNotMultipleOf4(@ForAll("P4") int year) {
        assertFalse(LeapYear.isLeapYear(year));
    }
}