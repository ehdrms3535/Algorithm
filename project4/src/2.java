//22112353 김동근

import java.util.Arrays;
import java.util.Scanner;

class FINDING {

    static String first;
    static String second;
    static char[] tmp;
    static int maxlen = 0;

    public FINDING(String s1, String s2) {
        this.first = s1;
        this.second = s2;
        int max = Math.min(s1.length(), s2.length());
        this.tmp = new char[max];
    }

    public static void Compare(char[] arr, int locate) {
        if (locate > maxlen) {
            System.arraycopy(arr, 0, tmp, 0, locate);
            maxlen = locate;
        }

    }

    public static void Find(int a, int b,char[] arr, int locate) {
        if (a < 0 || b < 0) {
            Compare(arr, locate);
            return;
        }

        if (first.charAt(a) == second.charAt(b)) {
            arr[locate] = second.charAt(b);
            Find(a - 1, b - 1, Arrays.copyOf(arr, arr.length), locate + 1);
        }
        else {
            Find(a - 1, b, Arrays.copyOf(arr, arr.length), locate);
            Find(a, b - 1, Arrays.copyOf(arr, arr.length), locate);
        }
    }
}

public class HW2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();

        FINDING find = new FINDING(s1,s2);
        char[] arr= new char[Math.min(s1.length(), s2.length())];
        FINDING.Find(find.first.length()-1,find.second.length()-1,arr,0);

        for(int i=find.maxlen-1;i>=0;i--) {
            System.out.print(find.tmp[i]+"");
        }
        System.out.println();
        System.out.println(find.maxlen);
    }
}
