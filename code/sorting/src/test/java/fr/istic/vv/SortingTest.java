package fr.istic.vv;

import net.jqwik.api.*;

public class SortingTest<T> {
	
	@Property
    boolean bubbleSortInt(@ForAll("tabInt") Integer[] tab) {
    	Integer[] t = Sorting.bubblesort(tab, (Integer a,Integer b) -> Integer.compare(a, b));
        for(int i=1; i<t.length; i++) {
        	if(t[i] < t[i-1]) return false;
        }
        return true;
    }
    
    @Property
    boolean bubbleSortChar(@ForAll("tabChar") Character[] tab) {
    	Character[] t = Sorting.bubblesort(tab, (Character a,Character b) -> Character.compare(a, b));
    	for(int i=1; i<t.length; i++) {
        	if(t[i] < t[i-1]) return false;
        }
        return true;
    }
    
    @Property
    boolean quickSortInt(@ForAll("tabInt") Integer[] tab) {
    	Integer[] t = Sorting.quicksort(tab, (Integer a,Integer b) -> Integer.compare(a, b));
        for(int i=1; i<t.length; i++) {
        	if(t[i] < t[i-1]) return false;
        }
        return true;
    }
    
    @Property
    boolean quickSortChar(@ForAll("tabChar") Character[] tab) {
    	Character[] t = Sorting.quicksort(tab, (Character a,Character b) -> Character.compare(a, b));
    	for(int i=1; i<t.length; i++) {
        	if(t[i] < t[i-1]) return false;
        }
        return true;
    }
    
    @Property
    boolean mergeSortInt(@ForAll("tabInt") Integer[] tab) {
    	Integer[] t = Sorting.mergesort(tab, (Integer a,Integer b) -> Integer.compare(a, b));
        for(int i=1; i<t.length; i++) {
        	if(t[i] < t[i-1]) return false;
        }
        return true;
    }
    
    @Property
    boolean mergeSortChar(@ForAll("tabChar") Character[] tab) {
    	Character[] t = Sorting.mergesort(tab, (Character a,Character b) -> Character.compare(a, b));
    	for(int i=1; i<t.length; i++) {
        	if(t[i] < t[i-1]) return false;
        }
        return true;
    }
    
    @Provide
    Arbitrary<Integer[]> tabInt() {
    	Arbitrary<Integer> ints = Arbitraries.integers().between(1, 1000);
    	Arbitrary<Integer[]> tab = ints.array(Integer[].class).ofMinSize(0).ofMaxSize(100);
    	return tab;
    }
    
    @Provide
    Arbitrary<Character[]> tabChar() {
    	Arbitrary<Character> chars = Arbitraries.chars().ascii();
    	Arbitrary<Character[]> tab = chars.array(Character[].class).ofMinSize(0).ofMaxSize(100);
    	return tab;
    }
}
