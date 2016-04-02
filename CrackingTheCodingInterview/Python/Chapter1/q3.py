#!/usr/bin/env python3
"""Solution to question 3 of chapter 1"""

def is_perm(s1, s2):
    """Return true if s1 is a permutation of s2, assuming spaces count"""
    if len(s1) != len(s2):
        return False

    # Add for occurences in s1, subtract for occurences in s2
    counts = [0 for _ in range(128)]
    for c in s1:
        counts[ord(c)] += 1
    for c in s2:
        counts[ord(c)] -= 1

    # Everything should be zero
    return all([x == 0 for x in counts])

if __name__ == '__main__':
    string1 = input('Enter string 1: ')
    string2 = input('Enter string 2: ')

    if is_perm(string2, string1):
        print('They are permutations')
    else:
        print('They are not permutations')

