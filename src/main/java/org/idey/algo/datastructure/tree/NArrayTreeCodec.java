package org.idey.algo.datastructure.tree;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class NArrayTreeCodec<E> {
    private TreeDeSerialize<E> deSerialize;
    private TreeSerialize<E> serialize;
    private static final String MARKER= "#";

    public NArrayTreeCodec(TreeDeSerialize<E> deSerialize, TreeSerialize<E> serialize) {
        this.deSerialize = deSerialize;
        this.serialize = serialize;
    }


    public String serialize(NArrayTreeNode<E> root) {
        StringBuilder sb = new StringBuilder();
        String seperator="";
        buildString(root,sb,seperator);
        return sb.toString();
    }


    private void buildString(NArrayTreeNode<E> node, StringBuilder sb, String seperator) {
        if (node == null) {
            return;
        }
        String str = serialize.serialize(node.getData());
        sb.append(seperator).append(str);
        for(NArrayTreeNode child:node.getChilds()){
            buildString(child,sb,",");
        }
        sb.append(",").append(MARKER);
    }


    public NArrayTreeNode<E> deserialize(String data, String seperator) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(seperator)));
        return buildTree(nodes);
    }

    private NArrayTreeNode<E> buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals(MARKER)) return null;
        NArrayTreeNode<E> node = new NArrayTreeNode<>(deSerialize.deserialize(val));
        while (!nodes.isEmpty()){
            NArrayTreeNode<E> child = buildTree(nodes);
            if(child!=null)
                node.addChild(child);
            else
                break;
        }
        return node;
    }

    public interface TreeDeSerialize<E> {
        E deserialize(String value);
    }

    public interface TreeSerialize<E> {
        String serialize(E e);
    }


    public static void main(String[] args) {
        NArrayTreeNode<Integer> root = new NArrayTreeNode<>(1);
        NArrayTreeNode<Integer> child1 = new NArrayTreeNode<>(2);
        NArrayTreeNode<Integer> child2 = new NArrayTreeNode<>(3);
        NArrayTreeNode<Integer> child3 = new NArrayTreeNode<>(4);

        NArrayTreeNode<Integer> child5 = new NArrayTreeNode<>(5);
        NArrayTreeNode<Integer> child6 = new NArrayTreeNode<>(6);

        child1.addChild(child5).addChild(child6);
        root.addChild(child1).addChild(child2).addChild(child3);
        System.out.println(root);

        TreeSerialize<Integer> serialize = new TreeSerialize<Integer>() {
            @Override
            public String serialize(Integer integer) {
                return integer.toString();
            }
        };

        TreeDeSerialize<Integer> deSerialize = new TreeDeSerialize<Integer>() {
            @Override
            public Integer deserialize(String value) {
                return Integer.valueOf(value);
            }
        };

        NArrayTreeCodec<Integer> codec = new NArrayTreeCodec<>(deSerialize,serialize);
        String str = codec.serialize(root);
        System.out.println(str);
        NArrayTreeNode<Integer> root1 = codec.deserialize(str,",");
        System.out.println(root1);

        System.out.println((1>>2));

    }
}
