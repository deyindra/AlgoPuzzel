package org.idey.algo.datastructure.list.undoredo;

import java.util.List;

public class DeleteCommand<T> extends AbstractListCommand<T> {
    public DeleteCommand(final int index) {
        super(index, "DELETE");
    }

    @Override
    protected void undo(List<T> list) {
        validate(list,false);
        list.add(index, newObject);
    }

    @Override
    protected void redo(List<T> list) {
        validate(list,true);
        this.newObject = list.remove(index);
    }
}
