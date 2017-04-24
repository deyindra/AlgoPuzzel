package org.idey.algo.datastructure.array;


import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    public static <T> void rearrangeAlternate(T[] arr, Filter<T> filter){
        if(arr.length>1) {
            segrate(arr, filter);
            int left = 1;
            int high = 0;
            while (filter.validate(arr[high])) {
                high++;
            }
            int right = high;
            while (filter.validate(arr[left]) && right < arr.length) {
                T temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
                left = left + 2;
                right++;
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
        int i,j=0;
        for(i=0;i<array.length;i++){
            if(filter.validate(array[i])){
                T obj = array[i];
                array[i] = array[j];
                array[j] = obj;
                j++;
            }
        }
    }


    public static <T> List<T> rearrange2(T[] array, Filter<T> filter){
        LinkedList<T> list = new LinkedList<T>();
        int index=0;
        for(T obj:array){
            if(filter.validate(obj)){
                list.add(index,obj);
                index++;
            }else{
                list.addLast(obj);
            }
        }
       return list;
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
        //Integer[] a = new Integer[]{null,4,null,5,null};
        //Integer[] b = new Integer[]{2,4,10};

        //mergeTwoSortedArray(a,b);
        //System.out.println(Arrays.deepToString(a));

//        Integer[] array=new Integer[]{5,5,2,7,9,10,1,16,3,3};
//        rearrange(array,evenFilter);
//        System.out.println(Arrays.deepToString(array));

        //System.out.println(returnDeuplicate(new int[]{4,0,1,1,3,}));
        System.out.println(returnAnyDeuplicate(new int[]{3,2,3,1,1}));
//
//        Integer[] arr = new Integer[]{2,3,5,9,7,4};
//        rearrangeAlternate(arr, evenFilter);
//        System.out.println(Arrays.deepToString(arr));

//        System.out.println(absoluteDistinctCountOfSortedIntegers(new int[] {-1,1,2}));
//        Integer[] array = new Integer[]{1,3,5,2,1,8,7,4,5,10};
//        rearrange(array, object -> object%2==0);
//        System.out.println(Arrays.deepToString(array));
//        System.out.println(rearrange2(new Integer[]{1,3,5,2,1,8,7,4,5,10}, object -> object%2==0));

        System.out.println(checkDuplicateWithinK(new int[] {1,2,1,1,3,5}, 2));

    }

    public static int absoluteDistinctCountOfSortedIntegers(int[] array){
        int count = array.length;
        int i=0,j=array.length-1;

        while (i<j){
            while (i!=j && array[i]==array[i+1]){
                count--;
                i++;
            }
            while (i!=j && array[j]==array[j-1]){
                count--;
                j--;
            }
            if(i==j)
                break;

            int sum = array[i]+array[j];
            if(sum==0){
                count--;
                i++;
                j--;
            }else if(sum<0){
                i++;
            }else{
                j--;
            }
        }
        return count;

    }


    public static boolean checkDuplicateWithinK(int[] array, int k){
        /** Base case: when K is greater than array size **/
        if (k > array.length || array.length == 1) {
            return false;
        }

        /** Hash Set to hold k element **/
        HashSet<Integer> hashSet = new HashSet<>();

        /** base element **/
        hashSet.add(array[0]);

        /** Iterate one by one of array element **/
        for (int i = 1; i < array.length; i++) {

            /** If duplicate contains **/
            if (hashSet.contains(array[i])) {
                return true;
            }

            /** Remove first inserted element from array **/
            if (i > k - 1) {
                hashSet.remove(array[i - k]);
            }

            /** Insert element to hash set **/
            hashSet.add(array[i]);
        }

        return false;
    }


}
