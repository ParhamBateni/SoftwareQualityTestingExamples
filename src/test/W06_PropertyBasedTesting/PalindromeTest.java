package W06_PropertyBasedTesting;

import static org.junit.Assert.*;

import W06_PropertyBasedTesting.Palindrome;
import net.jqwik.api.*;
import net.jqwik.api.constraints.*;

public class PalindromeTest {
    @Example
    public void testEdgeCases(){
        assertTrue(Palindrome.isPalindrome(""));
        assertTrue(Palindrome.isPalindrome("a"));
        assertTrue(Palindrome.isPalindrome("Aa"));
        assertFalse(Palindrome.isPalindrome("abcbad"));
        assertFalse(Palindrome.isPalindrome("eabcba"));
    }

    @Property
    public void testIsPalindrome1(@ForAll @AlphaChars String s){
        StringBuilder sb = new StringBuilder(s);
        sb.append(reverse(s));
        assertTrue(Palindrome.isPalindrome(sb.toString()));
    }
    @Property
    public void testIsPalindrome2(@ForAll @AlphaChars String s){
        StringBuilder sb = new StringBuilder(s);
        sb.append('a');
        sb.append(reverse(s));
        assertTrue(Palindrome.isPalindrome(sb.toString()));
    }
    @Property
    public void testIsPalindrome3(@ForAll @AlphaChars String s){
        StringBuilder sb = new StringBuilder(s);
        sb.append('a');
        sb.append('a');
        sb.append(reverse(s));
        assertTrue(Palindrome.isPalindrome(sb.toString()));
    }
    @Property
    public void testIsNotPalindrome1(@ForAll @AlphaChars String s){
        StringBuilder sb = new StringBuilder(s);
        sb.append('*');
        sb.append('&');
        assertFalse(Palindrome.isPalindrome(sb.toString()));
    }
    static String reverse(String str) {
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

}