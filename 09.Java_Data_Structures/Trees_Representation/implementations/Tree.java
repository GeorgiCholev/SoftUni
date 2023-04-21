package implementations;

import interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {

    private E key;

    private Tree<E> parent;

    private final List<Tree<E>> children;

    @SafeVarargs
    public Tree(E key, Tree<E>... children) {

        this.key = key;
        this.children = children != null ? new ArrayList<>(Arrays.asList(children)) : new ArrayList<>();
        setChildrenParentToThis();
    }


    public List<Tree<E>> getChildren() {
        return children;
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {

        StringBuilder output = new StringBuilder();
        dfs(this, "  ", 0, output);

        return output.toString().trim();
    }

    private void dfs(Tree<E> node, String indentation, int level, StringBuilder output) {
        output
                .append(String.valueOf(indentation).repeat(Math.max(0, level)))
                .append(node.getKey())
                .append(System.lineSeparator());

        for (Tree<E> child : node.getChildren()) {
            this.dfs(child, indentation, level + 1, output);
        }
    }

    @Override
    public List<E> getLeafKeys() {
        List<E> leaves = new ArrayList<>();

        Deque<Tree<E>> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(this);

        while (!nodeQueue.isEmpty()) {

            Tree<E> current = nodeQueue.poll();
            if (current.getChildren().isEmpty()) {
                leaves.add(current.getKey());
            }

            for (Tree<E> child : current.getChildren()) {
                nodeQueue.offer(child);

            }
        }
        return leaves;
    }

    @Override
    public List<E> getMiddleKeys() {
        List<E> middleNodes = new ArrayList<>();

        Deque<Tree<E>> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(this);

        while (!nodeQueue.isEmpty()) {
            Tree<E> current = nodeQueue.poll();

            if (!current.getChildren().isEmpty() && current.getParent() != null) {
                middleNodes.add(current.getKey());
            }

            for (Tree<E> child : current.getChildren()) {
                nodeQueue.offer(child);
            }
        }

        return middleNodes;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {

        Deque<Tree<E>> nodeQueue = new ArrayDeque<>();
        nodeQueue.offer(this);

        Tree<E> deepest = null;
        while (!nodeQueue.isEmpty()) {

            deepest = nodeQueue.poll();
            List<Tree<E>> children = deepest.getChildren();
            for (int i = children.size() - 1; i >= 0; i--) {
                nodeQueue.offer(children.get(i));
            }
        }
        return deepest;
    }

    @Override
    public List<E> getLongestPath() {
        return this.traverseBack(this.getDeepestLeftmostNode());
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {

        if (isIntegerClass(this.getKey().getClass())) {
            List<List<E>> allPathsWithGivenSum = new ArrayList<>();

            bfs(this, 0, sum, allPathsWithGivenSum);

            return allPathsWithGivenSum;
        }
        return null;
    }

    private void bfs(Tree<E> node, int sum, int neededSum, List<List<E>> listOfPaths) {
        sum += (int) node.getKey();

        if (sum == neededSum) {
            listOfPaths.add(this.traverseBack(node));
            return;
        }

        for (Tree<E> child : node.getChildren()) {
            bfs(child, sum, neededSum, listOfPaths);
        }
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {

        if (isIntegerClass(this.getKey().getClass())) {

            List<Tree<E>> subTreesWithGivenSum = new ArrayList<>();
            bfsForSum(this, sum, subTreesWithGivenSum);

            return subTreesWithGivenSum;
        }

        return null;
    }

    private int bfsForSum(Tree<E> node, int neededSum, List<Tree<E>> listOfSubTrees) {
        int sum = (int) node.getKey();

        for (Tree<E> child : node.getChildren()) {
            sum += bfsForSum(child, neededSum, listOfSubTrees);
        }

        if (sum == neededSum) {
            listOfSubTrees.add(node);
        }

        return sum;
    }

    private List<E> traverseBack(Tree<E> current) {
        LinkedList<E> path = new LinkedList<>();
        path.push(current.getKey());

        while ((current = current.getParent()) != null) {
            path.push(current.getKey());
        }

        return path;
    }

    private void setChildrenParentToThis() {
        for (Tree<E> child : this.children) {
            child.setParent(this);
        }
    }
    private boolean isIntegerClass(Class<?> clazz) {
        return clazz == Integer.class;
    }

}



