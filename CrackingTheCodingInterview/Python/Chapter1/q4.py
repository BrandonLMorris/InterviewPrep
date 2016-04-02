#!/usr/bin/env python3
"""Solution to question 4 of chapter 1"""

def encode_spaces(st):
    """Replace spaces with %20, using C style strings"""
    # Make sure string is a list, so I can't cheat
    if type(st) is str:
        st = list(st)

    # Find the true length of the string
    str_len = len(st)
    while st[str_len - 1] == ' ':
        str_len -= 1

    spaces = list()
    for i in range(str_len):
        if st[i] == ' ':
            spaces.append(i)

    # Move backwards, copying the string and fill in the encoding
    i, j = len(st) - 1, str_len - 1
    while i > 0:
        if st[j] == ' ':
            st[i] = '0'
            i -= 1
            st[i] = '2'
            i -= 1
            st[i] = '%'
        else:
            st[i] = st[j]

        i -= 1
        j -= 1

    return ''.join(st)

if __name__ == '__main__':
    print(encode_spaces(input('Enter the string to encode: ')))
