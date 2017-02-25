package org.idey.algo.oops.expression.net.objecthunter.exp4j;

import org.idey.algo.oops.expression.net.objecthunter.exp4j.function.Function;
import org.idey.algo.oops.expression.net.objecthunter.exp4j.operator.Operator;
import org.idey.algo.oops.expression.net.objecthunter.exp4j.shuntingyard.ShuntingYard;
import org.idey.algo.oops.expression.net.objecthunter.exp4j.tokenizer.Token;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        Operator factorial = new Operator("!", 1, true, Operator.PRECEDENCE_POWER + 1) {
            @Override
            public double apply(double... args) {
                final int arg = (int) args[0];
                if ((double) arg != args[0]) {
                    throw new IllegalArgumentException("Operand for factorial has to be an integer");
                }
                if (arg < 0) {
                    throw new IllegalArgumentException("The operand of the factorial can not be less than zero");
                }
                double result = 1;
                for (int i = 1; i <= arg; i++) {
                    result *= i;
                }
                return result;
            }
        };

        Function function = new Function("now",0) {
            @Override
            public double apply(double... args) {
                return 1;
            }
        };


        Expression ex = new ExpressionBuilder("-3now()").functions(function).build();
        System.out.println(ex.validate().isValid());

        List<String> list = new ArrayList<>();
        for(int i=0;i<101; i++){
            list.add(i+"");
        }

        int threasold = Math.min(24, list.size());

        File file = new File("/Users/i.dey/Downloads/a.txt");
        if(file.exists()){
            file.delete();
        }
        file.createNewFile();
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);

        StringBuilder builder = new StringBuilder();
        String separator="";
        int count=0;
        for(int i=0;i<list.size();i++){
            builder.append(separator).append(list.get(i));
            if(count==threasold){
                System.out.println("Flushing to file....");
                bw.write(builder.toString());
                count=0;
                builder = new StringBuilder();
            }
            separator="\n";
            count++;
        }
        System.out.println("Flushing to file....");
        bw.write(builder.toString());
        bw.close();


    }
}
