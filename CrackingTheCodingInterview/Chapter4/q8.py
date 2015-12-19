"""
Chapter 4 Question 8

Given two large binary trees, determine if the second (assumed to be smaller) is
a subtree of the first

Approach: Using the equality method I defined in BinaryTree.py, I can check if
two trees are equal. To find a subtree, traverse the larger tree looking for a
node equal to the root of the second tree. Then check for equality at that
point.
"""

from BinaryTree import BinaryTree, create_full

def is_subtree(root1, root2):
    if root1 is None:
        return False

    # Most of the work is done by __eq__
    if root1.data == root2.data and root1 == root2:
        return True

    return is_subtree(root1.left, root2) or is_subtree(root1.right, root2)

if __name__ == '__main__':
    root1 = create_full()
    root2 = BinaryTree(2).addleft(1).addright(3)
    print('Expect True:', is_subtree(root1, root2))
    print('Expect True:', is_subtree(root1, BinaryTree(1)))
    print('Expect False:', is_subtree(root1, BinaryTree(2).addright(3)))
