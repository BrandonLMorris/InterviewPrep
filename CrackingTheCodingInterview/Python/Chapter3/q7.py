"""
Chapter 3 Question 7

An animal shelter houses both dogs and cats and works strictly on a 'first-in-first-out' basis.
However, an adopter can specify if they want a dog, a cat, or no preference. In each case, the
oldest possible should be chosen

Approach: Store the cats and dogs in seperate lists, but maintain with them an increasing integer
age. If the adopter does not specify, determine the animal as the head of the two lists with the
greatest age. Complexity: O(1)
"""

from collections import deque

class Animal:
    def __init__(self, name):
        self.name = name

    def __str__(self):
        return self.name

class Shelter:
    def __init__(self):
        self.next_age = 0
        self.cats = deque()
        self.dogs = deque()

    def receive_animal(self, animal, is_cat):
        if is_cat:
            self.cats.append((animal, self.next_age))
        else:
            self.dogs.append((animal, self.next_age))
        self.next_age += 1

    def adopt(self):
        """Return the oldest animal, regardless of type"""
        if len(self.cats) != 0 and len(self.dogs) != 0:
            if (self.cats[0][1] < self.dogs[0][1]):
                return self.cats.popleft()[0]
            else:
                return self.dogs.popleft()[0]

        # At least one list is empty, so return from the non-empty lists
        if len(self.cats) != 0:
            return self.cats.popleft()[0]
        elif len(self.dogs) != 0:
            return self.dogs.popleft()[0]
        else:
            print("ERROR: SHELTER IS EMPTY")
            exit(1)

    def adopt_cat(self):
        """Return the oldest cat"""
        if len(self.cats) == 0:
            print("ERROR: SHELTER HAS NO CATS")
            exit(1)
        return self.cats.popleft()[0]

    def adopt_dog(self):
        """Return the oldest dog"""
        if len(self.dogs) == 0:
            print("ERROR: SHELTER HAS NO DOG")
            exit(1)
        return self.dogs.popleft()[0]

if __name__ == '__main__':
    shelter = Shelter()
    shelter.receive_animal("Fluffy", False)
    shelter.receive_animal("Mittens", True)
    print(shelter.adopt())
    shelter.receive_animal("Tiger", False)
    print(shelter.adopt_dog())
    print(shelter.adopt_cat())
    print(shelter.adopt_cat()) # Should cause error
