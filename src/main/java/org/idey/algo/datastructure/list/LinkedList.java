package org.idey.algo.datastructure.list;

import org.idey.algo.iterator.FilteredIterator.Filter;

public class LinkedList<T extends Comparable<T>> implements Comparable<LinkedList<T>>{
    private LinkedNode<T> start;
    private LinkedNode<T> last;

    //always appending at the end
    public LinkedList<T> addNode(LinkedNode<T> node){
        if(start==null){
            start = node;
            last=node;
        }else{
            last.setNext(node);
            last=node;
        }
        return this;
    }


    @Override
    public int compareTo(LinkedList<T> o) {
        if(o==null){
            return 1;
        }else{
            LinkedNode<T> current = this.start;
            LinkedNode<T> secondCurrent = o.start;

            if(current==null && secondCurrent==null){
                return 0;
            }else{
                while (current!=null && secondCurrent!=null && current.equals(secondCurrent)){
                    current=current.getNext();
                    secondCurrent=secondCurrent.getNext();
                }

               if(current==null && secondCurrent==null){
                   return 0;
               }else{
                   if(current!=null){
                       return current.compareTo(secondCurrent);
                   }else{
                       return -1;
                   }
               }
            }

        }
    }



    public  boolean isPallinDrome(){
        LinkedNode.LinkedNodeWrapper<T> wrapper= new LinkedNode.LinkedNodeWrapper<>();
        wrapper.setLinkedNode(this.start);
        return isPallinDrome(wrapper, this.start);
    }
    private boolean isPallinDrome(LinkedNode.LinkedNodeWrapper<T> wrapper, LinkedNode<T> right){
        if (right == null)
            return true;

        boolean isp = isPallinDrome(wrapper,right.getNext());
        if (!isp)
            return false;

		/* Check values at current left and right */
        boolean isp1 = (right.equals(wrapper.getLinkedNode()));
        wrapper.setLinkedNode(wrapper.getLinkedNode().getNext());
        return isp1;
    }

    public void mergeSortedReverse(LinkedList<T> secondList){
        if(secondList!=null){
            LinkedNode<T> next;
            LinkedNode<T> prev= null;


            LinkedNode<T> current = this.start;
            LinkedNode<T> secondCurrent = secondList.start;


            while (current!=null && secondCurrent!=null){
                if(current.compareTo(secondCurrent)<=0){
                    next=current.getNext();
                    current.setNext(prev);
                    prev=current;
                    current=next;
                }else{
                    next=secondCurrent.getNext();
                    secondCurrent.setNext(prev);
                    prev=secondCurrent;
                    secondCurrent=next;
                }
            }
            while (current!=null){
                next=current.getNext();
                current.setNext(prev);
                prev=current;
                current=next;
            }

            while (secondCurrent!=null){
                next=secondCurrent.getNext();
                secondCurrent.setNext(prev);
                prev=secondCurrent;
                secondCurrent=next;
            }
            if(this.start.getNext()==null){
                this.last=this.start;
            }else if(secondList.start.getNext()==null){
                this.last=secondList.start;
            }
            this.start=prev;
        }
    }

    public void mergeSorted(LinkedList<T> secondList){
        if(secondList!=null){
            LinkedNode<T> dummy = new LinkedNode<>(null);
            LinkedNode<T> dummyCurrent = dummy;

            LinkedNode<T> current = this.start;
            LinkedNode<T> secondCurrent = secondList.start;


            while (current!=null && secondCurrent!=null){
                if(current.compareTo(secondCurrent)<=0){
                    dummyCurrent.setNext(current);
                    current=current.getNext();
                }else{
                    dummyCurrent.setNext(secondCurrent);
                    secondCurrent=secondCurrent.getNext();
                }
                dummyCurrent=dummyCurrent.getNext();

            }
            if(secondCurrent!=null){
                dummyCurrent.setNext(secondCurrent);
                this.last=secondList.last;
            }

            if(current!=null){
                dummyCurrent.setNext(current);
            }
            this.start=dummy.getNext();
        }
    }


    //Merge in alternate position
    public void merge(LinkedList<T> secondList){
        if(secondList!=null){
            LinkedNode<T> current = this.start;
            LinkedNode<T> secondCurrent = secondList.start;
            LinkedNode<T> dummy=new LinkedNode<>(null);
            LinkedNode<T> dummyCurrent=dummy;

            while (current!=null || secondCurrent!=null){
                if(current!=null){
                    dummyCurrent.setNext(current);
                    current=current.getNext();
                    dummyCurrent=dummyCurrent.getNext();
                }
                if(secondCurrent!=null){
                    dummyCurrent.setNext(secondCurrent);
                    secondCurrent=secondCurrent.getNext();
                    dummyCurrent=dummyCurrent.getNext();
                }
            }
            this.start=dummy.getNext();
            this.last=dummyCurrent;
        }
    }

