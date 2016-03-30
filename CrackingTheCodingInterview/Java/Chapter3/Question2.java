package CrackingTheCodingInterview.Chapter3;

import java.util.LinkedList;

/**
 * Chapter 3 Question 2
 *
 * How would you design a stack which, in addition to push and pop, also had
 * a function min which returns the minimum element? All three should be in
 * O(1) time.
 *
 * Created by Brandon Morris on 10/2/15.
 */
public class Question2<T extends Comparable<T>> {

  LinkedList<T> stack;
  LinkedList<T> minList;

  public Question2() {
    stack = new LinkedList<T>();
    minList = new LinkedList<T>();
  }

  public boolean push(T element) {
    stack.addFirst(element);

    // Be sure to push if element is equal to minimum to account for duplicates
    if (minList.isEmpty() || element.compareTo(minList.peekFirst()) <= 0) {
      minList.addFirst(element);
    }

    return true;
  }

  public T pop() {
    T element = stack.removeFirst();
    if (element == minList.peek()) {
      minList.removeFirst();
    }
    return element;
  }

  public T min() {
    return minList.peek();
  }

}
