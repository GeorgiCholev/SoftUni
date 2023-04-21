package core;

import models.Expression;
import models.ExpressionType;

import java.util.HashMap;
import java.util.Map;

public class ExpressionistImpl implements Expressionist {

    private Map<String, Expression> expressionsById = new HashMap<>();
    private Map<String, Expression> parentByChildId = new HashMap<>();

    private Expression root;

    @Override
    public void addExpression(Expression expression) {
        if (size() != 0) {
            throw new IllegalArgumentException();
        }

        expressionsById.put(expression.getId(), expression);
        root = expression;
    }

    @Override
    public void addExpression(Expression expression, String parentId) {
        Expression parent = expressionsById.get(parentId);

        // There will be no cases in which the parent will have a Right Child, but no Left Child
        if (parent == null || parent.getRightChild() != null) {
            throw new IllegalArgumentException();
        }

        if (parent.getLeftChild() == null) {
            parent.setLeftChild(expression);
        } else {
            parent.setRightChild(expression);
        }

        String childId = expression.getId();
        expressionsById.put(childId, expression);
        parentByChildId.put(childId, parent);
    }

    @Override
    public boolean contains(Expression expression) {
        return expressionsById.containsKey(expression.getId());
    }

    @Override
    public int size() {
        return expressionsById.size();
    }

    @Override
    public Expression getExpression(String expressionId) {
        Expression expression = expressionsById.get(expressionId);
        if (expression == null) {
            throw new IllegalArgumentException();
        }
        return expression;
    }

    @Override
    public void removeExpression(String expressionId) {
        Expression expression = getExpression(expressionId);

        if (expressionId.equals(root.getId())) {
            resetContainers();
            return;
        }

        Expression parent = parentByChildId.get(expressionId);
        boolean isLeftChild = expressionId.equals(parent.getLeftChild().getId());

        remove(expression);

        if (isLeftChild) {
            parent.setLeftChild(parent.getRightChild());
            parent.setRightChild(null);
        }
    }

    private void remove(Expression expression) {
        if (expression == null) {
            return;
        }

        remove(expression.getLeftChild());
        remove(expression.getRightChild());

        String id = expression.getId();
        expressionsById.remove(id);
        parentByChildId.remove(id);
    }

    @Override
    public String evaluate() {
        return evaluate(root);
    }

    private String evaluate(Expression expression) {

        if (expression == null) {
            return "";
        }

        String value = String.valueOf(expression.getValue());

        if (expression.getType() == ExpressionType.VALUE) {
            return value;
        }

        return "(" + evaluate(expression.getLeftChild()) + " " +
                value + " " +
                evaluate(expression.getRightChild()) + ")";

    }

    private void resetContainers() {
        expressionsById = new HashMap<>();
        parentByChildId = new HashMap<>();
        root = null;
    }
}
