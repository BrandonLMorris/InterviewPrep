"""
A simple Binary Tree impelementation
"""

class BinaryTree:
    def __init__(self, data=None, left=None, right=None):
        self.data = data
        self.left = left
        self.right = right
        self.parent = None

    def addright(self, node):
        """Add a BinaryTree as the right child of this tree"""
        if not isinstance(node, BinaryTree):
            node = BinaryTree(node)
        self.right = node
        node.parent = self
        return self

    def addleft(self, node):
        """Add a BinaryTree as the left child of this tree"""
        if not isinstance(node, BinaryTree):
            node = BinaryTree(node)
        self.left = node
        node.parent = self
        return self

    def __eq__(self, other):
        """
        Equality of two trees determined if they have the same data
        and children
        """
        if other is None or self.data != other.data: return False

        if self.left is not None and self.right is not None:
            return self.left == other.left and self.right == other.right
        elif self.left is not None:
            return False if other.right is not None else self.left == other.left
        elif self.right is not None:
            return False if other.left is not None else self.right == other.right

        # Both children are None and roots match
        return True

def preorder(root, lst=None):
    """Return a list of nodes from the preorder traversal of the tree"""
    if lst is None:
        lst = list()
    lst.append(root)
    if root.left is not None:
        preorder(root.left, lst)
    if root.right is not None:
        preorder(root.right, lst)
    return lst

def inorder(root, lst=None):
    """Return a list of nodes from the inorder traversal of the tree"""
    if lst is None:
        lst = list()
    if root.left is not None:
        inorder(root.left, lst)
    lst.append(root)
    if root.right is not None:
        inorder(root.right, lst)
    return lst

def postorder(root, lst=None):
    """Return a list of nodes from the postorder traversal of the tree"""
    if lst is None:
        lst = list()
    if root.left is not None:
        postorder(root.left, lst)
    if root.right is not None:
        postorder(root.right, lst)
    lst.append(root)
    return lst

def to_list(root, pos=0, lst=None):
    """
    Convert this pointer-based BinaryTree into a list based one.
    If i is the index of a node, its left child is at 2*i + 1 and its right
    child is at 2*i + 2. Empty spots are filled with None
    """
    if lst is None:
        lst = [root]

    if root.left is not None or root.right is not None:
        while len(lst) < 2*pos + 3:
            lst.append(None)
        lst[2*pos+1] = root.left
        lst[2*pos+2] = root.right

    if root.left is not None:
        to_list(root.left, 2*pos+1, lst)
    if root.right is not None: to_list(root.right, 2*pos+2, lst)

    return lst

def create_full():
    """Helper method to create a full tree of height 3 (for testing)"""
    root = BinaryTree(4)
    root.addright(BinaryTree(6))
    root.addleft(BinaryTree(2))
    root.right.addright(BinaryTree(7))
    root.right.addleft(BinaryTree(5))
    root.left.addleft(BinaryTree(1))
    root.left.addright(BinaryTree(3))
    return root
