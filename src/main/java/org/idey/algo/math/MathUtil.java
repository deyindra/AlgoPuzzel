package org.idey.algo.math;

import org.idey.algo.divnconcur.BinarySearch;

import java.util.*;

public class MathUtil {


    public static String convertToBase26(int number){
        String converted = "";
        do
        {
            int remainder = number % 26;
            if(remainder==0){
                remainder=26;
            }
            converted = (char)(remainder + 64) + converted;
            number = (number - remainder) / 26;
        }while (number > 0);
        return converted;
    }

    public static String convertToBase16(int number){
        char hex[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        String converted = "";
        do
        {
            int remainder = number % 16;
            converted = hex[remainder] + converted;
            number = number / 16;
        }while (number > 0);
        return converted;
    }

    public static double convertFromBase16ToDeciman(String base16){
        char hex[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        Map<Character, Integer> map = new HashMap<>();
        int i=0;
        for(char ch:hex){
            map.put(ch, i);
            i++;
        }
        double number=0;
        while (base16.length() > 0)
        {
            char firstChar = base16.charAt(0);
            base16 = base16.substring(1);
            number = number + (map.get(firstChar) * Math.pow(16, base16.length()));
        }
        return number;
    }



    public static int findGCD(int a, int b){
        if(a==0)
            return b;
        return findGCD(b%a,a);
    }

    public static int findLCM(int a, int b){
        return a*b/findGCD(a,b);
    }


    public static int findGCD(int... array){
        assert(array!=null && array.length>=2);
        int result=array[0];
        for(int i=1;i<array.length;i++){
            result = findGCD(result,array[i]);
        }
        return result;
    }

    public static int findLCM(int... array){
        assert(array!=null && array.length>=2);
        int result=array[0];
        for(int i=1;i<array.length;i++){
            result = (result*array[i])/findGCD(result,array[i]);
        }
        return result;
    }

    public static boolean isPrime(int number){
        if(number<0)
            number = -number;
        if(number==0 || number==1){
            return false;
        }else if(number==2){
            return true;
        }else {
            if (number % 2 == 0)
                return false;
            for (int i = 3; i * i <= number; i = i + 2) {
                if (number % i == 0)
                    return false;
            }
            return true;
        }
    }


    public static void printFibonacii(int start, int end, int total){
        System.out.println(start);
        System.out.println(end);
        for(int i=1;i<=total-2;i++){
            int sum=start+end;
            System.out.println(sum);
            start=end;
            end=sum;
        }
    }

    public static int tailRecursiveFibonacii(int n, int end, int start){
        return n==1 ? start :tailRecursiveFibonacii(n-1,end+start,end);
    }

    //With O(n) extra space with O(1) time
    public static Set<Integer> findAllOddOccuranceNumbers(int[] array){
        Set<Integer> sets = new HashSet<>();
        for (int anArray : array) {
            boolean addStatus = sets.add(anArray);
            if (!addStatus) {
                sets.remove(anArray);
            }
        }
        return sets;
    }

    public static Set<Integer> findAllEvenOccuranceNumber(int[] array){
        Set<Integer> duplicateSets = new HashSet<>();
        Set<Integer> nonDuplicateSets = new HashSet<>();

        for(int val:array){
            boolean status = nonDuplicateSets.add(val);
            if(status){
                duplicateSets.remove(val);
            }else{
                nonDuplicateSets.remove(val);
                duplicateSets.add(val);
            }
        }

        return duplicateSets;
    }

    //With O(n) extra space with O(1) time
    public static int findAllOneOddOccurance(int[] array){
        int xor=array[0];
        for(int x=1;x<array.length;x++){
            xor=xor^array[x];
        }
        return xor;
    }


    public static int findTrailingZeros(int  n)
    {
        int count = 0;
        for (int i=5; n/i>=1; i *= 5)
            count += n/i;
        return count;
    }

    public static long calculateAP(long start, long totalNumber, long difference){
       return  (totalNumber*((2*start)+(totalNumber-1)*difference))/2;
    }


    public static long sumOfDivisbleOfn10rn2(int number, int n1, int n2){
        long totalNumberDivisiblebyn1=number/n1;
        long totalNumberDivisiblebyn2 = number/n2;
        long lcm = findLCM(n1,n2);
        long totalNumberDivisiblebyLcm = number/lcm;
        return calculateAP(n1,totalNumberDivisiblebyn1,n1)
                +calculateAP(n2,totalNumberDivisiblebyn2,n2)
                -calculateAP(lcm,totalNumberDivisiblebyLcm,lcm);
    }


    private static long binomialCoefficient(long n, long k){
        long result = 1;
        if(k>n-k){
            k=n-k;
        }
        for(long i=0;i<k;i++){
            result=result*(n-i);
            result=result/(i+1);
        }
        return result;
    }

    public static long nthCatalanNumber(long n){
        long c = binomialCoefficient(2*n,n);
        return c/(n+1);
    }


    public static int findingNthFibo(int n, int start, int end){
        if(n==1){
            return start;
        }else if (n==2){
            return end;
        }else{
            return findingNthFibo(n-1,start,end)+findingNthFibo(n-2, start,end);
        }
    }

    public static void main(String[] args) {
//        printFibonacii(1,2,5);
//        System.out.println(tailRecursiveFibonacii(5,2,1));
//        System.out.println(findAllOddOccuranceNumbers(new int[] {1,2,3,4,2,1,5,4,3,7}));
//        System.out.println(findAllOneOddOccurance(new int[]{1, 2, 3, 4, 2, 1, 4, 3, 7, 7, 7}));
//        System.out.println(sumOfDivisbleOfn10rn2(20, 4, 6));
//        System.out.println(findTrailingZeros(100));
        //System.out.println(isPrime(6));
//        System.out.println(nthCatalanNumber(3));
//        System.out.println(convertToBase16(164));
//        System.out.println(findingNthFibo(4,1,2));
//        System.out.println(convertFromBase16ToDeciman(""));
//        for(int i=1;i<=144;i++)
//            System.out.print(" "+getRandomObjectWithGivenProbability(new Integer[]{2,3,4}, new Integer[]{1,10,1}));
//
//        System.out.println("\n"+factorial(4,1));

          System.out.println(findAllEvenOccuranceNumber(new int[] {1,5,1,5,5,3,3,2}));
    }


    public static int countZeoInFactorialRecurrsive(int n, int i){
        if(n/i==0) {
            return 0;
        }else{
            return n/i+countZeoInFactorialRecurrsive(n, i*5);
        }
    }

    public static <T> T getRandomObjectWithGivenProbability(T[] array, Integer[] frequency){
        Integer[] prefix = new Integer[frequency.length];
        prefix[0] = frequency[0];
        for(int i=1;i<prefix.length;i++){
            prefix[i]=prefix[i-1]+frequency[i];
        }

        Random r = new Random();
        Integer rand = r.nextInt(prefix[prefix.length-1])+1;
        int index = BinarySearch.findCeil(prefix,rand);
        return array[index];
    }

    public static long factorial(long n, long a){
        if(n==0){
            return a;
        }
        return factorial(n-1, n*a);
    }




}
