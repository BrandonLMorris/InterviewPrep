package CrackingTheCodingInterview.Chapter2;

/**
 * Chapter 2 Question 4
 *
 * Write code to partition a linked list around a value x, such that all nodes
 * less than x come before all nodes greater than or equal to x.
 *
 * Created by bmorris on 9/30/15.
 */
public class Question4 {
  public static <T extends Comparable<T>> Node<T> parition(Node<T> head, T value) {

    if (head == null || value == null) {
      throw new IllegalArgumentException();
    }

    int size = 1;
    Node<T> tail;
    Node<T> current = head;
    while (current.next != null) {
      size++;
      current = current.next;
    }
    tail = current;

    current = head;
    for (int i = 0; i < size; i++) {
      // Make a copy of this node and add it to the end or beginning of the list
      Node<T> temp = new Node<T>(current.data);
      if (current.data.compareTo(value) < 0) {
        temp.next = head;
        head = temp;

      } else {
        tail.next = temp;
        tail = temp;
      }

      // Delete this node
      if (current.next == null) {
        current = null;
      } else {
        current.data = current.next.data;
        current.next = current.next.next;
      }
    }

    return head;
  }
}
