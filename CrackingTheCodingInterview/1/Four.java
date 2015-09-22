import java.util.*;

public class Four {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("This program takes a string and replaces all ");
        System.out.println("spaces with '%20'. It is assumed that the string");
        System.out.println("has enough space to accomodate the extra ");
        System.out.println("characters (use trailing spaces).");
        System.out.println("Press Ctrl-c to quit.");
        while (true) {
          String input = in.nextLine();
          int x = input.length() - 1;
          while (input.charAt(x) == ' ') x--;

          // char[] test = {'M','r',' ','J','o','h','n',' ','S','m','i','t','h',' ',' ',' ',' '};
          System.out.println(replaceSpace(input.toCharArray(), x+1));
        }
    }

    public static char[] replaceSpace(char[] cArr, int trueLength) {
        int spaces = 0;
        for(int i = 0; i < trueLength; i++) {
            if(cArr[i] == ' ') {
                spaces++;
            }
        }

        int copyIndex = trueLength - 1;
        for(int i = trueLength + (2 * spaces) - 1; i >= 0; i--) {
            if(cArr[copyIndex] == ' ') {
                cArr[i] = '0';
                cArr[i-1] = '2';
                cArr[i-2] = '%';
                copyIndex--;
                i -= 2;
            } else {
                cArr[i] = cArr[copyIndex];
                copyIndex--;
            }
        }

        return cArr;
    }
}
