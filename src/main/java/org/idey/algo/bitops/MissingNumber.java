package org.idey.algo.bitops;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MissingNumber {
    //4 billion non negative integers and may have some duplicate
    //Memory needed .5GB
    public static List<Integer> findMissingNumber(String fileName) throws FileNotFoundException {
        List<Integer> list = new ArrayList<>();
        long upperLimit = ((long)Integer.MAX_VALUE)+1;
        byte[] bitArray = new byte[(int)(upperLimit/Byte.SIZE)];
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNextInt()){
            int n = sc.nextInt();
            bitArray[n/Byte.SIZE] = (byte)(bitArray[n/Byte.SIZE] | (1<<(n%Byte.SIZE)));
        }
        for(int i=0;i<bitArray.length;i++){
            for(int j=0;j<8;j++){
                if((bitArray[i] & (1<<j)) ==0){
                    list.add(i*Byte.SIZE+j);
                }
            }
        }
        return list;
    }
    // All distinct postive integers starting from 0 and and use only 10 MB memory find missing integers
    public static List<Integer> findMissingNumber2(String fileName)throws FileNotFoundException {
        int range = 1<<20;
        int[] block = getNumberCountPerBlock(fileName,range);
        List<Integer> blockIndexWithMissingInteger = blockWithMissingNumber(block,range);
        if(blockIndexWithMissingInteger.isEmpty()){
            return Collections.emptyList();
        }else{
            List<Integer> missingIntegers = new ArrayList<>();
            for(Integer missingBlockIndex:blockIndexWithMissingInteger){
                populateMissingIntegers(fileName,missingIntegers,missingBlockIndex,range);
            }
            return missingIntegers;
        }
    }


    private static int[] getNumberCountPerBlock(String fileName, int range) throws FileNotFoundException {
        int[] block = new int[(Integer.MAX_VALUE/range)+1];
        Scanner sc = new Scanner(new File(fileName));
        while (sc.hasNextInt()){
            int value = sc.nextInt();
            block[value/range]++;
        }
        return block;
    }

    private static List<Integer> blockWithMissingNumber(int[] block, int range){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<block.length;i++){
            if(block[i]<range){
                list.add(i);
            }
        }
        return list;
    }

    private static void populateMissingIntegers(String fileName, List<Integer> missingIntegers,
                                                int blockIndex, int range) throws FileNotFoundException {

        int startIndex = blockIndex*range;
        int endIndex = startIndex+range;
        byte[] array = new byte[range/Byte.SIZE];

        Scanner in = new Scanner(new File(fileName));
        while (in.hasNextInt()){
            int value = in.nextInt();
            if(value>=startIndex && value<endIndex){
                int offset = value - startIndex;
                array[offset/Byte.SIZE] = (byte)(array[offset/Byte.SIZE] | (1<<(offset%Byte.SIZE)));

            }
        }
        for(int i=0;i<array.length;i++){
            for(int j=0;j<Byte.SIZE;j++){
                if( (array[i] & (1<<j)) ==0){
                    missingIntegers.add((blockIndex*range)+((i*Byte.SIZE)+j));
                }
            }
        }

    }


}
