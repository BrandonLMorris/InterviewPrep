package CrackingTheCodingInterview.Chapter1;

import java.util.Scanner;
import java.util.HashSet;

public class Question7 {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);

    System.out.println("This program takes an MxN matrix of integers. If any elements");
    System.out.println("in the matrix are 0, that element's entire row and column is");
    System.out.println("set to 0.");
    System.out.println("Press Ctrl-c to quit.");

    while (true) {
      System.out.print("Please enter the number of rows: ");
      int m = in.nextInt();
      System.out.print("Please enter the number of columns: ");
      int n = in.nextInt();

      System.out.println("Now enter " + m + " rows of " + n + " integers");
      System.out.println("seperated by spaces.");

      int[][] matrix = new int[m][n];
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          matrix[i][j] = in.nextInt();
        }
      }

      // Where the magic happens
      zeroify(matrix, m, n);

      System.out.println("\nThe new matrix is:");
      for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
          System.out.print(matrix[i][j] + " ");
        }
        System.out.println();
      }
    }
  }

  private static void zeroify(int[][] matrix, int m, int n) {
    HashSet<Integer> row_hs = new HashSet<Integer>();
    HashSet<Integer> col_hs = new HashSet<Integer>();

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (matrix[i][j] == 0) {
          row_hs.add(i);
          col_hs.add(j);
        }
      }
    }

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        if (row_hs.contains(i) || col_hs.contains(j)) {
          matrix[i][j] = 0;
        }
      }
    }
  }
}
