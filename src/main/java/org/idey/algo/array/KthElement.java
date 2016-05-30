package org.idey.algo.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KthElement {

    private static <T extends Comparable<T>> T getMedian(List<T> list){
        Collections.sort(list);
        return list.get(list.size() / 2);
    }

    private static <T extends Comparable<T>> T findMediansOfMedians(List<T> list){
        int size = list.size();
        if(size<=5){
            return getMedian(list);
        }else{
            List<T> medians = new ArrayList<>();
            for (int i = 0; i < list.size() - list.size() % 5; i = i + 5){
                medians.add(getMedian(list.subList(i, i + 5)));
            }
            return findMediansOfMedians(medians);
        }
    }

    public static <T extends Comparable<T>> T findKthElement(List<T> list, final int k, boolean isMin){
            T median = findMediansOfMedians(list);
            List<T> left = new ArrayList<>();
            List<T> right = new ArrayList<>();
            if(isMin){
                left.addAll((list.stream().parallel().filter(obj -> median.compareTo(obj) > 0).collect(Collectors.toList())));
                right.addAll(list.stream().parallel().filter(obj -> median.compareTo(obj) < 0).collect(Collectors.toList()));
            }else{
                left.addAll((list.stream().parallel().filter(obj -> median.compareTo(obj) < 0).collect(Collectors.toList())));
                right.addAll(list.stream().parallel().filter(obj -> median.compareTo(obj) > 0).collect(Collectors.toList()));
            }
            if(k<=left.size()){
                return findKthElement(left,k, isMin);
            }else if(k==left.size()+1){
                return median;
            }else{
                return findKthElement(right,k-left.size()-1, isMin);
            }
    }






    public static void main(String[] args) {
        List<Integer> list1 = Arrays.asList(2,3,5,4,1,12,11,13,16,7,8,6,10,9,17, 15, 19, 20, 18, 23, 21, 22, 25, 24, 14 ,26);
        List<Integer> list2 = Arrays.asList(2,3,5,4,1,12,11,13,16,7,8,6,10,9,17, 15, 19, 20, 18, 23, 21, 22, 25, 24, 14 ,26);
        Collections.sort(list2);
        System.out.println(list2);
        System.out.println(findKthElement(list1,6, false));
    }


}
