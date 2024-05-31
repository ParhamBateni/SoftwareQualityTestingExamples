package W04_PropertyBasedTesting;

import net.jqwik.api.*;

import static org.junit.Assert.*;

public class TwoIntegersTest {
    private TwoIntegers ti = new TwoIntegers();
    @Provide
    Arbitrary<Integer> P1(){
        return Arbitraries.integers().lessOrEqual(0);
    }
    @Provide
    Arbitrary<Integer> P2(){
        return Arbitraries.integers().between(1,99);
    }
    @Provide
    Arbitrary<Integer> P3(){
        return Arbitraries.integers().greaterOrEqual(100);
    }

    @Property
    public void testErrorCase1(@ForAll("P1") int n, @ForAll("P1") int m){
        assertThrows(IllegalArgumentException.class,()-> ti.sum(n,m));
    }

    @Property
    public void testErrorCase2(@ForAll("P2") int n, @ForAll("P1") int m){
        assertThrows(IllegalArgumentException.class,()-> ti.sum(n,m));
    }
    @Property
    public void testErrorCase3(@ForAll("P2") int n, @ForAll("P3") int m){
        assertThrows(IllegalArgumentException.class,()-> ti.sum(n,m));
    }
    @Property
    public void testErrorCase4(@ForAll("P3") int n, @ForAll("P1") int m){
        assertThrows(IllegalArgumentException.class,()-> ti.sum(n,m));
    }
    @Property
    public void testSumCase(@ForAll("P2") int n, @ForAll("P2") int m){
        assertEquals(n+m,ti.sum(n,m));
    }

    @Example
    public void testEdgeCases(){
        assertEquals(2,ti.sum(1,1));
        assertEquals(100,ti.sum(99,1));
        assertEquals(100,ti.sum(1,99));
        assertEquals(2*99,ti.sum(99,99));
        assertThrows(IllegalArgumentException.class,()->ti.sum(0,50));
        assertThrows(IllegalArgumentException.class,()->ti.sum(1,0));
        assertThrows(IllegalArgumentException.class,()->ti.sum(1,100));
        assertThrows(IllegalArgumentException.class,()->ti.sum(100,50));
        assertThrows(IllegalArgumentException.class,()->ti.sum(100,1));
        assertThrows(IllegalArgumentException.class,()->ti.sum(100,100));
    }
}