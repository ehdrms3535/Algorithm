//22112353 김동근

import java.util.Scanner;

class Solution{
    boolean Check(int array[][]){
        int row = array.length;
        int col = row;
        int tmp = array[0][0];

        for(int i=0; i<row;i++){
            for(int j=0;j<col;j++){
                if(array[i][j]!=tmp) return false;
            }
        }
        return true;
    }

    int[][] Sub(int[][] arr, int startRow, int startCol, int endRow, int endCol) {
        int[][] result = new int[endRow - startRow][endCol - startCol];
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                result[i - startRow][j - startCol] = arr[i][j];
            }
        }
        return result;
    }

    void quad(int array[][],int[] answer){
        int row=array.length;
        int col = row;
        if( row == 1 || Check(array)) {
            answer[array[0][0]]++;
            return;
        }

        else{
            quad(Sub(array,0,0,row/2,col/2),answer);
            quad(Sub(array,row/2,0,row,col/2),answer);
            quad(Sub(array,0,col/2,row/2,col),answer);
            quad(Sub(array,row/2,col/2,row,col),answer);
        }
    }

    int[] solution(int[][] array){
        int row = array.length;
        int col = row;

        int[] answer = {0,0};

        quad(array,answer);
        return answer;
    }
}

public class HW3 {


    public static void main(String[] args) {
       // int[][] a={{1,1,0,0},{1,0,0,0},{1,0,0,1},{1,1,1,1}};
        int[][] a={{1,1,1,1,1,1,1,1},
                {0,1,1,1,1,1,1,1},
                {0,0,0,0,1,1,1,1},
                {0,1,0,0,1,1,1,1},
                {0,0,0,0,0,0,1,1},
                {0,0,0,0,0,0,0,1},
                {0,0,0,0,1,0,0,1},
                {0,0,0,0,1,1,1,1}};
        Solution sol = new Solution();
        int[] result = sol.solution(a);
        System.out.println("["+result[0]+ " "+ result[1]+"]");
    }
}
