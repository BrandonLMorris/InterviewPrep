"""
Chapter 4 Question 3

Given a sorted list of unique integers, create a binary search tree of
minimal height

Approach: Do a binary search traversal, building up a BST as you go. The list
is sorted, so take the middle element as the root, then recursively build
the left and right subtrees with the left and right halves of the list,
respectively.
"""
from BinaryTree import BinaryTree

def make_bst(lst, start, end):
    if end - start < 0:
        return None
    # Base case: one element
    if end - start == 0:
        return BinaryTree(lst[start])
    mid = start + end // 2      # Center, leaning left
    temp = BinaryTree(lst[mid])
    temp.left = make_bst(lst, start, mid-1)
    temp.right = make_bst(lst, mid+1, end)
    return temp


if __name__ == '__main__':
    root = make_bst([1, 2, 3], 0, 2)
    print('root:', root.data)
    print('root.right:', root.right.data)
    print('root.left:', root.left.data)
