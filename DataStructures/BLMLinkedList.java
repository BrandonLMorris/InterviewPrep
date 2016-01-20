package DataStructures;

/**
 * A simple Doubly Linked List implementation.
 */
public class BLMLinkedList<T> {
  private int size;
  private Node head, tail;


  /** Basic constructor: creates an empty list */
  public BLMLinkedList() {
    this.size = 0;
    this.head = this.tail = null;
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
   * @param elem the element to search for
   */
  public boolean contains(T elem) {
    Node current = head;

    // Iterate over the list
    while (current != null) {
      if (current.data.equals(elem)) {
        return true;
      }
      current = current.next;
    }

    // Couldn't find the element
    return false;
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
