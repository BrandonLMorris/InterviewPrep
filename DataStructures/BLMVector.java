/**
 * A simple implementation of a dynamically-sized array, also known as vector
 * more commonly know in Java as an ArrayList.
 *
 * Created by Brandon Morris 2015-16
 */
package DataStructures;

import java.util.Arrays;

public class BLMVector<T> {
  private int size;
  private T[] elements;


  /** Constructor with initial capacity */
  public BLMVector(int capacity) {
    if (capacity <= 0) {
      throw new IllegalArgumentException("Cannot have negative capacity");
    }

    // "In the beginning, there were no elements."
    size = 0;

    // Have to suppress the type cast warning
    @SuppressWarnings("unchecked")
    T[] arr = (T[])(new Object[capacity]);
    this.elements = arr;
  }

  // Constructor w/o initial capacity
  public BLMVector() {
    this(1);
  }

  /**
   * Accessor method; returns the element at a particular index
   * Complexity: O(1)
   *
   * @param index the index to retrieve
   * @throws java.lang.IndexOutOfBoundsException for bad indexes
   */
  public T get(int index) {
    if (index > size - 1 || index < 0) {
      throw new ArrayIndexOutOfBoundsException();
    } 

    return elements[index];
  }

  /**
   * Removes an element at an index, possibly resizing the underlying array.
   * Halves the array is less than 25% full
   * Complexity: O(n)
   *
   * @return the element that was removed
   * @throws java.lang.IndexOutOfBoundsException for bad indexes
   */
  public T remove(int index) {
    if (index > size - 1 || index < 0) {
      throw new ArrayIndexOutOfBoundsException();
    }

    T removed = elements[index];

    // Shift all the elements to the right of the removed element
    // for (int i = index; i < size - 1; i++) {
    //   elements[i] = elements[i + 1];
    // }
    if (size > 1) {
      System.arraycopy(elements, index + 1, elements, index, size - index);
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

  /**
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
  }

  /**
   * Add an element to the list at a particular index. Double the underlying
   * array if reached capacity.
   *
   * @param toAdd   the element to add
   * @param index   the index at which to add it
   */
  public void add(T toAdd, int index) {
    // Handle the out of bounds case
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }

    // Add to the end, then move to correct position
    add(toAdd);

    int pos = size - 1;
    T temp;
    while (pos != index) {
      // Swap pos and pos-1
      temp = elements[pos - 1];
      elements[pos - 1] = elements[pos];
      elements[pos] = temp;
      pos--;
    }

    // Don't need to adjust the size, since add() already did
  }

  /**
   * Update the value of an element at a particular index.
   * @param value   the value to update to
   * @param index   where to update the value
   */
  public void update(T value, int index) {
    // Handle the out of bounds case
    if (index < 0 || index > size - 1) {
      throw new IndexOutOfBoundsException();
    }

    elements[index] = value;
  }

  /**
   * Determine if this Vector contains a particular value. Relies on equals()
   *
   * @param value the value to look for
   * @return  if found or not
   */
  public boolean contains(T value) {
    // Utilize our indexOf method
    return indexOf(value) != -1;
  }

  /**
   * Find the index of a particular value in the list. Relies on equals()
   * @param value the value to search for
   * @return  the index of the value, or -1 if not present
   */
  public int indexOf(T value) {
    // No particular ordering, so we have to do a linear search
    for (int i = 0; i < size; i++) {
      if (elements[i].equals(value)) {
        return i;
      }
    }

    // Couldn't find it
    return -1;
  }

  /** Return the size of this Vector */
  public int size() {
    return size;
  }

  /** Obtain a string representation of this Vector */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");
    for (int i = 0; i < size; i++) {
      sb.append(elements[i].toString());
      sb.append(", ");
    }

    // Remove the trailing ", "
    if (size > 0) {
      sb.delete(sb.length() - 2, sb.length());
    }

    sb.append("]");
    return sb.toString();
  }

}
