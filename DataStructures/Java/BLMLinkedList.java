/**
 * A simple Doubly Linked List implementation.
 *
 * Created by Brandon Morris
 */
package DataStructures;


import java.util.AbstractList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BLMLinkedList<T> extends AbstractList<T>
    implements Iterable<T>, List<T> {
  private int size;
  private Node head, tail;

  /** Basic constructor: creates an empty list */
  public BLMLinkedList() {
    this.size = 0;
    this.head = this.tail = null;
  }

  /** Create a Linked List from an iterable collection */
  public BLMLinkedList(Iterable<T> collection) {
    this.size = 0;
    this.head = this.tail = null;

    // Copy in all the elements
    for (T element : collection) {
      this.add(element);
    }
  }

  /** Create a Linked List from an array */
  public BLMLinkedList(T[] arr) {
    this.size = 0;
    this.head = this.tail = null;

    for (int i = 0; i < arr.length; i++) {
      this.add(arr[i]);
    }
  }

  /**
   * Add an element to the list at an index
   *
   * @param toAdd the element to add
   * @param index where to add the element
   * @throws java.lang.IndexOutOfBoundsException if the index is out of range
   */
  public void add(int index, T toAdd) {
    // Handle the out of bounds case
    if (index < 0 || index > size) {
      throw new IndexOutOfBoundsException();
    }

    // If it's added to the end, use our other method
    if (index == size) {
      add(toAdd);
      return;
    }

    // If it's being added to the front, use our other method
    if (index == 0) {
      push(toAdd);
      return;
    }

    // Traverse the list to the insertion point
    int counter = 0;
    Node current = head;
    while (counter < index) {
      current = current.next;
      counter++;
    }

    // Add our element before the "current" Node
    Node n = new Node(toAdd);
    current.prev.next = n;
    n.prev = current.prev;
    current.prev = n;
    n.next = current;

    size++;
  }

  /**
   * Add an element to the front of the list
   *
   * @param toAdd the element to add
   */
  public boolean push(T toAdd) {
    Node n = new Node(toAdd);

    // Make the Node the new head
    n.next = head;
    head.prev = n;
    head = n;

    size++;
    return true;
  }

  /** Delete and return the first element in the list */
  public T pop() {
    if (size == 0) {
      throw new IllegalStateException("Can't remove from and empty list.");
    }
    Node n = head;
    head = head .next;
    size--;
    return n.data;
  }

  /**
   *  Add an element to the end of the list
   *
   *  @param toAdd the item to add
   */
  public boolean add(T toAdd) {
    if (size == 0) {
      head = tail = new Node(toAdd);
    } else {
      tail.append(new Node(toAdd));
      tail = tail.next;
    }

    size++;
    return true;
  }

  /**
   * Determine if list contains an element. Relies on the equals() method to
   * determine equality.
   *
   * @param o the object to search for
   */
  public boolean contains(Object o) {
    return indexOf(o) != -1;
  }

  /** Returns the index of a value in the list or -1 if not present */
  public int indexOf(Object o) {
    Node current = head;
    int pos = 0;

    // Iterate over the list
    while (current != null) {
      if (current.data.equals(o)) {
        return pos;
      }
      current = current.next;
      pos++;
    }

    return -1;
  }

  /**
   * Retrieve an element from a particular index
   *
   * @param index the index to get from
   * @throws java.lang.IndexOutOfBoundsException if index is out of range
   */
  public T get(int index) {
    // Handle the out of bounds case
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    // If getting from either end, use our own variables
    if (index == 0) {
      return head.data;
    }
    if (index == size-1) {
      return tail.data;
    }

    // Traverse the list, forwards or backwards depending on the index
    boolean forward = index < size / 2;
    Node current = forward ? head : tail;
    int pos = forward ? 0 : size-1;
    int step = forward ? 1 : -1;
    while (pos != index) {
      current = forward ? current.next : current.prev;
      pos += step;
    }

    return current.data;
  }

  /**
   * Update an index to a particular value
   *
   * @param value the value to update to
   * @param index the index to change
   * @throws java.lang.IndexOutOfBoundsException if index is out of range
   */
  public T set(int index, T value) {
    // Handle the out of bounds error
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    // Handle setting the tail separately (optimization)
    if (index == size - 1) {
      T returnValue = tail.data;
      tail.data = value;
      return returnValue;
    }

    // Traverse the list to find the element.
    // Move forward if the index is in the front half, backwards otherwise
    boolean forward = index < size / 2;
    Node current = forward ? head : tail;
    int step = forward ? 1 : -1;
    int pos = forward ? 0 : size-1;
    while (pos != index) {
      pos += step;
      current = forward ? current.next : current.prev;
    }

    T returnValue = current.data;
    current.data = value;
    return returnValue;
  }

  /** Delete and return the last element in the list */
  public T removeLast() {
    if (head == null) {
      throw new IllegalStateException("Can't remove from an empty list.");
    }

    // Special case of one element
    if (head == tail) {
      Node n = head;
      head = tail = null;
      size--;
      return n.data;
    }

    Node removed = tail;
    tail = removed.prev;
    tail.next = null;

    size--;
    return removed.data;
  }

  /**
   * Delete and return the element at a particular index
   *
   * @param index the index to remove from
   * @return the element at that index
   * @throws java.lang.IndexOutOfBoundsException if the element is out of range
   * @throws java.lang.IllegalStateException if list is empty
   */
  public T remove(int index) {
    // Handle the out of bounds case
    if (index < 0 || index >= size) {
      throw new IndexOutOfBoundsException();
    }

    // Handle empty list
    if (size == 0) {
      throw new IllegalStateException("Cannot remove from an empty list.");
    }

    // If the head or tail, use specialized methods
    if (index == 0) {
      return pop();
    }
    if (index == size-1) {
      return removeLast();
    }

    // Traverse to the element, either backwards or forwards
    boolean forwards = index < size / 2;
    int step = forwards ? 1 : -1;
    int pos = forwards ? 0 : size-1;
    Node current = forwards ? head : tail;
    while (pos != index) {
      current = forwards ? current.next : current.prev;
      pos += step;
    }

    // Remove the actual node from the list
    current.prev.next = current.next;
    current.next.prev = current.prev;

    size--;
    return current.data;
  }

  public boolean remove(Object value) {
    int index = indexOf(value);
    if (index != -1) {
      remove(index);
      return true;
    }
    return false;
  }

  /** Return true if the list contains no elements */
  public boolean isEmpty() {
    return size == 0;
  }

  /** Display the contents of the linked list in string form */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");

    // Traverse the list, adding to our string
    Node current = head;
    while (current != null) {
      sb.append(current.data.toString());
      sb.append(", ");
      current = current.next;
    }

    // Remove the trailing ", "
    if (size > 0) {
      sb.delete(sb.length() - 2, sb.length());
    }

    sb.append("]");
    return sb.toString();
  }

  /** Return the size of the Linked List */
  public int size() {
    return this.size;
  }

  /** Return the head of the Linked List */
  public T head() {
    if (size == 0) {
      return null;
    }
    return this.head.data;
  }

  /** Return the tail of the Linked List */
  public T tail() {
    if (size == 0) {
      return null;
    }
    return this.tail.data;
  }

  // TODO: Implement an interator
  public Iterator<T> iterator() {
    return new Iterator<T>() {
      Node current = head;

      public void remove() {
        throw new UnsupportedOperationException(
            "BLMLinkedList iterator does not support remove()");
      }

      public boolean hasNext() {
        return current != null;
      }

      public T next() {
        if (!hasNext()) {
          throw new NoSuchElementException();
        }

        T returnValue = current.data;
        current = current.next;
        return returnValue;
      }
    };
  }

  /** Nested class to store the linked list nodes */
  private class Node {
    Node next = null, prev = null;
    T data;

    /**
     * Constructs a Node with a value
     * @param value the data within this Node
     */
    public Node(T value) {
      this.data = value;
    }


    /**
     * Append a Node right after this one. If this Node has another element
     * after it, it gets overwritten.
     *
     * @param toAdd the node to append to this one
     */
    public void append(Node toAdd) {
      this.next = toAdd;
      toAdd.prev = this;
    }

  }
}
