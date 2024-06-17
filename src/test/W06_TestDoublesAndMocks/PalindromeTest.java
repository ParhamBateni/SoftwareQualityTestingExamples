package W06_TestDoublesAndMocks;

import net.jqwik.api.Example;
import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import net.jqwik.api.constraints.AlphaChars;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PalindromeTest {
    static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

    @Example
    public void testEdgeCases() {
        assertTrue(Palindrome.isPalindrome(""));
        assertTrue(Palindrome.isPalindrome("a"));
        assertTrue(Palindrome.isPalindrome("Aa"));
        assertFalse(Palindrome.isPalindrome("abcbad"));
        assertFalse(Palindrome.isPalindrome("eabcba"));
    }

    @Property
    public void testIsPalindrome1(@ForAll @AlphaChars String s) {
        assertTrue(Palindrome.isPalindrome(s + reverse(s)));
    }

    @Property
    public void testIsPalindrome2(@ForAll @AlphaChars String s) {
        String sb = s + 'a' +
                reverse(s);
        assertTrue(Palindrome.isPalindrome(sb));
    }

    @Property
    public void testIsPalindrome3(@ForAll @AlphaChars String s) {
        String sb = s + 'a' +
                'a' +
                reverse(s);
        assertTrue(Palindrome.isPalindrome(sb));
    }

    @Property
    public void testIsNotPalindrome1(@ForAll @AlphaChars String s) {
        String sb = s + '*' +
                '&';
        assertFalse(Palindrome.isPalindrome(sb));
    }

}