package org.idey.algo.bitops;

/**
 * assumption all number is unsigned 32 bit interger
 */
public class BitMagic {
    public static boolean isPowerOfTwo (int x){
        if(x<0)
            return ((x & -x)==-x);
        else
            return ((x & -x)==x);
    }

    public static boolean isParse(int number){
        return ( (number & (number<<1))==0);
    }


    public static void main(String[] args) {
//        System.out.println(countNumberofOneBit(3));
//        System.out.println(countNumberofNonLeadingZeroBit(3));
//        System.out.println(toBinaryString(73));
        System.out.println(toBinaryString(Integer.MAX_VALUE));
        System.out.println(updateBit(5,2,true));

    }

    public static int updateBit(int number, int k, boolean isBit1){
        int value = isBit1 ? 1 : 0;
        int mask = ~(1<<(k-1));
        return (number & mask) | (value << (k-1));
    }


    public static int countNumberofOneBit(int number){
        int count=0;
        while (number!=0){
            count++;
            number=(number-1)&number;
        }
        return count;
    }

    public static int countNumberofNonLeadingZeroBit(int number){
        int result=0;
        while (number!=0){
            if((number & 1)==0){
                result++;
            }
            number >>>= 1;
        }
        return result;
    }

    public static boolean isSet(int number, int k){
        return ((number & (1<<k-1)) !=0);
    }

    public static boolean isPallindrome(int number){
        int totalBit = countNumberofOneBit(number)+countNumberofNonLeadingZeroBit(number);
        int start=1;
        while (start<totalBit){
            if(isSet(number,start) != isSet(number,totalBit)){
                return false;
            }
            start++;
            totalBit--;
        }
        return true;
    }

    public static String toBinaryString(int number){
        int totalBit = countNumberofOneBit(number)+countNumberofNonLeadingZeroBit(number);
        int start=1;
        StringBuilder sb=new StringBuilder();
        while (start<=totalBit){
            sb.append(isSet(number,start) ? "1":"0");
            start++;
        }
        return sb.toString();
    }
}
