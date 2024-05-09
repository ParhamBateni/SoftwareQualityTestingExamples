package W03_StructuralSpecificationBasedTesting;

import java.util.HashSet;
import java.util.Set;

public class CaseUtils {
    /**
     * Converts all the delimiter separated words in a String into camelCase, that
     * is each word is made up of a titlecase character and then a series of
     * lowercase characters.
     *
     * <p>
     * The delimiters represent a set of characters understood to separate words.
     * The first non-delimiter character after a delimiter will be capitalized. The
     * first String character may or may not be capitalized and it's determined by
     * the user input for capitalizeFirstLetter variable.
     *
     * <p>
     * A <code>null</code> input String returns <code>null</code>. Capitalization
     * uses the Unicode title case, normally equivalent to upper case and cannot
     * perform locale-sensitive mappings.
     *
     * @param str
     *            the String to be converted to camelCase, may be null
     * @param capitalizeFirstLetter
     *            boolean that determines if the first character of first word
     *            should be title case.
     * @param delimiters
     *            set of characters to determine capitalization, null and/or empty
     *            array means whitespace
     * @return camelCase of String, <code>null</code> if null String input
     */
    public static String toCamelCase(String str, final boolean capitalizeFirstLetter, final char... delimiters) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        str = str.toLowerCase();
        final int strLen = str.length();
        final int[] newCodePoints = new int[strLen];
        int outOffset = 0;
        final Set<Integer> delimiterSet = generateDelimiterSet(delimiters);
        boolean capitalizeNext = false;
        if (capitalizeFirstLetter) {
            capitalizeNext = true;
        }
        for (int index = 0; index < strLen;) {
            final int codePoint = str.codePointAt(index);
            if (delimiterSet.contains(codePoint)) {
                capitalizeNext = true;
                if (outOffset == 0) {
                    capitalizeNext = false;
                }
                index += Character.charCount(codePoint);
            } else if (capitalizeNext) {
                final int titleCaseCodePoint = Character.toTitleCase(codePoint);
                newCodePoints[outOffset++] = titleCaseCodePoint;
                index += Character.charCount(titleCaseCodePoint);
                capitalizeNext = false;
            } else {
                newCodePoints[outOffset++] = codePoint;
                index += Character.charCount(codePoint);
            }
        }
        if (outOffset != 0) {
            return new String(newCodePoints, 0, outOffset);
        }
        return str;
    }

    /**
     * Converts an array of delimiters to a hash set of code points. Code point of
     * space(32) is added as the default value. The generated hash set provides O(1)
     * lookup time.
     *
     * @param delimiters
     *            set of characters to determine capitalization, null means
     *            whitespace
     * @return Set<Integer>
     */
    private static Set<Integer> generateDelimiterSet(final char[] delimiters) {
        final Set<Integer> delimiterHashSet = new HashSet<>();
        delimiterHashSet.add(Character.codePointAt(new char[]{' '}, 0));
        if (delimiters == null || delimiters.length == 0) {
            return delimiterHashSet;
        }
        for (int index = 0; index < delimiters.length; index++) {
            delimiterHashSet.add(Character.codePointAt(delimiters, index));
        }
        return delimiterHashSet;
    }
}

