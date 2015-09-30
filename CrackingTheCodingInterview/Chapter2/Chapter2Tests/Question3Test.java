package CrackingTheCodingInterview.Chapter2.Chapter2Tests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import CrackingTheCodingInterview.Chapter2.Node;
import CrackingTheCodingInterview.Chapter2.Question3;

public class Question3Test {
  Node<Integer> head;

  @Before
  public void setUp() throws Exception {
    head = new Node<Integer>(0);
    head.next = new Node<Integer>(1);
    head.next.next = new Node<Integer>(2);
  }

  @Test
  public void testDelete() throws Exception {
    Question3.delete(head.next);
    Node<Integer> current = head;
    while (current != null) {
      System.out.print(current.data + " ");
      current = current.next;
    }
    System.out.println();

    // TODO: Add more robust testing
  }
}