/**
 * Created by bmorris on 9/25/15.
 */
package CrackingTheCodingInterview.Chapter2;

public class Node<T> {

  // Should probably use getters/setters for access control
  public T data;
  public Node<T> next;

  public Node(T data) {
    this.data = data;
  }
}