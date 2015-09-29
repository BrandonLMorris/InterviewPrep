package CrackingTheCodingInterview.Chapter1;

import java.util.Scanner;

public class Question5 {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    System.out.println("This program performs basic string compression");
    System.out.println("using the counts of repeated characters. If");
    System.out.println("the compressed string is not smaller than the");
    System.out.println("original, the original is returned.");
    System.out.println("Press Ctrl-c to quit.");
    while (true) {
      String s = input.nextLine();
      System.out.println(compress(s));
    }
  }

  private static String compress(String s) {
    char[] cArr = s.toCharArray();
    StringBuilder output = new StringBuilder();
    for(int i = 0; i < cArr.length; i++) {
      char c = cArr[i];
      int counter = 1;
      while(i+1 < cArr.length && cArr[i] == cArr[i+1]) {
          counter++;
          i++;
      }
      output.append(c);
      output.append(counter);
    }
    if(output.length() >= cArr.length) {
      return s;
    } else {
      return output.toString();
    }
  }
}
