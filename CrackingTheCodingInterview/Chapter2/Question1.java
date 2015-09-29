package CrackingTheCodingInterview.Chapter2;

import java.util.HashMap;

/*
 *  Cracking the Coding Interview Chapter 2 Question 1
 *
 *  1 Write code to remove duplicates from an unsorted linked list.
 *    Follow up: How would you soleve this problem if a temporary buffer is not allowed?
 *
 *  Created by Brandon Morris 2015
 */
public class Question1 {
  public static <T> void removeDuplicates(Node<T> head) {

    if (head == null || head.next == null) {
      return;
    }

    HashMap<T, Integer> counts = new HashMap<T, Integer>();

    Node<T> current = head;
    while (current != null) {
      Integer count = counts.get(current.data);
      if (count != null) {
        counts.put(current.data, count + 1);

      } else {
        counts.put(current.data, 1);
      }
      current = current.next;
    }

    Node<T> prev = head;
    current = head.next;
    while (current != null) {
      if (counts.get(current.data) > 1) {
        // Remove the duplicate
        counts.put(current.data, counts.get(current.data)-1);
        prev.next = current.next;
        current = prev.next;
      } else {
        prev = current;
        current = current.next;
      }
    }

  }

  // Follow up that doesn't use a buffer
  public static <T> void removeDuplicatesWithoutBuffer(Node<T> head) {
    if (head == null || head.next == null) {
      return;
    }
  }


}