    public void mergeAlternate(){
        LinkedNode<T> current=this.start;
        LinkedNode<T> middle=this.start;
        LinkedNode<T> fast = this.start;

        while (fast!=null && fast.getNext()!=null){
            current=middle;
            middle=middle.getNext();
            fast=fast.getNext().getNext();
        }
        LinkedNode<T> firstStartNode = this.start;
        LinkedNode<T> secondStartNode = this.last;
        reverseLinkedList(middle, this.last);
        current.setNext(null);

        LinkedNode<T> dummy=new LinkedNode<>(null);
        LinkedNode<T> dummyCurrent=dummy;

        while (firstStartNode!=null || secondStartNode!=null){
            if(firstStartNode!=null){
                dummyCurrent.setNext(firstStartNode);
                dummyCurrent=dummyCurrent.getNext();
                firstStartNode=firstStartNode.getNext();
            }

            if(secondStartNode!=null){
                dummyCurrent.setNext(secondStartNode);
                dummyCurrent=dummyCurrent.getNext();
                secondStartNode=secondStartNode.getNext();
            }
        }
        this.start=dummy.getNext();
        this.last=dummyCurrent;
    }



    public void reverseLinkedList(){
      reverseLinkedList(start,last);
    }

    public void reverseLinkedList(final LinkedNode<T> startNode, final LinkedNode<T> lastNode){
        final LinkedNode<T> nextOfLastNode = lastNode.getNext();
        LinkedNode<T> prevOfStartNode;
        if(startNode!=this.start)
            for(prevOfStartNode=this.start;prevOfStartNode.getNext()!=startNode;prevOfStartNode=prevOfStartNode.getNext());
        else
            prevOfStartNode=this.start;
        reverseLinkedList(nextOfLastNode,prevOfStartNode,startNode, lastNode);
    }

    public void reverseLinkedList(int m, int n){
        if(m>n){
            throw new IllegalArgumentException("Input m less be greater than n");
        }else if(m<n){
                LinkedNode<T> prevOfStartNode = this.start;
                LinkedNode<T> current = this.start;

                for (int i = 1; i < m; i++) {
                    if (current != null) {
                        prevOfStartNode=current;
                        current=current.getNext();
                    } else {
                        break;
                    }
                }
                if (current != null) {
                    LinkedNode<T> start=current;
                    LinkedNode<T> prev = null;
                    LinkedNode<T> next;

                    for (int i = m; i <= n && current != null; i++) {
                        next = current.getNext();
                        current.setNext(prev);
                        prev = current;
                        current = next;
                     }
                    if (start == this.start) {
                        this.start = prev;
                    } else {
                        prevOfStartNode.setNext(prev);
                    }

                    if(current==null){
                        this.last=start;
                    }else{
                        start.setNext(current);
                    }
            }
        }
    }

    private void reverseLinkedList(final LinkedNode<T> nextOfLastNode, final LinkedNode<T> prevOfStartNode, final LinkedNode<T> startNode, final LinkedNode<T> lastNode){
        LinkedNode<T> prev=null;
        LinkedNode<T> current = startNode;
        LinkedNode<T> next;
        while (current!=nextOfLastNode){
            next=current.getNext();
            current.setNext(prev);
            prev=current;
            current=next;
        }
        if(startNode==this.start){
            this.start=lastNode;
        }else{
            prevOfStartNode.setNext(lastNode);
        }

        if(lastNode==this.last){
            this.last=startNode;
        }else{
            startNode.setNext(nextOfLastNode);
        }
    }


    public void deleteNNodeFromLast(int n){
        LinkedNode<T> prevNodeOfNthNode = findNthNodeFromLast(n+1);
        if(prevNodeOfNthNode==null){
            this.start=null;
            this.last=null;
        }else{
            prevNodeOfNthNode.setNext(null);
            this.last=prevNodeOfNthNode;
        }
    }

    public LinkedNode<T> findNthNodeFromLast(int n) {
        if(n<1){
            throw new IllegalArgumentException("Invalid Number");
        }
        LinkedNode<T> slowPtr = this.start;
        LinkedNode<T> fastPtr = this.start;

        int count = 0;
        while (count < n && fastPtr != null) {
            count++;
            fastPtr = fastPtr.getNext();
        }
        while (fastPtr != null) {
            slowPtr = slowPtr.getNext();
            fastPtr = fastPtr.getNext();
        }
        if(count==n){
            return slowPtr;
        }else{
            return null;
        }
    }


