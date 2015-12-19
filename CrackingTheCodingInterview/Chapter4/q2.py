"""
Chapter 4 Question 3

Given two nodes of a directed, determine if a path between them exists

Approach: Any traversal will do. I'll do BFS because why not.
"""

from Graph import Node
from collections import deque

def bfs(start, end):
    to_visit = deque()
    to_visit.append(start)
    visited = set()

    while len(to_visit) > 0:
        current = to_visit.popleft()
        if current is end:
            return True
        visited.add(current)
        for node in current.neighbors:
            if node not in visited:
                to_visit.append(node)

    # If control reaches here, the nodes are not connected
    return False

if __name__ == '__main__':
    start = Node()
    start.add_neighbor(Node())
    start.neighbors[0].add_neighbor(Node())
    end = start.neighbors[0].neighbors[0]

    print(bfs(start, end))  # Expect: True
    print(bfs(end, start))  # Expect: False
