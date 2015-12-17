"""
Chapter 3 Question 4

Write a program to solve the Towers of Hanoi
"""
from sys import argv

DEBUG = False

# Establish tower height
if len(argv) > 1:
    tower_height = argv[1]
else:
    tower_height = input("Enter the tower height: ")
try:
    tower_height = int(tower_height)
except ValueError:
    print("Error: Please provide the tower height as an integer")
    exit(1)


# Initialize the towers as three lists
first, second, third = list(), list(), list()
for i in range(1, tower_height+1):
    first.append(i)


def print_towers():
    """Prints the towers as horizontally"""
    temp = 'First  Tower: '
    for i in reversed(first):
        temp += str(i) + ' '
    temp += '\nSecond Tower: '
    for i in reversed(second):
        temp += str(i) + ' '
    temp += '\nThird  Tower: '
    for i in reversed(third):
        temp += str(i) + ' '
    print(temp)

def can_move(f, t):
    """Returns if moving the top plate from f to t is valid"""
    if len(t) == 0 or (f[0] > t[0]):
        return True
    return False

def move(f, t):
    """Moves a single plate from tower f to tower t"""
    t.append(f.pop())

def peek(tower):
    """
    Returns the value of the top plate on `tower`, or -1 if the tower is
    empty
    """
    if len(tower) == 0:
        return -1
    else:
        return tower[0]

def transfer(src, dst, size, print_moves=False):
    """
    transfer - move all the plates from source tower to destination tower
    Takes advantage of the recursive principle of Hanoi Towers: moving a tower
    of n plates is equivalent to moving a tower of n-1 plates, moving the n-th
    plate, then moving the n-1 plates back on top of the n-th plate.
    """
    if DEBUG:
        print("\nTransfer size =", size)
        print_towers()
        input("Press enter to continue")

    other = None
    if first is not src and first is not dst:
        other = first
    elif second is not src and second is not dst:
        other = second
    else:
        other = third

    # Edge case: tower of size 1
    if size == 1:
        move(src, dst)
    # Base case: tower of size 2
    elif size == 2:
        move(src, other)
        move(src, dst)
        move(other, dst)
        if print_moves:
            print_towers()
    else:
        transfer(src, other, size-1)
        move(src, dst)
        transfer(other, dst, size-1)

if __name__ == '__main__':
    print_towers()
    transfer(first, third, tower_height)
    print() # empty line
    print_towers()
