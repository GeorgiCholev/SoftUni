package implementations;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TreeFactory {
    private final Map<Integer, Tree<Integer>> nodesByKeys;

    public TreeFactory() {
        this.nodesByKeys = new LinkedHashMap<>();
    }

    public Tree<Integer> createTreeFromStrings(String[] input) {

        for (String line : input) {
            int[] components = Arrays.stream(line.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            this.addEdge(components[0], components[1]);
        }
        return this.getRoot();
    }

    private Tree<Integer> getRoot() {
        for (Tree<Integer> node : nodesByKeys.values()) {
            if (node.getParent() == null) {
                return node;
            }
        }
        return null;
    }

    public Tree<Integer> createNodeByKey(int key) {
        this.nodesByKeys.putIfAbsent(key, new Tree<>(key));
        return this.nodesByKeys.get(key);
    }

    public void addEdge(int parent, int child) {

        Tree<Integer> parentTree = this.createNodeByKey(parent);
        Tree<Integer> childTree = this.createNodeByKey(child);

        parentTree.addChild(childTree);
        childTree.setParent(parentTree);

    }
}



