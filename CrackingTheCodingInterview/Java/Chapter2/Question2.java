package CrackingTheCodingInterview.Chapter2;

/**
 * Chapter 2 - Question 2
 *
 * Implement an algorithm to find the kth to last element of a singly linked
 *  list
 *
 * Created by bmorris on 9/29/15.
 */
public class Question2 {

  public static <T> T getKthElemnent(Node<T> head, int k) {

    if (head == null || k == 0) {
      throw new IllegalArgumentException();
    }

    Node<T> current = head;
    int count = 0;
    while (current != null) {
      count++;
      current = current.next;
    }

    if (k > count) {
      throw new IllegalArgumentException();
    }

    int i = 0;
    current = head;
    while (i < count - k) {
      current = current.next;
      i++;
    }

    return current.data;
  }
}

// 1 2 3