    public LinkedNode<T> getLast() {
        return last;
    }

    public LinkedNode<T> getStart() {
        return start;
    }

    public boolean isIntersect(LinkedList<T> secondLinkedList){
       int firstLinkedListLength = this.getLength();
       int secondLinkedListLength= secondLinkedList.getLength();

        LinkedNode<T> firstStart,secondStart;

        if(firstLinkedListLength>secondLinkedListLength){
            firstStart = this.getKthNodeFromFirst(firstLinkedListLength-secondLinkedListLength);
            secondStart=secondLinkedList.start;
        }else if(firstLinkedListLength<secondLinkedListLength){
            firstStart = secondLinkedList.getKthNodeFromFirst(secondLinkedListLength-firstLinkedListLength);
            secondStart=this.start;
        }else{
            firstStart =this.start;
            secondStart=secondLinkedList.start;
        }

        while(firstStart!=null && secondStart!=null){
            if(firstStart==secondStart){
                return true;
            }
            firstStart=firstStart.getNext();
            secondStart=secondStart.getNext();
        }

        return false;

    }

    public int getLength(){
        int lenth=0;
        for(LinkedNode<T> current=this.start;current!=null;current=current.getNext(),lenth++);
        return lenth;
    }

    public LinkedNode<T> getKthNodeFromFirst(final int k){
        int count=0;
        LinkedNode<T> kthNode;
        for(kthNode=this.start;count<k && kthNode!=null ; count++,kthNode=kthNode.getNext());
        return kthNode;
    }

    @Override
    protected LinkedList<T> clone() throws CloneNotSupportedException {
        if(this.start==null){
            return null;
        }else{
            LinkedList<T> cloneList = new LinkedList<>();
            LinkedNode<T> current = this.start;
            while (current!=null){
                LinkedNode<T> copy = current.clone();
                copy.setNext(current.getNext());
                current.setNext(copy);
                current=copy.getNext();
            }

            current = this.start;
            while (current!=null){
                if(current.getRandom()!=null){
                    current.getNext().setRandom(current.getRandom().getNext());
                }
                current=current.getNext().getNext();
            }

            current = this.start;
            cloneList.start=this.start.getNext();
            cloneList.last=this.last.getNext();

            while (current!=null){
                LinkedNode<T> temp = current.getNext();
                current.setNext(temp.getNext());
                if(temp.getNext()!=null)
                    temp.setNext(temp.getNext().getNext());
                current=current.getNext();
            }

            return cloneList;
        }
    }

    public void deleteNNodeAfterMNode(int m, int n){
        if(m<0){
            throw new IllegalArgumentException("Invalid Value");
        }
        if(n<0){
            throw new IllegalArgumentException("Invalid Value");
        }

        if(m==0 && n>0){
            this.start=null;
            this.last=null;
        }else{
            LinkedNode<T> current = this.start;
            while (current!=null){
                for(int i=1;i<m && current!=null;i++,current=current.getNext());

                if(current!=null){
                    LinkedNode<T> temp = current.getNext();
                    LinkedNode<T> prevTemp = current.getNext();

                    for(int i=1;i<=n && temp!=null;i++){
                        prevTemp=temp;
                        temp=temp.getNext();
                    }
                    current.setNext(temp);
                    prevTemp.setNext(null);
                    current=temp;
                }
            }
            for(current=this.start;current.getNext()!=null;current=current.getNext());
            this.last=current;
        }
    }

    public void rearrange(Filter<T> filter){
       LinkedNode<T> firstStart = null;
       LinkedNode<T> firstend = null;
       LinkedNode<T> secondStart = null;
       LinkedNode<T> secondend = null;

       LinkedNode<T> currentNode = this.start;
       while (currentNode!=null){
           if(filter.isValid(currentNode.getObject())){
               if(firstStart==null){
                   firstStart = currentNode;
                   firstend = firstStart;
               }else{
                   firstend.setNext(currentNode);
                   firstend = firstend.getNext();
               }
           }else{
               if(secondStart==null){
                   secondStart = currentNode;
                   secondend = secondStart;
               }else{
                   secondend.setNext(currentNode);
                   secondend = secondend.getNext();
               }

           }
           currentNode = currentNode.getNext();
       }

        if(firstStart == null || secondStart == null) {
            return;
        }

       firstend.setNext(secondStart);
       secondend.setNext(null);
       this.start = firstStart;
       this.last = secondend;
    }



}
