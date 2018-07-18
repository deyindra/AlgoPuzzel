package org.idey.algo.divnconcur;

import java.util.Comparator;

public class BinarySearch<T> {
    //rotated and non rotated
    public int simpleBinarySearch(T[] source, T object, Comparator<T> comparator){
        int low = 0;
        int high = source.length - 1;
        while (low <= high) {
            int middle = low + ((high - low) >>> 1);
            if (comparator.compare(source[middle], object) == 0)
                return middle;
            if(comparator.compare(source[middle],object)<0){
                low=middle+1;
            }else{
                high=middle-1;
            }
        }
        return -(low+1);
    }


    public int binarySearch(T[] source, T object, Comparator<T> comparator){
        int low = 0;
        int high = source.length - 1;
        while (low <= high) {
            int middle = low + ((high - low) >>> 1);
            if(comparator.compare(source[middle],object)==0)
                return middle;
            //low=1,middle=5,object=4
            if(comparator.compare(source[low],source[middle])<0){
                if(comparator.compare(source[low],object)<=0 && comparator.compare(object,source[middle])<0){
                    high=middle-1;
                }else{
                    low=middle+1;
                }
            }else if(comparator.compare(source[low], source[middle])>0){
                if(comparator.compare(source[middle],object)<0 && comparator.compare(object,source[high])<=0){
                    low=middle+1;
                }else{
                    high=middle-1;
                }
            }else{
                low++;
            }
        }

        return -(low+1);
    }


    public int firstOccurance(T[] source, T object, Comparator<T> comparator){
        int low = 0;
        int high = source.length - 1;
        int firstOccurrence = Integer.MIN_VALUE;
        while (low <= high) {
            int middle = low + ((high - low) >>> 1);

            if (comparator.compare(source[middle],object)==0) {
                firstOccurrence=middle;
                high = middle -1;
            } else if (comparator.compare(source[middle],object)<0) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        if (firstOccurrence != Integer.MIN_VALUE) {
            return firstOccurrence;
        }

        return -(low + 1);
    }


    public int lastOccurance(T[] source, T object, Comparator<T> comparator){
        int low = 0;
        int high = source.length - 1;
        int lastOccurrence = Integer.MIN_VALUE;
        while (low <= high) {
            int middle = low + ((high - low) >>> 1);

            if (comparator.compare(source[middle],object)==0) {
                lastOccurrence=middle;
                low = middle+1;
            } else if (comparator.compare(source[middle],object)<0) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }
        }

        if (lastOccurrence != Integer.MIN_VALUE) {
            return lastOccurrence;
        }

        return -(low + 1);
    }

    public T searchOddOne(T[] array, int low, int high){
        if (low > high) {
            return null;
        }if (low==high) {
            return array[low];
        }else{
                int mid = low + (high-low) / 2;
                if (mid % 2 == 0) {
                    if (array[mid].equals(array[mid + 1]))
                        return searchOddOne(array, mid + 2, high);
                    else
                        return searchOddOne(array, low, mid);
                } else {
                    if (array[mid].equals(array[mid - 1]))
                        return searchOddOne(array, mid+1, high);
                    else
                        return searchOddOne(array, low, mid - 1);
                }
            }
    }

    public static int findMagicIndex(int[] array, int low, int high){
        if(low>high)
            return -1;
        int mid = low + (high - low)/2;
        int midValue = array[mid];
        if(midValue == mid)
            return mid;


        int leftIndex = Math.min(mid-1, midValue);
        int left = findMagicIndex(array, low, leftIndex);
        if(left>=0)
            return left;

        int rightIndex = Math.max(mid+1, midValue);
        int right = findMagicIndex(array, rightIndex, high);

        return right;

    }

