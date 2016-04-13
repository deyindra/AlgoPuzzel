package org.idey.algo.datastructure.tree;



import java.util.*;

public class TreeCodec<E> {

    private TreeDeSerialize<E> deSerialize;
    private TreeSerialize<E> serialize;

    public TreeCodec(TreeDeSerialize<E> deSerialize, TreeSerialize<E> serialize) {
        this.deSerialize = deSerialize;
        this.serialize = serialize;
    }

    public String serialize(TreeNode<E> root) {
        StringBuilder sb = new StringBuilder();
        String seperator="";
        buildString(root,sb,seperator);
        return sb.toString();
    }


    private void buildString(TreeNode<E> node, StringBuilder sb, String seperator) {
        if (node == null) {
            sb.append(seperator).append("null");
            return;
        }
        String str = serialize.serialize(node.getData());
        sb.append(seperator).append(str);
        buildString(node.getLeft(), sb, ",");
        buildString(node.getRight(),sb, ",");

    }


    public TreeNode<E> deserialize(String data, String seperator) {
        Deque<String> nodes = new LinkedList<>();
        nodes.addAll(Arrays.asList(data.split(seperator)));
        return buildTree(nodes);
    }

    private TreeNode<E> buildTree(Deque<String> nodes) {
        String val = nodes.remove();
        if (val.equals("null")) return null;
        TreeNode<E> node = new TreeNode<>(deSerialize.deserialize(val));
        node.addLeft(buildTree(nodes));
        node.addRight(buildTree(nodes));
        return node;
    }


    public interface TreeDeSerialize<E> {
        E deserialize(String value);
    }

    public interface TreeSerialize<E> {
        String serialize(E e);
    }


}
