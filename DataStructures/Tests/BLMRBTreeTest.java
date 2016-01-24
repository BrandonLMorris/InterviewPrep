package DataStructures.Tests;

import org.junit.*;
import static org.junit.Assert.*;
import DataStructures.BLMRBTree;

import java.util.NoSuchElementException;

/**
 * Unit tests for the Red-Black tree implementation BLMRBTree
 *
 * Created by bmorris on 1/21/16.
 */
public class BLMRBTreeTest {
  BLMRBTree<Integer> intTree, singleIntTree, tenIntTree, empty, evens;
  int defaultSize = 1000;

  @Before
  public void setUp() {
    // Relies on add() to work properly

    // Integers 0 through defaultSize - 1
    intTree = new BLMRBTree<Integer>();
    for (int i = 0; i < defaultSize; i++) {
      intTree.add(i);
    }

    // Tree with one element
    singleIntTree = new BLMRBTree<Integer>();
    singleIntTree.add(0);

    // Tree with ten elements (0-9)
    tenIntTree = new BLMRBTree<Integer>();
    for (int i = 0; i < 10; i++) {
      tenIntTree.add(i);
    }

    // Empty tree
    empty = new BLMRBTree<Integer>();

    // Even integers 0-100
    evens = new BLMRBTree<Integer>();
    for (int i = 0; i < 50; i++) {
      evens.add(i*2);
    }
  }

  @Test
  public void testAdd() {
    // Normal use (relies on contains() working properly)
    assertEquals(defaultSize, intTree.size());
    assertTrue(intTree.add(Integer.MAX_VALUE));
    assertEquals(defaultSize+1, intTree.size());
    assertTrue(intTree.contains(Integer.MAX_VALUE));

    // Case with one element
    assertTrue(singleIntTree.add(Integer.MAX_VALUE));
    assertEquals(2, singleIntTree.size());
    assertTrue(singleIntTree.contains(Integer.MAX_VALUE));

    // Inserting to empty tree
    assertTrue(empty.add(0));
    assertEquals(1, empty.size());

    // Returns false if item was already present (relies on contains)
    assertFalse(empty.add(0));
  }

  @Test
  public void testContains() {
    // Normal use
    assertTrue(intTree.contains(0));
    assertTrue(intTree.contains(defaultSize-1));
    assertTrue(intTree.contains(defaultSize/2));
    assertFalse(intTree.contains(defaultSize));

    // Case with one element
    assertTrue(singleIntTree.contains(0));
    assertFalse(singleIntTree.contains(Integer.MAX_VALUE));

    // Case with empty tree
    assertFalse(empty.contains(Integer.MAX_VALUE));

    // Case with custom object that is comparable to tree type
    assertTrue(intTree.contains(new Comparable<Integer>() {
      public int compareTo(Integer i) {
        return defaultSize - 1 - i;
      }
    }));
  }

  @Test(expected=ClassCastException.class)
  public void testContainsOtherType() {
    intTree.contains("This is not an integer");
    intTree.contains(3.14159);
  }

  @Test
  public void testFirst() {
    assertEquals((Integer)0, intTree.first());
    assertEquals((Integer)0, singleIntTree.first());
    assertEquals((Integer)0, tenIntTree.first());
  }

  @Test(expected= NoSuchElementException.class)
  public void testFirstEmpty() {
    empty.first();
  }

  @Test
  public void testLast() {
    assertEquals((Integer)(defaultSize-1), intTree.last());
    assertEquals((Integer)9, tenIntTree.last());
    assertEquals((Integer)0, singleIntTree.last());
  }

  @Test
  public void testCeiling() {
    // When the ceiling is in the tree
    for (int i = 0; i < defaultSize; i++) {
      assertEquals((Integer)i, intTree.ceiling(i));
    }
    for (int i = 0; i < 10; i++) {
      assertEquals((Integer)i, tenIntTree.ceiling(i));
    }

    // Values beyond the range of the tree
    assertEquals((Integer)0, intTree.ceiling(Integer.MIN_VALUE));
    assertNull(intTree.ceiling(Integer.MAX_VALUE));

    // Try with some elements not in the tree
    for (int i = 1; i < 99; i += 2) {
      assertEquals((Integer)(i+1), evens.ceiling(i));
    }

    // Tree with one element
    assertEquals((Integer)0, singleIntTree.ceiling(0));
    assertEquals((Integer)0, singleIntTree.ceiling(Integer.MIN_VALUE));
    assertNull(singleIntTree.ceiling(Integer.MAX_VALUE));

    // Emtpy tree
    assertNull(empty.ceiling(Integer.MAX_VALUE));
  }

