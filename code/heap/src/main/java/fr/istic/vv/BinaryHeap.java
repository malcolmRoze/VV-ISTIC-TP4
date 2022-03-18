package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import net.jqwik.api.*;


public class BinaryHeapTest {
	
    @Property
    boolean heapTestPop() {
    	Comparator<Integer> c = (Integer a,Integer b) -> Integer.compare(a, b);
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(c, Integer.class);
        heap.push(2);
        heap.push(10);
        return heap.pop() == 2 && heap.pop() == 10;
    }
    
    @Property
    boolean heapTestPeek() {
    	Comparator<Integer> c = (Integer a,Integer b) -> Integer.compare(a, b);
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(c, Integer.class);
        heap.push(2);
        heap.push(10);
        return heap.peek() == 2 && heap.peek() == 2;
    }
    
    @Property
    boolean heapTestPush() {
    	Comparator<Integer> c = (Integer a,Integer b) -> Integer.compare(a, b);
        BinaryHeap<Integer> heap = new BinaryHeap<Integer>(c, Integer.class);
        heap.push(2);
        heap.push(10);
        heap.pop();
        heap.push(13);
        return heap.count() == 2 && heap.pop() == 10;
    }
    
    @Property
    <T> boolean heapTestMin(@ForAll("multipleBinaryHeap") BinaryHeap<T> heap) {
    	List<T> l = new ArrayList<T>();
    	while(heap.count() > 0) {
        	l.add(heap.pop());
        }
    	List<T> lSort = List.copyOf(l);
    	l.sort(heap.getComparator());
    	return l.equals(lSort);
    }
    
    @Property
    <T> boolean heapTestGeneric(@ForAll("multipleBinaryHeap") BinaryHeap<T> heap) {
        int len = heap.count();
        
        return heap.peek() == heap.pop() && heap.count()+1==len;
    }
    
    @Property
    <T> boolean heapTestThrow(@ForAll("multipleBinaryHeap") BinaryHeap<T> heap) {
        while(heap.count() > 0) {
        	heap.pop();
        }
        try {
        	heap.pop();
        	return false;
        }catch(NoSuchElementException e) {
        	return true;
        }
    }
    
    @Provide
	<T> Arbitrary<BinaryHeap<? extends Object>> multipleBinaryHeap() {
    	Comparator<Integer> cInt = (Integer a,Integer b) -> Integer.compare(a, b);
        BinaryHeap<Integer> heapInt = new BinaryHeap<Integer>(cInt, Integer.class);
		Arbitraries.integers().between(0, 100).forEachValue(v -> heapInt.push(v));
		
		Comparator<Character> cChar = (Character a,Character b) -> Character.compare(a, b);
        BinaryHeap<Character> heapChar = new BinaryHeap<Character>(cChar, Character.class);
		Arbitraries.chars().range('a', 'Z').digit().forEachValue(v -> heapChar.push(v));
		
		return Arbitraries.of(heapInt, heapChar);
	}
}
