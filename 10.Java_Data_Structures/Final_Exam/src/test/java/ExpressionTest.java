import core.Expressionist;
import core.ExpressionistImpl;
import models.Expression;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExpressionTest {

    private final Expressionist expressionist = new ExpressionistImpl();

    @Test
    public void testRemove() {
        Expression e0 = new Expression("7", "", null, null, null);
        Expression e1 = new Expression("14", "", null, null, null);
        Expression e2 = new Expression("13", "", null, null, null);
        Expression e3 = new Expression("9", "", null, null, null);
        Expression e4 = new Expression("12", "", null, null, null);
        Expression e5 = new Expression("3", "", null, null, null);
        Expression e6 = new Expression("2", "", null, null, null);

        expressionist.addExpression(e0);
        expressionist.addExpression(e1, "7");
        expressionist.addExpression(e2, "7");
        expressionist.addExpression(e3, "14");
        expressionist.addExpression(e4, "14");
        expressionist.addExpression(e5, "9");
        expressionist.addExpression(e6, "9");


        expressionist.removeExpression("14");

        assertEquals(2, expressionist.size());
    }
}
