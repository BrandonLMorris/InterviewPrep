"""
Chapter 4 Question 4

Given a binary tree, convert it to a set of linked lists, each list representing
a layer of the tree.

(I'm kind of cheating: instead of a stict linked list, I'm using regular
lists. Same principle, though.)
"""

from BinaryTree import BinaryTree

def layerize(root, current_layer=0, lst=None):
    if not lst:
        lst = list()
    if len(lst) < current_layer+1:
        lst.append(list())
    lst[current_layer].append(root.data)
    if root.left:
        lst = layerize(root.left, current_layer+1, lst)
    if root.right:
        lst = layerize(root.right, current_layer+1, lst)

    return lst

if __name__ == '__main__':
    root = BinaryTree(1)
    root.left = BinaryTree(2)
    root.right = BinaryTree(3)
    root.right.right = BinaryTree(4)
    print(layerize(root))
