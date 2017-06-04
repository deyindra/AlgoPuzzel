package org.idey.algo.datastructure.list.undoredo;


import java.util.List;
import java.util.Objects;

abstract class AbstractListCommand<T> {
    protected final String opName;
    protected final int index;
    protected T newObject;
    protected T oldObject;

    protected AbstractListCommand(final int index, final String opName) {
        this.index = index;
        this.opName = opName;
    }

    protected void validate(List<T> list, boolean isSizeInclusive){
        if(index<0 || (isSizeInclusive? index>=list.size() : index>list.size())){
            throw new IllegalArgumentException("Invalid Index "+index);
        }
    }

    protected abstract void undo(List<T> list);
    protected abstract void redo(List<T> list);

    @Override
    public String toString() {
      return  String.format("Operation: %s, index: %s, " +
                      "newObject: %s, oldObject %s",
              opName,index,
              Objects.toString(newObject, "null"),
              Objects.toString(oldObject, "null"));
    }
}
