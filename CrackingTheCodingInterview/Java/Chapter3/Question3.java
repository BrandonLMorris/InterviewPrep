package CrackingTheCodingInterview.Chapter3;

import java.util.LinkedList;
import java.util.NoSuchElementException;

/**
 * Chapter 3 Question 3
 *
 * Implement a data structure SetOfStacks composed of several stacks and should
 * create a new stack once the previous one exceeds capacity. All operations
 * (push, pop, peek) should work as expected with a normal stack.
 *
 * Created by Brandon Morris on 10/2/15.
 */
public class Question3<T> {
  public static final int MAX_STACK_SIZE = 100;

  LinkedList<LinkedList<T>> stackSet;
  int currentSize;

  public Question3() {
    stackSet = new LinkedList<LinkedList<T>>();
    stackSet.addFirst(new LinkedList<T>());
    currentSize = 0;
  }

  public boolean push(T element) {
    if (currentSize == MAX_STACK_SIZE) {
      stackSet.push(new LinkedList<T>());
      currentSize = 0;
    }

    stackSet.peek().addFirst(element);
    currentSize++;

    return true;
  }

  public T pop() {
    if (stackSet.size() == 1 && currentSize == 0) {
      throw new NoSuchElementException();
    }

    T element = stackSet.peek().pop();
    currentSize--;
    if (currentSize == 0 && stackSet.size() > 1) {
      stackSet.pop();
      currentSize = MAX_STACK_SIZE;
    }

    return element;
  }

  public T peek() {
    return stackSet.peek().peek();
  }
}
