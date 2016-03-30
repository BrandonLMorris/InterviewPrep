"""
Chapter 3 Question 5

Implement a queue using two stacks

Approach: Fairly straightforward, performance just stinks. To pop, dequeue from
the currently used queue. To push, copy the elements from the current queue to
the second, enqueue the new element, then copy all the elements back. (Copying
is done for pushing and not popping to improve peek performance.) Overall, peek
and pop are O(1), but pushing is O(n).
"""

from collections import deque

class MyQueue:
    # Initialize the queues
    def __init__(self):
        self.q1, self.q2 = deque(), deque()

    def push(self, elem):
        while len(self.q1) > 0:
            self.q2.append(self.q1.popleft())
        self.q1.append(elem)
        while len(self.q2) > 0:
            self.q1.append(self.q2.popleft())

    def pop(self):
        return self.q1.popleft()

    def peek(self):
        return self.q1[0]

    def __str__(self):
        temp = '['
        for i in self.q1:
            temp += str(i) + ', '
        if len(self.q1) > 0:
            temp = temp[:-2]
        temp += ']'
        return temp

if __name__ == '__main__':
    my_queue = MyQueue()
    for i in range(1,4):
        my_queue.push(i)
    print("Initial `stack`:", my_queue)
    my_queue.push(4)
    print("Push 4:", my_queue)
    print("Pop:", my_queue.pop())
    print("Peek:", my_queue.peek())
    print("Final `stack`", my_queue)
