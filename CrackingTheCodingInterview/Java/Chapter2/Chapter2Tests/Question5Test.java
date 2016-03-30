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

    int firstInt = 99999;
    result = Question5.addList(intToLinkedList(firstInt), intToLinkedList(firstInt));
    System.out.println(toBackwardsString(intToLinkedList(firstInt)) + " + " + toBackwardsString(intToLinkedList(firstInt)) + " = " + toBackwardsString(result));
  }

  // toBackwardsString(Node<T>) - converts a list into a string of its elements
  // in reverse order.
  private static <T> String toBackwardsString(Node<T> head) {
    Node<T> current = head;
    String result = "";
    while (current != null) {
      result = current.data + result;
      current = current.next;
    }
    return result;
  }

  // intToLinkedList(int) - Converts an integer to a linked list
  private static Node<Integer> intToLinkedList(int n) {
    char[] arr = Integer.toString(n).toCharArray();
    Node<Integer> head = new Node<Integer>(arr[0] - '0');
    Node<Integer> current = head;
    for (int i = 1; i < arr.length; i++) {
      current.next = new Node<Integer>(arr[i] - '0');
      current = current.next;
    }
    return head;
  }
}