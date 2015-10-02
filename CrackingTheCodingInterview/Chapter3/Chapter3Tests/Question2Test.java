package CrackingTheCodingInterview.Chapter3.Chapter3Tests;

import CrackingTheCodingInterview.Chapter3.Question2;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Question2Test {
  Question2<Integer> q2;

  @Before
  public void setUp() throws Exception {
    q2 = new Question2<Integer>();
    q2.push(3);
    q2.push(2);
    q2.push(1);
  }

  @Test
  public void testMin() throws Exception {
    assertEquals((Integer)1, q2.min());
    q2.pop();
    assertEquals((Integer)2, q2.min());
    q2.pop();
    assertEquals((Integer)3, q2.min());
    q2.pop();

    q2.push(1);
    q2.push(2);
    q2.push(1);
    assertEquals((Integer) 1, q2.min());
    q2.pop();
    assertEquals((Integer) 1, q2.min());
  }
}