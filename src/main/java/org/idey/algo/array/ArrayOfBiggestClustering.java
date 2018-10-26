package org.idey.algo.array;

import java.util.function.Predicate;

public class ArrayOfBiggestClustering {

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

    private static <T> int DFS(final T array[][],
                                final int rowIndex,
                                final int colIndex,
                                final boolean visited[][],
                                Predicate<T> predicate,
                                int numberOfElementInCluster){
        int rowNbr[] = new int[] {-1, -1, -1,  0, 0,  1, 1, 1};
        int colNbr[] = new int[] {-1,  0,  1, -1, 1, -1, 0, 1};
        visited[rowIndex][colIndex] = true;
        for (int k = 0; k < 8; ++k) {
            if (isSafe(array,
                    rowIndex + rowNbr[k],
                    colIndex + colNbr[k],
                    visited,
                    predicate)) {
                numberOfElementInCluster++;
                numberOfElementInCluster = DFS(array, rowIndex + rowNbr[k], colIndex + colNbr[k], visited, predicate, numberOfElementInCluster);
            }
        }
        return numberOfElementInCluster;
    }

    public static <T> int count(T[][] array, Predicate<T> predicate, int numberOfElementInCluster){
        final int ROW = array.length;
        final int COL = array[0].length;

        final boolean[][] visited = new boolean[ROW][COL];
        int count=0;
        for (int i = 0; i < ROW; ++i){
            for (int j = 0; j < COL; ++j){
                if(predicate.test(array[i][j]) && !visited[i][j]){
                    numberOfElementInCluster++;
                    numberOfElementInCluster = DFS(array,i,j,visited,predicate,numberOfElementInCluster);
                    if(count<numberOfElementInCluster){
                        count=numberOfElementInCluster;
                    }
                }else{
                    numberOfElementInCluster=0;
                }
            }
        }
        return count;
    }



    public static void main(String[] args) {
        Integer M[][]=  new Integer[][] {
                {1, 1, 0, 1, 0},
                {0, 1, 0, 0, 1},
                {1, 0, 0, 1, 1},
                {0, 0, 0, 1, 0},
                {1, 0, 1, 0, 1}
        };

        System.out.println(count(M, t -> t==1,0));
    }
}
