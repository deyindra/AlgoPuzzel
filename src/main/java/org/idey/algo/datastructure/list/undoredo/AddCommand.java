package org.idey.algo.datastructure.list.undoredo;

import java.util.List;

public class AddCommand<T> extends AbstractListCommand<T> {

    public AddCommand(final int index, T newObject) {
        super(index, "ADD");
        this.newObject = newObject;
    }



    @Override
    protected void undo(List<T> list) {
        validate(list,true);
        this.newObject = list.remove(index);
    }

    @Override
    protected void redo(List<T> list) {
        validate(list,false);
        list.add(index,newObject);
    }
}
