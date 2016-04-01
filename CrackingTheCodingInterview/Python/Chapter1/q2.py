#!/bin/usr/env python3
"""Solution to question 2 of chapter 1"""

def my_reverse(st):
    """Reverse a C-style string"""
    # Make sure st is a list, not a normal string so I can't cheat
    if type(st) is str:
        st = list(st)

    tmp = None
    i, length = 0, len(st) // 2
    while i < length:
        tmp = st[i]
        st[i] = st[len(st) - i - 1]
        st[len(st) - i - 1] = tmp
        i += 1

    return ''.join(st)


if __name__ == '__main__':
    input_str = input('Enter the string to reverse: ')
    print(my_reverse(input_str))



