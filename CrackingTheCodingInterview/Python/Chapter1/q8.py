"""Solution to question 8 of chapter 1"""

def is_rotation(string1, string2):
    return string2 in (string1 + string1)


if __name__ == '__main__':
    print('Checking if \'waterbottle\' is a rotation of \'erbottlewat\'')
    print(is_rotation('watterbottle', 'erbottlewat'))
