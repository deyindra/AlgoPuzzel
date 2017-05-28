package org.idey.algo.oops;

public class Memento {
    private String value;

    public Memento(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Memento{" +
                "value='" + value + '\'' +
                '}';
    }
}
