package DataStructures.Tests;

import org.junit.*;
import static org.junit.Assert.*;
import DataStructures.BLMHeap;

public class BLMHeapTest {
  @Test
  public void myTest() {
    BLMHeap<Integer> heap = new BLMHeap<Integer>();
    heap.add((Integer)4);
    heap.add((Integer)3);
    heap.add((Integer)2);
    heap.add((Integer)1);

    System.out.println(heap);

    while (heap.size() > 0) {
      System.out.println(heap.poll());
    }
  }
}