    public static void main(String[] args) {
//        Integer[] array = {1,1,4,3,3,2,2};
//        System.out.println(new BinarySearch<Integer>().searchOddOne(array, 0, array.length - 1));

//        Predicate<Integer> p = new Predicate<Integer>() {
//            @Override
//            public boolean process(Integer object) {
//                return (object%2)==0;
//            }
//        };
//        System.out.println(new BinarySearch<Integer>().findCountLast(new Integer[]{9,11,15,2,4,6},p));
        //System.out.println(missingNumber(new int[] {2,4,6,8,10,12,16},2));
//        Integer[] array = new Integer[]{9,99,999};
//        System.out.println(new BinarySearch<Integer>().simpleBinarySearch(array, 5, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return o1-o2;
//            }
//        }));
//        Predicate<Integer> badRevisionPredicate = new BadRevisionPredicate(18);
//        System.out.println(new BinarySearch<Integer>().findFirstBadRevision(3,17,badRevisionPredicate));

        //System.out.println(new BinarySearch<Integer>().findPeak(new int[]{10, 20, 30, 40, 50}));

//        Predicate<Integer> p = new Predicate<Integer>() {
//            @Override
//            public boolean process(Integer object) {
//                return (object%2)!=0;
//            }
//        };
//         System.out.println(new BinarySearch<Integer>().findCountLast(new Integer[]{4, 51, 1, 3, 9}, p));

//        int i=findPeak(new Integer[]{1,2,3,4,5,4,3,2,1});
//        System.out.println(i);

        //printKthClosedElement(new int[]{2,7,9,11},11,1);
        //System.out.println(closestElement(new int[]{-4,-1,0,3,19},-2));
//        Function fn = new Function() {
//            @Override
//            public int calculate(int number) {
//                return number*number-10*number-30;
//            }
//        };
//        System.out.println(findNumberReturningPositive(fn));
//        System.out.println(findCeil(new Integer[]{1,2,5,6,10,12},7));
//        System.out.println(findFloor(new Integer[]{1, 2, 5, 6, 10, 12},7));
//
//        System.out.println(findCount(new Integer[][]{{2, 4, 6, 9, 11}, {4, 5, 7, 11, 9}}, new Predicate<Integer>() {
//            @Override
//            public boolean process(Integer object) {
//                return object%2==0;
//            }
//        }));
//
//
//        System.out.println(getMinMax(new int[]{2,1,-1,11,16,-8}));
          System.out.println(new BinarySearch<Integer>().searchOddOne(new Integer[]{1,1,2,2,2,2,3,3,3,3,4,4,4},0,12));
          System.out.println(new BinarySearch<Integer>().binarySearch(new Integer[]{2,2,2,1,2}, 2,new Comparator<Integer>() {
              @Override
              public int compare(Integer o1, Integer o2) {
                  return o1.compareTo(o2);
              }
          }));

          System.out.println(BinarySearch.FindPivote(new Integer[]{1,2,3,4,5,6},new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        }));


         System.out.println(findMagicIndex(new int[]{-1,3,3,4,4,6,6},0,6));

