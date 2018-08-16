package org.idey.algo.math;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class SqureRoot {
    public static double nthroot(int n, double x, double p)
    {
        boolean isReciprocal=false;
        if(x < 0 || n==0)
        {
            System.err.println("Negative!");
            return -1;
        }
        if(n<0){
            isReciprocal=true;
            n=-n;
        }
        if(x == 0)
            return 0;
        double x1 = x;
        double x2 = x / n;
        while (Math.abs(x1 - x2) > p)
        {
            x1 = x2;
            x2 = ((n - 1.0) * x2 + x / Math.pow(x2, n - 1.0)) / n;
        }
        return (!isReciprocal?x2:1/x2);
    }


    public static float power(float x, int y){
        if(y==0){
            return 1f;
        }
        float temp = power(x,y/2);
        if(y%2==0){
            return temp*temp;
        }else{
            if(y>0){
                return x*temp*temp;
            }else{
                return (temp*temp)/x;
            }
        }
    }

    public static int randomNotInArray(int[] array, Rand rand, int number){
        Arrays.sort(array);
        List<Integer> list = new ArrayList<>();

        for(int i=0;i<number;i++){
            int index = Arrays.binarySearch(array,i);
            if(index<0){
                list.add(i);
            }
        }
        System.out.println(list);
        int index = rand.rand(list.size());
        return list.get(index);
    }

    public static int returnRandam(int min, int max){
        Random r = new Random();
        return r.nextInt(max-min+1)+min;
    }

    public static int calculateRandom7(){
        int number=22;
        while (number>=22){
            number=5*returnRandam(1,5)-5+returnRandam(1,5);
        }
        return number%7+1;
    }



    public static void main(String[] args) {
        System.out.println(power(2,-2));
        System.out.println(nthroot(3,3d,.01d));
//        float d = (1>>2);
//        System.out.println(d);

        Rand rand = new Rand() {
            @Override
            public int rand(int number) {
                Random r = new Random();
                return r.nextInt(number);
            }
        };

        System.out.println(randomNotInArray(new int[] {2,3,10},rand,8));
        System.out.println(calculateRandom7());
    }


    private interface Rand{
        int rand(int number);
    }
}
