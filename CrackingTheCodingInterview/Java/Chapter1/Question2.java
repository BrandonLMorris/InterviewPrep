package CrackingTheCodingInterview.Chapter1;

import java.util.Scanner;

public class Question2 {

  // TODO: Substitute main w/ junit tests
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("This program reverses a null-terminated string.");
    System.out.println("Note that this program is written in Java, where");
    System.out.println("strings are not necessarily null-terminanted.");
    System.out.println("Although you can't tell, ensure that I am");
    System.out.println("converting the input to a null-terminated string.");
    System.out.println("Press Ctrl-c to quit.");

    while (true) {
      String s = in.nextLine();
      char[] arr = new char[s.length() + 1];
      for (int i = 0; i < s.length(); i++) {
        arr[i] = s.charAt(i);
      }
      arr[s.length()] = '\0';

      System.out.println(reverse(arr));
    }
  }

  private static String reverse(char[] arr) {
    for (int i = 0; i < (arr.length - 1) / 2; i++) {
      char temp = arr[i];
      arr[i] = arr[arr.length-2-i];
      arr[arr.length-2-i] = temp;
    }
    return new String(arr);
  }
}
