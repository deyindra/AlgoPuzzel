package org.idey.algo.datastructure.list;

import org.idey.algo.iterator.FilteredIterator;

public class LinkedListUtil {
//    public static <T> boolean isPallinDrome(LinkedList<T> linkedList){
//        return isPallinDrome(linkedList.getStart(), linkedList.getStart());
//    }
//    private static <T> boolean isPallinDrome(LinkedList.LinkedNode<T> left, LinkedList.LinkedNode<T> right){
//        if (right == null)
//            return true;
//
//        boolean isp = isPallinDrome(left, right.getNext());
//        if (!isp)
//            return false;
//
//		/* Check values at current left and right */
//        boolean isp1 = (right.getObject().equals(left.getObject()));
//        left = left.getNext();
//        return isp1;
//    }
//
//
    public static <T extends Comparable<T>> boolean hasCycle(LinkedList<T> linkedList){
        LinkedNode<T> slow = linkedList.getStart();
        LinkedNode<T> fast = linkedList.getStart();

        while (slow!=null && fast!=null && fast.getNext()!=null){
            slow=slow.getNext();
            fast=fast.getNext().getNext();
            if(slow==fast) {
                System.out.println(slow);
                System.out.println(fast.getObject());
                //removeLoop(slow,linkedList.getStart());
                removeLoop(linkedList,fast);
                return true;

            }
        }
        return false;
    }

    public static <T extends Comparable<T>> void removeLoop(LinkedList<T> linkedList, LinkedNode<T> fastNode){
        LinkedNode<T> slow = linkedList.getStart();
        int count=0;
        while (slow.getNext()!=fastNode.getNext()){
            slow = slow.getNext();
            fastNode = fastNode.getNext();
            count++;
        }
        System.out.println("looping "+count);
        fastNode.setNext(null);
    }
    public static <T extends Comparable<T>> void removeLoop(LinkedNode<T> loop,
                                      LinkedNode<T> curr) {
        LinkedNode<T> ptr1 = null, ptr2 = null;

        /* Set a pointer to the beging of the Linked List and
         move it one by one to find the first node which is
         part of the Linked List */
        ptr1 = curr;
        while (true) {

            /* Now start a pointer from loop_node and check if it ever
             reaches ptr2 */
            ptr2 = loop;
            while (ptr2.getNext() != loop && ptr2.getNext() != ptr1) {
                ptr2 = ptr2.getNext();
            }

            /* If ptr2 reahced ptr1 then there is a loop. So break the
             loop */
            if (ptr2.getNext() == ptr1) {
                break;
            }

            /* If ptr2 did't reach ptr1 then try the next node after ptr1 */
            ptr1 = ptr1.getNext();
        }

        /* After the end of loop ptr2 is the last node of the loop. So
         make next of ptr2 as NULL */
        ptr2.setNext(null);
    }





    public static void main(String[] args) {
       LinkedList<Integer> linkedList = new LinkedList<>();
       LinkedNode<Integer> node1 = new LinkedNode<>(1);
       LinkedNode<Integer> node2 = new LinkedNode<>(2);
       LinkedNode<Integer> node3 = new LinkedNode<>(3);
       LinkedNode<Integer> node4 = new LinkedNode<>(4);
       LinkedNode<Integer> node41 = new LinkedNode<>(41);
//
        LinkedList<Integer> list = new LinkedList<>();
        list.addNode(node1).addNode(node2).addNode(node3).addNode(node4).addNode(node41);
        list.getLast().setNext(node2);

        System.out.println(hasCycle(list));
        System.out.println(list.getLast().getNext());

//        LinkedList<Integer> linkedList1 = new LinkedList<>();
//        LinkedNode<Integer> node5 = new LinkedNode<>(5);
//        LinkedNode<Integer> node6 = new LinkedNode<>(6);
//        LinkedNode<Integer> node7 = new LinkedNode<>(7);
////        LinkedNode<Integer> node8 = new LinkedNode<>(8);
////        LinkedNode<Integer> node9 = new LinkedNode<>(9);
////
//        linkedList.addNode(node1).addNode(node2).addNode(node3).addNode(node4).addNode(node41);
//        linkedList1.addNode(node5).addNode(node6).addNode(node7);
//
//        linkedList1.merge(linkedList);

//       LinkedList<Integer> linkedList = new LinkedList<>();
//       LinkedNode<Integer> node1 = new LinkedNode<>(1);
//       LinkedNode<Integer> node2 = new LinkedNode<>(7);
//       LinkedNode<Integer> node3 = new LinkedNode<>(10);
//       LinkedNode<Integer> node4 = new LinkedNode<>(15);
//       LinkedNode<Integer> node41 = new LinkedNode<>(23);


//        LinkedList<Integer> linkedList1 = new LinkedList<>();
//        LinkedNode<Integer> node5 = new LinkedNode<>(5);
//        LinkedNode<Integer> node6 = new LinkedNode<>(6);
//        LinkedNode<Integer> node8 = new LinkedNode<>(8);
//        LinkedNode<Integer> node9 = new LinkedNode<>(9);
//        LinkedNode<Integer> node10 = new LinkedNode<>(22);
//
//
//
//        linkedList.addNode(node1).addNode(node2).addNode(node3).addNode(node4).addNode(node41);
//        linkedList1.addNode(node5).addNode(node6).addNode(node8).addNode(node9).addNode(node10);
//
//        linkedList.mergeSortedReverse(linkedList1);


//
//        //linkedList.merge(linkedList1);
//        linkedList.mergeAlternate();

//        linkedList.reverseLinkedList(2,2);

        //linkedList.addNode(new LinkedNode<>(1)).addNode(new LinkedNode<>(2)).addNode(new LinkedNode<>(2)).addNode(new LinkedNode<>(1)).addNode(new LinkedNode<>(3));
        //System.out.println(linkedList.isPallinDrome());
        //linkedList.reverseLinkedList(1,10);
//
//       System.out.println("Last ==" + linkedList.getLast());
//        System.out.println("First =="+linkedList.getStart());
       //System.out.println(linkedList.isIntersect(linkedList1));

//
//        System.out.println(linkedList.isPallinDrome());
//        linkedList.reverseLinkedList();;



//        for(LinkedNode<Integer> linkedNode=linkedList1.getStart();linkedNode!=null;linkedNode=linkedNode.getNext()){
//            System.out.println(linkedNode);
//        }

        linkedList.addNode(node1).addNode(node2).addNode(node3).addNode(node4).addNode(node41);
        linkedList.rearrange(new FilteredIterator.Filter<Integer>() {
            @Override
            public boolean isValid(Integer obj) {
                return obj%2==0;
            }
        });

        System.out.println(linkedList);
        for(LinkedNode<Integer> current = linkedList.getStart(); current!=null; current = current.getNext()){
            System.out.print(current);
        }
    }


}
