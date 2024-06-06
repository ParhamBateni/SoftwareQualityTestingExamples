package W07_IntegrationTesting;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;

import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class MathArraysTest {


    @Property
    public void testUnique(@ForAll @Size(min=0,max = 50) @UniqueElements List<Integer> list, @ForAll @IntRange(min=0,max=20) int numberOfRedundant){
        Random random = new Random(System.currentTimeMillis());
        double[] originalArray = convertListToArray(list.stream().sorted(Comparator.reverseOrder())
                .collect(Collectors.toList()));
        if (list.size()>0) {
            for (int i = 0; i < numberOfRedundant; i++) {
                list.add(list.get(random.nextInt(list.size())));
            }
        }
        double[] duplicatedArray = convertListToArray(list);
        assertArrayEquals(originalArray,MathArrays.unique(duplicatedArray),1e-6);
    }

    private double[] convertListToArray(List<Integer> numbers) {
        double[] array = numbers.stream().mapToDouble(x -> x).toArray();
        return array;
    }
}