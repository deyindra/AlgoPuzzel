package org.idey.algo.array;

//Moore Voting algorithim
public class FindMejority {
    public static <T> T findMejority(T[] array){
        int mejityIndex=0;
        int count=1;
        for(int i=1;i<array.length;i++){
            if(array[i].equals(array[mejityIndex])){
                count++;
            }else{
                count--;
            }
            if(count==0){
                mejityIndex=i;
                count=1;
            }
        }
        return array[mejityIndex];
    }

    public static void main(String[] args) {
        System.out.println(findMejority(new Integer[]{1,1,5,6,5,7,7,7,5,6,5,10,5}));
    }
}
