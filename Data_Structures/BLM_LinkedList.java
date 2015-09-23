public class BLM_LinkedList<T> {
  private int size;
  private Node head;

  public BLM_LinkedList() {
    this.size = 0;
    this.head = null;
  }

  // Add an element to the list
  public boolean add(T toAdd) {
    if (head == null) {
      head = new Node(toAdd);
    } else {
      head.add(toAdd);
    }

    size++;

    return true;
  }

  // Determine if list contains an element
  public boolean contains(T elem) {
    Node current = head;

  }

  private class Node {
    Node next = null;
    T data;

    public Node(T value) {
      this.data = value;
    }

    void add(T toAdd) {
      Node end = new Node(toAdd);
      Node n = this;
      while (n.next != null) {
        n = n.next;
      }
      n.next = end;
    }

  }
}
