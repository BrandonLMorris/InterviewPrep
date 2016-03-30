"""
Chpater 4 Question 7

Given two nodes from a Binary (not search) Tree, find their first common ancestor
"""

from BinaryTree import BinaryTree

def find_anscestor(root, first, second):
    first_on_left = search(root.left, first)
    sec_on_left = search(root.left, second)

    if first_on_left != sec_on_left:
        return root
    root = root.left if first_on_left else root.right
    return find_anscestor(root, first, second)

def search(root, node):
    """Returns if a given node is within a BinaryTree"""
    if root is None:
        return False
    if root is node:
        return True

    return search(root.left, node) or search(root.right, node)

if __name__ == '__main__':
    root = BinaryTree(4)
    root.addright(BinaryTree(6))
    root.addleft(BinaryTree(2))
    root.right.addleft(BinaryTree(5))
    root.right.addright(BinaryTree(7))
    root.left.addleft(BinaryTree(1))
    root.left.addright(BinaryTree(3))

    print('Expect 4:', find_anscestor(root, root.right, root.left).data)
    print('Expect 2:', find_anscestor(root, root.left.left, root.left.right).data)
