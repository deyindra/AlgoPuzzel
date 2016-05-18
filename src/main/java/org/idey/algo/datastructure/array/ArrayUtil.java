package org.idey.algo.datastructure.array;


import java.util.Arrays;

public class ArrayUtil {
    public static <T> void segrate(T[] arr, Filter<T> filter){
        int left = 0, right = arr.length - 1;
        while (left<right){
            while (filter.validate(arr[left]) && left<right)
                left++;

            while (!filter.validate(arr[right]) && left<right)
                right--;

            if(left<right){
                T temp = arr[left];
                arr[left]=arr[right];
                arr[right]=temp;
                left++;
                right--;
            }
        }
    }



    public static <T> void moveAllSpecificObjectTowardsEnd(T[] array, T object){
        int count=0;
        for(int i=0;i<=array.length-1;i++){
            if(array[i]!=null && !array[i].equals(object)){
                array[count++]=array[i];
            }
        }
        while(count<=array.length-1)
            array[count++] =object;
    }


    private interface Filter<T>{
        boolean validate(T object);
    }

    public static <T extends Comparable<T>> void mergeTwoSortedArray(T[] a, T[]b){
        int numberOfElementOfA = a.length-b.length;
        int numberOfElementOfB = b.length;

        int i=numberOfElementOfA-1;
        int j=numberOfElementOfB-1;
        int k = numberOfElementOfA+numberOfElementOfB-1;

        moveAllSpecificObjectTowardsEnd(a,null);

        while (i>=0 && j>=0){
            if(a[i].compareTo(b[j])>0){
                a[k--]=a[i--];
            }else{
                a[k--]=b[j--];
            }
        }

        while (j>=0){
            a[k--]=b[j--];
        }
    }

    // This is more generic when you want to move all the objects to the begining of the Array with same order and rest all objects (not same)
    //to the end of the array
    public static <T> void rearrange(T[] array , Filter<T> filter){
        int i=0;
        int firstIndex=0;
        int secondIndex=0;
        boolean isIndexFound=false;
        while(i<array.length){
            for(;i<array.length && filter.validate(array[i]);i++);
            if(!isIndexFound)
                firstIndex=i;
            else
                firstIndex = firstIndex+1;
            for(;i<array.length && !filter.validate(array[i]);i++);
            secondIndex=i;
            if(secondIndex>firstIndex && secondIndex<array.length){
                T temp = array[firstIndex];
                array[firstIndex] = array[secondIndex];
                array[secondIndex] = temp;
                isIndexFound = true;
            }
        }
    }

    //find one duplicte element
    public static int returnDeuplicate(int[] array){
        int result=0;
        for(int i=0;i<array.length;i++){
            result = result^array[i]^i;
        }
        return result;
    }

    //Number ranges from 0 to n-2
    public static int returnAnyDeuplicate(int[] array){
        int slow = array.length-1;
        int fast = array.length-1;
        while (true){
            slow = array[slow];
            fast = array[array[fast]];
            if(slow==fast)
                break;
        }
        int finder = array.length-1;
        while (true){
            slow   = array[slow];
            finder = array[finder];
            if(slow==finder)
                return slow;
        }
    }


    public static void main(String[] args) {
        Filter<Integer> evenFilter = new Filter<Integer>() {
            @Override
            public boolean validate(Integer object) {
                return object%2==0;
            }
        };
//        Integer[] arr=new Integer[]{2,7,9,10,1,16};
//        segrate(arr,evenFilter);
//        System.out.println(Arrays.deepToString(arr));
//
//        Integer[] arr1=new Integer[]{2,7,0,10,0,16};
//        moveAllSpecificObjectTowardsEnd(arr1,0);
//        System.out.println(Arrays.deepToString(arr1));
//
        Integer[] a = new Integer[]{null,4,null,5,null};
        Integer[] b = new Integer[]{2,4,10};

        mergeTwoSortedArray(a,b);
        System.out.println(Arrays.deepToString(a));

//        Integer[] array=new Integer[]{5,5,2,7,9,10,1,16,3,3};
//        rearrange(array,evenFilter);
//        System.out.println(Arrays.deepToString(array));

        System.out.println(returnDeuplicate(new int[]{1,2,4,2,3}));
        System.out.println(returnAnyDeuplicate(new int[]{0,2,3,1,3}));

    }


}
