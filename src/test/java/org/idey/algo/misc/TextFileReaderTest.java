package org.idey.algo.misc;

import org.idey.algo.iterator.MergeIterator;
import org.idey.algo.iterator.TextFileReader;
import org.junit.Test;

import java.io.FileNotFoundException;

public class TextFileReaderTest {
    @Test
    public void testFileContent() throws FileNotFoundException {
        TextFileReader.DeSerialize<Integer> deSerialize = new TextFileReader.DeSerialize<Integer>() {
            @Override
            public Integer deserialize(String str) {
                return Integer.valueOf(str);
            }
        };

        TextFileReader<Integer> fileReader1 = new TextFileReader<>(TextFileReaderTest.class.getResource("/A.txt").getPath(),deSerialize);
        TextFileReader<Integer> fileReader2 = new TextFileReader<>(TextFileReaderTest.class.getResource("/B.txt").getPath(),deSerialize);

        MergeIterator<Integer> mergeIterator = new MergeIterator<>(fileReader1.iterator(),fileReader2.iterator());
        while (mergeIterator.hasNext()){
            System.out.println(mergeIterator.next());
        }

    }

}
