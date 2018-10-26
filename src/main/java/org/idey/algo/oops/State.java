package org.idey.algo.oops;

public class State {
    private String value;

    public State(String value) {
        this.value = value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Memento saveToMemento(){
        return new Memento(value);
    }

    public void restoreFromMemento(Memento m){
        this.value = m.getValue();
    }

    @Override
    public String toString() {
        return "State{" +
                "value='" + value + '\'' +
                '}';
    }
}
