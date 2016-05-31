package DataStructures.Tests;

import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import DataStructures.BLMHeap;

public class BLMHeapTest {
  /** Ensure the basic functionality of the heap */
  @Test
  public void smokeTest() {
    BLMHeap<Integer> heap = new BLMHeap<Integer>();

    // Enter some values in reverse order
    for (Integer i = 100; i >= 0; i--) {
      heap.add(i);
    }

    // Make sure they come out in sorted order
    for (Integer i = 0; i <= 100; i++) {
      assertEquals(i, heap.poll());
    }
  }


  /** Test the basic constructor */
  @Test
  public void constructorTest() {
    BLMHeap<Integer> heap = new BLMHeap<Integer>();
    assertEquals(0, heap.size());
  }


  /** Test construction with an iterable */
  @Test
  public void constructorWithIterableTest() {
    ArrayList<Integer> myList = new ArrayList<Integer>();
    for (Integer i = 100; i >= 0; i--) {
      myList.add(i);
    }
    BLMHeap<Integer> heap = new BLMHeap<Integer>(myList);
    for (Integer i = 0; i <= 100; i++) {
      assertEquals(i, heap.poll());
    }
  }

  /** Test the string representation of a heap */
  @Test
  public void toStringTest() {
    BLMHeap<Integer> heap = new BLMHeap<Integer>();
    heap.add((Integer)1);
    heap.add((Integer)2);
    assertEquals("[1, 2]", heap.toString());
  }

  /** Test the poll function */
  @Test
  public void pollTest() {
    BLMHeap<Integer> heap = new BLMHeap<Integer>();
    heap.add((Integer)4);
    heap.add((Integer)100);
    int size = heap.size();
    Integer result = heap.poll();
    assertEquals((Integer)4, result);
    assertEquals(size - 1, heap.size());
  }


  /** Test the add function...kind of */
  public void testAdd() {
    BLMHeap<Integer> heap = new BLMHeap<Integer>();
    int beforeSize = heap.size();
    heap.add((Integer)0);
    assertEquals(beforeSize + 1, heap.size());
  }
}
