package org.idey.algo.array;

//Moore Voting algorithim
public class FindMejority {
    public static <T> T findMejority(T[] array){
        int mejityIndex=0;
        int count=1;
        for(int i=1;i<array.length;i++){
            if(count==0){
                mejityIndex=i;
                count=1;
            }else if(array[i].equals(array[mejityIndex])){
                count++;
            }else{
                count--;
            }
        }
        return array[mejityIndex];
    }

    public static void main(String[] args) {
        System.out.println(findMejority(new Integer[]{1,3,1,1,4,1,2,2}));
    }
}
