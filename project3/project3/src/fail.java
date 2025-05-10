import java.util.Arrays;
import java.util.Scanner;

public class fail {
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
    static void MakeArray(int[][] array,int input, int n,int k){

        if(n<k || k<0) return;

        else{
            int arrsize = MakeSize(n,k);
            int j=0;

            for(;array[j][array[0].length-k-1]!=0;j++);
            for(int i=0;i<arrsize;i++){
                array[j++][array[0].length-k-1] = input;
            }

            MakeArray(array,input+1,n-1,k-1);
            MakeArray(array,input+1,n-1,k);

        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n= scanner.nextInt();
        int k = scanner.nextInt();
        int size = MakeSize(n,k);

        int[][] arr = new int[size][k];
        MakeArray(arr,1,n-1,k-1);
        for (int i = 0; i < size; i++) {
            System.out.print(Arrays.toString(arr[i])+" ");
        }
    }
}
