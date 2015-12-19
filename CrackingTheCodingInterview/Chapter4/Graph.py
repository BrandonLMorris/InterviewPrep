"""
GRAPHS GRAPHS GRAPH!

This module contains a simple graph implementation using adjacency lists
"""

class Node:
    """Simple node class used for a directed, unweighted graph"""
    def __init__(self, data=None):
        self.data = data
        self.neighbors = list()

    def add_neighbor(self, neighbor):
        # Directed, so adjacency is not symmetric
        self.neighbors.append(neighbor)
