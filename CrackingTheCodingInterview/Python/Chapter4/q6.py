"""
Chater 4 Question 6

Given a BST, write a function to find the 'next' (in-order successor) node in
the tree.

Approach: Since it's a BST, it can be shown that the in-order successor can be
found by going one child to the right, then keep going left until you encounter
a leaf. The edge case is when this child is a leaf or does not have a right
child. In that case, the in-order successor will the the parent of which this
subtree is a left child.
"""

from BinaryTree import BinaryTree

def next_node(root):
    current = root
    if current.right is not None:
        current = current.right
        while current.left is not None:
            current = current.left
        return current

    # Go up until we find a parent who this is the left subtree of
    while current.parent is not None:
        if current.parent.left is current:
            return current.parent
        current = current.parent


if __name__ == '__main__':
    root = BinaryTree(4)
    root.addleft(BinaryTree(2))
    root.left.addright(BinaryTree(3))
    root.left.addleft(BinaryTree(1))
    root.addright(BinaryTree(6))
    print('Expect 3:', next_node(root.left).data)
    print('Expect 4:', next_node(root.left.right).data)

    # Tree of one node
    root = BinaryTree(0)
    print('Expect None:', next_node(root))
