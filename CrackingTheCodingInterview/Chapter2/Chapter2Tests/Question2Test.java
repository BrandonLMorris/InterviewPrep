package CrackingTheCodingInterview.Chapter2.Chapter2Tests;

import org.junit.Before;
import org.junit.Test;
import CrackingTheCodingInterview.Chapter2.Node;
import CrackingTheCodingInterview.Chapter2.Question2;

import static org.junit.Assert.*;

public class Question2Test {

  public static final int LIST_SIZE = 1000;
  Node<Integer> head;

  @Before
  public void setUp() throws Exception {
    head = new Node<Integer>(1);
    Node<Integer> current = head;
    for (int i = 2; i <= LIST_SIZE; i++) {
      current.next = new Node<Integer>(i);
      current = current.next;
    }
  }

  @Test
  public void testGetKthElemnent() throws Exception {

    // General smoke test
    assertEquals(LIST_SIZE - 1, (int) Question2.getKthElemnent(head, 2));

    // Test with some random values of k
    for (int i = 0; i < 1000; i++) {
      int testK = (int) Math.ceil(Math.random() * 1000);
      assertEquals(LIST_SIZE - (testK - 1),
          (int) Question2.getKthElemnent(head, testK));
    }

    // Test edge cases
    assertEquals(LIST_SIZE, (int) Question2.getKthElemnent(head, 1));
    assertEquals(1, (int) Question2.getKthElemnent(head, LIST_SIZE));

  }

  // Test when k is zero
  @Test(expected = IllegalArgumentException.class)
  public void testZeroK() throws Exception {
    Question2.getKthElemnent(head, 0);
  }

  // Test when the (size+1) is passed in as k
  @Test(expected = IllegalArgumentException.class)
  public void testSizeK() throws Exception {
    Question2.getKthElemnent(head, LIST_SIZE+1);
  }

  // Test with null list
  @Test(expected = IllegalArgumentException.class)
  public void testNullHead() throws Exception {
    Question2.getKthElemnent(null, LIST_SIZE);
  }

}