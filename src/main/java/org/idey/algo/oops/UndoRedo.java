package org.idey.algo.oops;


import java.util.Stack;

public class UndoRedo {
    private State value;
    private Stack<Memento> undo = new Stack<>();
    private Stack<Memento> redo = new Stack<>();


    public UndoRedo(String value) {
        this.value = new State(value);
    }

    public void addState(String value){
        undo.push(this.value.saveToMemento());
        this.value = new State(value);
    }

    public void undo(){
        if(!undo.isEmpty()){
            redo.push(this.value.saveToMemento());
            this.value.restoreFromMemento(undo.pop());
        }
    }

    public void redo(){
        if(!redo.isEmpty()){
            undo.push(this.value.saveToMemento());
            this.value.restoreFromMemento(redo.pop());
        }
    }

    @Override
    public String toString() {
        return "UndoRedo{" +
                "value=" + value +
                '}';
    }

    public static void main(String[] args) {
        UndoRedo x = new UndoRedo("Empty");
        x.addState("1");
        System.out.println(x);
        x.undo();
        System.out.println(x);
        x.redo();
        System.out.println(x);
        x.addState("2");
        x.undo();
        System.out.println(x);
        x.redo();
        System.out.println(x);

    }
}
