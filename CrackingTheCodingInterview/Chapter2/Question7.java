package CrackingTheCodingInterview.Chapter2;

import java.util.LinkedList;

/**
 * Chapter 2 Question 7
 * Implement an algorithm to determine if a linked list is a palindrome
 *
 * Created by Brandon Morris on 10/1/15.
 */
public class Question7 {
  public static <T> boolean isPalindrome(Node<T> head) {

    /*
     * In order to be a valid palindrome, the front half must of the list must
     * be the same as the back half of the list, in reverse order. This can be
     * accomplished with a simple stack (ironically, a LinkedList in Java).
     */
    LinkedList<Node<T>> stack = new LinkedList<Node<T>>();

    // Will need to know the length of the list to determine the middle
    int length = getListSize(head);
    Node<T> current = head;
    for (int i = 0; i < length/2; i++) {
      stack.addFirst(current);
      current = current.next;
    }

    // Skip the middle element if odd number
    if (length % 2 != 0) {
      current = current.next;
    }

    // Should probably use .equals() instead of just comparing the data values
    while (!stack.isEmpty()) {
      if (stack.removeFirst().data != current.data) {
        return false;
      }
      current = current.next;
    }

    return true;
  }

  private static <T> int getListSize(Node<T> node) {
    int count = 0;
    while (node != null) {
      node = node.next;
      count++;
    }
    return count;
  }
}
