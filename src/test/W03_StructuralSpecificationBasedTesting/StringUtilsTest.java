package W03_StructuralSpecificationBasedTesting;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StringUtilsTest {

    @Test
    public void testReplaceBaseCase() {
        assertEquals("", StringUtils.replace("", "abc", "abc", 10, true));
        assertNull(StringUtils.replace(null, "adf", "adsf", 10, true));
        assertEquals("SDF", StringUtils.replace("SDF", null, "adsf", 10, true));
        assertEquals("SDF", StringUtils.replace("SDF", "adf", null, 10, true));
        assertEquals("abc", StringUtils.replace("abc", "", "abc", 10, true));
        assertEquals("abc", StringUtils.replace("abc", "abc", null, 10, true));
        assertEquals("abc", StringUtils.replace("abc", "abc", "abc", 0, true));
    }

    @Test
    public void testReplaceIndexNotFound() {
        assertEquals("abcDE", StringUtils.replace("abcDE", "PO", "a", 10, true));
        assertEquals("abcDE", StringUtils.replace("abcDE", "P", "a", 10, false));
    }

    @Test
    public void testReplaceSuccessful() {
        assertEquals("abcDEf", StringUtils.replace("abcDEp", "p", "f", 1, false));
        assertEquals("abcDEf", StringUtils.replace("abcDEP", "p", "f", 2, true));
        assertEquals("efghdefgh", StringUtils.replace("abcdabc", "abc", "efgh", 70, false));
        assertEquals("abcdefg", StringUtils.replace("abcdYUIX", "YUIX", "efg", -1, false));
        assertEquals("abcabcdefDEF", StringUtils.replace("DEFdefdefDEF", "DEF", "abc", 2, true));
        assertEquals("abcdefdefabc", StringUtils.replace("DEFdefdefDEF", "DEF", "abc", 2, false));
        assertEquals("DEfGhijkabc", StringUtils.replace("abcabc", "abc", "DEfGhijk", 1, false));
    }

}