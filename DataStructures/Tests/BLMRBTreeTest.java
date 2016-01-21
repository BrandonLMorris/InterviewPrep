package DataStructures.Tests;

import org.junit.*;
import static org.junit.Assert.*;
import DataStructures.BLMRBTree;

/**
 * Unit tests for the Red-Black tree implementation BLMRBTree
 *
 * Created by bmorris on 1/21/16.
 */
public class BLMRBTreeTest {
  BLMRBTree<Integer> intTree, singleIntTree;
  int defaultSize = 1000;

  @Before
  public void setUp() {
    // Relies on add() to work properly
    intTree = new BLMRBTree<Integer>();
    for (int i = 0; i < defaultSize; i++) {
      intTree.add(i);
    }

    singleIntTree = new BLMRBTree<Integer>();
    singleIntTree.add(0);
  }

  @Test
  public void testAdd() {
    // Normal use (relies on contains() working properly)
    assertEquals(defaultSize, intTree.size());
    intTree.add(Integer.MAX_VALUE);
    assertEquals(defaultSize+1, intTree.size());
    assertTrue(intTree.contains(Integer.MAX_VALUE));

    // Case with one element
    singleIntTree.add(Integer.MAX_VALUE);
    assertEquals(2, singleIntTree.size());
    assertTrue(singleIntTree.contains(Integer.MAX_VALUE));

    // Inserting to empty tree
    BLMRBTree<Integer> testTree = new BLMRBTree<Integer>();

  }

  @Test
  public void testContains() { /* TODO */ }

  @Test
  public void testFirst() { /* TODO */ }

  @Test
  public void testLast() { /* TODO */ }

  @Test
  public void testCeiling() { /* TODO */ }

  @Test
  public void testFloor() { /* TODO */ }

  @Test
  public void testHigher() { /* TODO */ }

  @Test
  public void testLower() { /* TODO */ }

  @Test
  public void testRemove() { /* TODO */ }

  @Test
  public void testPollFirst() { /* TODO */ }

  @Test
  public void testPollLast() { /* TODO */ }

}
