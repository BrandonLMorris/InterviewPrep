package DataStructures.Tests;
import DataStructures.BLMLinkedList;
import org.junit.*;
import static org.junit.Assert.*;

/**
 * Unit tests for the BLMLinkedList data structure
 *
 * Created by Brandon Morris on 1/20/16.
 */
public class BLMLinkedListTest {
  BLMLinkedList<Integer> intList, singleIntList;
  int defaultSize = 1000;

  @Before
  public void setUp() {
    intList = new BLMLinkedList<Integer>();
    for (int i = 0; i < defaultSize; i++) {
      intList.add(i);
    }

    singleIntList = new BLMLinkedList<Integer>();
    singleIntList.add(0);
  }

  @Test
  public void testAdd() {
    intList = new BLMLinkedList<Integer>();
    int howMany = 1000;
    for (int i = 0; i < howMany; i++) {
      intList.add(i);
    }
    assertEquals(howMany, intList.size());

    intList.add(-1, 0);
    assertEquals(-1, (int)intList.get(0));
  }

  @Test
  public void testGet() {
    for (int i = defaultSize-1; i >= 0; i--) {
      assertEquals(i, (int)intList.get(i));
    }
  }

  @Test
  public void testAddAt() {
    int index = defaultSize / 2;
    int prev = intList.get(index-1);
    int after = intList.get(index);

    intList.add(-99, index);

    assertEquals(-99, (int) intList.get(index));
    assertEquals(prev, (int)intList.get(index-1));
    assertEquals(after, (int)intList.get(index+1));
  }

  @Test
  public void testPush() {
    intList.push(-999);
    assertEquals(-999, (int)intList.head());
  }

  @Test
  public void testContains() {
    assertTrue(intList.contains(defaultSize-1));
    assertTrue(intList.contains(defaultSize/2));
    assertFalse(intList.contains(defaultSize+10));
  }

  @Test
  public void testUpdate() {
    intList.update(Integer.MIN_VALUE, defaultSize / 2);
    assertEquals(Integer.MIN_VALUE, (int)intList.get(defaultSize / 2));

    intList.update(Integer.MAX_VALUE, 0);
    assertEquals(Integer.MAX_VALUE, (int)intList.get(0));

    intList.update(Integer.MIN_VALUE, defaultSize-1);
    assertEquals(Integer.MIN_VALUE, (int)intList.get(defaultSize-1));
  }

  @Test
  public void testPop() {
    // Normal use
    assertEquals(0, (int)intList.pop());
    assertEquals(1, (int)intList.head());
    assertEquals(defaultSize-1, intList.size());

    // Case with one element
    assertEquals(0, (int)singleIntList.pop());
    assertEquals(0, singleIntList.size());
    assertNull(singleIntList.head());
    assertNull(singleIntList.tail());
  }

  @Test
  public void testRemoveLast() {
    // Normal use
    assertEquals(defaultSize-1, (int)intList.removeLast());
    assertEquals(defaultSize-2, (int)intList.tail());
    assertEquals(defaultSize-1, intList.size());

    // Case with one element
    assertEquals(0, (int)singleIntList.removeLast());
    assertEquals(0, singleIntList.size());
    assertNull(singleIntList.head());
    assertNull(singleIntList.tail());
  }

  @Test
  public void testDelete() {
    // Normal use
    int index = defaultSize / 2;
    int before = (int)intList.get(index+1);
    assertEquals(intList.get(index), intList.delete(index));
    assertEquals(before, (int)intList.get(index));
    assertEquals(defaultSize-1, intList.size());

    // Case with one element
    assertEquals(0, (int)singleIntList.delete(0));
    assertEquals(0, singleIntList.size());
    assertNull(singleIntList.head());
    assertNull(singleIntList.tail());
  }

  @Test
  public void testToString() {
    // Test simple case
    assertEquals("[0]", singleIntList.toString());

    // Empty list
    BLMLinkedList<Integer> lst = new BLMLinkedList<Integer>();
    assertEquals("[]", lst.toString());

    // List with > 1 elements
    lst.add(1); lst.add(2);
    assertEquals("[1, 2]", lst.toString());
  }
}