         System.out.println(findMin(new int[]{2, 2, 2, 2, 2, 2, 2, 0, 0, 1, 1, 2}));

    }


    public int findCountFirst(T[] array,Predicate<T> predicate){
        int count=0;
        if(array!=null && array.length!=0){
            int start =0;
            int end = array.length -1;
            while(end>=start){
                int middle = start + (end-start)/2;
                if(predicate.process(array[middle])){
                    count = middle+1;
                    start=middle+1;
                }else{
                    end=middle-1;
                }
            }
        }
        return count;
    }

    public int findCountLast(T[] array, Predicate<T> predicate){
        int count=0;
        if(array!=null && array.length!=0){
            int start =0;
            int end = array.length -1;
            while(end>=start){
                int middle = start + ((end - start)/2);
                if(predicate.process(array[middle])){
                    count=array.length-middle;
                    end=middle-1;
                }else{
                    start=middle+1;
                }
            }
        }
        return count;
    }

    public int findFirstBadRevision(int firstRevision, int lastRevision, Predicate<Integer> badRevisionChecker){
        int firstBadRevision=Integer.MIN_VALUE;
        int low = firstRevision;
        int high = lastRevision;

        while (low<=high){
            int mid = low+(high-low)/2;
            if(badRevisionChecker.process(mid)){
                firstBadRevision=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return firstBadRevision;
    }




    private static int missingNumber(int[] array, int low, int high, int diff){
        int missingNumber = Integer.MIN_VALUE;

        if(high<low)
            return missingNumber;

        int mid = low + (high - low)/2;

        if (mid<array.length-1 && array[mid+1] - array[mid] != diff)
            return (array[mid] + diff);

        // The element just before mid is missing
        if (mid > 0 && array[mid] - array[mid-1] != diff)
            return (array[mid-1] + diff);

        // If the elements till mid follow AP, then recur
        // for right half
        if (array[mid] == array[0] + mid*diff)
            return missingNumber(array, mid + 1, high, diff);

        // Else recur for left half
        return missingNumber(array, low, mid - 1, diff);
    }



    public static <T> int FindPivote(T[] array, Comparator<T> comparator){
        int low=0;
        int high = array.length-1;
        while (comparator.compare(array[low],array[high])>0){
            int middle = low + (high-low)/2;
            if(comparator.compare(array[middle],array[high])>0){
                low = middle+1;
            }else{
                high=middle;
            }
        }
        return low;
    }

    public  int findPeak(int[] array){
        int low=0;
        int high = array.length-1;
        while (low<=high){
            int middle = low+(high-low)/2;
            if((middle==0) || array[middle]>=array[middle-1] && (middle==array.length-1 || array[middle]>=array[middle+1])){
                return array[middle];
            }else if(middle>0 && array[middle-1]>array[middle]){
                high = middle-1;
            }else{
                low=middle+1;
            }
        }
        return -1;
    }


    public static int missingNumber(int[] array, int diff){
        int low=0;
        int high = array.length-1;
        return missingNumber(array, low, high, diff);
    }

    public interface Predicate<T>{
        boolean process(T object);
    }

    private  static class BadRevisionPredicate implements Predicate<Integer>{
        private int startBadRevision;

        private BadRevisionPredicate(int startBadRevision) {
            this.startBadRevision = startBadRevision;
        }

        @Override
        public boolean process(Integer object) {
            if(object>=startBadRevision){
                return true;
            }
            return false;
        }
    }

    public static <T extends Comparable<T>> T findPeak(T[] array){
        T object=null;
        int low = 0;
        int high = array.length-1;
        while (low<=high){
            int middle = low+(high-low)/2;
            if((middle==0 || array[middle].compareTo(array[middle-1])>=0)
                    && (middle==array.length-1 || array[middle].compareTo(array[middle+1])>=0)){
                object=array[middle];
                break;
            }else if(middle>0 && array[middle-1].compareTo(array[middle])>0){
                high=middle-1;
            }else{
                low=middle+1;
            }
        }
        return object;
    }


    private static int findCrossOverIndex(int[] array, final int low, final int high, int x){
        if(array[high]<=x)
            return high;
        if(array[low]>x)
            return low;

        int middle = low+(high-low)/2;
        if(array[middle]<=x && array[middle+1]>x){
            return middle;
        }
        if(array[middle]>x)
            return findCrossOverIndex(array,low,middle-1,x);

        return findCrossOverIndex(array,middle+1, high,x);
    }

    public static int findMin(int[] num) {
        return findMin(num, 0, num.length-1);
    }

    public static int findMin(int[] num, int low, int high){
        if(high==low){
            return num[low];
        }
        if(high == low +1){
            return Math.min(num[low], num[high]);
        }
        // 3 3 1 3 3 3

        int middle = (high-low)/2 + low;
        // already sorted
        if(num[high] > num[low]){
            return num[low];
            //right shift one
        }else if(num[high] == num[low]){
            return findMin(num, low+1, high);
            //go right
        }else if(num[middle] >= num[low]){
            return findMin(num, middle, high);
            //go left
        }else{
            return findMin(num, low, middle);
        }
    }


    public static void printKthClosedElement(int[] array,int x, int k){
        int left = findCrossOverIndex(array,0,array.length-1,x);
        int right = left+1;
        if(array[left]==x)
            left--;

        int count=0;
        while (left>=0 && right<=array.length-1 && count<k){
            if(x-array[left]<array[right]-x){
                System.out.println(array[left--]);
            }else{
                System.out.println(array[right++]);
            }
            count++;
        }

        while (left>=0 && count<k){
            System.out.println(array[left--]);
            count++;
        }
        while (right<=array.length-1 && count<k){
            System.out.println(array[right++]);
            count++;
        }
    }

    public static int closestElement(int[] array, int number){
        int low=0;
        int high=array.length-1;
        while (low<high){
            int middle = low+(high-low)/2;
            int diff1 = Math.abs(array[middle]-number);
            int diff2 = Math.abs(array[middle+1]-number);
            if(diff2<=diff1){
                low=middle+1;
            }else{
                high=middle;
            }
        }
        return array[high];
    }


    private static int findNumberReturningPositive(Function fn){
        if(fn.calculate(0)>0)
            return 0;
        int i=1;
        while (fn.calculate(i)<=0){
            i=i*2;
        }
        return performFunctionSearch(i/2,i, fn);
    }

    private static int performFunctionSearch(int low, int high, Function fn){
        int number=-1;
        while (low<=high){
            int mid = low+(high-low)/2;
            if(fn.calculate(mid)>0){
                number=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return number;
    }

    //smallest element greater than or equal to key
    public static <T extends Comparable<T>> int findCeil(final T[] array, final T key){
        int low=0;
        int high = array.length-1;
        while (low<high){
            int mid = low + (high-low)/2;
            if(key.compareTo(array[mid])>0){
                low = mid+1;
            }else{
                high = mid;
            }
        }
        if(array[low].compareTo(key)>=0){
            return low;
        }else{
            return -1;
        }
    }

    //largest element smaller than or equal to key
    public static <T extends Comparable<T>> int findFloor(final T[] array,final T key){
       int low=0;
       int high = array.length-1;
       while (low<high){
           int mid = low + (high-low)/2;
           if(key.compareTo(array[mid])<0){
               high=mid-1;
           }else{
               low = mid;
           }
       }
        if(array[high].compareTo(key)<=0){
            return high;
        }else{
            return -1;
        }
    }


    public static <T> int findCount(T[][] array, Predicate<T> predicate){
        int count=0;
        int row=0;
        int col = array[0].length-1;

        while (row<=array.length-1 && col>=0){
            T value = array[row][col];
            if(!predicate.process(value)){
                col--;
            }else{
                count+=(col+1);
                row++;
            }
        }
        return count;

    }


    private static class MinMax{
        private int min;
        private int max;

        @Override
        public String toString() {
            return "MinMax{" +
                    "min=" + min +
                    ", max=" + max +
                    '}';
        }
    }


    public static MinMax getMinMax(int[] array){
        MinMax minMax = new MinMax();
        int i=0;
        int length = array.length;
        if(length%2==0){
            if(array[0]>array[i]){
                minMax.max=array[0];
                minMax.min=array[1];
            }else{
                minMax.max=array[1];
                minMax.min=array[0];
            }
            i=2;
        }else{
            minMax.max=array[0];
            minMax.min=array[0];
            i=1;
        }

        while (i<length){
            if(array[i]>array[i+1]){
                if(minMax.min>array[i+1]){
                    minMax.min=array[i+1];
                }
                if(minMax.max<array[i]){
                    minMax.max=array[i];
                }
            }else{
                if(minMax.min>array[i]){
                    minMax.min=array[i];
                }
                if(minMax.max<array[i+1]){
                    minMax.max=array[i+1];
                }
            }
            i=i+2;
        }
        return minMax;
    }



}
