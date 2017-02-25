package org.idey.algo.datastructure.tree;


public class SiblingTree {

    public  static <T> void connect(SiblingTreeNode<T> node){
        if(node != null){
            //if we have left child
            if(node.left != null) {
                if(node.right!=null)
                    node.left.next = node.right;
                else
                    if(node.next!=null) {
                        node.left.next = node.next.left;
                    }
                connect(node.left);
            }
            if(node.right != null) {
                if(node.next != null) {
                        node.right.next = node.next.left;
                } else {
                    node.right.next = null;
                }
                connect(node.right);
            }
        }
    }



    private static class SiblingTreeNode<T>{
        private T data;
        private SiblingTreeNode<T> left;
        private SiblingTreeNode<T> right;
        private SiblingTreeNode<T> next;

        public SiblingTreeNode(T data) {
            this.data = data;
        }

        public SiblingTreeNode<T> addLeft(SiblingTreeNode<T> left){
            this.left = left;
            return this;
        }
        public SiblingTreeNode<T> addRight(SiblingTreeNode<T> right){
            this.right = right;
            return this;
        }
        public SiblingTreeNode<T> addNext(SiblingTreeNode<T> next){
            this.next = next;
            return this;
        }

        public SiblingTreeNode<T> getLeft() {
            return left;
        }

        public SiblingTreeNode<T> getRight() {
            return right;
        }

        public SiblingTreeNode<T> getNext() {
            return next;
        }

        @Override
        public String toString() {
            return "SiblingTreeNode{" +
                    "data=" + data +
                    '}';
        }
    }

    public static void main(String[] args) {
        SiblingTreeNode<Integer> A = new SiblingTreeNode<>(1);
        SiblingTreeNode<Integer> B = new SiblingTreeNode<>(2);
        //SiblingTreeNode<Integer> C = new SiblingTreeNode<>(3);
        SiblingTreeNode<Integer> D = new SiblingTreeNode<>(4);
        SiblingTreeNode<Integer> E = new SiblingTreeNode<>(5);
        SiblingTreeNode<Integer> F = new SiblingTreeNode<>(6);
        SiblingTreeNode<Integer> G = new SiblingTreeNode<>(7);

        A.addLeft(B);
        B.addLeft(D).addRight(E);
        //C.addLeft(F).addRight(G);

        SiblingTree.connect(A);


        System.out.println(A+"->"+A.getNext());
        System.out.println(B+"->"+B.getNext());
//        System.out.println(C+"->"+C.getNext());
        System.out.println(D+"->"+D.getNext());
        System.out.println(E+"->"+E.getNext());
//        System.out.println(F+"->"+F.getNext());
//        System.out.println(G+"->"+G.getNext());


    }
}
