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
    balanceUp(node);

    return true;
  }

  /**
   * Determine if the Tree contains a give value is in this
   * Tree. Relies on equals(). Object argument must be comparable to the Tree
   * type or will throw a ClassCastException.
   *
   * @param o value to search for
   * @return  true if in the Tree, false otherwise.
   * @throws java.lang.ClassCastException if the argument cannot be comparable
   *                                      with the tree type
   */
  public boolean contains(Object o) {
    return find(o) != null;
  }

  /**
   * Find a node in the tree based on a given value
   *
   * @param o the value to search off of
   * @throws java.util.NoSuchElementException if the tree is empty
   * @throws java.lang.ClassCastException if the argument is not comparable
   *                                      with the tree type
   * @return  the Node whose value is equal to the argument, or null if no
   *          such element exists
   */
  private Node find(Object o) {
    if (isEmpty()) {
      return null;
    }

    // Attempt to cast to perform binary search
    Comparable c;
    if (o instanceof Comparable) {
      c = (Comparable)o;
    } else {
      // Can't locate unless comparable
      return null;
    }

    Node current = root;
    while (current != null) {
      int comp = c.compareTo(current.value);
      if (comp == 0) {
        return current;
      } else if (comp < 0) {
        current = current.left;
      } else {
        current = current.right;
      }
    }

    return null;
  }

  /**
   * Returns the lowest (least) element in this tree.
   *
   * @throws java.util.NoSuchElementException if the tree is empty
   */
  public T first() {
    return firstNode().value;
  }

  /**
   * Returns the lowest (least) Node in this tree.
   *
   * @throws java.util.NoSuchElementException if the tree is empty
   */
  private Node firstNode() {
    if (isEmpty()) {
      throw new NoSuchElementException("No first in empty tree");
    }

    // The lowest element is always the leftmost element in the tree
    Node current = root;
    while (current.left != null) {
      current = current.left;
    }

    return current;
  }

  /**
   * Returns the highest (greatest) element in this tree
   *
   * @throws java.util.NoSuchElementException if the tree is empty
   */
  public T last() {
    return lastNode().value;
  }

  /**
   * Returns the highest (greatest) element in this tree
   *
   * @throws java.util.NoSuchElementException if the tree is empty
   */
  private Node lastNode() {
    if (isEmpty()) {
      throw new NoSuchElementException("No last in empty tree");
    }

    // The highest element is always the rightmost element in the tree
    Node current = root;
    while (current.right != null) {
      current = current.right;
    }

    return current;
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
    Node ceil = successor(element, true);
    return ceil == null ? null : ceil.value;
  }

  /**
   * Returns the greatest element less than or equal to the
   * given element, or null
   *
   * @param element the element to find the floor off of
   */
  public T floor(T element) {
    Node floor = predecessor(element, true);
    return floor == null ? null : floor.value;
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
    Node higher = successor(element, false);
    return higher == null ? null : higher.value;
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
    Node lower = predecessor(element, false);
    return lower == null ? null : lower.value;
  }

  /**
   * Ensure that the tree does not contain an element that equals
   * the one given (equality determined with the equals() method.
   *
   * @param o   the object to remove from the tree
   * @return    true if the object was found in the tree
   */
  public boolean remove(Object o) {
    Node node = find(o), parent = null;
    if (node == null) {
      return false;
    }

    // When removing the last element from the tree
    boolean isRoot = node == root;
    if (isRoot && size == 1) {
      root = null;
      size--;
      return true;
    }

    boolean isLeftChild = false;
    boolean needBalance = true;
    if (!isRoot) {
      isLeftChild = node.parent.left == node;
    }

    // Case 1: node is a leaf. Remove and return
    if (node.left == null && node.right == null) {
      // Disconnect from the parent
      if (!isRoot && isLeftChild) {
        node.parent.left = null;
      } else if (!isRoot) {
        node.parent.right = null;
      }
      parent = node.parent;
      needBalance = node.black();
      node.parent = null;
      if (needBalance) {
        deleteBalance(null, parent);
      }
      size--;
      return true;
    }

    // Case 2: node only has one child. Copy into this position
    if (node.right != null && node.left == null) {
      // Move the right child
      node.right.parent = node.parent;
      if (!isRoot) {
        if (isLeftChild) {
          node.parent.left = node.right;
        } else {
          node.parent.right = node.right;
        }
        if ((node.right != null && node.right.red()) || node.red()) {
          needBalance = false;
        }
        if (needBalance) {
          deleteBalance(node.right, node.parent);
        } else {
          node.right.color = BLACK;
        }
      } else {
        root = node.right;
        root.color = BLACK;
      }
      node.right = null;
      size--;
      return true;
    } else if (node.left != null && node.right == null) {
      // Move the left child
      node.left.parent = node.parent;
      if (!isRoot) {
        if (isLeftChild) {
          node.parent.left = node.left;
        } else {
          node.parent.right = node.left;
        }
        if ((node.left != null && node.left.red()) || node.red()) {
          needBalance = false;
        }
        if (needBalance) {
          deleteBalance(node.left, node.parent);
        } else {
          node.left.color = BLACK;
        }
      } else {
        root = node.left;
        root.color = BLACK;
      }
      node.left = null;
      size--;
      return true;
    }

    // Case 3: node has two children. Swap with the in-order successor then
    // recur on that node
    Node replacement = successor(node.value, false);
    T newVal = replacement.value;
    remove(replacement.value);
    node.value = newVal;
    return true;
  }

  /**
   * Rebalance a tree at a particular Node after a deletion operation given
   * the Node that replaces the deleted one and its parent (in case the
   * replacement is null).
   *
   * @param u the node that replaces the deleted one
   * @param p the parent of the replacement node
   */
  private void deleteBalance(Node u, Node p) {
    if (u == root) {
      u.color = BLACK;
      return;
    }

    boolean sIsLeft = p.left != u;
    Node s = sIsLeft ? p.left : p.right;

    if (s == null) {
      if (p.black()) {
        deleteBalance(p, p.parent);
      } else {
        p.color = RED;
        return;
      }
    }

    // Case when s is red: recolor, rotate, then recur
    if (s.red()) {
      s.color = !s.color;
      p.color = !p.color;
      if (sIsLeft) {
        rotateRight(p);
      } else {
        rotateLeft(p);
      }
      deleteBalance(u, p);
      return;
    }

    boolean sLeftRed = false, sRightRed = false;
    if (s.left != null && s.left.red()) {
      sLeftRed = true;
    }
    if (s.right != null && s.right.red()) {
      sRightRed = true;
    }

    // First case: s has a red child
    if (sLeftRed || sRightRed) {
      if (sIsLeft) {
        if (sLeftRed) {
          // Left left
          rotateRight(p);
          s.left.color = !s.left.color;
        } else {
          // Left right
          rotateLeft(s);
          rotateRight(p);
          p.parent.color = !p.parent.color;
        }
      } else if (sRightRed) {
        // Right right
        rotateLeft(p);
        s.right.color = !s.right.color;
      } else {
        // Right left
        rotateRight(s);
        rotateLeft(p);
        p.parent.color = !p.parent.color;
      }
      return;
    }

    // Case when s is black an has no red children
    s.color = !s.color;
    if (p.black()) {
      deleteBalance(p, p.parent);
    } else {
      p.color = BLACK;
    }
  }

  /**
   * Find and remove the first (least) element in the set
   *
   * @return the element removed
   */
  public T pollFirst() {
    if (isEmpty()) {
      return null;
    }
    T first = first();
    remove(first);
    return first;
  }

  /**
   * Find and remove the last (greatest) element in the set
   * @return the element removed
   */
  public T pollLast() {
    if (isEmpty()) {
      return null;
    }
    T last = last();
    remove(last);
    return last;
  }

  /** Return a list of all the elements in this Tree in ascending order. */
  public List<T> toList() {
    return inOrder(root, new ArrayList<T>(size));
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
  private void balanceUp(Node node) {
    // Root must always be black (Rule #2)
    if (node == root) {
      root.color = BLACK;
      return;
    }

    // If child of the root or not a red-red pair, call recursively on
    // the parent
    if (node.parent == root || !(node.red() && node.parent.red())) {
      balanceUp(node.parent);
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
      balanceUp(parent);
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

      balanceUp(parent);
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
    balanceUp(parent);
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
   * Find the in-order successor to a given value in the tree.
   *
   * @param element  the value to find the successor of
   * @param canEqual  whether the successor can equal the given value
   * @return  the next value of greater (or equal) value, or null if no such
   *          element exists in the tree.
   */
  private Node successor(T element, boolean canEqual) {
    Node current = root;

    if (!isEmpty() && last().compareTo(element) < 0) {
      return null;
    }

    while (current != null) {
      int comp = current.value.compareTo(element);

      if (comp == 0 && canEqual) {
        return current;
      }

      if (comp < 0 || (comp == 0 && !canEqual)) {
        if (current.right != null) {
          current = current.right;
        } else {
          // Move up until find a parent that this is the right child of
          while (current.parent != null) {
            if (current.parent.left == current) {
              return current.parent;
            }
            current = current.parent;
          }
          return null;
        }
      } else {                       // current is greater than element
        if (current.left == null) {
          return current;
        }
        current = current.left;
      }
    }

    return null;
  }

  /**
   * Find the in-order predecessor to a given value in the tree.
   *
   * @param element the value to find the predecessor of
   * @param canEqual whether the predecessor can equal the element
   * @return the largest value of lesser (or equal) value to element, or null if
   *         no such element exists in the tree.
   */
  private Node predecessor(T element, boolean canEqual) {
    Node current = root;

    if (isEmpty() || first().compareTo(element) > 0) {
      return null;
    }

    while (current != null) {
      int comp = current.value.compareTo(element);

      if (comp == 0 && canEqual) {
        return current;
      }

      if (comp > 0 || (comp == 0 && !canEqual)) {
        if (current.left != null) {
          current = current.left;
        } else {
          // Find the first parent that this is a right child of
          while (current.parent != null) {
            if (current.parent.right == current) {
              return current.parent;
            }
            current = current.parent;
          }
          return null;
        }
      } else {  // current is less than the element
        if (current.right == null) {
          return current;
        }
        current = current.right;
      }
    }
    return null;
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

  /**
   * Determines if any red node has a red sibling in the tree. Used to ensure
   * balance and adherence to the rules of a Red Black tree.
   *
   * @return true if a red-red pair is found, false otherwise
   */
  public boolean hasRedRedPair() {
    // Do an in-order traversal, looking for red-red pairs
    return root != null && hasRedRedPair(root);
  }

  /**
   * Determines if any red node has a red sibling, starting from the given node
   *
   * @param n the node to search off of
   * @return true if the tree rooted at n contains a red-red pair
   */
  private boolean hasRedRedPair(Node n) {
    if (n.black()) {
      // This node is black, so it can't be part of a red-red pair
      return (n.left != null && hasRedRedPair(n.left))
          || (n.right != null && hasRedRedPair(n.right));
    }
    // This node is red
    return (n.left != null && n.left.red())
        || (n.right != null && n.right.red());
  }

  /** Return a string representation of an in-order traversal */
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

/*
 * Deletion Algorithm details
 * Taken from GeeksForGeeks.org
 * www.geeksforgeeks.org/red-black-tree-set-3-delete-2/
 *
 * Unlike insertion, deletion can break the structural integrity of the tree
 * mainly by removing a black node, thus breaking rule #3. When a black node
 * gets replaced by another black, it is considered a "double black" and must
 * be dealt with.
 *
 * 1. Perform standard BST delete. Since the BST delete function recurs when
 * the deleted node has two children, the only case needs to be considered is
 * when a node has zero or one children, since that is the node that is actually
 * removed from the tree. v is the node that is being deleted, and u is the
 * child that is replacing v.
 *
 * 2. Case when either u or v is red. This case does not damage the structure
 * of the tree. Simply ensure that the replacement node is black.
 *
 * 3. Case when both u and v are black
 *    3.1 u is now a double black (even occurs when v is a leaf, since empty
 *    trees are considered black).
 *    3.2 While u is double black or not the root:
 *      a) If u's sibling (the other child of the parent) s is black and has at
 *      least one red child, perform rotations.
 *        i) Left left: s is a left child and the left child of s is red (or
 *        both children). Rotate right about the parent
 *        ii) Left right: s is a left child and only the right child of s is
 *        red. Rotate left about s, then right about p.
 *        iii) Right right: s is a right child and the right child of s is red
 *        (or both children). This is a mirror of (i). Rotate left about parent
 *        iv) Right left: s is a right child and only the left child of s is
 *        red. This is a mirror of (ii). Rotate left about s, then right about
 *        p.
 *
 *      b) u's sibling (s) is black and has two black (or null) children.
 *      Recolor the sibling to be red, then recursively call the balance on the
 *      parent. (If the parent is red, just recolor, don't call again).
 *
 *      c) If u's sibling (s) is red, recolor the sibling and the parent, then
 *      rotate such that the sibling is now in the parent's position (right
 *      rotation if s is a left child, left rotation is s is a right child).
 *      Now it is a case of (a) or (b).
 */
