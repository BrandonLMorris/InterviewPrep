import java.util.*;
import java.io.*;

public class Three {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    PrintStream out = System.out;

    out.println("This program takes two strings and outputs if they are " +
                "permutations of each other.");
    out.println("Press Ctrl-c to quit");
    while(true) {
      out.print("String 1: ");
      String s1 = in.nextLine();
      out.print("String 2: ");
      String s2 = in.nextLine();
      out.println(isPerm(s1, s2));
    }
  }

  private static boolean isPerm(String s1, String s2) {
    if (s1.length() != s2.length()) return false;

    int[] pool1 = new int[128];
    int[] pool2 = new int[128];

    for (int i = 0; i < s1.length(); i++) {
      pool1[s1.charAt(i)]++;
      pool2[s2.charAt(i)]++;
    }

    for (int i = 0; i < s1.length(); i++) {
      if (pool1[i] != pool2[i]) return false;
    }

    return true;
  }
}
