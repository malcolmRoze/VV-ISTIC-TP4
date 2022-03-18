package fr.istic.vv;

import java.util.Arrays;
import java.util.Comparator;

public class Sorting {
	
	private static <T> void swap(T[] array, int i, int j) {
		T tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	public static <T> T[] bubblesort(T[] array, Comparator<T> comparator) {
		T[] newArray = Arrays.copyOf(array, array.length);
		
		for (int i = 0; i < newArray.length-1; i++) {
			for (int j = 0; j < newArray.length-i-1; j++) {
				if (comparator.compare(newArray[j], newArray[j+1]) > 0) {
					swap(newArray, j, j+1);
				}
			}
		}
		return newArray;
	}

	private static <T> int partition(T array[], Comparator<T> comparator, int begin, int end) {
		T pivot = array[end];
		int i = (begin - 1);

		for (int j = begin; j < end; j++) {
			if (comparator.compare(array[j], pivot) <= 0) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, i+1, end);
		return i + 1;
	}

	private static <T> void quicksortAux(T[] array, Comparator<T> comparator, int begin, int end) {
		if (begin < end) {
			int i = partition(array, comparator, begin, end);

			quicksortAux(array, comparator, begin, i-1);
			quicksortAux(array, comparator, i+1, end);
		}
	}

	public static <T> T[] quicksort(T[] array, Comparator<T> comparator) {
		T[] newArray = Arrays.copyOf(array, array.length);
		quicksortAux(newArray, comparator, 0, newArray.length-1);
		return newArray;
	}

	private static <T> void merge(T[] a, T[] l, T[] r, int left, int right, Comparator<T> comparator) {

		int i = 0, j = 0, k = 0;
		while (i < left && j < right) {
			if (comparator.compare(l[i], r[j]) <= 0) {
				a[k++] = l[i++];
			} else {
				a[k++] = r[j++];
			}
		}
		while (i < left) {
			a[k++] = l[i++];
		}
		while (j < right) {
			a[k++] = r[j++];
		}
	}

	private static <T> void mergesortAux(T[] a, int n, Comparator<T> comparator) {
		if (n < 2) return;
		
		int mid = n / 2;
		T[] l = Arrays.copyOf(a, mid);
		T[] r = Arrays.copyOfRange(a, mid, n);

		mergesortAux(l, mid, comparator);
		mergesortAux(r, n-mid, comparator);

		merge(a, l, r, mid, n-mid, comparator);
	}

	public static <T> T[] mergesort(T[] array, Comparator<T> comparator) {
		T[] newArray = Arrays.copyOf(array, array.length);
		mergesortAux(newArray, newArray.length, comparator);
		return newArray;
	}

}
