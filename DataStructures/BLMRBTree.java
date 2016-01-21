/**
 * A basic implementation of a Red-Black Binary Search tree, using an array
 * as the underlying data structure. The purpose is to mock functionality
 * similar to Java's TreeSet class. As such, duplicates are not allowed.
 *
 * Created by Brandon Morris 2016
 *
 * The four rules of Red-Black trees are:
 *  1) Each node is either red or black
 *  2) The root and all empty trees are black
 *  3) All paths from the root to an empty tree contain the same number of
 *     black nodes
 *  4) A red node cannot have a red child
 */
package DataStructures;

public class BLMRBTree<T extends Comparable<? super T>> {
  public static final boolean RED = true;
  public static final boolean BLACK = false;

  private T[] elements;
  private int size;
  private Node root;

  /** Basic constructor: creates a new tree */
  public BLMRBTree() {
    // TODO
  }

  /**
   * Adds a new element to the Red Black tree. Automatically takes
   * care of balancing the tree.
   *
   * @param toAdd the value to add to the tree
   */
  public void add(T toAdd) {
    Node n = new Node(toAdd);

    if (root == null) {
      root = n;
      return;
    }

    Node current = root;

    while (current != null) {
      if (n.value.compareTo(current.value) <= 0) {
        if (current.left == null) {
          current.left = n;
          break;
        }
        // Move down the left side
        current = current.left;
      } else {
        if (current.right == null) {
          current.right = n;
          break;
        }
        // Move down the right side
        current = current.right;
      }
    }

    /* Balancing */

    // Case 1: The new node is the root
    if (n == root) {
      n.color = BLACK;
      return;
    }

    // Case 2: The parent is red
    if (n.parent.color == BLACK) {
      return;
    }

    // Case 3:


  }


  public boolean contains(T value) { return false; }

  public T first() { return null; }

  public T last() { return null; }

  public T ceiling(T element) { return null; }

  public T floor(T element) { return null; }

  public T higher(T element) { return null; }

  public T lower(T element) { return null; }

  public boolean remove(Object o) { return false; }

  public T pollFirst() { return null; }

  public T pollLast() { return null; }

  public boolean isEmpty() {
    return size == 0;
  }

  public int size() {return -1;}

  private void balance(Node n) {
    // Case 1
    if (n == this.root) {
      n.color = BLACK;
      return;
    }

    // Case 2
    if (n.parent.color == BLACK) {
      return;
    }

    // Find if n is left or right child
    boolean nIsLeft = n.parent.left == n;

    // Find the uncle, if it exists
    boolean parentIsLeft = n.parent.parent.left == n.parent;

    Node uncle;
    if (parentIsLeft) {
      uncle = n.parent.parent.right;
    } else {
      uncle = n.parent.parent.left;
    }

    // Case 3
    if (n.parent.color == RED && uncle.color == RED) {
      n.parent.color = BLACK;
      n.parent.parent.color = BLACK;
      uncle.color = RED;
      balance(n.parent.parent);
    }

    // Case 4
    if (n.parent.color == RED && uncle.color == BLACK && nIsLeft != parentIsLeft) {
      
    }
  }

  private boolean rotateLeft(Node n) {
    // Cannot rotate left if n's right child is null
    if (n.right == null) return false;

    Node temp = n.right.left;


    return false;
  }

  /**
   * The Node class. Contains the actual value, its color, and references to
   * the parent and both children.
   */
  private class Node {

    public T value;
    private boolean color;

    public Node parent;
    public Node left;
    public Node right;

    public boolean color() {
      return color;
    }

    public Node(T value) {
      this.color = RED;
      this.value = value;
    }
  }
}

/*
 * Insertion
 * 1. Insert the node in its correct postion (in the BST)
 * 2. Paint it red
 * 3. Chapter1 of the following cases:
 *    1. N (the node) is the root. Paint it black. Done.
 *    2. P (the parent) is black. Nothing needs to be done.
 *    3. Both P and U (the uncle) are red. Repaint P, U and 
 *       G (the grandparent). Recursively move up from G and
 *       correct any possible errors.
 *    4. P is red but U is black, N on the inside.
 *       Rotate around P such that red is on the outside,
 *       then perform case 5.
 *    5. P is red but U is black, N is on the outside.
 *       Rotate around G, repaint P and G.
 *
 */
