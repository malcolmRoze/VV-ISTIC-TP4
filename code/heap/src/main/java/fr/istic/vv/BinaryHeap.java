package fr.istic.vv;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class BinaryHeap<T> {
	
	private Comparator<T> comparator;
	private T[] heap;
	private int len;

    @SuppressWarnings("unchecked")
	public BinaryHeap(Comparator<T> comparator, Class<?> clazz) {
    	this.comparator = comparator;
    	heap = (T[]) Array.newInstance(clazz, 0);
    	len = -1;
    }

    public Comparator<T> getComparator() {
		return comparator;
	}

	public T pop() {
    	if(len<0) throw new NoSuchElementException();
    	T t = heap[len];
    	len--;
    	heap = Arrays.copyOf(heap, len+1);
    	return t;
    }

    public T peek() {
    	if(len<0) throw new NoSuchElementException();
    	return heap[len];
    }

    public void push(T element) {
    	len++;
    	heap = Arrays.copyOf(heap, len+1);
    	heap[len] = element;
    	if(len == 0) return;
    	
    	int tmpLen = len;
    	while(tmpLen > 0 && comparator.compare(heap[tmpLen], heap[tmpLen-1]) > 0) {
    		T tmp = heap[tmpLen];
    		heap[tmpLen] = heap[tmpLen-1];
    		heap[tmpLen-1] = tmp;
    		tmpLen--;
    	}
    }

    public int count() {
    	return len+1;
    }

}
