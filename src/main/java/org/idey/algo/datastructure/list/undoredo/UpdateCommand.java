package org.idey.algo.datastructure.list.undoredo;

import java.util.List;

public class UpdateCommand<T> extends AbstractListCommand<T> {
    public UpdateCommand(final int index, T newObject) {
        super(index, "UPDATE");
        this.newObject = newObject;
    }

    @Override
    protected void undo(List<T> list) {
        validate(list,true);
        this.newObject = list.set(index, oldObject);
    }

    @Override
    protected void redo(List<T> list) {
        validate(list,true);
        this.oldObject = list.set(index, newObject);
    }

}
