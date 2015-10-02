package CrackingTheCodingInterview.Chapter2.Chapter2Tests;

import CrackingTheCodingInterview.Chapter2.Node;
import CrackingTheCodingInterview.Chapter2.Question6;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Question6Test {
  Node<Character> head;
  Node<Character> duplicate;

  @Before
  public void setUp() throws Exception {
    // Create a linked list with a cycle in it
    head = new Node<Character>('A');
    Node<Character> current = head;
    current.next = new Node<Character>('B');
    current = current.next;
    current.next = new Node<Character>('C');
    current = current.next;
    duplicate = current;
    current.next = new Node<Character>('D');
    current = current.next;
    current.next = new Node<Character>('E');
    current = current.next;
    current.next = new Node<Character>('F');
    current = current.next;
    current.next = duplicate;
  }

  @Test
  public void testFindStartOfCycleInPlace() throws Exception {
    // Basic smoke test
    assertEquals(duplicate, Question6.findStartOfCycleInPlace(head));
  }

  @Test
  public void testFindStartOfCycleHashing() throws Exception {
    // Basic smoke test
    assertEquals(duplicate, Question6.findStartOfCycleHashing(head));
  }


}