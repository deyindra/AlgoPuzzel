package org.idey.algo.array;

import java.util.Arrays;

public abstract class AssociativeOperation {
    protected int intVar;

    protected AssociativeOperation(int intVar) {
        this.intVar = intVar;
    }

    protected abstract int operation(int a, int b);

    public  static class AddOperation extends AssociativeOperation{
        public AddOperation() {
            super(0);
        }

        @Override
        protected int operation(int a, int b) {
            return a+b;
        }
    }

    public static class MultiplyOperation extends AssociativeOperation{
        public MultiplyOperation() {
            super(1);
        }

        @Override
        protected int operation(int a, int b) {
            return a*b;
        }
    }

    public int getIntVar() {
        return intVar;
    }


    public static int[] getOutput(int[] array, AssociativeOperation associativeOperation){
        int[] finalOutput = new int[array.length];
        int temp = associativeOperation.getIntVar();
        for(int i=0; i<finalOutput.length;i++){
            finalOutput[i] = temp;
        }

        for(int i=0;i<finalOutput.length;i++){
            finalOutput[i] = temp;
            temp = associativeOperation.operation(temp , array[i]);
        }

        temp = associativeOperation.getIntVar();
        for (int i = finalOutput.length - 1; i >= 0; i--)
        {
            finalOutput[i] = associativeOperation.operation(finalOutput[i], temp);
            temp = associativeOperation.operation(temp, array[i]);
        }

        return finalOutput;
    }

    public static void main(String[] args) {
        AssociativeOperation operation = new AddOperation();
        int[] array = AssociativeOperation.getOutput(new int[]{10,1,2,3}, operation);
        System.out.println(Arrays.toString(array));
    }
}
