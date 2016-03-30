package CrackingTheCodingInterview.Chapter3;

import java.util.NoSuchElementException;

/**
 * Chapter 2 Question 1
 * Implement 3 stacks using only an array.
 *
 * Created by Brandon Morris on 10/1/15.
 */
public class Question1<T> {
  public static final int STACK_SIZE = 100;
  public static final int FIRST = 1;
  public static final int SECOND = 2;
  public static final int THIRD = 3;

  private T[] data;

  int head1, head2, head3;

  // Default constructor - creates a new array and initializes the head
  // references
  @SuppressWarnings("unchecked")
  public Question1() {
    data = (T[])(new Object[(STACK_SIZE*3) + 1]);
    head1 = 0;
    head2 = STACK_SIZE;
    head3 = STACK_SIZE*2;
  }

  // size(int) - returns the size of the specified stack. Stacks are specified
  // by constants FIRST, SECOND, and THIRD
  public int size(int stack) {
    if (stack == FIRST) {
      return head1;
    }
    if (stack == SECOND) {
      return head2 - STACK_SIZE;
    }
    if (stack == THIRD) {
      return head3 - STACK_SIZE*2;
    }

    // Incorrect stack specified, throw an exception
    throw new IllegalArgumentException();
  }

  /* push(int, element) - pushes the element onto the specified stack */
  public boolean push(int stack, T element) {
    if (stack == FIRST && size(FIRST) < STACK_SIZE) {
      data[head1] = element;
      head1++;
      return true;
    }
    if (stack == SECOND && size(SECOND) < STACK_SIZE) {
      data[head2] = element;
      head2++;
      return true;
    }
    if (stack == THIRD && size(THIRD) < STACK_SIZE) {
      data[head3] = element;
      head3++;
      return true;
    }

    // Should probably check if the stack was incorrectly specified or full
    throw new IllegalArgumentException();
  }

  /* pop(int) - pops the top element off the specified stack */
  public T pop(int stack) {
    if (stack == FIRST) {
      if (head1 == 0) {
        throw new NoSuchElementException();
      }
      head1--;
      T element = data[head1];
      data[head1] = null;
      return element;
    }
    if (stack == SECOND) {
      if (head2 == STACK_SIZE) {
        throw new NoSuchElementException();
      }
      head2--;
      T element = data[head2];
      data[head2] = null;
      return element;
    }
    if (stack == THIRD) {
      if (head3 == STACK_SIZE*2) {
        throw new NoSuchElementException();
      }
      head3--;
      T element = data[head3];
      data[head3] = null;
      return element;
    }
    throw new IllegalArgumentException();
  }


  /* peek(int) - returns the element at the top of the specified stack, without
   * removing it.
   */
  public T peek(int stack) {
    T element = pop(stack);
    push(stack, element);
    return element;
  }
}
