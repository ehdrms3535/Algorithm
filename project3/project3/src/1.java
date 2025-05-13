//22112353 김동근

class Solution{
    public boolean Check(int array[][]){
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

    public int[][] Sub(int[][] arr, int startRow, int startCol, int endRow, int endCol) {
        int[][] result = new int[endRow - startRow][endCol - startCol];
        for (int i = startRow; i < endRow; i++) {
            for (int j = startCol; j < endCol; j++) {
                result[i - startRow][j - startCol] = arr[i][j];
            }
        }
        return result;
    }

    public void quad(int array[][],int[] answer){
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

    public int[] solution(int[][] array){
        int row = array.length;
        int col = row;

        int[] answer = {0,0};

        quad(array,answer);
        return answer;
    }
}

