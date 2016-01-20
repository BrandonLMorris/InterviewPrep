package DataStructures;

import java.util.Arrays;

/**
 * A simple implementation of a dynamically-sized array,
 * also known as vector or ArrayList.
 */
public class BLMVector<T> {
  private int size;
  private T[] elements;


  // Constructor with initial capacity
  public BLMVector(int capacity) {

    // Minimal error checking, simply persists
    if (capacity <= 0) {
      capacity = 1;
    }

    // "In the beginning, there were no elements."
    size = 0;

    // Have to supress the type cast warning
    @SuppressWarnings("unchecked")
    T[] arr = (T[])(new Object[capacity]);
    this.elements = arr;
  }

  // Constructor w/o initial capacity
  public BLMVector() {
    this(1);
  }

  /*
   * Accessor method; returns the element at a particular index
   * Complexity: O(1)
   *
   * @param index the index to retrieve
   * @throws ArrayIndexOutOfBoundsException for bad indexes
   */
  public T get(int index) {
    if (index > size - 1 || index < 0) {
      throw new ArrayIndexOutOfBoundsException();
    } 

    return elements[index];
  }

  /*
   * Removes an element at an index, possibly resizing the underlying array.
   * Halves the array is less than 25% full
   * Complexity: O(n)
   *
   * @return the element that was removed
   * @throws ArrayIndexOutOfBoundsExceiption for bad indexes
   */
  public T remove(int index) {
    if (index > size - 1 || index < 0) {
      throw new ArrayIndexOutOfBoundsException();
    }

    T removed = elements[index];

    // Shift all the elements to the right of the removed element
    for (int i = index; i < size - 1; i++) {
      elements[i] = elements[i + 1];
    }

    // Set the last element to null
    elements[size - 1] = null;

    // Adjust the size
    size--;

    if (size < elements.length / 4) {
      elements = Arrays.copyOf(elements, elements.length / 2);
    }

    return removed;
  }


  /*
   * Add an element to the end of the list. Doubles the size of the underlying
   * array if full.
   * Complexity: O(1) amortized
   *
   * @param toAdd the element to add to the vector
   */
  public void add(T toAdd) {
    // Resize the array if full
    if (size == elements.length) {
      elements = Arrays.copyOf(elements, elements.length * 2);
    }

    elements[size] = toAdd;

    // Adjust the size
    size++;

    return;
  }

  public int size() {
    return size;
  }

}
