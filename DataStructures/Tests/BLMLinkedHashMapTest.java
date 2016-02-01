package DataStructures.Tests;

import org.junit.*;
import static org.junit.Assert.*;
import DataStructures.BLMLinkedHashMap;

/**
 * Unit tests for all the public methods in the BLMLinkedHashMap class.
 *
 * Created by Brandon Morris on 1/27/16.
 */
public class BLMLinkedHashMapTest {
  public static final int TEST_SIZE = 10000;
  BLMLinkedHashMap<Integer, Integer> basicMap;
  BLMLinkedHashMap<String, Integer> stringToInt;
  BLMLinkedHashMap<Object, Object> empty;

  String[] zeroToTen = {"zero", "one", "two", "three", "four", "five", "six",
      "seven", "eight", "nine", "ten"};

  @Before
  public void setUp() {
    // Relies on put() to work correctly
    basicMap = new BLMLinkedHashMap<Integer, Integer>();
    for (int i = 0; i < TEST_SIZE; i++) {
      basicMap.put(i, i);
    }

    stringToInt = new BLMLinkedHashMap<String, Integer>();
    for (int i = 0; i <= 10; i++) {
      stringToInt.put(zeroToTen[i], i);
    }

    empty = new BLMLinkedHashMap<Object, Object>();
  }

  @Test
  public void testClear() {
    basicMap.clear();
    assertTrue("basicMap should be empty", basicMap.isEmpty());
    assertEquals("basicMap.size() should be 0", 0, basicMap.size());

    stringToInt.clear();
    assertTrue("stringToInt should be emtpy", stringToInt.isEmpty());
    assertEquals("stringToInt.size() should be 0", 0, stringToInt.size());

    empty.clear();
    assertTrue("empty should be empty", empty.isEmpty());
    assertEquals("emtpy.size() should be zero", 0, empty.size());
  }

  @Test
  public void testContainsKey() {
    for (int i = 0; i < TEST_SIZE; i++) {
      assertTrue("basicMap should contain " + i, basicMap.containsKey(i));
    }
    for (int i = Integer.MIN_VALUE; i < Integer.MIN_VALUE+TEST_SIZE; i++) {
      assertFalse("basicMap should NOT contain " + i, basicMap.containsKey(i));
    }

    for (String s : zeroToTen) {
      assertTrue("stringToInt should contain " + s, stringToInt.containsKey(s));
    }
    assertFalse("stringToInt should NOT contain 'negative one'",
        stringToInt.containsKey("negative one"));
    assertFalse("stringToInt should NOT contain 'ONE'",
        stringToInt.containsKey("ONE"));
    assertFalse("stringToInt should NOT contain 'eleven'",
        stringToInt.containsKey("eleven"));

    // TODO: Non-Integer object that equals() an Integer
  }

  @Test
  public void testContainsValue() {
    for (int i = 0; i < TEST_SIZE; i++) {
      assertTrue(basicMap.containsValue(i));
    }
    for (int i = Integer.MIN_VALUE; i < Integer.MIN_VALUE+TEST_SIZE; i++) {
      assertFalse(basicMap.containsValue(i));
    }

    for (int i = 0; i <= 10; i++) {
      assertTrue(stringToInt.containsValue(i));
    }
  }

  @Test
  public void testEntrySet() {
    // TODO
  }

  @Test
  public void testEquals() {
    assertEquals(empty, new BLMLinkedHashMap<Object, Object>());
    assertTrue(empty.equals(new BLMLinkedHashMap<Object, Object>()));

    BLMLinkedHashMap<Integer, Integer> first =
        new BLMLinkedHashMap<Integer, Integer>();
    assertFalse(basicMap.equals(first));
    first.put(-1, 1);

    BLMLinkedHashMap<Integer, Integer> second =
        new BLMLinkedHashMap<Integer, Integer>();
    assertFalse(first.equals(second));
    assertFalse(second.equals(first));

    second.put(1, -1);
    assertFalse(first.equals(second));
    assertFalse(second.equals(first));

    first.put(1, -1);
    second.put(-1, 1);
    assertTrue(first.equals(second));
    assertTrue(second.equals(first));
  }

  @Test
  public void testGet() {
    for (Integer i = 0; i <= 10; i++) {
      assertEquals(i, stringToInt.get(zeroToTen[i]));
    }

    for (Integer i = 0; i < TEST_SIZE; i++) {
      assertEquals(i, basicMap.get(i));
    }

    assertNull(empty.get(new Object()));
    assertNull(basicMap.get(Integer.MAX_VALUE));
    assertNull(stringToInt.get("not in the map"));

    assertNull(basicMap.get("not even the same type"));
  }

  @Test
  public void testIsEmpty() {
    assertFalse(basicMap.isEmpty());
    assertFalse(stringToInt.isEmpty());

    assertTrue(empty.isEmpty());
    assertTrue(new BLMLinkedHashMap<Object, Object>().isEmpty());
  }

  @Test
  public void testKeySet() {
    // TODO
  }

  @Test
  public void testPut() {
    // Relies on contains, size to work

    // Adding new values to the map
    int prevSize = basicMap.size();
    for (int i = -1; i > -TEST_SIZE; i--) {
      assertNull(basicMap.put(i, i));
      assertEquals(++prevSize, basicMap.size());
    }
    prevSize = stringToInt.size();
    for (int i = 0; i < 100; i++) {
      assertNull(stringToInt.put(Integer.toString(i), i));
      assertEquals(++prevSize, stringToInt.size());
    }

    // Updating values in the map
    prevSize = basicMap.size();
    for (Integer i = -1; i > -TEST_SIZE; i--) {
      assertEquals(i, basicMap.put(i, -i));
      assertEquals(prevSize, basicMap.size());
    }
    prevSize = stringToInt.size();
    for (Integer i = 0; i < 100; i++) {
      assertEquals(i, stringToInt.put(Integer.toString(i), i + i));
      assertEquals(prevSize, stringToInt.size());
    }
  }

  @Test
  public void testPutAll() {
    // TODO
  }

  @Test
  public void testRemove() {
    // TODO
  }

  @Test
  public void testSize() {
    // TODO
  }

  @Test
  public void testValues() {
    // TODO
  }
}
