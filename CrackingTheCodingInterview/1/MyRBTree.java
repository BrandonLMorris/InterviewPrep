import java.util.*;

/**
 * A basic implementation of a binary search tree using an array
 */
public class MyRBTree<T extends Comparable<? super T>> {
  private T[] elements;
  private Node root;

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
      n.color = Node.BLACK;
      return;
    }

    // Case 2: The parent is red
    if (n.parent.color == Node.BLACK) {
      return;
    }

    // Case 3:


  }

  private void balance(Node n) {
    // Case 1
    if (n == this.root) {
      n.color = Node.BLACK;
      return;
    }

    // Case 2
    if (n.parent.color == Node.BLACK) {
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
    if (n.parent.color == Node.RED && uncle.color == Node.RED) {
      n.parent.color = Node.BLACK;
      n.parent.parent.color = Node.BLACK;
      uncle.color = Node.RED;
      balance(n.parent.parent);
    }

    // Case 4
    if (n.parent.color == Node.RED && uncle.color == Node.BLACK && nIsLeft != parentIsLeft) {
      
    }
  }

  private boolean rotateLeft(Node n) {
    // Cannot rotate left if n's right child is null
    if (n.right == null) return false;

    Node temp = n.right.left;


    return false;
  }


  /**
   * The Node class.
   * Contains the actual value, its color,
   * and references to the parent and both
   * children.
   */
  private class Node {
    public static final int RED = 1;
    public static final int BLACK = 0;

    public T value;
    private int color;

    public Node parent;
    public Node left;
    public Node right;

    public int color() {
      return color;
    }

    public Node(T value) {
      this.color = RED;
      this.value = value;
    }
  }
}

/*
 *  *** 5 Rules of a RB Tree  ***
 * 1. Every node must be red or black
 * 2. The root node must be black
 * 3. Every leaf node (null) must be black
 * 4. No red node can be the child of a red node
 * 5. The number of black node along any path from 
 *    the root to a leaf must contain the same number
 *    or red nodes.
 *
 * Insertion
 * 1. Insert the node in its correct postion (in the BST)
 * 2. Paint it red
 * 3. One of the following cases:
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
