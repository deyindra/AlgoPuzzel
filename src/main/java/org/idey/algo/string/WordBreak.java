package org.idey.algo.string;

import java.util.ArrayList;
import java.util.List;

public class WordBreak {
    public static List<String> wordBreak(String str){
        if(str==null){
            throw new IllegalArgumentException("Invalid String");
        }

        List<String> wordList = new ArrayList<>();
        String subString="";
        boolean isQuoteFound=false;
        for(char c:str.toCharArray()){
            if(c!=' ' && c!='"'){
                subString+=c;
            }else if(c=='"'){
                subString+=c;
                isQuoteFound=!isQuoteFound;
            }else{
                if(isQuoteFound){
                    subString+=c;
                }else{
                    if(!"".equals(subString)){
                        wordList.add(subString);
                        subString="";
                        isQuoteFound=false;
                    }
                }

            }

        }
        if(!"".equals(subString)) {
            wordList.add(subString);
        }
        return wordList;
    }

    public static void main(String[] args) {
        System.out.println(wordBreak("   \"ANC E\"FG    XYZ  1  AMC \"ANC\" ABC  XYZ H    "));
    }
}
