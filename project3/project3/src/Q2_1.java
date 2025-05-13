//22112353 김동근

import java.util.Scanner;
import java.util.Arrays;

public class Q2_1 {

    static int idx = 0;

    static int MakeSize(int n, int k){
        if (k < 0 || n < 0 || k > n) return 0;
        if (k == 0 || k == n) return 1;

        int tmp = 1;
        int tmp2 = 1;

        for (int i = 1; i <= k; i++) {
            tmp *= (n - i + 1);
            tmp2 *= i;
        }

        return tmp / tmp2;
    }


    static void MakeArray(int[][] array, int[] temp, int input, int n, int k, int depth){
        if (k == 0) {
            for (int i = 0; i < temp.length; i++) {
                array[idx][i] = temp[i];
            }
            idx++;
            return;
        }

        if (input > n) return;

        temp[depth] = input;
        MakeArray(array, temp, input + 1, n, k - 1, depth + 1);

        MakeArray(array, temp, input + 1, n, k, depth);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int size = MakeSize(n, k);
        int[][] arr = new int[size][k];
        int[] temp = new int[k];

        MakeArray(arr, temp, 1, n, k, 0);

        for (int i = 0; i < size; i++) {
            System.out.print(Arrays.toString(arr[i])+" ");
        }
    }
}
