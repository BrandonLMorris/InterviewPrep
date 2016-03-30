"""
Chapter 3 Question 6

Implement a method to sort a stack using another stack as the only other data structure

Approach: Selection sort, using the other stack to find the minimum of the subarray. Need to be
    careful for duplicates, though. Complexity: O(n^2)
"""

def sort_stack(stack):
    """
    Sorts a stack using only another stack for additional data structures. Only push (written
    as stack.append()), pop, peek (written as stack[len(stack)-1]), and isEmpty (written as
    len(stack) > 0) operations are allowed.
    """
    # The second stack
    stack2 = list()

    # Find the size of the stack using
    size = 0
    while len(stack) > 0:
        stack2.append(stack.pop())
        size += 1
    while len(stack2) > 0:
        stack.append(stack2.pop())

    for i in range(1, size+1):
        # Find the max value
        max_val = stack[len(stack)-1]
        for j in range(i):
            if max_val < stack[len(stack)-1]:
                max_val = stack[len(stack)-1]
            stack2.append(stack.pop())

        # Put all the elements back, except for the max element
        firstMax = True
        while len(stack2) > 0:
            if stack2[len(stack2)-1] == max_val and firstMax:
                firstMax = False
                stack2.pop()
            else:
                stack.append(stack2.pop())
        # Now put back the max
        stack.append(max_val)

if __name__ == '__main__':
    stack = [4, 3, 2, 1]
    print('Original stack:', stack)
    sort_stack(stack)
    print('Final stack: ', stack)
    print(stack.pop())
