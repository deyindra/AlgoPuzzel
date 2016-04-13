package org.idey.algo.array;


import java.util.Arrays;

public class RotateArray<T> {
    private T[] array;

    public RotateArray(T[] array) {
        if(array==null || array.length==0){
            throw new IllegalArgumentException("Invalid Array");
        }

        this.array = array;
    }

    public void rotateArray(int number){
        if(number<=0 || number>array.length){
            throw new IllegalArgumentException("Invalid number "+number);
        }
        rotateArray(0,array.length-1);
        rotateArray(0,number-1);
        rotateArray(number,array.length-1);
    }


    private void rotateArray(int startIndex, int endIndex){
        for(int i=startIndex,j=endIndex;i<j;i++,j--){
            T temp = array[i];
            array[i]=array[j];
            array[j]=temp;
        }
    }

    @Override
    public String toString() {
        return Arrays.deepToString(array);
    }

    public static void main(String[] args) {
        RotateArray<Integer> rotateArray = new RotateArray<>(new Integer[] {1,2,3,4,5,6});
        rotateArray.rotateArray(2);
        System.out.println(rotateArray);
    }
}
