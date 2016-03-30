package CrackingTheCodingInterview.Chapter3.Chapter3Tests;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import CrackingTheCodingInterview.Chapter3.Question1;

public class Question1Test {
  Question1<Integer> q1;

  @Before
  public void setUp() throws Exception {
    q1 = new Question1<Integer>();
    q1.push(Question1.FIRST, 1);
    q1.push(Question1.FIRST, 2);
    q1.push(Question1.FIRST, 3);
    q1.push(Question1.FIRST, 4);

    q1.push(Question1.SECOND, 1);
    q1.push(Question1.SECOND, 2);
    q1.push(Question1.SECOND, 3);
    q1.push(Question1.SECOND, 4);

    q1.push(Question1.THIRD, 1);
    q1.push(Question1.THIRD, 2);
    q1.push(Question1.THIRD, 3);
    q1.push(Question1.THIRD, 4);
  }

  @Test
  public void testSize() throws Exception {
    assertEquals(4, q1.size(Question1.FIRST));
    assertEquals(4, q1.size(Question1.SECOND));
    assertEquals(4, q1.size(Question1.THIRD));
  }

  @Test
  public void testPush() throws Exception {
    assertEquals(true, q1.push(Question1.FIRST, 5));
    assertEquals((Integer)5, q1.peek(Question1.FIRST));
  }

  @Test
  public void testPop() throws Exception {
    assertEquals((Integer)4, q1.pop(Question1.FIRST));
    assertEquals((Integer)3, q1.pop(Question1.FIRST));
    assertEquals((Integer)4, q1.pop(Question1.THIRD));
    assertEquals((Integer)4, q1.pop(Question1.SECOND));
  }

  @Test
  public void testPeek() throws Exception {
    assertEquals((Integer)4, q1.peek(Question1.FIRST));
    assertEquals((Integer)4, q1.peek(Question1.FIRST));
    assertEquals((Integer)4, q1.peek(Question1.SECOND));
    assertEquals((Integer)4, q1.peek(Question1.SECOND));
    assertEquals((Integer)4, q1.peek(Question1.THIRD));
    assertEquals((Integer)4, q1.peek(Question1.THIRD));
  }
}