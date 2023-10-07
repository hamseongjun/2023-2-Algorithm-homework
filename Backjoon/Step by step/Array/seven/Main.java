package seven;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean[] arr = new boolean[30];

        for (int i = 0; i < 28; i++) {
            int n = scanner.nextInt();
            arr[n-1] = true;
        }
        for (int i = 0; i < 30; i++) {
            if (!arr[i]) {
                System.out.println((i+1) + " ");
            }
        }
    }
}
