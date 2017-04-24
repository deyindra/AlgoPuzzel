package org.idey.algo.math;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public enum PositiveBaseConverter {
    BINARY(new String[]{"0", "1"}),
    HEX(new String[] {"0","1","2","3","4","5","6","7","8","9","A","B","C","D","E","F"}),
    BASE62(new String[] {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o",
            "p","q","r","s","t","u","v","w","x","y","z","1","2","3","4",
            "5","6","7","8","9","0","A","B","C","D","E","F","G","H","I",
            "J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X",
            "Y","Z"});
    private String[] array;
    private Map<String, Long> map=new HashMap<>();

    PositiveBaseConverter(String[] array) {
        this.array = array;
        for(int i=0;i<array.length;i++){
            map.put(array[i],(long)i);
        }
    }



    public String encode(long number){
        String returnValue="";
        do{
            int reminder = (int)(number%array.length);
            returnValue = array[reminder]+returnValue;
            number = number/array.length;
        }while (number>0);
        return returnValue;
    }

    public long decode(String str){
        long returnValue = 0;
        while (str.length()>0){
            String prefix = str.substring(0,1);
            str = str.substring(1);
            returnValue = returnValue+(map.get(prefix)*(long)Math.pow(array.length,str.length()));
        }
        return returnValue;
    }

    public static void main(String[] args) {
        long number = new Random().nextInt(10)+1;
        System.out.println(number);
        String encode = BASE62.encode(number);
        System.out.println(encode);
        System.out.println(BASE62.decode(encode));
    }


}
