package CrackingTheCodingInterview.Chapter2.Chapter2Tests;

import CrackingTheCodingInterview.Chapter2.Node;
import CrackingTheCodingInterview.Chapter2.Question7;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Question7Test {
  Node<Character> head;

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void testIsPalindrome() throws Exception {
    head = makeList('R', 'A', 'C', 'E', 'C', 'A', 'R');
    assertEquals(true, Question7.isPalindrome(head));

    head = makeList('A', 'P', 'P', 'L', 'E');
    assertEquals(false, Question7.isPalindrome(head));
  }

  private static <T> Node<T> makeList(T...elements) {
    Node<T> head = new Node<T>(elements[0]);
    Node<T> current = head;
    for (int i = 1; i < elements.length; i++) {
      current.next = new Node<T>(elements[i]);
      current = current.next;
    }
    return head;
  }
}