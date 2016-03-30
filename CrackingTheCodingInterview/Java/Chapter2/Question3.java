package CrackingTheCodingInterview.Chapter2;

/**
 * Chapter 2 Question 3
 *
 * Implement an algorithm to delete a node in the middle of a singly linked
 * list given only access to that node (not the head).
 *
 * Created by Brando Morris on 9/30/15.
 */
public class Question3 {

  public static void delete(Node n) {

    // Don't have access to previous node, so copy next node's data into this
    // one and delete the next node if necessary
    if (n.next == null) {
      n = null;
    } else {
      n.data = n.next.data;
      n.next = n.next.next;
    }

  }

}
