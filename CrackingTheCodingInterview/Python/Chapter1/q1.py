#!/usr/bin/env python3
"""Solution to Question 1 of Chapter 1"""

def all_unique_chars(string):
    """Return true if all the characters in a string are unique"""
    chars = set()
    for c in string:
        if c in chars: return False
        chars.add(c)

    return True

if __name__ == '__main__':
    s = input('Enter a string to test: ')
    print(all_unique_chars(s))
