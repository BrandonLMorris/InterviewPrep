package CrackingTheCodingInterview.Chapter3.Chapter3Tests;

import CrackingTheCodingInterview.Chapter3.Question3;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Question3Test {
  Question3<Integer> q3;

  @Before
  public void setUp() throws Exception {
    q3 = new Question3<Integer>();
    for (int i = 0; i <= 100; i++) {
      q3.push(i);
    }
  }

  @Test
  public void testStackSet() throws Exception {
    assertEquals(100, (int)q3.peek());
    assertEquals(100, (int)q3.pop());
    assertEquals(99, (int) q3.peek());
    assertEquals(true, q3.push(10));
    assertEquals(10, (int) q3.peek());
  }
}