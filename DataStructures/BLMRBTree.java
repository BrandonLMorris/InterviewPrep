/**
 * A basic implementation of a Red-Black Binary Search tree, using pointer
 * chains to construct the underlying tree. The purpose is to mock functionality
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

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BLMRBTree<T extends Comparable<? super T>> {
  public static final boolean RED = true;
  public static final boolean BLACK = false;

  private int size;
  public Node root;

  /** Basic constructor: creates a new tree */
  public BLMRBTree() {
    size = 0;
    root = null;
  }

  /**
   * Ensure that the tree contains the given element. Repositions
   * elements internally to ensure a balanced Red-Black Tree.
   *
   * Detailed notes on the insertion/balancing algorithm can be
   * found at the bottom of this file.
   *
   * @param toAdd the value to add to the tree
   * @return true if the element was actually added, false if already existed
   */
  public boolean add(T toAdd) {
    if (contains(toAdd)) {
      return false;
    }

    Node node = new Node(toAdd);
    insert(node);
    size++;
    balance(node);

    return true;
  }

  /**
   * Determine if the Tree contains a give value is in this
   * Tree. Relies on equals(). Object argument must be comparable to the Tree
   * type or will throw a ClassCastException.
   *
   * @param o value to search for
   * @return  true if in the Tree, false otherwise.
   * @throws java.util.NoSuchElementException if the tree is empty
   * @throws java.lang.ClassCastException if the parameter cannot be comparable
   *                                      with the tree type
   */
  public boolean contains(Object o) {
    if (isEmpty()) {
      return false;
    }

    // Attempt to cast to perform binary search
    Comparable c;
    if (o instanceof Comparable) {
      c = (Comparable)o;
    } else {
      // Resort to linear search
      return toList().contains(o);
    }

    Node current = root;
    while (current != null) {
      int comp = c.compareTo(current.value);
      if (comp == 0) {
        return true;
      } else if (comp < 0) {
        current = current.left;
      } else {
        current = current.right;
      }
    }

    return false;
  }

  /**
   * Returns the lowest (least) element in this tree.
   *
   * @throws java.util.NoSuchElementException if the tree is empty
   */
  public T first() {
    if (isEmpty()) {
      throw new NoSuchElementException("No first in empty tree");
    }

    // The lowest element is always the leftmost element in the tree
    Node current = root;
    while (current.left != null) {
      current = current.left;
    }

    return current.value;
  }

  /**
   * Returns the highest (greatest) element in this tree
   *
   * @throws java.util.NoSuchElementException if the tree is empty
   */
  public T last() {
    if (isEmpty()) {
      throw new NoSuchElementException("No last in empty tree");
    }

    // The highest element is always the rightmost element in the tree
    Node current = root;
    while (current.right != null) {
      current = current.right;
    }

    return current.value;
  }

  /**
   * Return the least element greater than or equal to given
   * element, or null
   *
   * @param element the element to find the ceiling against
   * @return the ceiling from the given element, or null if the greatest element
   *         in the set is less than the given element
   */
  public T ceiling(T element) {
    Node current = root;

    if (!isEmpty() && last().compareTo(element) < 0) {
      return null;
    }

    while (current != null) {
      int comp = current.value.compareTo(element);

      if (comp == 0) {
        return current.value;
      }

      if (comp < 0) {                 // current is less than element
        if (current.right != null) {
          current = current.right;
        } else {
          // Move up until find a parent that this is the right child of
          while (current.parent != null) {
            if (current.parent.left == current) {
              return current.parent.value;
            }
            current = current.parent;
          }
          return null;
        }
      } else {                       // current is greater than element
        if (current.left == null) {
          return current.value;
        }
        current = current.left;
      }
    }

    return null;
  }

  /**
   * Returns the greatest element less than or equal to the
   * given element, or null
   *
   * @param element the element to find the floor off of
   */
  public T floor(T element) {
    Node current = root;

    if (isEmpty() || first().compareTo(element) > 0) {
      return null;
    }

    while (current != null) {
      int comp = current.value.compareTo(element);

      if (comp == 0) {
        return current.value;
      }

      if (comp > 0) {           // current is greater than element
        if (current.left == null) {
          return current.value;
        }
        current = current.left;
      } else {                 // current is less than element
        if (current.right != null) {
          current = current.right;
        } else {
          // Move up until encounter a parent that this is the left child of
          while (current.parent != null && current.parent.left == current) {
            current = current.parent;
          }
          return current.value.compareTo(element) <= 0 ? current.value : null;
        }
      }
    }

    return null;
  }

  /**
   * Return the least element strictly greater than the given
   * element, or null if the argument is greater than or equal
   * to the highest element in the tree.
   *
   * @param element the value to find the lower of
   * @throws java.util.NoSuchElementException if the tree is empty
   */
  public T higher(T element) {
    Node current = root;

    if (isEmpty() || last().compareTo(element) <= 0) {
      return null;
    }

    while (current != null) {
      int comp = current.value.compareTo(element);

      if (comp <= 0) {           // current is less than or equal to the element
        if (current.right != null) {
          current = current.right;
        } else {
          // Move up until find a parent that this is the right child of
          while (current.parent != null) {
            if (current.parent.left == current) {
              return current.parent.value;
            }
            current = current.parent;
          }
          return null;
        }
      } else {                  // current is greater than the element
        if (current.left == null) {
          return current.value;
        }
        current = current.left;
      }
    }
    return null;
  }

  /**
   * Return the greatest element strictly lesser than the given
   * element, or null if the argument is less tha or equal to
   * the lowest element in the tree.
   *
   * @param element the value to find the lower of
   * @throws java.util.NoSuchElementException if tree is emtpy
   */
  public T lower(T element) {
    Node current = root;

    if (isEmpty() || first().compareTo(element) >= 0) {
      return null;
    }

    while (current != null) {
      int comp = current.value.compareTo(element);

      if (comp >= 0) {  // current is greater than or equal to the element
        if (current.left != null) {
          current = current.left;
        } else {
          // Find the first parent that this is a right child of
          while (current.parent != null) {
            if (current.parent.right == current) {
              return current.parent.value;
            }
            current = current.parent;
          }
          return null;
        }
      } else {  // current is less than the element
        if (current.right == null) {
          return current.value;
        }
        current = current.right;
      }
    }
    return null;
  }

  /**
   * Ensure that the tree does not contain an element that equals
   * the one given (equality determined with the equals() method.
   *
   * @param o   the object to remove from the tree
   * @return    true if the object was found in the tree
   */
  public boolean remove(Object o) {
    // TODO
    return false;
  }

  /**
   * Find and remove the first (least) element in the set
   *
   * @return the element removed
   */
  public T pollFirst() {
    // TODO
    return null;
  }

  /**
   * Find and remove the last (greatest) element in the set
   * @return the element removed
   */
  public T pollLast() {
    // TODO
    return null;
  }

  /** Return a list of all the elements in this Tree in ascending order. */
  public List<T> toList() {
    Node current = root;
    ArrayList<T> result = new ArrayList<T>(size);

    return inOrder(root, result);
  }

  /** Determine if the tree contains any elements */
  public boolean isEmpty() {
    return size == 0;
  }

  /** Retrieve the number of (distinct) elements in the set */
  public int size() {
    return size;
  }

  /**
   * Inserts the given node into the tree using standard BST rules. Assumes
   * that the element is not in the list already.
   *
   * @param node the node to insert into the tree
   * @throws java.util.NoSuchElementException if tree contains the element
   */
  private void insert(Node node) {
    // Trivial case when list is empty
    if (isEmpty()) {
      root = node;
      return;
    }

    Node current = root;
    while (true) {
      int comp = node.compareTo(current);
      if (comp == 0) {
        throw new NoSuchElementException("Cannot insert existing element");
      }

      // On the left side
      if (comp < 0) {
        if (current.left == null) {
          current.connect(node, Node.LEFT);
          return;
        }
        current = current.left;
        continue;
      }

      // On the right side
      if (current.right == null) {
        current.connect(node, Node.RIGHT);
        return;
      }
      current = current.right;
    }
  }

  /**
   * Helper method to balance the tree. Calls itself recursively moving
   * up the tree until at the root.
   *
   * @param node the Node to balance off of
   */
  private void balance(Node node) {
    // Root must always be black (Rule #2)
    if (node == root) {
      root.color = BLACK;
      return;
    }

    // If child of the root or not a red-red pair, call recursively on
    // the parent
    if (node.parent == root || !(node.red() && node.parent.red())) {
      balance(node.parent);
      return;
    }

    // Create the 4-node neighborhood in question
    Node parent = node.parent;
    Node grandparent = parent.parent;
    Node ankle = (parent == grandparent.left) ? grandparent.right
        : grandparent.left;

    // If the ankle is red, all we need to do is recolor (Case 1)
    if (ankle != null && ankle.red()) {
      parent.color = !parent.color;
      grandparent.color = !grandparent.color;
      ankle.color = !ankle .color;
      balance(parent);
      return;
    }

    // Determine if this node is the right or left child
    boolean isLeftChild = (node == parent.left);
    boolean isParentLeftChild = (parent == grandparent.left);

    // If both are left or both right, Cases 3 and 5 respectively
    if (isLeftChild == isParentLeftChild) {
      // Recolor
      parent.color = !parent.color;
      grandparent.color = !grandparent.color;

      // Rotate
      if (isLeftChild) {
        rotateRight(grandparent);
      } else {
        rotateLeft(grandparent);
      }

      balance(parent);
      return;
    }

    // Must be a Case 2 or 4. Rotate and call recursively
    if (isLeftChild) {
      // Case 4
      rotateRight(parent);
    } else {
      // Case 2
      rotateLeft(parent);
    }

    // Rebalance on the parent since it's now the lowest member of the
    // neighborhood
    balance(parent);
  }

  /**
   * Rotate a tree rooted around a given Node to the left
   *
   * @param pivot the root of the tree to rotate
   * @throws java.lang.IllegalStateException if the right child of the pivot is
   *                                         null
   */
  private void rotateLeft(Node pivot) {
    if (pivot.right == null) {
      throw new IllegalStateException(
          "Can't rotate left when right child is null");
    }

    /*
       Rearrange the tree like so:

           |             |
           a             b
          / \           / \
         ^   b   =>    a   ^
            / \       / \
           c   ^     ^   c
     */
    Node a = pivot, b = pivot.right, c = b.left;
    b.parent = a.parent;
    a.right = c;
    if (c != null) c.parent = a;
    b.left = a;

    // Reset the pointers going to a, if any
    if (a.parent != null) {
      if (a == a.parent.left) {
        a.parent.left = b;
      } else {
        a.parent.right = b;
      }
    }
    a.parent = b;

    // Reset the root if it was changed
    if (root == a) {
      root = b;
    }
  }

  /**
   * Rotate a tree rooted around a given Node to the right
   *
   * @param pivot the root of the tree to rotate
   * @throws java.lang.IllegalStateException if the left child of the pivot is
   *                                         null
   */
  private void rotateRight(Node pivot) {
    if (pivot.left == null) {
      throw new IllegalStateException(
          "Can't rotate right when left child is null"
      );
    }

    /*
      Rearrange the tree like so:

           |             |
           a             b
          / \           / \
         b   ^   =>    ^   a
        / \               / \
       ^   c             c   ^
     */
    Node a = pivot, b = pivot.left, c = b.right;
    b.parent = a.parent;
    a.left = c;
    if (c != null) c.parent = a;
    b.right = a;

    // Reset the pointers going to a, if any
    if (a.parent != null) {
      if (a == a.parent.left) {
        a.parent.left = b;
      } else {
        a.parent.right = b;
      }
    }
    a.parent = b;

    // Reset the root
    if (root == a) {
      root = b;
    }
  }

  /**
   * Create a list of elements from an inOrder traversal of this Tree
   *
   * @param node  the current node on the traversal
   * @param list  the list containing the elements
   * @return  the list of elements from the traversal
   */
  private ArrayList<T> inOrder(Node node, ArrayList<T> list) {
    if (node.left != null) {
      list = inOrder(node.left, list);
    }

    list.add(node.value);

    if (node.right != null) {
      list = inOrder(node.right, list);
    }

    return list;
  }

  /** Return a string representation of this traversal */
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[");

    for (T element : toList()) {
      sb.append(element.toString());
      sb.append(", ");
    }

    if (size > 0) {
      sb.delete(sb.length()-2, sb.length());
    }

    sb.append("]");
    return sb.toString();
  }

  /**
   * The Node class. Contains the actual value, its color, and references to
   * the parent and both children.
   */
  private class Node implements Comparable<Node>{
    public static final boolean LEFT = true, RIGHT= false;

    public T value;
    public boolean color;
    public Node parent, left, right;

    /** Create a new (red) node */
    public Node(T value) {
      this.parent = this.left = this.right = null;
      this.color = RED;
      this.value = value;
    }

    /** Make a Node a child of this one */
    public void connect(Node child, boolean side) {
      if (side == RIGHT) {
        this.right = child;
        child.parent = this;
      } else {
        this.left = child;
        child.parent = this;
      }
    }

    /** Basic comparable implementation to facilitate comparison */
    public int compareTo(Node that) {
      return this.value.compareTo(that.value);
    }

    /** Returns if this node is red */
    public boolean red() {
      return this.color == RED;
    }

    /** Returns if this node is black */
    public boolean black() {
      return this.color == BLACK;
    }
  }
}

