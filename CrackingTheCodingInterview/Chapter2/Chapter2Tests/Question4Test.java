package CrackingTheCodingInterview.Chapter2.Chapter2Tests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import CrackingTheCodingInterview.Chapter2.Node;
import CrackingTheCodingInterview.Chapter2.Question4;

public class Question4Test {
  Node<Integer> head;

  @Before
  public void setUp() throws Exception {
    head = new Node<Integer>(10);
    Node<Integer> current = head;
    for (int i = 0; i < 10; i++) {
      current.next = new Node<Integer>(10-i-1);
      current = current.next;
    }
  }

  // TODO: Add more robust tests
  @Test
  public void testParition() throws Exception {
    printList(head);
    head = Question4.parition(head, 10);
    printList(head);
  }

  private static <T> void printList(Node<T> head) {
    Node<T> current = head;
    while (current != null) {
      System.out.print(current.data + " ");
      current = current.next;
    }
    System.out.println();
  }
}