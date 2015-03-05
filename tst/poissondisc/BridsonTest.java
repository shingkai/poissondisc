package poissondisc;

import org.junit.*;

import static org.junit.Assert.*;

public class BridsonTest {
    private static final double DELTA = 1e-15;
    Grid testGrid;
    Bridson testBridson;

    @Before
    public void setUp() {
        testGrid = new Grid(1000, 1000, 24.0);
        testBridson = new Bridson(testGrid, 25);
    }

    @After
    public void tearDown() {
        testGrid = null;
    }

    @Test
    public void testRun() {
        assertEquals(0, countPoints());
        testBridson.run();
        assertTrue(1 <= countPoints());
        printPoints();
    }

    private int countPoints() {
        int count = 0;

        int rows = testGrid.getRows();
        int cols = testGrid.getCols();

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (testGrid.getSquare(i, j).hasPoint())
                    count++;
            }
        }
        return count;
    }

    private void printPoints() {
        int rows = testGrid.getRows();
        int cols = testGrid.getCols();
        Square square;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                square = testGrid.getSquare(i, j);
                if (square.hasPoint())
                    System.out.println(square.getPoint().getX() + ","
                            + square.getPoint().getY());
            }

        }
    }
}
