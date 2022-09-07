import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PrimTests {

    @Test
    public void testPrimWithSingleEdge() {
        List<Edge> graphEdges = new ArrayList<>();
        graphEdges.add(new Edge(0, 1, 3));

        List<List<Edge>> expectedForest = new ArrayList<>();
        expectedForest.add(new ArrayList<>(List.of(graphEdges.get(0))));

        List<List<Edge>> actualForest = Prim.minSpanningForest(graphEdges);

        Assert.assertEquals(expectedForest.size(), actualForest.size());

        compareTreeWeight(actualForest, 3);

        compareTreesInForest(expectedForest, actualForest);
    }


    @Test
    public void testPrimWithTwoConnectedEdges() {
        List<Edge> graphEdges = new ArrayList<>();
        graphEdges.add(new Edge(0, 1, 3));
        graphEdges.add(new Edge(2, 1, 4));

        List<List<Edge>> expectedForest = new ArrayList<>();
        expectedForest.add(new ArrayList<>(List.of(graphEdges.get(0), graphEdges.get(1))));

        List<List<Edge>> actualForest = Prim.minSpanningForest(graphEdges);

        Assert.assertEquals(expectedForest.size(), actualForest.size());

        compareTreeWeight(actualForest, 7);

        compareTreesInForest(expectedForest, actualForest);
    }

    @Test
    public void testPrimWith9VerticesAnd11Edges() {

        List<Edge> graphEdges = new ArrayList<>();
        graphEdges.add(new Edge(0, 3, 9));  // 0
        graphEdges.add(new Edge(0, 5, 4));  // 1
        graphEdges.add(new Edge(0, 8, 5));  // 2
        graphEdges.add(new Edge(1, 4, 8));  // 3
        graphEdges.add(new Edge(1, 7, 7));  // 4
        graphEdges.add(new Edge(2, 6, 12)); // 5
        graphEdges.add(new Edge(3, 5, 2));  // 6
        graphEdges.add(new Edge(3, 6, 8));  // 7
        graphEdges.add(new Edge(3, 8, 20)); // 8
        graphEdges.add(new Edge(4, 7, 10)); // 9
        graphEdges.add(new Edge(6, 8, 7));  // 10

        List<List<Edge>> expectedForest = new ArrayList<>();
        expectedForest.add(new ArrayList<>(List.of(
                graphEdges.get(6),
                graphEdges.get(1),
                graphEdges.get(2),
                graphEdges.get(10),
                graphEdges.get(5)
        )));

        expectedForest.add(new ArrayList<>(List.of(
                graphEdges.get(4),
                graphEdges.get(3)
        )));

        List<List<Edge>> actualForest = Prim.minSpanningForest(graphEdges);

        Assert.assertEquals(expectedForest.size(), actualForest.size());

        compareTreeWeight(actualForest, 45);

        compareTreesInForest(expectedForest, actualForest);
    }

    @Test
    public void testPrimWith9VerticesAnd10Edges() {

        List<Edge> graphEdges = new ArrayList<>();
        graphEdges.add(new Edge(0, 3, 9));   //0
        graphEdges.add(new Edge(0, 8, 5));   //1
        graphEdges.add(new Edge(1, 4, 8));   //2
        graphEdges.add(new Edge(1, 7, 7));   //3
        graphEdges.add(new Edge(2, 6, 12));  //4
        graphEdges.add(new Edge(3, 5, 2));   //5
        graphEdges.add(new Edge(3, 6, 8));   //6
        graphEdges.add(new Edge(3, 8, 20));  //7
        graphEdges.add(new Edge(4, 7, 10));  //8
        graphEdges.add(new Edge(6, 8, 7));   //9

        List<List<Edge>> expectedForest = new ArrayList<>();
        expectedForest.add(new ArrayList<>(List.of(
                graphEdges.get(5),
                graphEdges.get(1),
                graphEdges.get(9),
                graphEdges.get(6),
                graphEdges.get(4)
        )));

        expectedForest.add(new ArrayList<>(List.of(
                graphEdges.get(3),
                graphEdges.get(2)
        )));

        List<List<Edge>> actualForest = Prim.minSpanningForest(graphEdges);

        Assert.assertEquals(expectedForest.size(), actualForest.size());

        compareTreeWeight(actualForest, 49);

        compareTreesInForest(expectedForest, actualForest);
    }

    private void compareTreesInForest(List<List<Edge>> expectedForest, List<List<Edge>> actualForest) {
        for (int i = 0; i < expectedForest.size(); i++) {
            List<Edge> expectedTree = expectedForest.get(i);
            List<Edge> actualTree = actualForest.get(i);
            Assert.assertEquals(expectedTree.size(), actualTree.size());

            compareEdges(expectedForest, actualForest, i, expectedTree);
        }
    }

    private void compareEdges(List<List<Edge>> expectedForest, List<List<Edge>> actualForest, int i, List<Edge> expectedTree) {
        for (int j = 0; j < expectedTree.size(); j++) {
            Edge expectedEdge = expectedForest.get(i).get(j);
            Edge actualEdge = actualForest.get(i).get(j);
            Assert.assertEquals(expectedEdge, actualEdge);
        }
    }

    private void compareTreeWeight(List<List<Edge>> actualForest, int expectedWeight) {
        int actualWeight = actualForest.stream()
                .mapToInt(tree -> tree.stream().mapToInt(Edge::getWeight).sum())
                .sum();

        Assert.assertEquals(expectedWeight, actualWeight);
    }
}