/*
 * Insertion Algorithm Details
 * Taken from Dr. Hendrix's Auburn COMP2210 lecture notes
 *
 * To insert a new element into the Red-Black tree, start by inserting into its
 * correct position based on the rules of an ordinary Binary Search Tree. Make
 * the new Node red (since making it black would automatically unbalance the
 * tree).
 *
 * From the insertion point, walk back up the tree looking for a red-red Node
 * pair between a Node and its parent. Since this violates rule #4 of Red-Black
 * trees, it must be repaired.
 *
 * The repairs will all take place on the 4-node "neighborhood" involving the
 * initial Node, the parent, the grandparent, and the ankle (the grandparent's
 * other child). There are several structural possibilities for the nodes, and
 * each one needs to be addressed differently. The five significant cases are
 * as follows:
 *
 * Case 1: Ankle is red
 *        G(b)            | Here, the solution is just to recolor the Parent,
 *       /   \            | Ankle, and the Grandparent. Any other structural
 *     A(r)   P(r)        | possibility that involves a red Ankle can be
 *                \       | resolved similarly.
 *                N(r)    |
 *
 *
 * Case 2: Left side, inner
 *        G(b)            | To resolve, rotate the subtree rooted at the Parent
 *       /   \            | to the left. The tree now becomes a Case 3.
 *     P(r)   A(b)        |
 *         \              |
 *          N(r)          |
 *
 *
 * Case 3: Left side, outer
 *        G(b)            | To resolve, recolor the Parent and Grandparent, and
 *       /   \            | then rotate the tree to the right about the
 *     P(r)   A(b)        | Grandparent.
 *     /                  |
 *   N(r)                 |
 *
 *
 * Case 4: Right side, inner
 *        G(b)            | To resolve, rotate the subtree rooted at the Parent
 *       /   \            | to the right. The tree now becomes a Case 5.
 *     A(b)   P(r)        |
 *            /           |
 *          N(r)          |
 *
 *
 * Case 5: Right side, outer
 *        G(b)            | To resolve, recolor the Parent and Grandparent, and
 *       /   \            | then rotate the tree the the left about the
 *     A(b)   P(r)        | Grandparent.
 *              \         |
 *              N(r)      |
 */
