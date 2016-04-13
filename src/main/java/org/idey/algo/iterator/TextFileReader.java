package org.idey.algo.iterator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class TextFileReader<T> implements Iterable<T>{
    private BufferedReader reader;
    private DeSerialize<T> deSerialize;

    public TextFileReader(String fileName, DeSerialize<T> deSerialize) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(fileName));
        this.deSerialize=deSerialize;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private String line = null;

            @Override
            public boolean hasNext() {
                try {
                    line = reader.readLine();
                    if (line != null) {
                        return true;
                    }
                    else {
                        reader.close();
                        return false;
                    }
                }
                catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public T next() {
                return deSerialize.deserialize(line);
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove operation is not supported");
            }
        };
    }

    public interface DeSerialize<T1>{
        T1 deserialize(String str);
    }
}
