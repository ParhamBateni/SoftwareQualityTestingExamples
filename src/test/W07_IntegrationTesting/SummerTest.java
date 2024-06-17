package W07_IntegrationTesting;

import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SummerTest {
    @Example
    public void testEdgeCases() {
        List<Double> temperatures = List.of(18.0, 19.0, 20.0, 21.0, 22.0, 23.0, 24.0, 25.0);
        assertTrue(Summer.isItSummer(temperatures));
    }

    @Property
    public void testIsItSummer(@ForAll List<@DoubleRange(min = 20.0) Double> temperaturesHigher,
                               @ForAll List<@DoubleRange(max = 20.0, maxIncluded = false) Double> temperaturesLower) {
        List<Double> temperatures = new ArrayList<>(temperaturesHigher);
        temperatures.addAll(temperaturesLower);
        Collections.shuffle(temperatures);
        assertEquals(temperaturesHigher.size() >= 0.75 * temperatures.size(), Summer.isItSummer(temperatures));
    }
}