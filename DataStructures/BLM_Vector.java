package DataStructures;

import java.util.Arrays;

/**
 * A simple implementation of a dynamically-sized array,
 * also known as vector or ArrayList
 */
public class BLM_Vector<T> {
  private int size;
  private T[] elements;


  // Constructor with initial capacity
  public BLM_Vector(int capacity) {

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
  public BLM_Vector() {
    this(1);
  }

  /*
   * Accessor method
   * return the element at the particular index
   * throws ArrayIndexOutOfBoundsException for bad indexes
   * O(1) complexity
   */
  public T get(int index) {
    // Basic error checking
    if (index > size - 1 || index < 0) {
      throw new ArrayIndexOutOfBoundsException();
    } 

    return elements[index];
  }

  /*
   * Removes an element at an index, and adjust the array O(n)
   * Halves the array is less than 25% full
   * return the element that was removed
   * throws ArrayIndexOutOfBoundsExceiption for bad indexes
   * O(n) complexity
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
   * Add an element to the end of the list.
   * Has to copy the array over if underlying array is full.
   * O(1) complexity, amortized
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


  // A driver to test the class with
  public void driver() {
    BLM_Vector<String> testVect = new BLM_Vector<String>();
    testVect.add("This");
    testVect.add("my");
    testVect.add("my");
    testVect.add("vector");

    for (int i = 0; i < testVect.size(); i++) {
      System.out.println(testVect.get(i));
    }

    while (testVect.size() > 0) {
      testVect.remove(0);
    }

    System.out.println("tesVect.size(): " + testVect.size());

  }

  public static void main(String[] args) {
    new BLM_Vector<Void>().driver();
  }
}
