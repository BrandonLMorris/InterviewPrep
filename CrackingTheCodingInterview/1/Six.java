import java.util.*;

public class Six {
  public static void main(String[] args) {
    /*
     * Original problem is:
     * "Given an image represented by an NxN matrix, where each pixel in
     * the image is 4 bytes, write a method to rotate the image by 90
     * degrees. Can you do this in place?"
     */

    /*
     * 1 2    3 1
     * 3 4    4 2
     */
    Scanner in = new Scanner(System.in);

    System.out.println("This program takes in an NxN matrix");
    System.out.println("and rotates it 90 degrees.");
    System.out.println("Enter a value for n, and then");
    System.out.println("n lines of n values separated by");
    System.out.println("spaces.");
    System.out.println("Press Ctrl-c to quit");


    while(true) {
      System.out.print("\nEnter the size of the matrix (n): ");
      int n = in.nextInt();

      int matrix[][] = new int[n][n];

      System.out.println("Now enter " + n + " rows of " + n + " values seperated by spaces.");
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          matrix[i][j] = in.nextInt();
        }
      }

      rotate(n, matrix);

      System.out.println("\nThe new matrix is:");
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          System.out.print(matrix[i][j] + " ");
        }
        System.out.println();
      }
    }

  }

  public static void rotate(int n, int[][] matrix) {
    for (int layer = 0; layer < n/2; layer++) {
      int first = layer;
      int last = n - 1 - layer;
      for (int i = first; i < last; i++) {
        int offset = i - first;
        int temp = matrix[first][i];

        // top-left = bottom-left
        matrix[first][i] = matrix[last-offset][first];

        // bottom-left = bottom-right
        matrix[last-offset][first] = matrix[last][last-offset];

        // bottom-right = top-right
        matrix[last][last - offset] = matrix[i][last];

        // top-right = top-left
        matrix[i][last] = temp;

      }
    }
  }
}
