package org.idey.algo.oops;

import java.io.*;

public class Singleton implements Serializable {
    private static volatile Singleton object;

    private Singleton() {

    }

    public static Singleton getInstance() {
        if (object == null) {
            synchronized (Singleton.class) {
                if (object == null) {
                    object = new Singleton();
                }
            }
        }
        return object;
    }

    //If Implement Serialization then it should return the same singleton object
    protected Object readResolve() throws ObjectStreamException {
        return Singleton.getInstance();
    }

}



