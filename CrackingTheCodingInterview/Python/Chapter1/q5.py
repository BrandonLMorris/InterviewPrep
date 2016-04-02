#!/usr/bin/env python3
"""Solution to question 5 of chapter one"""

def compress(st):
    """Basic string compression...sort of"""
    # Make sure we're using a C style string
    if type(st) is str:
        st = list(st)

    # Could do it in place, but get's messy since not null-terminated
    i, result = 0, list()
    while i < len(st):
        count, current_letter = 0, st[i]
        while i < len(st) and st[i] == current_letter:
            count += 1
            i += 1
        result += [current_letter, str(count)]

    result = ''.join(result)
    return result if len(result) < len(st) else ''.join(st)


if __name__ == '__main__':
    print(compress(input('Enter the string to compress: ')))

