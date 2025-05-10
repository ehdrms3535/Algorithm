//22112353 김동근

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HW1 {

    static class coor{
        float x;
        float y;
        float dis;
    }

    static class Bucket{
        int size=0;
        coor[] arr;
        Bucket(int n) {
            this.arr = new coor[n];
        }

    }
    static void RadixSort(coor[] arr, int k, int n,Bucket[] buc) {
        int i,j=0,count=0;
        float m;

        for (i = 0; i < n; i++) {
            int digits = (arr[i].dis <= 0) ? 0 : (int)Math.log10(arr[i].dis) / 2;
            digits = Math.min(digits, buc.length - 1);
            buc[digits].arr[buc[digits].size++]=arr[i];
        }

        int limit=k;

        for(i=0;i<buc.length;i++){
            count+=buc[i].size;
            if (buc[i].size <= 32) {
                BiInsertionSort(buc[i].arr, 0, buc[i].size - 1);
            } else {
                QuickSort(buc[i].arr, 0, buc[i].size - 1, limit);
            }
            limit-=buc[i].size;
            if(count>=k) break;
        }
    }

    static void QuickSort(coor[] arr, int left, int right, int k) {
        if (left>=k) return;
        if (left >= right) return;

        if(right-left>=16) {
            if (arr[left].dis > arr[right].dis) {
                swap(arr, left, right);
            }

            coor pivot1 = arr[left];
            coor pivot2 = arr[right];

            int leftpoint = left + 1;
            int rightpoint = right - 1;
            int locate = leftpoint;

            while (locate <= rightpoint) {
                if (arr[locate].dis < pivot1.dis) {
                    swap(arr, locate++, leftpoint++);
                } else if (arr[locate].dis > pivot2.dis) {
                    swap(arr, locate, rightpoint--);
                } else {
                    locate++;
                }
            }

            swap(arr, left, --leftpoint);
            swap(arr, right, ++rightpoint);
            // 재귀 정렬
            QuickSort(arr, left, leftpoint - 1,k);
            QuickSort(arr, leftpoint + 1, rightpoint - 1,k);
            QuickSort(arr, rightpoint + 1, right,k);
        }
        else {
            BiInsertionSort(arr, left, right);
        }
    }

    static void BiInsertionSort(coor[] arr, int left, int right){
        for(int i=left+1;i<=right;i++){
            coor key = arr[i];
            int lo = left;
            int hi = i;
            while(lo<hi) {
                int mid = (lo + hi) / 2;
                if (arr[mid].dis >= key.dis) hi = mid;
                else lo = mid+1;
            }
            for(int j=i;j>lo;j--){
                arr[j]=arr[j-1];
            }
            arr[lo] = key;
        }
    }

    static void swap(coor[] arr, int i, int j) {
        coor temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
    static void best(Scanner sc){

        float initialx = sc.nextFloat();
        float initialy = sc.nextFloat();
        int k =  sc.nextInt();
        int number = sc.nextInt();
        int max=0;
        long starttime, endtime;
        coor[] arr = new coor[number];

        for (int i = 0; i < number; i++) {
            arr[i] =  new coor();
            arr[i].x = sc.nextFloat();
            arr[i].y = sc.nextFloat();
            float dx = arr[i].x - initialx;
            float dy = arr[i].y - initialy;
            arr[i].dis = dx * dx + dy * dy;
            max = (int)((max>arr[i].dis)? max:arr[i].dis);
        }

        starttime = System.currentTimeMillis();
        k = (k == -1) ? number : k;
        if(k<number/2) {

            int len = (int) Math.log10(max) / 2 + 1;
            Bucket[] buc = new Bucket[len];

            for (int i = 0; i < len; i++) {
                Bucket a = new Bucket(number);
                buc[i] = a;
            }

            RadixSort(arr, k, number, buc);

            endtime = System.currentTimeMillis();
            System.out.println("k = "+k+"일 때의 실행시간 = "+(endtime-starttime) + "ms");

            int t = 0;

            for (int j = 0; j < buc.length && t < k; j++) {
                for (int i = 0; i < buc[j].size && t < k; i++) {
                    System.out.println(t++ + ": (" + buc[j].arr[i].x + ", " + buc[j].arr[i].y + ")  거리:" + (float) Math.sqrt(buc[j].arr[i].dis));
                }
            }
        }

        else {
            QuickSort(arr, 0, number - 1, k);
            endtime = System.currentTimeMillis();
            System.out.println("k = "+k+"일 때의 실행시간 = "+(endtime-starttime) + "ms");

            for(int i=0;i<k;i++) {
                System.out.println(i + ": (" + arr[i].x + ", " + arr[i].y + ")  거리:" + (float) Math.sqrt(arr[i].dis));
            }
        }


        return;
    }

    public static void main(String[] args) {
        System.out.print("파일 이름? ");
        Scanner sc = new Scanner(System.in);
        String fname = sc. nextLine();
        sc.close();

        try{
            sc = new Scanner(new File(fname));
            best(sc);
        }
        catch (IOException e) {System.out.println(e); return;}
    }
}
