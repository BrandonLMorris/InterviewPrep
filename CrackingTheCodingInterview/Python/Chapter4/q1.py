"""
Chapter 4 Question 1

Determine if a binary tree is balance (no subtree differs in height by more than 1)
"""
from BinaryTree import BinaryTree

def calc_height(root, acc=1):
    """
    Calculates the height of a BinaryTree (defined as the number of nodes
    along the longest path) recursively
    """
    # Base case
    if root.left == None and root.right == None:
        return acc
    elif root.left != None and root.right != None: # Neither child is None
        return max(calc_height(root.left, acc+1),
                   calc_height(root.right, acc+1))
    elif root.left != None:                        # At least one child is None
        return calc_height(root.left, acc+1)
    else:
        return calc_height(root.right, acc+1)

def is_balanced(root):
    if root.left != None and root.right != None:
        return abs(calc_height(root.left) - calc_height(root.right)) <= 1
    elif root.left != None:
        return calc_height(root.left) <= 1
    elif root.right != None:
        return calc_height(root.right) <= 1
    else:
        return True

if __name__ == '__main__':
    root = BinaryTree(1)
    root.right, root.left = BinaryTree(2), BinaryTree(3)
    print(is_balanced(root))
    print('Unbalancing the tree...')
    root.right.right, root.right.right.right = BinaryTree(4), BinaryTree(5)
    print(is_balanced(root))
