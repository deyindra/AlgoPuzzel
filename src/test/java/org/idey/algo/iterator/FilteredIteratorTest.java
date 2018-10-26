package org.idey.algo.iterator;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class FilteredIteratorTest {
    private FilteredIterator.Filter<Integer> filter = null;
    public Integer[] inputArray=null;
    public Integer[] outputArray=null;

    public FilteredIteratorTest(FilteredIterator.Filter<Integer> filter,
                                Integer[] inputArray, Integer[] outputArray) {
        this.filter = filter;
        this.inputArray = inputArray;
        this.outputArray = outputArray;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {
                        (FilteredIterator.Filter<Integer>) obj -> obj==null,
                        new Integer[]{null,null,1,null},
                        new Integer[]{null,null,null}
                },
                {
                   (FilteredIterator.Filter<Integer>) obj -> obj!=null,
                   new Integer[]{null,null,1,null},
                   new Integer[]{1}
                },
                {
                   (FilteredIterator.Filter<Integer>) obj -> obj!=null && obj%2==0,

                   new Integer[]{null,null,1,null},
                   new Integer[]{}

                },
                {
                      (FilteredIterator.Filter<Integer>) obj -> obj!=null && obj%2==0,
                      new Integer[]{null,null,2,null},
                      new Integer[]{2}

                }
        });
    }


    @Test
    public void test(){
        FilteredIterator<Integer> filteredIterator = new FilteredIterator<>(inputArray,filter);
        while(filteredIterator.hasNext()){
            for(Integer arrayVal:outputArray){
                Assert.assertEquals(filteredIterator.next(),arrayVal);
            }
        }
    }
}
