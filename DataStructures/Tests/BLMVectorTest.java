/**
 * Unit tests for the BLMVector class.
 *
 * Created by Brandon Morris 2016
 */
package DataStructures.Tests;

import org.junit.*;
import static org.junit.Assert.*;
import DataStructures.BLMVector;

public class BLMVectorTest {
  BLMVector<Integer> intVector, singleIntVector;
  int defaultSize = 1000;

  @Before
  public void setUp() {
    intVector = new BLMVector<Integer>();
    for (int i = 0; i < defaultSize; i++) {
      intVector.add(i);
    }

    singleIntVector = new BLMVector<Integer>();
    singleIntVector.add(0);
  }

  @Test
  public void testAdd() {
    intVector.add(Integer.MAX_VALUE);
    assertEquals(defaultSize+1, intVector.size());
    assertEquals(Integer.MAX_VALUE, (int)intVector.get(defaultSize));
  }

  @Test
  public void testAddAt() {
    // TODO
  }

  @Test
  public void testUpdate() {
    // Normal use (relies on get() working correctly)
    intVector.update(Integer.MIN_VALUE, defaultSize/2);
    assertEquals(Integer.MIN_VALUE, (int)intVector.get(defaultSize / 2));
    intVector.update(Integer.MAX_VALUE, defaultSize-1);
    assertEquals(Integer.MAX_VALUE, (int)intVector.get(defaultSize - 1));
  }

  @Test
  public void testGet() {
    // Normal use (index matches the value at that index)
    assertEquals(defaultSize / 2, (int)intVector.get(defaultSize / 2));
    assertEquals(0, (int)intVector.get(0));
    assertEquals(defaultSize - 1, (int)intVector.get(defaultSize - 1));

    // Case with one element
    assertEquals(0, (int)singleIntVector.get(0));
  }

  @Test
  public void testRemove() {
    // Normal use
    assertEquals(defaultSize - 1, (int)intVector.remove(defaultSize - 1));
    assertEquals(defaultSize - 1, intVector.size());
    assertEquals(0, (int)intVector.remove(0));
    assertEquals(defaultSize - 2, intVector.size());

    // Case with one element
    assertEquals(0, (int)singleIntVector.remove(0));
    assertEquals(0, singleIntVector.size());
  }

  @Test
  public void testContains() {
    // Normal use
    assertFalse(intVector.contains(Integer.MIN_VALUE));
    assertTrue(intVector.contains(0));
    assertTrue(intVector.contains(defaultSize - 1));

    // Case with one element
    assertTrue(singleIntVector.contains(0));
    assertFalse(singleIntVector.contains(-1));

    // Case with no elements
    BLMVector<Integer> emptyVector = new BLMVector<Integer>();
    assertFalse(emptyVector.contains(Integer.MAX_VALUE));
  }

  @Test
  public void testIndexOf() {
    // Normal use
    assertEquals(0, intVector.indexOf(0));
    assertEquals(defaultSize - 1, intVector.indexOf(defaultSize - 1));
    assertEquals(defaultSize / 2, intVector.indexOf(defaultSize / 2));
    assertEquals(-1, intVector.indexOf(Integer.MIN_VALUE));

    // Case with one element
    assertEquals(0, singleIntVector.indexOf(0));
    assertEquals(-1, singleIntVector.indexOf(Integer.MIN_VALUE));
  }

  @Test
  public void testToString() {
    // Single element
    assertEquals("[0]", singleIntVector.toString());

    // Empty case
    assertEquals("[]", new BLMVector<Integer>().toString());

    // Case with > 1 elements
    BLMVector<Integer> testVect = new BLMVector<Integer>();
    testVect.add(17); testVect.add(21); testVect.add(44);
    assertEquals("[17, 21, 44]", testVect.toString());
  }
}
