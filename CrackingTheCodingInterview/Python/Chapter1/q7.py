"""Solution to question 7 of chapter 1"""


def zeroify(matrix):
    """Zeroify a 2D matrix"""
    rows, cols = set(), set()

    # Initial pass: find the rows/cols with zeros
    for row in range(len(matrix)):
        for col in range(len(matrix[row])):
            if matrix[row][col] == 0:
                rows.add(row)
                cols.add(col)

    # Second pass: fill in the zeros
    for row in range(len(matrix)):
        for col in range(len(matrix[row])):
            if row in rows or col in cols: matrix[row][col] = 0

    # Not completely necessary since lists are mutable
    return matrix


example = [
    [1, 1, 1, 1, 1],
    [0, 1, 1, 1, 1],
    [1, 1, 1, 1, 1],
    [0, 1, 1, 0, 1],
    [1, 1, 1, 1, 1]
]


if __name__ == '__main__':
    print('hi there')
    print('\n'.join([str(e) for e in zeroify(example)]))
