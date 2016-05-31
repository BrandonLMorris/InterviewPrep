package DataStructures;

import java.util.NoSuchElementException;
import java.util.Iterator;

/**
 * A basic implementation of a minimum heap (comprable to the java.util
 * PriorityQueue class).
 *
 * Created by Lance Morris on 5/30/2016
 */
public class BLMHeap<T extends Comparable<? super T>> {
  // Default number of elements the heap is able to store
  public static final int DEFAULT_CAPACITY = 100;

  // Actual storage of the heap
  private Object[] elements;

  /** Basic constructor of the heap */
  public BLMHeap() {
    // Generics aren't Java's forte, so elements are stored as Objects
    Object[] arr = new Object[DEFAULT_CAPACITY];
    this.elements = arr;
  }


  /** Construct a new heap from an existing interable */
  public BLMHeap(Iterable<T> list) {
    // Copy the elements into our internal storage
    @SuppressWarnings("unchecked")
    T[] arr = (T[])(new Object[DEFAULT_CAPACITY]);
    Iterator<T> iterator = list.iterator();
    while (iterator.hasNext()) {
      if (elements.length == size) {
        doubleCapacity();
      }
      elements[size++] = iterator.next();
    }

    // Now repair the ordering (slightly faster than maintaining during
    // insertion
    for (int i = size; i > 0; i--) {
      heapify(i);
    }
  }


  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < size - 1; i++) {
      if (elements[i] == null) {
        sb.append("(null), ");
      } else {
        sb.append(elements[i]);
        sb.append(", ");
      }
    }
    if (size > 0) {
      sb.append(elements[size - 1]);
    }
    sb.append("]");

    return sb.toString();
  }


  /** @return the minimum element stored in this heap */
  public T poll() {
    if (size == 0) {
      throw new NoSuchElementException("Heap is empty!");
    }

    // Take off the first element and restore ordering
    @SuppressWarnings("unchecked")
    T toReturn = (T)elements[0];
    elements[0] = elements[--size];
    if (size > 0) {
      heapify(0);
    }

    return toReturn;
  }

  /** Add a new element to the heap */
  public void add(T toAdd) {
    // Bump up capacity if we're full
    if (size == elements.length) {
      doubleCapacity();
    }

    // Add the element and restore ording
    elements[size] = toAdd;
    bubbleUp(size);
    size++;
  }


  /** Repair ordering of the heap by moving downward */
  @SuppressWarnings("unchecked")
  private void heapify(int index) {
    T elem = (T)elements[index];
    T minElem = elem;

    // Two children: put the minimum at the top and bubble down if necessary
    if (index * 2 + 1 < size) {
      T left = (T)elements[index * 2], right = (T)elements[index * 2 + 1];
      if (elem.compareTo(left) > 0) {
        minElem = (T)elements[index * 2];
      }
      if (minElem.compareTo(right) > 0) {
        minElem = (T)elements[index * 2 + 1];
      }

      if (minElem == elem) {
        return;
      }
      int minIndex;
      if (minElem == right) {
        minIndex = index * 2 + 1;
      } else {
        minIndex = index * 2;
      }
      swap(index, minIndex);
      heapify(minIndex);
    }

    // One child: swap if necessary
    if (index * 2 <= size) {
      @SuppressWarnings("unchecked")
      T parent = (T)elements[index], child = (T)elements[index * 2];
      if (parent.compareTo(child) > 0) {
        swap(index, index * 2);
      }
    }

    // Nothing to do if index is a leaf
  }


  /** Repair the ordering of the heap moving upward */
  private void bubbleUp(int index) {
    if (index == 0) {
      // Element is root, nothing to do
      return;
    }

    int parent = index / 2;
    @SuppressWarnings("unchecked")
    T parentElem = (T)elements[parent], childElem = (T)elements[index];
    if (parentElem.compareTo(childElem) > 0) {
      swap(parent, index);
      bubbleUp(parent);
    }
  }


  /** Double the capacity of this heap */
  private void doubleCapacity() {
    @SuppressWarnings("unchecked")
    Object[] arr = new Object[this.elements.length * 2];
    for (int i = 0; i < elements.length; i++) {
      arr[i] = elements[i];
    }
    this.elements = arr;
  }


  /** Swap two elements in an array */
  private void swap(int a, int b) {
    @SuppressWarnings("unchecked")
    T temp = (T)elements[a];
    elements[a] = elements[b];
    elements[b] = temp;
  }


  /** The number of elements in the heap */
  private int size;


  /** @return the number of elements in the heap */
  public int size() { return size; }
}
