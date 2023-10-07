package star;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();

        for (int i = 0; i < 9; i++) {
            if (i < 5) {
                for (int j = 0; j <= i; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
            else {
                for (int j = 0; j < 9-i; j++) {
                    System.out.print("*");
                }
                System.out.println();
            }
        }

    }
}
