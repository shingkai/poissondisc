package poissondisc;

import org.junit.*;
import static org.junit.Assert.*;

public class GridTest {
    private static final int TEST_ITERS = 100000;
    private static final double DELTA = 1e-15;
    Generator testGenerator;
    Grid testGrid;

    @Before
    public void setUp() {
        testGenerator = new Generator("resources/image.jpg",
                "resources.output.jpg", 8.0, 10);
        testGrid = testGenerator.getGrid();
    }

    @After
    public void tearDown() {
        testGenerator = null;
        testGrid = null;
    }

    @Test
    public void testGrid() {
        assertEquals(11776, testGrid.getWidth());
        assertEquals(1924, testGrid.getHeight());
        assertEquals(5, testGrid.getUnit());
        assertEquals(2356, testGrid.getCols());
        assertEquals(385, testGrid.getRows());
        assertEquals(8, testGrid.getRadius(), DELTA);
    }

    @Test
    public void testGridGetSquare() {
        Square testSquare = testGrid.getSquare(0, 0);
        assertEquals(0, testSquare.getRow());
        assertEquals(0, testSquare.getCol());
        assertEquals(testGrid.getUnit(), testSquare.getUnit());

        testSquare = testGrid.getSquare(0, testGrid.getCols() - 1);
        assertEquals(0, testSquare.getRow());
        assertEquals(testGrid.getCols() - 1, testSquare.getCol());
        assertEquals(testGrid.getUnit(), testSquare.getUnit());

        testSquare = testGrid.getSquare(testGrid.getRows() - 1, 0);
        assertEquals(testGrid.getRows() - 1, testSquare.getRow());
        assertEquals(0, testSquare.getCol());
        assertEquals(testGrid.getUnit(), testSquare.getUnit());

        testSquare = testGrid.getSquare(testGrid.getRows() - 1,
                testGrid.getCols() - 1);
        assertEquals(testGrid.getRows() - 1, testSquare.getRow());
        assertEquals(testGrid.getCols() - 1, testSquare.getCol());
        assertEquals(testGrid.getUnit(), testSquare.getUnit());
    }

    @Test
    public void testGridGetSquareNulls() {
        Square testSquare = testGrid.getSquare(-1, -1);
        assertEquals(null, testSquare);

        testSquare = testGrid.getSquare(0, -1);
        assertEquals(null, testSquare);

        testSquare = testGrid.getSquare(-1, 0);
        assertEquals(null, testSquare);

        testSquare = testGrid.getSquare(testGrid.getRows(), testGrid.getCols());
        assertEquals(null, testSquare);

        testSquare = testGrid.getSquare(0, testGrid.getCols());
        assertEquals(null, testSquare);

        testSquare = testGrid.getSquare(testGrid.getRows(), 0);
        assertEquals(null, testSquare);
    }

    @Test
    public void testGridGetSquareContaining() {
        Square testSquare = testGrid.getSquareContaining(0, 0);
        assertEquals(0, testSquare.getRow());
        assertEquals(0, testSquare.getCol());
        assertEquals(testGrid.getUnit(), testSquare.getUnit());

        testSquare = testGrid.getSquareContaining(0, testGrid.getHeight() - 1);
        assertEquals(testGrid.getRows() - 1, testSquare.getRow());
        assertEquals(0, testSquare.getCol());
        assertEquals(testGrid.getUnit(), testSquare.getUnit());

        testSquare = testGrid.getSquareContaining(testGrid.getWidth() - 1, 0);
        assertEquals(0, testSquare.getRow());
        assertEquals(testGrid.getCols() - 1, testSquare.getCol());
        assertEquals(testGrid.getUnit(), testSquare.getUnit());

        testSquare = testGrid.getSquareContaining(testGrid.getWidth() - 1,
                testGrid.getHeight() - 1);
        assertEquals(testGrid.getRows() - 1, testSquare.getRow());
        assertEquals(testGrid.getCols() - 1, testSquare.getCol());
        assertEquals(testGrid.getUnit(), testSquare.getUnit());

        testSquare = testGrid.getSquareContaining(testGrid.getUnit(),
                testGrid.getUnit());
        assertEquals(1, testSquare.getRow());
        assertEquals(1, testSquare.getCol());
        assertEquals(testGrid.getUnit(), testSquare.getUnit());

        testSquare = testGrid.getSquareContaining(testGrid.getUnit() - 1,
                testGrid.getUnit() - 1);
        assertEquals(0, testSquare.getRow());
        assertEquals(0, testSquare.getCol());
        assertEquals(testGrid.getUnit(), testSquare.getUnit());
    }

    @Test
    public void testGridGetSquareContainingNulls() {
        Square testSquare = testGrid.getSquareContaining(-1, -1);
        assertEquals(null, testSquare);

        testSquare = testGrid.getSquareContaining(0, -1);
        assertEquals(null, testSquare);

        testSquare = testGrid.getSquareContaining(-1, 0);
        assertEquals(null, testSquare);

        testSquare = testGrid.getSquareContaining(testGrid.getWidth(),
                testGrid.getHeight());
        assertEquals(null, testSquare);

        testSquare = testGrid.getSquareContaining(0, testGrid.getHeight());
        assertEquals(null, testSquare);

        testSquare = testGrid.getSquareContaining(testGrid.getWidth(), 0);
        assertEquals(null, testSquare);

    }

    @Test
    public void testGridSamplePoint() {
        boolean flag = true;
        Point candidate;
        int x;
        int y;
        int width = testGrid.getWidth();
        int height = testGrid.getHeight();
        int row;
        int col;
        int rows = testGrid.getRows();
        int cols = testGrid.getCols();
        for (int i = 0; i < TEST_ITERS; i++) {
            candidate = testGrid.samplePoint();
            x = candidate.getX();
            y = candidate.getY();
            if (x < 0 || x >= width || y < 0 || y >= height)
                flag = false;
            col = testGrid.getColFromX(x);
            row = testGrid.getRowFromY(y);
            if (row < 0 || row >= rows || col < 0 || col >= cols) {
                System.err.println(row + "," + col + " " + rows + "," + cols);
                flag = false;
            }
        }
        assertTrue(flag);
    }
}