  @Test
  public void testFloor() {
    // When the floor is in the tree
    for (int i = 0; i < defaultSize; i++) {
      assertEquals((Integer)i, intTree.floor(i));
    }
    for (int i = 0; i < 10; i++) {
      assertEquals((Integer)i, tenIntTree.floor(i));
    }

    // Values beyond the range of the tree
    assertEquals((Integer)(defaultSize-1), intTree.floor(Integer.MAX_VALUE));
    assertNull(intTree.floor(Integer.MIN_VALUE));
    assertEquals((Integer)9, tenIntTree.floor(Integer.MAX_VALUE));
    assertNull(tenIntTree.floor(Integer.MIN_VALUE));

    // Tree with one element
    assertEquals((Integer)0, singleIntTree.floor(0));
    assertEquals((Integer)0, singleIntTree.floor(Integer.MAX_VALUE));
    assertNull(singleIntTree.floor(Integer.MIN_VALUE));

    // Empty tree
    assertNull(empty.ceiling(Integer.MIN_VALUE));
  }

  @Test
  public void testHigher() {
    // When the higher is in the tree
    for (int i = 0; i < defaultSize-1; i++) {
      assertEquals((Integer)(i+1), intTree.higher(i));
    }
    for (int i = 0; i < 9; i++) {
      assertEquals((Integer)(i+1), tenIntTree.higher(i));
    }

    // Values not in the tree
    assertEquals((Integer)0, intTree.higher(Integer.MIN_VALUE));
    assertNull(intTree.higher(Integer.MAX_VALUE));
    for (int i = 0; i < evens.size(); i += 2) {
      assertEquals((Integer)i, evens.higher(i-1));
    }

    // Tree with one element
    assertNull(singleIntTree.higher(0));
    assertNull(singleIntTree.higher(Integer.MAX_VALUE));
    assertEquals((Integer)0, singleIntTree.higher(Integer.MIN_VALUE));

    // Emtpy tree
    assertNull(empty.higher(Integer.MIN_VALUE));
  }

  @Test
  public void testLower() {
    for (int i = 1; i < defaultSize; i++) {
      assertEquals((Integer)(i-1), intTree.lower(i));
    }
    for (int i = 1; i < 10; i++) {
      assertEquals((Integer)(i-1), intTree.lower(i));
    }

    // Values not in the tree
    for (int i = 1; i < evens.size(); i += 2) {
      assertEquals((Integer)(i - 1), evens.lower(i));
    }
    assertEquals((Integer)(defaultSize - 1), intTree.lower(Integer.MAX_VALUE));
    assertNull(intTree.lower(Integer.MIN_VALUE));

    // Tree with one element
    assertNull(singleIntTree.lower(0));

    // Emtpy tree
    assertNull(empty.lower(Integer.MAX_VALUE));
  }

  /*
  @Test
  public void testRemove() {
    int prevSize = defaultSize;

    // Try to remove an element not in the tree
    assertFalse(intTree.remove(Integer.MAX_VALUE));
    assertEquals(prevSize, intTree.size());

    // Normal usage
    for (int i = defaultSize - 1; i >= 0; i--) {
      assertTrue(intTree.remove(i));
      assertEquals(prevSize - 1, intTree.size());
      prevSize = intTree.size();
    }

    prevSize = 10;
    for (int i = 0; i < 10; i++) {
      assertTrue(tenIntTree.remove(i));
      assertEquals(prevSize - 1, tenIntTree.size());
      prevSize = tenIntTree.size();
    }

    assertTrue(singleIntTree.remove(0));
    assertEquals(0, singleIntTree.size());

    // Removing from an empty tree
    assertFalse(empty.remove(Integer.MIN_VALUE));
  }

  @Test
  public void testPollFirst() {
    int prevSize = defaultSize;

    // Normal usage
    for (int i = 0; i < defaultSize; i++) {
      assertEquals((Integer)i, intTree.pollFirst());
      assertEquals(prevSize - 1, intTree.size());
      prevSize = intTree.size();
    }

    prevSize = 10;
    for (int i = 0; i < 10; i++) {
      assertEquals((Integer)i, tenIntTree.pollFirst());
      assertEquals(prevSize - 1, tenIntTree.size());
      prevSize = tenIntTree.size();
    }

    assertEquals((Integer)0, singleIntTree.pollFirst());
    assertEquals(0, singleIntTree.size());
  }

  @Test
  public void testPollLast() {
    int prevSize = defaultSize;

    // Normal usage
    for (int i = defaultSize - 1; i > 0; i--) {
      assertEquals((Integer) i, intTree.pollLast());
      assertEquals(prevSize - 1, intTree.size());
      prevSize = intTree.size();
    }

    prevSize = 10;
    for (int i = 0; i < 10; i++) {
      assertEquals((Integer) i, tenIntTree.pollLast());
      assertEquals(prevSize - 1, tenIntTree.size());
      prevSize = tenIntTree.size();
    }

    assertEquals((Integer) 0, singleIntTree.pollLast());
    assertEquals(0, singleIntTree.size());
  }
  */
}
