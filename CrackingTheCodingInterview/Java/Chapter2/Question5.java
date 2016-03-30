package CrackingTheCodingInterview.Chapter2;

/**
 * Chapter 2 Question 5
 *
 * You have two numbers represented by a linked list, where each node contains
 * a single digit. The digits are stored in reverse order, such that the 1's
 * digit is at the head of the list. Write a function that add the two number
 * and returns the sum as a linked list.
 *
 * EXAMPLE:
 * (7->1->6) + (5->9->2) is 617 + 295
 * Result: (2->1->9) or 912
 *
 * Created by bmorris on 9/30/15.
 */
public class Question5 {
  public static Node<Integer> addList(Node<Integer> firstHead,
                                      Node<Integer> secondHead) {
    Node<Integer> resultHead = null;
    Node<Integer> resultTail = null;
    Node<Integer> firstCurrent = firstHead;
    Node<Integer> secondCurrent = secondHead;

    while (firstCurrent != null || secondCurrent != null) {
      if (firstCurrent == null) {
        firstCurrent = new Node<Integer>(0);
      }
      if (secondCurrent == null) {
        secondCurrent = new Node<Integer>(0);
      }

      Node<Integer> temp = new Node<Integer>(firstCurrent.data +
          secondCurrent.data);
      if (resultHead == null) {
        resultHead = temp;
        resultTail = temp;
      } else {
        resultTail.next = temp;
        resultTail = temp;
      }

      firstCurrent = firstCurrent.next;
      secondCurrent = secondCurrent.next;
    }

    Node<Integer> current = resultHead;
    while (current != null) {
      if (current.data > 9) {
        if (current.next == null) {
          current.next = new Node<Integer>(1);
        } else {
          current.next.data += 1;
        }
        current.data -= 10;
      }
      current = current.next;
    }

    return resultHead;
  }

  // This version takes advantage of the Java API's ability to parse strings
  // as integers and skirts around the point of the exercise
  public static Node<Integer> addListShortcut(Node<Integer> first,
                                              Node<Integer> second) {
    String firstStr = "";
    Node<Integer> current = first;
    while (current != null) {
      firstStr = current.data + firstStr;
      current = current.next;
    }

    String secondStr = "";
    current = second;
    while (current != null) {
      secondStr = current.data + secondStr;
      current = current.next;
    }

    // Using ints, but BigInteger would work if lists are really long
    // TODO: Change to BigInteger
    int firstInt = Integer.parseInt(firstStr);
    int secondInt = Integer.parseInt(secondStr);
    int resultInt = firstInt + secondInt;
    char[] resultArr = Integer.toString(resultInt).toCharArray();
    Node<Integer> resultHead = new Node<Integer>(resultArr[0] - '0');
    current = resultHead;
    for (int i = 1; i < resultArr.length; i++) {
      current.next = new Node<Integer>(resultArr[i] - '0');
      current = current.next;
    }
    return resultHead;
  }
}
