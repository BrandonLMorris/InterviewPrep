#!/usr/bin/env python3
"""Solution to question 6 of chapter 1"""


def transpose(matrix):
    """Rotate a 2D matrix 90 degrees clockwise"""
    n = len(matrix)
    circles = round(n / 2)
    n -= 1

    for i in range(circles):
        for j in range(i, n - i):
            # This made my brain hurt
            tmp = matrix[i][j]
            matrix[i][j] = matrix[n-i-j][i]
            matrix[n-i-j][i] = matrix[n-i][n-i-j]
            matrix[n-i][n-i-j] = matrix[n-i-j][n-i]
            matrix[n-i-j][n-i] = tmp

    return matrix


example = [
    ['0', '0', '0', '0', '0'], # ^  <--j-->
    ['1', '1', '1', '1', '1'], # |
    ['2', '2', '2', '2', '2'], # i
    ['3', '3', '3', '3', '3'], # |
    ['4', '4', '4', '4', '4'], # ,
]


if __name__ == '__main__':
    print('\n'.join([' '.join(row) for row in transpose(example)]))
