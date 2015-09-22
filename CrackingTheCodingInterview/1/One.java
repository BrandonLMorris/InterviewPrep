import java.util.*;

public class One {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    System.out.println("This program determines if a string is made " +
        "up of all unique characters.");
    System.out.println("Press Ctrl-c to quit");
    System.out.println("Note that 'a' and 'A' are considered disctinct");
    while (true) {
      String str = in.nextLine();
      System.out.println(isUniqueChars(str));
    }
  }

  public static boolean isUniqueChars(String str) {
    boolean[] hasUsed = new boolean[128];
    for (int i = 0; i < str.length(); i++) {
      if (hasUsed[str.charAt(i)]) {
        return false;
      } else {
        hasUsed[str.charAt(i)] = true;
      }
    }
    return true;
  }
}
