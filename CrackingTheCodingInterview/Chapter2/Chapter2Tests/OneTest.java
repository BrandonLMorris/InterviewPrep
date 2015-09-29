package CrackingTheCodingInterview.Chapter2.Chapter2Tests;

import CrackingTheCodingInterview.Chapter2.Node;
import CrackingTheCodingInterview.Chapter2.Question1;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class OneTest {
  Node<Integer> intNode;
  @Before
  public void setUp() throws Exception {
    intNode = new Node<Integer>(0);
  }

  @Test
  /*
   * testRemoveDuplicates - tests chapter two, question one
   */
  public void testRemoveDuplicates() throws Exception {

    // Use a list that has two of every number 0-100
    Node<Integer> current = intNode;
    for (int i = 0; i < 100; i++) {
      current.next = new Node<Integer>(i);
      current = current.next;
      current.next = new Node<Integer>(i);
      current = current.next;
    }
    Question1.removeDuplicates(intNode);
    assertEquals(0, countDuplicates(intNode));

    // Run again with random numbers
    current = intNode;
    for (int i = 0; i < 100000; i++) {
      current.next = new Node<Integer>((int)Math.floor(Math.random()*100));
      current = current.next;
    }
    Question1.removeDuplicates(intNode);
    assertEquals(0, countDuplicates(intNode));
  }

  /*
   * countDuplicates(Node<T> head) - return the number of duplicates in a
   * linked list
   */
  private <T> int countDuplicates(Node<T> head) {
    HashSet<T> hs = new HashSet<T>();
    Node<T> current = head;
    int dupCount = 0;
    while (current != null) {
      if (hs.contains(current.data)) {
        dupCount++;
      } else {
        hs.add(current.data);
      }
      current = current.next;
    }
    return dupCount;
  }

  /*
   * printList(Node<T> head) - Print the entire list on a single line
   */
  private <T> void printList(Node<T> head) {
    Node<T> current = head;
    while (current != null) {
      System.out.print(current.data + " ");
      current = current.next;
    }
    System.out.println();
  }
}