"""
Chapter 4 Question 5

Given a binary tree, determine if it's a binary search tree.

Approach: Check if the root of a subtree is less than or equal to its left
child, or greater than its left child. Then recursively work on each child
until an invalid subtree is found or the leaves of the tree.

CORRECTION: It's not enough to say that the individual children of a node obey
the binary search tree property; the entire subtrees must be taken into account.
For instance, the following tree would pass the test above, but is not a BST:
        1
          \
           2
          /
         0      0 < 1 but occurs in the right subtree of 1: invalid
New Aproach: To be a BST, the minimum of the right subtree must be greater than
(or equal to, per my definition) to the root of a tree, and the maximum of a
left subtree must be (strictly) less than the root of a tree. Apply this to
the root and recursively until reaching the leaves.
"""

from BinaryTree import BinaryTree

def is_bst(root):
    if root.left is not None and tree_max(root.left) >= root.data:
        return False
    if root.right is not None and tree_min(root.right) < root.data:
        return False

    # Both children are not None
    if root.left is not None and root.right is not None:
        return is_bst(root.left) and is_bst(root.right)
    # Both children are None
    elif root.right is None and root.right is None:
        return True
    # One child is None
    elif root.right is not None:
        return is_bst(root.right)
    else:
        return is_bst(root.left)

# Return the maximum value from a tree
def tree_max(root):
    # Four cases: No chilren, both chilren, only left child, only right child
    if root.left is None and root.right is None:
        return root.data
    if root.left is not None and root.right is not None:
        return max(root.data, max(tree_max(root.left), tree_max(root.right)))
    if root.left is not None:
        return max(root.data, tree_max(root.left))
    if root.right is not None:
        return max(root.data, tree_max(root.right))

# Return the minimum value from a tree
def tree_min(root):
    # Four cases: No chilren, both chilren, only left child, only right child
    if root.left is None and root.right is None:
        return root.data
    if root.left is not None and root.right is not None:
        return min(root.data, min(tree_max(root.left), tree_max(root.right)))
    if root.left is not None:
        return min(root.data, tree_max(root.left))
    if root.right is not None:
        return min(root.data, tree_max(root.right))


if __name__ == '__main__':
    root = BinaryTree(1)
    root.right = BinaryTree(2)
    root.left = BinaryTree(0)
    print('Expect True:', is_bst(root))
    root.right.right = BinaryTree(1)
    print('Expect False:', is_bst(root))
    # Tree with just one node
    print('Excpect True:', is_bst(root.right.right))

    # Specific case outlined in correcton
    root = BinaryTree(1)
    root.right = BinaryTree(2)
    root.right.left = BinaryTree(0)
    print('Expect False:', is_bst(root))         # Expected: False
