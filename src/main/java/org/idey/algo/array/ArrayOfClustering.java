package org.idey.algo.array;

import java.util.function.Predicate;

public class ArrayOfClustering {

    private static <T> boolean isSafe(
            final T array[][],
            final int rowIndex,
            final int colIndex,
            final boolean visited[][],
            final Predicate<T> predicate){

        final int ROW = array.length;
        final int COL = array[0].length;

        return (rowIndex >= 0) && (rowIndex < ROW) &&
                (colIndex >= 0) && (colIndex < COL) &&
                (predicate.test(array[rowIndex][colIndex])
                        && !visited[rowIndex][colIndex]);
    }

    private static <T> void DFS(final T array[][],
                                final int rowIndex,
                                final int colIndex,
                                final boolean visited[][],
                                Predicate<T> predicate){
        int rowNbr[] = new int[] {-1, -1, -1,  0, 0,  1, 1, 1};
        int colNbr[] = new int[] {-1,  0,  1, -1, 1, -1, 0, 1};
        visited[rowIndex][colIndex] = true;
        for (int k = 0; k < 8; ++k)
            if (isSafe(array,
                    rowIndex + rowNbr[k],
                    colIndex + colNbr[k],
                    visited,
                    predicate) )
                DFS(array, rowIndex + rowNbr[k], colIndex + colNbr[k], visited,predicate);
    }

    public static <T> int count(T[][] array, Predicate<T> predicate){
        final int ROW = array.length;
        final int COL = array[0].length;

        final boolean[][] visited = new boolean[ROW][COL];
        int count=0;
        for (int i = 0; i < ROW; ++i){
            for (int j = 0; j < COL; ++j){
                if(predicate.test(array[i][j]) && !visited[i][j]){
                    DFS(array,i,j,visited,predicate);
                    count++;
                }
            }
        }
        return count;
    }


    public static void main(String[] args) {
        Integer M[][]=  new Integer[][] {
                {1, 1, 0, 0, 0},
                {0, 1, 0, 0, 1},
                {1, 1, 0, 1, 1},
                {0, 1, 0, 0, 1},
                {1, 0, 1, 0, 1}
        };

        System.out.println(count(M, t -> t==0));
    }
}
