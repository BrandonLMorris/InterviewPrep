package CrackingTheCodingInterview.Chapter2.Chapter2Tests;

import CrackingTheCodingInterview.Chapter2.Node;
import CrackingTheCodingInterview.Chapter2.Question5;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Question5Test {

  @Before
  public void setUp() throws Exception {

  }

  @Test
  public void testAddList() throws Exception {
    Node<Integer> first = new Node<Integer>(7);
    first.next = new Node<Integer>(1);
    first.next.next = new Node<Integer>(6);

    Node<Integer> second = new Node<Integer>(5);
    second.next = new Node<Integer>(9);
    second.next.next = new Node<Integer>(2);

    Node<Integer> result = Question5.addList(first, second);
    System.out.println(toBackwardsString(first) + " + " + toBackwardsString(second) + " = " + toBackwardsString(result));

    result = Question5.addListShortcut(first, second);
    System.out.println(toBackwardsString(first) + " + " + toBackwardsString(second) + " = " + toBackwardsString(result));
  }

  private static <T> String toBackwardsString(Node<T> head) {
    Node<T> current = head;
    String result = "";
    while (current != null) {
      result = current.data + result;
      current = current.next;
    }
    return result;
  }
}