package CrackingTheCodingInterview.Chapter2;

import java.util.HashSet;

/**
 * Chapter 2 Question 6
 *
 * Given a cinrcular linked list, implement an algorithm which returns the node
 * the beginning of the loop
 *
 * EXAMPLE
 * A -> B -> C -> D -> E -> C
 * returns C
 *
 * Created by Brandon Morris on 9/30/15.
 */
public class Question6 {
  public static <T> Node<T> findStartOfCycleInPlace(Node<T> head) {
    Node<T> fastRunner = head;
    Node<T> slowRunner = head;

    // ...
    // Omitting math for now
    // ...

    fastRunner = fastRunner.next.next;
    slowRunner = slowRunner.next;

    while (fastRunner != slowRunner) {
      fastRunner = fastRunner.next.next;
      slowRunner = slowRunner.next;
    }

    // ...
    // Omitting math for now
    // ...

    fastRunner = head;
    while (slowRunner != fastRunner) {
      fastRunner = fastRunner.next;
      slowRunner = slowRunner.next;
    }

    return slowRunner;
  }

  // This version uses hashing to find the duplicate. Requires O(n) memory,
  // though.
  public static <T> Node<T> findStartOfCycleHashing(Node<T> head) {
    Node<T> current = head;

    // Keep track of all the nodes we've encountered, and stop as soon as we
    // get to one we've seen before
    HashSet<Node<T>> nodes = new HashSet<Node<T>>();
    while (!nodes.contains(current)) {
      nodes.add(current);
      current = current.next;
    }

    return current;
  }
}
