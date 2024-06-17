package W06_TestDoublesAndMocks;

import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.DoubleRange;

import static org.junit.Assert.assertEquals;

public class TaxIncomeTest {
    TaxIncome taxIncome = new TaxIncome();
    double delta = 1e-6;

    @Property
    public void testProperty1(@ForAll @DoubleRange(min = 0, max = 22100, maxIncluded = false) double income) {
        assertEquals(income * 0.15, taxIncome.calculate(income), delta);
    }

    @Property
    public void testProperty2(@ForAll @DoubleRange(min = 22100, max = 53500, maxIncluded = false) double income) {
        assertEquals(3315 + 0.28 * (income - 22100), taxIncome.calculate(income), delta);
    }

    @Property
    public void testProperty3(@ForAll @DoubleRange(min = 53500, max = 115000, maxIncluded = false) double income) {
        assertEquals(12107 + 0.31 * (income - 53500), taxIncome.calculate(income), delta);
    }

    @Property
    public void testProperty4(@ForAll @DoubleRange(min = 115000, max = 250000, maxIncluded = false) double income) {
        assertEquals(31172 + 0.36 * (income - 115000), taxIncome.calculate(income), delta);
    }

    @Property
    public void testProperty5(@ForAll @DoubleRange(min = 250000) double income) {
        assertEquals(79772 + 0.396 * (income - 250000), taxIncome.calculate(income), delta);
    }

    @Property
    public void testProperty6(@ForAll @DoubleRange(min = -Double.MAX_VALUE, max = 0, maxIncluded = false) double income) {
        assertEquals(TaxIncome.CANNOT_CALC_TAX, taxIncome.calculate(income), delta);
    }
}